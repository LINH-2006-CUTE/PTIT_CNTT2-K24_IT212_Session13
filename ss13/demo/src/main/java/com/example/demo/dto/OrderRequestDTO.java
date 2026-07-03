package com.example.demo.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import java.util.List;

/**
 * DTO chứa thông tin đơn hàng và danh sách sản phẩm yêu cầu thanh toán.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    @NotBlank(message = "Địa chỉ nhận hàng không được để trống")
    private String shippingAddress;

    @NotBlank(message = "Số điện thoại nhận hàng không được để trống")
    private String phoneNumber;

    @NotBlank(message = "Phương thức thanh toán không được để trống")
    private String paymentMethod; // VD: COD, MOMO, VNPAY

    @NotEmpty(message = "Danh sách sản phẩm thanh toán không được để trống")
    @Valid
    private List<OrderItemRequestDTO> items;
}
