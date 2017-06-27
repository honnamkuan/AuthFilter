package com.hnkuan.auth.filter.authenticator;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 27/6/2017
 *
 * @author honnamkuan
 */
public class UserAuthenticator {

  public Principal authenticate(HttpServletRequest pHttpRequest) {
    return () -> "John Doe";
  }
}
