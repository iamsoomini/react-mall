package org.jrock.mallapi.service;


import jakarta.transaction.Transactional;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.ProductDTO;

@Transactional
public interface ProductService {


    PageResponseDto<ProductDTO> getList(PageRequestDto pageRequestDto);



}
