package org.jia.ptrack.domain;

import java.io.*;
import java.util.*;

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
  public void setTimestamp(java.util.Date timestamp)
  {
    this.timestamp = timestamp;
  }
  public void setFromStatus(Status fromStatus)
  {
    this.fromStatus = fromStatus;
  }
  public Status getFromStatus()
  {
    return fromStatus;
  }
  public void setToStatus(Status toStatus)
  {
    this.toStatus = toStatus;
  }
  public Status getToStatus()
  {
    return toStatus;
  }
  public void setComments(String comments)
  {
    this.comments = comments;
  }
  public String getComments()
  {
    return comments;
  }
  public void setUser(User user)
  {
    this.user = user;
  }
  public User getUser()
  {
    return user;
  }
}
