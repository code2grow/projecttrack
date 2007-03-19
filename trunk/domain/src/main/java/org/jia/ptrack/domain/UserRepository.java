package org.jia.ptrack.domain;

public interface UserRepository {

	User findUser(String login);

}
