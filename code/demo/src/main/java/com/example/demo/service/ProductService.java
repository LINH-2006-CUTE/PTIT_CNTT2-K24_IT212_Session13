package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import java.util.List;

/**
 * Interface định nghĩa các nghiệp vụ liên quan đến quản lý sản phẩm.
 */
public interface ProductService {
    
    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);
    
    ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO);
    
    void deleteProduct(Long id);
    
    ProductResponseDTO getProductById(Long id);
    
    List<ProductResponseDTO> getAllProducts();
}
