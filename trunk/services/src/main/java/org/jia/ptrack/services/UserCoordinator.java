package org.jia.ptrack.services;

import java.io.Serializable;

import org.jia.ptrack.domain.User;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public interface UserCoordinator extends Serializable
{
   public User getUser(String login, String password);
}
