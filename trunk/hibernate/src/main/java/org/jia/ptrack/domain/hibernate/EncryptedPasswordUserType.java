package org.jia.ptrack.domain.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.jia.ptrack.domain.Password;

public class EncryptedPasswordUserType implements UserType {

  public Object assemble(Serializable cached, Object owner)
      throws HibernateException {
    return cached;
  }

  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  public Serializable disassemble(Object value) throws HibernateException {
    return (Password) value;
  }

  public boolean equals(Object x, Object y) throws HibernateException {
    return x == null ? x == y : x.equals(y);
  }

  public int hashCode(Object x) throws HibernateException {
    return x == null ? 0 : x.hashCode();
  }

  public boolean isMutable() {
    return false;
  }

  public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
      throws HibernateException, SQLException {
    return rs.getString(names[0]);
  }

  public void nullSafeSet(PreparedStatement st, Object value, int index)
      throws HibernateException, SQLException {
    if (value == null)
      st.setNull(index, Types.VARCHAR);
    else
      st.setString(index, EncryptedPasswordUtil.ensureEncrypted((String)value));
  }

  public Object replace(Object original, Object target, Object owner)
      throws HibernateException {
    return original;
  }

  public Class<?> returnedClass() {
    return Password.class;
  }

  public int[] sqlTypes() {
    return new int[]{Types.VARCHAR};
  }

}
