package com.example.demo.service;

import com.example.demo.dto.ProductRequestDTO;
import com.example.demo.dto.ProductResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lớp thực hiện chi tiết các logic nghiệp vụ quản lý sản phẩm.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setCategory(requestDTO.getCategory());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        product.setImageUrl(requestDTO.getImageUrl());
        product.setDescription(requestDTO.getDescription());
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return mapToResponse(savedProduct);
    }

    @Override
    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));

        product.setName(requestDTO.getName());
        product.setCategory(requestDTO.getCategory());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());
        product.setImageUrl(requestDTO.getImageUrl());
        product.setDescription(requestDTO.getDescription());

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
        
        // Thực hiện xóa cứng (hoặc có thể soft-delete bằng cách chuyển trạng thái)
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với ID: " + id));
        return mapToResponse(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Helper mapping từ Entity sang Response DTO.
     */
    private ProductResponseDTO mapToResponse(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .description(product.getDescription())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
