package com.hnkuan.auth.filter.request;

import java.security.Principal;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * Http Request implementation to allow custom {@link Principal}.
 *
 * @author honnamkuan
 */
public class AppHttpRequest extends HttpServletRequestWrapper {

  private AppHttpRequest(HttpServletRequest pRequest) {
    super(pRequest);
  }

  /**
   * Construct a {@link HttpServletRequest} with assigned {@link Principal}
   *
   * @param pHttpServletRequest The request.
   * @param pPrincipal The principal.
   * @return The request with principal.
   */
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
