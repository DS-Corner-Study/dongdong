package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 예제 6.21
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired // 의존성 주입
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    // 예제 6.22: 조회 메서드
    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        return productResponseDto;
    }

    // 예제 6.23: 저장 메서드
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto(); // 클라이언트 결과 조회를 위해 DTO 객체에 저장 후 리턴
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto; // void 반환 시 조회 메서드 추가 구현
    }

    // 예제 6.24: 업데이트 메서드
    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception { // 식별 가능 인덱스 값과 변경 이름 받기
        Product changedProduct = productDAO.updateProductName(number, name); // 이름 변경

        ProductResponseDto productResponseDto = new ProductResponseDto(); // 클라이언트 결과 조회를 위해 DTO 객체에 저장 후 리턴
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    // 예제 6.25: 삭제 메서드
    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}