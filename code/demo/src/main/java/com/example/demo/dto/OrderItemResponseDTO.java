package com.example.demo.dto;

import lombok.*;
import java.math.BigDecimal;

/**
 * DTO chứa thông tin chi tiết từng sản phẩm phản hồi trong đơn hàng.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}
