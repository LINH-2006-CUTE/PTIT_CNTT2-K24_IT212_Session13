package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * DTO chứa thông tin sản phẩm và số lượng tương ứng trong yêu cầu đặt hàng.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDTO {

    @NotNull(message = "Mã sản phẩm không được để trống")
    private Long productId;

    @NotNull(message = "Số lượng sản phẩm không được để trống")
    @Positive(message = "Số lượng sản phẩm mua phải lớn hơn 0")
    private Integer quantity;
}
