package com.supinfo.supcommercews.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateFilter implements Filter {

    public AuthenticateFilter() {}

	/**
	 * Vérifie qu'un administrateur est authentifié avant d'atteindre la page demandée, sinon renvoie sur la page de login
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String username = (String) session.getAttribute("username");
		
		if(username==null)
			((HttpServletResponse)response).sendRedirect("../login");
		else chain.doFilter(request, response);
		
	}


	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
