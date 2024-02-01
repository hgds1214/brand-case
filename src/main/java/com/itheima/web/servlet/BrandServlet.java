package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    BrandService brandService = new BrandServiceImpl();
    //�û�ʵ�ַ�ҳ��ѯ
    //�û�ʵ�ַ�ҳ��ѯ
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<Brand> brands = brandService.selectAll();
       String jsonString = JSON.toJSONString(brands);
       resp.setContentType("text/json;charset=utf-8");
       resp.getWriter().write(jsonString);
    }

    //�����ҵ��Ϣ
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
       String params = br.readLine();
       Brand brand = JSON.parseObject(params,Brand.class);
       brandService.add(brand);
       resp.getWriter().write("success");

    }

    //�޸���ҵ��Ϣ
    public void update(HttpServletRequest req, HttpServletResponse resp) {}

    //ɾ����ҵ��Ϣ
    public void delete(HttpServletRequest req, HttpServletResponse resp) {}

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. �������� json  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json�ַ���
        //תΪ int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2. ����service���
        brandService.deleteByIds(ids);
        //3. ��Ӧ�ɹ��ı�ʶ
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. ���� ��ǰҳ�� �� ÿҳչʾ����    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. ����service��ѯ
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2. תΪJSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. д����
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * ��ҳ������ѯ
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. ���� ��ǰҳ�� �� ÿҳչʾ����    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // ��ȡ��ѯ��������
        BufferedReader br = request.getReader();
        String params = br.readLine();//json�ַ���

        //תΪ Brand
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. ����service��ѯ
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //2. תΪJSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. д����
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
