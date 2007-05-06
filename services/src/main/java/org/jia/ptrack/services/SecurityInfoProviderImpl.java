package org.jia.ptrack.services;

import org.acegisecurity.Authentication;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;

public class SecurityInfoProviderImpl implements SecurityInfoProvider {

	private UserRepository userRepository;
	
	
	public SecurityInfoProviderImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String getUsername() {
		// pia-lab-method-stub(acegi-biz)
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();
		String username = null;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	public String[] getGrantedAuthorities() {
		// pia-lab-method-stub(acegi-biz)
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		GrantedAuthority[] authorities = authentication.getAuthorities();
		String[] result = new String[authorities.length];
		for (int i = 0; i < authorities.length; i++) {
			GrantedAuthority authority = authorities[i];
			result[i] = authority.getAuthority();
		}
		return result;
	}

	public boolean isGrantedAuthority(String authority) {
		// pia-lab-method-stub(acegi-biz)
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		GrantedAuthority[] authorities = authentication.getAuthorities();
		for (int i = 0; i < authorities.length; i++) {
			GrantedAuthority authority1 = authorities[i];
			if (authority.equals(authority1.getAuthority()))
				return true;
		}
		return false;
	}

	public User getCurrentUser() {
		return userRepository.findUser(getUsername());
	}
}
