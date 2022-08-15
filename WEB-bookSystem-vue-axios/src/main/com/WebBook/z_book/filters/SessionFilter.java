package main.com.WebBook.z_book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*//根据包的位置关系  要先执行编码问题
//拦截器 当用户身份合法的时候才会进入index页面 不然就回到登陆页面
@WebFilter(urlPatterns = {"*.do","*.html"},
        initParams = {
                @WebInitParam(name = "bai",
                        value = "/page.do?operate=page&page=user/login,/user.do?null")
        })//只有这些是合法的 不用再次进行登陆页面*/

public class SessionFilter implements Filter {

    List<String> baiList = null ;

    @Override
    public void init(FilterConfig config) throws ServletException {
        String bai = config.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        baiList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest ;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        //http://localhost:8088/page.do?operate=page&page=user/login
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " + request.getQueryString());

        String uri = request.getRequestURI() ;
        String queryString = request.getQueryString() ;
        String str = uri + "?" + queryString ;
        if(baiList.contains(str)){
            filterChain.doFilter(request,response);
        }else{
            HttpSession session = request.getSession() ;
            Object currUserObj = session.getAttribute("currentUser");

            if(currUserObj==null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
