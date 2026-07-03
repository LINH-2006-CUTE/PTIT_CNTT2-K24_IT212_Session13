package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lớp thực hiện chi tiết các logic nghiệp vụ đặt hàng và thanh toán.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public OrderResponseDTO checkout(OrderRequestDTO requestDTO) {
        // 1. Lấy thông tin tài khoản đăng nhập hiện tại từ Security Context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Lỗi xác thực: Không tìm thấy tài khoản người dùng: " + username));

        // 2. Khởi tạo đối tượng Đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(requestDTO.getShippingAddress());
        order.setPhoneNumber(requestDTO.getPhoneNumber());

        // Mặc định trạng thái đơn hàng dựa trên phương thức thanh toán
        if ("COD".equalsIgnoreCase(requestDTO.getPaymentMethod())) {
            order.setStatus("PENDING");
        } else {
            order.setStatus("PAID"); // Giả định thanh toán trực tuyến thành công
        }
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        // 3. Duyệt danh sách sản phẩm mua để trừ tồn kho và tính tiền
        for (OrderItemRequestDTO itemDTO : requestDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + itemDTO.getProductId()));

            // Kiểm tra số lượng tồn kho khả dụng
            if (product.getStock() < itemDTO.getQuantity()) {
                throw new RuntimeException("Sản phẩm '" + product.getName() + "' không đủ số lượng tồn kho khả dụng.");
            }

            // Trừ tồn kho (Cơ chế khóa lạc quan @Version sẽ chặn đứng race condition)
            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            // Khởi tạo chi tiết đơn hàng
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(product.getPrice()); // Lưu lại giá bán tại thời điểm mua

            orderItems.add(orderItem);

            // Tính tổng tiền đơn hàng
            BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);
        }

        order.setTotalPrice(totalAmount);
        order.setOrderItems(orderItems);

        // 4. Lưu đơn hàng hoàn chỉnh vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        // 5. Trả về DTO phản hồi hoàn chỉnh
        return mapToResponse(savedOrder);
    }

    /**
     * Helper mapping từ Entity sang Response DTO.
     */
    private OrderResponseDTO mapToResponse(Order order) {
        List<OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
                .map(item -> OrderItemResponseDTO.builder()
                        .productId(item.getProduct().getId())
                        .productName(item.getProduct().getName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .build())
                .collect(Collectors.toList());

        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .username(order.getUser().getUsername())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .shippingAddress(order.getShippingAddress())
                .phoneNumber(order.getPhoneNumber())
                .createdAt(order.getCreatedAt())
                .items(itemDTOs)
                .build();
    }
}
