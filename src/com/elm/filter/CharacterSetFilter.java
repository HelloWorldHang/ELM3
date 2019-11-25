package com.elm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(urlPatterns= {"/servlet/*","/servlet/auth/*"})
public class CharacterSetFilter implements Filter {


	public void destroy() {
		System.out.println("charaterSetFiler destroy");
	}


	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		req. setCharacterEncoding("UTF-8");
		System.out.println("characterSetFilter1");

		// pass the request along the filter chain
		chain.doFilter(req, resp);
		System.out.println("characterSetFilter2");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("characterSetFilter init");
	}

}
