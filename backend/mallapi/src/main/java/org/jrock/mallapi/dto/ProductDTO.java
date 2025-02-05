package org.jrock.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    @Builder.Default
    private  List<MultipartFile> files = new ArrayList<>();

    // 조회용 - 기존에 이미 등록된 파일들의 이름
    @Builder.Default
    private List<String> uploadedFileNames = new ArrayList<>();





}
