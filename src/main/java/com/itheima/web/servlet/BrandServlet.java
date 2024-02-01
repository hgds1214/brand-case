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
    //用户实现分页查询
    //用户实现分页查询
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       List<Brand> brands = brandService.selectAll();
       String jsonString = JSON.toJSONString(brands);
       resp.setContentType("text/json;charset=utf-8");
       resp.getWriter().write(jsonString);
    }

    //添加企业信息
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
       String params = br.readLine();
       Brand brand = JSON.parseObject(params,Brand.class);
       brandService.add(brand);
       resp.getWriter().write("success");

    }

    //修改企业信息
    public void update(HttpServletRequest req, HttpServletResponse resp) {}

    //删除企业信息
    public void delete(HttpServletRequest req, HttpServletResponse resp) {}

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据 json  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2. 调用service添加
        brandService.deleteByIds(ids);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);


        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
