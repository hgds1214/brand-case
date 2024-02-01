package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.service.BrandServiceImpl;
import sun.net.www.content.image.gif;
import sun.net.www.content.image.jpeg;
import sun.net.www.content.image.png;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. ����service��ѯ
        List<Brand> brands = brandService.selectAll();
        //2. תΪJSON
        String jsonString = JSON.toJSONString(brands);
        //3. д����
        response.setContentType("text/json;charset=utf-8"); //��֪�������Ӧ��������ʲô�� ��֪�����ʹ��ʲô�ַ������н���
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }



}
