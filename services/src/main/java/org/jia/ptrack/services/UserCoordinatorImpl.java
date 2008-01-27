package org.jia.ptrack.services;

import org.jia.ptrack.domain.DataStoreException;
import org.jia.ptrack.domain.ObjectNotFoundException;
import org.jia.ptrack.domain.User;
import org.jia.ptrack.domain.UserRepository;

public class UserCoordinatorImpl implements IUserCoordinator {

	private UserRepository userRepository;

	public UserCoordinatorImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(String login, String password)
			throws DataStoreException, ObjectNotFoundException {
		User user = userRepository.findUser(login);
		if ((user != null) && (user.isPasswordValid(password))) {
			return user;
		} else {
			throw new ObjectNotFoundException(
					"No user found with that name and password");
		}
	}

}
