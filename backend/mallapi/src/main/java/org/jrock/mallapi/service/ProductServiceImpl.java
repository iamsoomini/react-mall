package org.jrock.mallapi.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.domain.Product;
import org.jrock.mallapi.domain.ProductImage;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.ProductDTO;
import org.jrock.mallapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public PageResponseDto<ProductDTO> getList(PageRequestDto pageRequestDto) {

        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        List<ProductDTO> dtoList = result.get().map(arr -> {

            ProductDTO productDTO = null;

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            productDTO = ProductDTO.builder()
                    .pno(product.getPno())
                    .price(product.getPrice())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .build();

            String imgStr = productImage.getFileName();
            productDTO.setUploadedFileNames(List.of(imgStr));


            return productDTO;
        }).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        return PageResponseDto.<ProductDTO>withAll()
                .dtoList(dtoList)
                .total(totalCount)
                .pageRequestDto(pageRequestDto)
                .build();
    }

    @Override
    public Long register(ProductDTO productDTO) {

        Product product = dtoToEntity(productDTO);

        log.info("------------------");

        Long pno = productRepository.save(product).getPno();

        return pno;
    }

    //외부에서 사용 안할거니까 private
    //포인트는 '컬렉션 처리' 엔티티안의 컬렉션은 새로 만드는게 아님.
    private Product dtoToEntity(ProductDTO productDTO){

        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .build();

        List<String> uploadedFileNames = productDTO.getUploadedFileNames();

        if(uploadedFileNames == null || uploadedFileNames.isEmpty()){
            return product;
        }

        uploadedFileNames.forEach(fileName -> {

            product.addImageString(fileName);
            //새로운 객체 만들지 않고 기존에 만들어놓은 함수를 사용
        });


        return product;
    }

}
