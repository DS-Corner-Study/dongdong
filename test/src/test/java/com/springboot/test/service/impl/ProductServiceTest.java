package com.springboot.test.service.impl;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class); // Mock 객체
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository); // 의존성 주입
    }

    // 예제 7.11
    @Test
    void getProductTest() {
        // given: Product 엔티티 객체 생성, 결괏값 리턴
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        // when: getProduct() 호출하여 테스트
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        // then: 리턴받은 객체에 대하여 값 검출
        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L); // 부가 검증
    }

    // 예제 7.12
    @Test
    void saveProductTest() {
        // given
        Mockito.when(productRepository.save(any(Product.class)))
                // any(): Mock 객체 동작 정의 or 검증 단계의 조건으로 (특정 매개변수 전달 설정 않고 메서드 실행만 확인하거나 / 클래스 객체를 매개변수로 전달받는 상황)에서 사용
                .then(returnsFirstArg());

        // when
        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        // then
        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }
}