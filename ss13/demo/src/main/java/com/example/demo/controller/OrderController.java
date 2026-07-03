package com.example.demo.controller;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller tiếp nhận yêu cầu đặt hàng và thanh toán từ Khách hàng (Customer).
 * Đường dẫn /api/v1/orders/** bắt buộc phải đăng nhập thành công.
 */
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * API thực hiện đặt hàng và thanh toán giỏ hàng. Validate dữ liệu đầu vào.
     */
    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDTO> checkout(@Valid @RequestBody OrderRequestDTO requestDTO) {
        OrderResponseDTO response = orderService.checkout(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
