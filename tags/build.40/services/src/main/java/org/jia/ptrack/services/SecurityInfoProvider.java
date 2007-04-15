package org.jia.ptrack.services;

public interface SecurityInfoProvider {

	public String getUsername();
	public String[] getGrantedAuthorities();
	public boolean isGrantedAuthority(String authority);
}
