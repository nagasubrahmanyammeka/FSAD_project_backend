package com.agriconnect.controller;

import com.agriconnect.dto.ProductDTO;
import com.agriconnect.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Agricultural shop products")
public class ProductController {

    private final ProductService productService;

    // ─── GET /api/products ───────────────────────────────────────────
    @GetMapping
    @Operation(summary = "Get all products (public)")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // ─── GET /api/products/{id} ──────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // ─── POST /api/products ──────────────────────────────────────────
    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "Add a new product (admin only)")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    // ─── PUT /api/products/{id} ──────────────────────────────────────
    @PutMapping("/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "Update product details (admin only)")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    // ─── DELETE /api/products/{id} ───────────────────────────────────
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "Delete product (admin only)")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Product deleted successfully"));
    }
}
