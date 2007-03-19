package org.jia.ptrack.domain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ProjectType extends EnumeratedType
{
	
  ProjectType() {}
  
  public final static ProjectType UNKNOWN = new ProjectType( -1, "Unknown");
  public final static ProjectType EXTERNAL_WEB = new ProjectType(0,
      "External Web Application");
  public final static ProjectType INTERNAL_WEB = new ProjectType(5,
      "Internal Web Application");
  public final static ProjectType EXTERNAL_DB = new ProjectType(10,
      "External Database");
  public final static ProjectType INTERANL_DB = new ProjectType(15,
      "Internal Database");
  public final static ProjectType EXTERNAL_DESKTOP = new ProjectType(20,
      "External Desktop Application");
  public final static ProjectType INTERNAL_DESKTOP = new ProjectType(25,
      "Internal Desktop Application");

  private static EnumManager enumManager;

  static
  {
    enumManager = new EnumManager();
    enumManager.addInstance(UNKNOWN);
    enumManager.addInstance(EXTERNAL_WEB);
    enumManager.addInstance(INTERNAL_WEB);
    enumManager.addInstance(EXTERNAL_DB);
    enumManager.addInstance(INTERANL_DB);
    enumManager.addInstance(EXTERNAL_DESKTOP);
    enumManager.addInstance(INTERNAL_DESKTOP);
  }

  public static EnumManager getEnumManager()
  {
    return enumManager;
  }

  private ProjectType(int value, String description)
  {
    super(value, description);
  }
}
