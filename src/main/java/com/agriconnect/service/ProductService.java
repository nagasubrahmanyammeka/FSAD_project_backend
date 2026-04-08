package com.agriconnect.service;

import com.agriconnect.dto.ProductDTO;
import com.agriconnect.entity.Product;
import com.agriconnect.exception.ResourceNotFoundException;
import com.agriconnect.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return modelMapper.map(p, ProductDTO.class);
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product entity = modelMapper.map(dto, Product.class);
        entity.setId(null);
        return modelMapper.map(productRepository.save(entity), ProductDTO.class);
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        if (dto.getName()        != null) product.setName(dto.getName());
        if (dto.getDescription() != null) product.setDescription(dto.getDescription());
        if (dto.getPrice()       != null) product.setPrice(dto.getPrice());
        if (dto.getCategory()    != null) product.setCategory(dto.getCategory());
        if (dto.getImageUrl()    != null) product.setImageUrl(dto.getImageUrl());
        if (dto.getStock()       != null) product.setStock(dto.getStock());
        return modelMapper.map(productRepository.save(product), ProductDTO.class);
    }

    public void deleteProduct(Long id) {
        Product p = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(p);
    }
}
