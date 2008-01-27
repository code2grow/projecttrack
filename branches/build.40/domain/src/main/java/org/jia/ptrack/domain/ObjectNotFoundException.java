package org.jia.ptrack.domain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ObjectNotFoundException extends DataStoreException
{
  public ObjectNotFoundException()
  {
    super();
  }

  public ObjectNotFoundException(String message)
  {
    super(message);
  }

  public ObjectNotFoundException(Exception e)
  {
    super(e);
  }

  public ObjectNotFoundException(String message, Exception e)
  {
    super(message, e);
  }

}
