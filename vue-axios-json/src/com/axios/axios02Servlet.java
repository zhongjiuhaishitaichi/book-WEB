package com.axios;

import com.google.gson.Gson;
import com.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/axios02.do")
public class axios02Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建一个字符串缓冲区，并将其内容初始化为指定的字符串内容 str，字符串缓冲区的初始容量为 16 加上字符串 str 的长度
        StringBuffer stringBuffer = new StringBuffer("");

        //json格式的接受
        BufferedReader bufferedReader = req.getReader();
        String str=null;
        while((str=bufferedReader.readLine())!=null){
            stringBuffer.append(str);
        }
        str = stringBuffer.toString();

        //已知String  转化为java object
        Gson gson = new Gson();
        //将String 转坏为JavaBean 这样就可以实现从前端传输到后端了  方便后端操作
        //2.toJson(java Object) 将java object转化成json字符串，这样才能响应给客户端
        User user = gson.fromJson(str, User.class);

        System.out.println(user);
    }
}
