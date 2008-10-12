package org.jia.ptrack.security;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.jia.ptrack.domain.RoleType;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;
import org.jia.ptrack.domain.hibernate.EncryptedPasswordUtil;
import org.springframework.dao.DataAccessException;

public class PtrackUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public PtrackUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    User user = userRepository.findUser(username);
    if (user == null)
      throw new UsernameNotFoundException("User not found: " + username);
    else {
      return makeAcegiUser(user);
    }
  }

	private org.acegisecurity.userdetails.User makeAcegiUser(User user) {
		return new org.acegisecurity.userdetails.User(user.getLogin().getLogin(), EncryptedPasswordUtil.getEncryptedPasswordWithoutPrefix(user.getPassword().getPasswordString()), true, true, true, true, makeGrantedAuthorities(user));
	}

  private GrantedAuthority[] makeGrantedAuthorities(User user) {
    GrantedAuthority[] result = new GrantedAuthority[user.getRoles().size()];
    int i = 0;
    for (RoleType role : user.getRoles()) {
      result[i++] = new GrantedAuthorityImpl(role.getValue());
    }
    return result;
  }

}
