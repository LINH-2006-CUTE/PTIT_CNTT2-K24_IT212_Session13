package com.example.demo.controller;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller xử lý các yêu cầu quản lý sản phẩm dành riêng cho Staff và Manager.
 * Được bảo mật tại đường dẫn /api/v1/admin/products/**.
 */
@RestController
@RequestMapping("/api/v1/admin/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * API thêm sản phẩm mới. Yêu cầu validate dữ liệu đầu vào.
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO response = productService.createProduct(requestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * API cập nhật thông tin sản phẩm dựa trên ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
                                                            @Valid @RequestBody ProductRequestDTO requestDTO) {
        ProductResponseDTO response = productService.updateProduct(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    /**
     * API xóa sản phẩm dựa trên ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * API lấy chi tiết sản phẩm dựa trên ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * API lấy danh sách toàn bộ sản phẩm.
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> response = productService.getAllProducts();
        return ResponseEntity.ok(response);
    }
}
