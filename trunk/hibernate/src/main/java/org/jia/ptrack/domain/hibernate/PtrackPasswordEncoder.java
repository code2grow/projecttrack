package org.jia.ptrack.domain.hibernate;

import org.jasypt.springsecurity.PasswordEncoder;
import org.jasypt.util.password.BasicPasswordEncryptor;

public class PtrackPasswordEncoder extends PasswordEncoder {
  private static final PtrackPasswordEncoder instance = new PtrackPasswordEncoder();
  
  public static final PtrackPasswordEncoder getInstance() {
    return instance;
  }

  private PtrackPasswordEncoder() {
    setPasswordEncryptor(new BasicPasswordEncryptor());
  }
  
}