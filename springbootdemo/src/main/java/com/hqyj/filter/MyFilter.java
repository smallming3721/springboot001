package com.hqyj.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(filterName="myFilter",value="/**")
public class MyFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletRequestWrapper wapper = new HttpServletRequestWrapper(req) {
			@Override
			public String getParameter(String name) {
				String value = req.getParameter(name);
				if (value != null && value != "") {
					value=value.replaceAll("fuck", "****");
					return value;
				}
				return super.getParameter(name);
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values = req.getParameterValues(name);
				if (values != null && values.length > 0) {
					for (int i = 0; i < values.length; i++) {
						values[i] = values[i].replaceAll("fuck", "****");
					}
					return values;
				}
				return super.getParameterValues(name);
			}

		};
		
		chain.doFilter(wapper, response);
	}

}
