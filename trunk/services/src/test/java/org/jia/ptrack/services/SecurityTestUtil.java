package org.jia.ptrack.services;

import java.util.ArrayList;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.TestingAuthenticationToken;
import org.jia.ptrack.domain.Password;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserId;

public class SecurityTestUtil {

	public static void setUser(UserId userId, Password password, RoleType[] roles) {
		String login = userId.getLogin();
    String passwordString = password.getPasswordString();
    SecurityContextHolder.getContext().setAuthentication(
				new TestingAuthenticationToken(login, passwordString,
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
		setUser(user.getLogin(), user.getPassword(), toArray(user.getRoles()));
	}

	private static RoleType[] toArray(Set roles) {
		return (RoleType[]) new ArrayList(roles).toArray(new RoleType[roles.size()]);
	}

	public static void clearUser() {
		SecurityContextHolder.clearContext();
	}

}
