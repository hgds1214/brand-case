package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {

    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {

        //2. ��ȡSqlSession����
        SqlSession sqlSession = factory.openSession();
//3. ��ȡBrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
//4. ���÷���
        List<Brand> brands = mapper.selectAll();
//5. �ͷ���Դ
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //2. ��ȡSqlSession����
        SqlSession sqlSession = factory.openSession();
//3. ��ȡBrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
//4. ���÷���
        mapper.add(brand);
//5. �ͷ���Դ
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        //2. ��ȡSqlSession����
        SqlSession sqlSession = factory.openSession();
        //3. ��ȡBrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. ���÷���
        mapper.deleteByIds(ids);

        sqlSession.commit();//�ύ����

        //5. �ͷ���Դ
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2. ��ȡSqlSession����
        SqlSession sqlSession = factory.openSession();
        //3. ��ȡBrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4. ���㿪ʼ����
        int begin = (currentPage - 1) * pageSize;
        // �����ѯ��Ŀ��
        int size = pageSize;
        //5. ��ѯ��ǰҳ����
        List<Brand> rows = mapper.selectByPage(begin, size);
        //6. ��ѯ�ܼ�¼��
        int totalCount = mapper.selectTotalCount();
        //7. ��װPageBean����
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. �ͷ���Դ
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2. ��ȡSqlSession����
        SqlSession sqlSession = factory.openSession();
        //3. ��ȡBrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        //4. ���㿪ʼ����
        int begin = (currentPage - 1) * pageSize;
        // �����ѯ��Ŀ��
        int size = pageSize;

        // ����brand������ģ�����ʽ
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }

        //5. ��ѯ��ǰҳ����
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        //6. ��ѯ�ܼ�¼��
        int totalCount = mapper.selectTotalCountByCondition(brand);

        //7. ��װPageBean����
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8. �ͷ���Դ
        sqlSession.close();

        return pageBean;
    }
}
