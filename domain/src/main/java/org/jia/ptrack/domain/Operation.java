package org.jia.ptrack.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Operation implements Serializable
{

	private int id;
  private java.util.Date timestamp;
  private Status toStatus;
  private String comments;
  private Status fromStatus;
  private User user;


  public Operation()
  {
    this.timestamp = new Date();
    toStatus = null;
    comments = null;
    fromStatus = null;
    user = null;
  }

  public Operation(Date timestamp, User user, Status fromStatus, Status toStatus,
                   String comments)
  {
    this.timestamp = timestamp;
    this.user = user;
    this.fromStatus = fromStatus;
    this.toStatus = toStatus;
    this.comments = comments;
  }

  public java.util.Date getTimestamp()
  {
    return timestamp;
  }

  public Status getFromStatus()
  {
    return fromStatus;
  }

  public Status getToStatus()
  {
    return toStatus;
  }
  public String getComments()
  {
    return comments;
  }

  public User getUser()
  {
    return user;
  }
}
