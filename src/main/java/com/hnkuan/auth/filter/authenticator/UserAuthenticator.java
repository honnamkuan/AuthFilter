package com.hnkuan.auth.filter.authenticator;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

/**
 * Authenticator to validate user.
 *
 * @author honnamkuan
 */
public class UserAuthenticator {

  /**
   * Authenticate the request against cache or identity federation service.
   *
   * @param pHttpRequest The request.
   * @return The Principal object associated with user.
   */
  public Principal authenticate(HttpServletRequest pHttpRequest) {
    return () -> "John Doe";
  }
}
