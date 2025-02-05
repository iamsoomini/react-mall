package org.jrock.mallapi.repository.search;

import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.ProductDTO;

public interface ProductSearch {

    PageResponseDto<ProductDTO> searchList (PageRequestDto pageRequestDto);
}
