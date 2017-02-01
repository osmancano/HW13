package com.ironyard.filter;

import com.ironyard.data.IronYardUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jasonskipper on 1/26/17.
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        IronYardUser user = (IronYardUser)request.getSession().getAttribute("ironyard_user");

        // means user is already logged in
        boolean tryingToLogin = request.getRequestURI().equals("/login");
        if(user!=null || tryingToLogin) {
            // send them on their way
            chain.doFilter(req, resp);
        }else{
            // forward to login page
            RequestDispatcher dispatcher =  request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);

        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
