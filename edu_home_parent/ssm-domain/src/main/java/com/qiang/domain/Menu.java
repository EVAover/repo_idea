package com.qiang.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class Menu {
    //主键id
    private Integer id;

    //父菜单id
    private int parentId;

    //菜单路径
    private String href;

    //菜单图标
    private String icon;

    //菜单名称
    private String name;

    //描述
    private String description;

    //排序号
    private int orderNum;

    //是否展示
    private int shown;

    //菜单层级，从0开始
    private int level;

    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    //更新时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    //创建人
    private String createdBy;

    //更新人
    private String updatedBy;

    // 声明集合：当前父级菜单所关联的子级菜单
    private List<Menu> subMenuList;

}
