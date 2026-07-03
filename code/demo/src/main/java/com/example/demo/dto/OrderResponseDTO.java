package com.example.demo.dto;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO chứa thông tin tổng hợp của đơn hàng phản hồi về phía Client sau khi thanh toán thành công.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Long userId;
    private String username;
    private BigDecimal totalPrice;
    private String status;
    private String shippingAddress;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDTO> items;
}
