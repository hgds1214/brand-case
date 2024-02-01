package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {

    List<Brand> selectAll();

    void add(Brand brand);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     *
     * @param currentPage 当前页码
     * @param pageSize    每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     *
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
