package org.jrock.mallapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDto<E> {

    private List<E> dtoList;

    private List<Integer> pageNumList;

    private PageRequestDto pageRequestDto;

    private boolean prev, next;

    private int totalCount, prevPage, nextPage, totalPage, current;

    @Builder(builderMethodName = "withAll")
    public  PageResponseDto(List<E> dtoList, PageRequestDto pageRequestDto, long total) {

        this.dtoList = dtoList;
        this.pageRequestDto = pageRequestDto;
        this.totalCount = (int) total;

        // 끝페이지(10단위의)
        // 현재 페이지 / 10 을 하고 올림을 하여 10을 곱함. 13이라 했을때 1.3 을 올려 2로 만들고 10을 곱해 20,
        int end = (int) (Math.ceil(pageRequestDto.getPage() / 10.0)) * 10;

        int start = end - 9;

        // 전체 페이지 끝
        int last = (int) (Math.ceil(totalCount / (double) pageRequestDto.getSize()));

        end = end > last ? last : end;

        this.prev = start > 1;

        this.next = totalCount > end * pageRequestDto.getSize();

        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;

        this.nextPage = next ? end + 1 : 0;


    }

}
