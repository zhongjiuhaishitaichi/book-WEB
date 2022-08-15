package com.axios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/axios01.do")
public class axios01Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println("uname: "+uname);
        System.out.println("pwd: "+pwd);

        resp.setCharacterEncoding("UTF-8");
        //相应普通文本值
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        //服务器里的writer 传给axios里的then 里的value
        writer.println(uname+"_"+pwd);

        throw new NullPointerException("我是故意报错的...");
    }
}
