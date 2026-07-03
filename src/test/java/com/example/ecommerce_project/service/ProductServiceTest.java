package com.example.ecommerce_project.service;

import com.example.ecommerce_project.model.Product;
import com.example.ecommerce_project.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // 1. ააქტიურებს Mockito-ს სატესტო გარემოს
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository; // 2. ვქმნით ყალბ (Mock) რეპოზიტორს

    @InjectMocks
    private ProductService productService; // 3. ამ ყალბ რეპოზიტორს ავტომატურად ვსვამთ სერვისში

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        // ეს მეთოდი ეშვება ყოველი ტესტის წინ და ამზადებს სატესტო პროდუქტს
        sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setProductName("iPhone 15");
        sampleProduct.setPrice(2500.0);
        sampleProduct.setDescription("Apple phone");
    } // test

    @Test
    void should_Save_Product_Successfully() {
        // Arrange (მომზადება): ვეუბნებით Mock-ს, რომ როცა save მეთოდს გამოიძახებენ, დააბრუნოს ჩვენი sampleProduct
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        // Act (მოქმედება): რეალურად ვიძახებთ სერვისის მეთოდს
        Product savedProduct = productService.add(new Product());

        // Assert (შემოწმება): ვამოწმებთ, რომ შედეგი ისაა, რასაც ველოდით
        assertNotNull(savedProduct);
        assertEquals("iPhone 15", savedProduct.getProductName());
        assertEquals(2500, savedProduct.getPrice());

        // ვრწმუნდებით, რომ რეპოზიტორის save მეთოდი ზუსტად ერთხელ გამოიძახა სერვისმა
        verify(productRepository, times(1)).save(any(Product.class));
    }
}