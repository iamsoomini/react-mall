package org.jrock.mallapi.repository;


import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.domain.Product;
import org.jrock.mallapi.dto.PageRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert(){

        for(int i = 0 ; i < 10; i++) {

            Product product = Product.builder()
                    .pname("TEST ")
                    .pdesc("test desc... ")
                    .price(1000)
                    .build();

            product.addImageString(UUID.randomUUID() + "-" + "Image1.jpg");
            product.addImageString(UUID.randomUUID() + "-" + "Image2.jpg");

            productRepository.save(product);
        }

    }


    @Test
    public void testRead(){

        Long pno = 1L;

        Optional<Product> result = productRepository.selectOne(pno);

        Product product = result.orElseThrow();

        log.info(product);

        log.info(product.getImageList());

    }


    @Test
    @Transactional
    @Commit
    public void testDelete(){

        Long pno = 1L;

        productRepository.updateToDelte(1L, true);
    }

    @Test
    public void testUpdate(){

        Product product = productRepository.selectOne(1L).get();

        product.changePrice(3000);

        product.clearList();

        product.addImageString(UUID.randomUUID()+"-"+"PImage1.jpg");
        product.addImageString(UUID.randomUUID()+"-"+"PImage2.jpg");
        product.addImageString(UUID.randomUUID()+"-"+"PImage3.jpg");

        productRepository.save(product);


    }

    @Test
    public void testList(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        result.getContent().forEach(arr -> log.info(Arrays.toString(arr)));
    }


    @Test
    public void testSearch(){

        PageRequestDto pageRequestDto = PageRequestDto.builder().build();

        productRepository.searchList(pageRequestDto);

    }




}
