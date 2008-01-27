package org.jia.ptrack.domain.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.jia.ptrack.domain.EnumeratedType;
import org.jia.ptrack.domain.EnumeratedType.EnumManager;

public abstract class EnumeratedUserType implements UserType, Serializable {

	protected abstract EnumManager getEnumManager();

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		return arg0 == null ? arg1 == null : arg0.equals(arg1);
	}

	public int hashCode(Object arg0) throws HibernateException {
		return arg0 == null ? 0 : arg0.hashCode();
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0]);
		if (rs.wasNull())
			return null;
		else
			return getEnumManager().getInstanceByValue(value);
	}

	public void nullSafeSet(PreparedStatement ps, Object value, int index) throws HibernateException, SQLException {
		if (value == null) 
			ps.setNull(index, Types.VARCHAR);
		else
			ps.setString(index, ((EnumeratedType) value).getValue());
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
