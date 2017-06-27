package com.hnkuan.auth.filter.request;

import java.security.Principal;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * Created on 27/6/2017
 *
 * @author honnamkuan
 */
public class AppHttpRequest extends HttpServletRequestWrapper {

  /**
   * Constructs a request object wrapping the given request.
   *
   * @throws IllegalArgumentException if the request is null
   */
  public AppHttpRequest(HttpServletRequest pRequest) {
    super(pRequest);
  }

  public static HttpServletRequest withPrincipal(HttpServletRequest pHttpServletRequest,
      Principal pPrincipal) {
    AppHttpRequest request = new AppHttpRequest(pHttpServletRequest);
    request.setUserPrincipal(pPrincipal);
    return request;
  }

  @Override
  public Principal getUserPrincipal() {

    HttpSession session = getSession(true);
    Object principal = session.getAttribute("UserPrincipal");
    if (principal instanceof Principal) {
      return (Principal) principal;
    }
    return null;
  }

  private void setUserPrincipal(Principal pPrincipal) {
    assert Objects.nonNull(pPrincipal);
    HttpSession session = getSession(true);
    session.setAttribute("UserPrincipal", pPrincipal);
  }
}
