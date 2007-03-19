package org.jia.ptrack.domain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class DataStoreException extends BaseException
{
  public DataStoreException()
  {
    super();
  }

  public DataStoreException(String message)
  {
    super(message);
  }

  public DataStoreException(Exception e)
  {
    super(e);
  }

  public DataStoreException(String message, Exception e)
  {
    super(message, e);
  }

}
