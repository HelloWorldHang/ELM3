package com.elm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns= {"/servlet/cart","/servlet/buy"})
public class UserFilter implements Filter {

	public void destroy() {
	}


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse respone = (HttpServletResponse) resp;
		if (request.getSession().getAttribute("user") == null) {
			String contextPath = request.getContextPath();
			respone.sendRedirect(contextPath + "/login.jsp");
			return;
		}
		chain.doFilter(req, resp);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("userFilter init");
	}

}
