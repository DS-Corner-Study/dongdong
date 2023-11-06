package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 예제 6.9
@Component // 스프링 빈 등록
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository; // 리포지토리 정의

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) { // 생성자 의존성 주입
        this.productRepository = productRepository;
    }

    // 예제 6.10: Product 엔티티를 데이터베이스에 저장하는 기능
    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product); // JPA 기본 메서드 제공

        return savedProduct;
    }

    // 예제 6.11: 조회 메서드
    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository.getById(number); // findById()

        return selectedProduct;
    }


    // 예제 6.15: 업데이트 메서드
    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    // 예제 6.17: 삭제 메서드
    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }
}