package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {

    List<Brand> selectAll();

    void add(Brand brand);

    /**
     * ����ɾ��
     *
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * ��ҳ��ѯ
     *
     * @param currentPage ��ǰҳ��
     * @param pageSize    ÿҳչʾ����
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    /**
     * ��ҳ������ѯ
     *
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);
}
