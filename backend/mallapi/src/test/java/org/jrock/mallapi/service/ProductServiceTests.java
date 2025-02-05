package org.jrock.mallapi.service;


import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
@Log4j2
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testList(){

        PageRequestDto pageRequestDto = PageRequestDto.builder().build();

        PageResponseDto<ProductDTO> responseDto = productService.getList(pageRequestDto);

        log.info(responseDto.getDtoList());

    }
}
