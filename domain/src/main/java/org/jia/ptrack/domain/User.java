package org.jia.ptrack.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class User implements Serializable
{
  private int id;
  private PersonName name;
  private UserId login;
  private Password password;
  private Set<RoleType> roles;
  private Department department;

  public User()
  {
  }

  public User(UserId login, PersonName name, Password password,
              Set<RoleType> roles, Department department)
  {
    this.login = login;
    this.name = name;
    this.password = password;
    this.roles = roles;
    assert !roles.isEmpty();
	  this.department = department;
  }

  public int getId() {
    return id;
  }
  
  public UserId getLogin()
  {
    return login;
  }

  public String getFirstName()
  {
    return name.getFirstName();
  }

  public String getLastName()
  {
    return name.getLastName();
  }

  
   public void setFirstName(String firstName) {
    name.setFirstName(firstName);
  }

  public void setLastName(String lastName) {
    name.setLastName(lastName);
  }

  public Password getPassword()
  {
    return password;
  }

  public void setRole(RoleType role)
  {
    throw new UnsupportedOperationException();
  }

  public RoleType getRole()
  {
	for (Iterator it = roles.iterator(); it.hasNext();) {
		RoleType role = (RoleType) it.next();
		if (role.isPtrackRole())
			return role;
		
	}
    throw new UnsupportedOperationException();
  }

  public String toString()
  {
    return name.getFirstName() + " " + name.getLastName();
  }

  public String getFullName() {
	  return toString();
  }
  
public boolean isPasswordValid(String password) {
	return getPassword().equals(password);
}

public Set<RoleType> getRoles() {
	return roles;
}

public Department getDepartment() {
	return department;
}

boolean hasRole(RoleType role) {
	return roles.contains(role);
}

}
