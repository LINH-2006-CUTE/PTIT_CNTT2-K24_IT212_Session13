package com.example.demo.service;

import com.example.demo.dto.OrderRequestDTO;
import com.example.demo.dto.OrderResponseDTO;

/**
 * Interface định nghĩa các nghiệp vụ liên quan đến Đơn hàng và thanh toán.
 */
public interface OrderService {
    
    /**
     * Tiến hành thanh toán giỏ hàng, trừ số lượng tồn kho và lưu thông tin đơn hàng.
     *
     * @param requestDTO Thông tin đơn hàng và danh sách sản phẩm đặt mua.
     * @return DTO kết quả đơn hàng đã tạo thành công.
     */
    OrderResponseDTO checkout(OrderRequestDTO requestDTO);
}
