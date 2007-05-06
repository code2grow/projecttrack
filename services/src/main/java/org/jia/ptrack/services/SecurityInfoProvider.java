package org.jia.ptrack.services;

import org.jia.ptrack.domain.User;

public interface SecurityInfoProvider {

	public String getUsername();
	public String[] getGrantedAuthorities();
	public boolean isGrantedAuthority(String authority);
	public User getCurrentUser();
}
