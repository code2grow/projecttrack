package org.jia.ptrack.domain.hibernate;


public class EncryptedPasswordUtil {
  private static final String ENCRYPTION_PREFIX = "{enc}";

  
  public static String ensureEncrypted(String password) {
    if (password.startsWith(ENCRYPTION_PREFIX))
      return password;
    else
      return ENCRYPTION_PREFIX + encryptPassword(password);
  }

  private static String encryptPassword(String password) {
    return PtrackPasswordEncoder.getInstance().encodePassword(password, null); 
  }

  public static String getEncryptedPasswordWithoutPrefix(String passwordString) {
    String encryptedPassword = ensureEncrypted(passwordString);
    return encryptedPassword.substring(ENCRYPTION_PREFIX.length());
  }
  
}
