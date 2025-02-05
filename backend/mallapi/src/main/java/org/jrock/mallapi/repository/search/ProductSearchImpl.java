package org.jrock.mallapi.repository.search;

import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.jrock.mallapi.domain.Product;
import org.jrock.mallapi.domain.QProduct;
import org.jrock.mallapi.domain.QProductImage;
import org.jrock.mallapi.dto.PageRequestDto;
import org.jrock.mallapi.dto.PageResponseDto;
import org.jrock.mallapi.dto.ProductDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;


@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch{

    public  ProductSearchImpl(){
        super(Product.class);
    }

    @Override
    public PageResponseDto<ProductDTO> searchList(PageRequestDto pageRequestDto) {

        log.info("--------------searchList-----");

        Pageable pageable = PageRequest.of(
                pageRequestDto.getPage() -1,
                pageRequestDto.getSize(),
                Sort.by("pno").descending());

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<Product> query = from(product);     // from에 해당

        query.leftJoin(product.imageList, productImage);  // product의 imageList를 productImage로 간주

        query.where(productImage.ord.eq(0));

        Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, query);

        List<Product> productList = query.fetch();

        long count = query.fetchCount();

        log.info("=====================");
        log.info(productList);

        return null;
    }
}
