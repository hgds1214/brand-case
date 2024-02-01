package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {

    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    @Insert("insert  into tb_brand value (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * ����ɾ��
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * ��ҳ��ѯ
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * ��ѯ�ܼ�¼��
     * @return
     */
    @Select("select count(*) from tb_brand ")
    int selectTotalCount();

    /**
     * ��ҳ������ѯ
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);

    /**
     * ����������ѯ�ܼ�¼��
     * @return
     */
    int selectTotalCountByCondition(Brand brand);

}
