package com.hnkuan.auth.filter;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 27/6/2017
 *
 * @author honnamkuan
 */
public class Authenticator {

  public Principal authenticate(HttpServletRequest pHttpRequest) {
    return () -> "John Doe";
  }
}
