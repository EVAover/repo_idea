package com.qiang;

import lombok.Data;

@Data
public class PublicPageInfoVo {
    //当前页
    private Integer currentPage;

    //每页显示的条数
    private Integer pageSize;
}
