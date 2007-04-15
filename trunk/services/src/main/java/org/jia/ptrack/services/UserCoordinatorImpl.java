package org.jia.ptrack.services;

import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;

public class UserCoordinatorImpl implements UserCoordinator {

	private UserRepository userRepository;

	public UserCoordinatorImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(String login, String password) {
		User user = userRepository.findUser(login);
		if ((user != null) && (user.isPasswordValid(password))) {
			return user;
		} else {
			throw new RuntimeException("not used");
		}
	}

}
