package com.hnkuan.auth.filter;

import com.hnkuan.auth.filter.authenticator.UserAuthenticator;
import com.hnkuan.auth.filter.request.AppHttpRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {@link Filter} implementation to authenticate user.
 *
 * @author honnamkuan
 */
public class AuthFilter implements Filter {

  private UserAuthenticator authenticator;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    authenticator = new UserAuthenticator();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;

    Principal principal = authenticator.authenticate(httpRequest);

    if (Objects.isNull(principal)) {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User is not authorized");
    }

    HttpServletRequest servletRequest = AppHttpRequest.withPrincipal(httpRequest, principal);

    chain.doFilter(servletRequest, response);
  }

  @Override
  public void destroy() {

  }
}
