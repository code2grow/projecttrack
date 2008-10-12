package org.jia.ptrack.services;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.jia.ptrack.domain.Department;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserMother;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class PtrackUserDetailsServiceTests extends
		AbstractDependencyInjectionSpringContextTests {

	private UserDetailsService userDetailsService;
	
	protected String[] getConfigLocations() {
		return new String[] { "classpath*:appCtx/common/**/*.xml",
				"classpath*:appCtx/common/**/*.xml" };
	}

	
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	public void testKnownUser() {
		User projectManager = UserMother.makeProjectManager(new Department("IT"));
		UserDetails user = userDetailsService.loadUserByUsername(projectManager.getLogin().getLogin());
		assertNotNull(user);
		assertEquals(projectManager.getRoles().size(), user.getAuthorities().length);
		
	}

	public void testUnKnownUser() {
		try {
			userDetailsService.loadUserByUsername("foobar");
			fail();
		} catch (UsernameNotFoundException e) {
			
		}
		
	}
}
