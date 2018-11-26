package com.idealo.springmvc.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CORSFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
                                  final FilterChain filterChain) throws ServletException, IOException {
    httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
    httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
    httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

}

