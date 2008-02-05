package org.jia.ptrack.services;

import java.util.ArrayList;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;

public class SecurityTestUtil {

	public static void setUser(String userId, String password, RoleType[] roles) {
		SecurityContextHolder.getContext().setAuthentication(
				new TestingAuthenticationToken(userId, password,
						SecurityTestUtil.makeGrantedAuthorities(roles)));
	}

	static GrantedAuthority[] makeGrantedAuthorities(RoleType[] roles) {
		GrantedAuthority[] grantedAuthorities = new GrantedAuthority[roles.length];
		for (int i = 0; i < roles.length; i++) {
			RoleType role = roles[i];
			grantedAuthorities[i] = new GrantedAuthorityImpl(role.getValue());
		}
		return grantedAuthorities;
	}

	public static void setUser(User user) {
		setUser(user.getLogin(), user.getPassword().getPasswordString(), toArray(user.getRoles()));
	}

	private static RoleType[] toArray(Set roles) {
		return (RoleType[]) new ArrayList(roles).toArray(new RoleType[roles.size()]);
	}

	public static void clearUser() {
		SecurityContextHolder.clearContext();
	}

}
