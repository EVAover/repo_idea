package com.qiang.domain;

import lombok.Data;

@Data
public class ResourceVo {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private Integer categoryId;
    private String url;
}
