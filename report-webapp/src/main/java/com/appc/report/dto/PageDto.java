package com.appc.report.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    private Long count;
    private List<T> data;

    public static <T> PageDto create(Long count, List<T> data) {
        PageDto pageDto = new PageDto();
        pageDto.count = count;
        pageDto.data = data;
        return pageDto;
    }
}
