package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BaseServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //��������ķַ�
        //1. ��ȡ����·��
        String uri = req.getRequestURI(); // ����·��Ϊ��/brand-case/brand/selectAll
        //2. ��ȡ���һ��·����������
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1); //  ��ȡ����Դ�Ķ���·��  selectAll

        //2. ִ�з���
        //2.1 ��ȡBrandServlet /UserServlet �ֽ������ Class
        //System.out.println(this);

        Class<? extends BaseServlet> cls = this.getClass();
        //2.2 ��ȡ���� Method����
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3 ִ�з���
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
