package org.jia.ptrack.domain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class RoleType extends EnumeratedType
{
	private final boolean ptrackRole;
	
  public final static RoleType PROJECT_CREATOR = new RoleType(
			"ROLE_PROJECT_CREATOR", "Project Creator",
			"/ptrack-ui/images/inbox.gif", false);

   public final static RoleType PROJECT_APPROVER = new RoleType(
			"ROLE_PROJECT_APPROVER", "Project Approver",
			"/ptrack-ui/images/inbox.gif", false);

   public final static RoleType PROJECT_VIEWER = new RoleType(
		   "ROLE_PROJECT_VIEWER", "Project VIEWER",
		   "/ptrack-ui/images/inbox.gif", false);

  public final static RoleType UPPER_MANAGER = new RoleType(
			"ROLE_UPPER_MANAGER", "Upper Manager",
			"/ptrack-ui/images/inbox.gif", true);
  public final static RoleType PROJECT_MANAGER = new RoleType("ROLE_PROJECT_MANAGER",
      "Project Manager", "/ptrack-ui/images/inbox.gif", true);
  public final static RoleType BUSINESS_ANALYST = new RoleType("ROLE_BUSINESS_ANALYST",
      "Business Analyst", "/ptrack-ui/images/inbox.gif", true);
  public final static RoleType DEVELOPMENT_MANAGER = new RoleType("ROLE_DEVELOPMENT_MANAGER",
      "Development Manager", "/ptrack-ui/images/inbox.gif", true);
  public final static RoleType SYSTEMS_MANAGER = new RoleType("ROLE_SYSTEMS_MANAGER",
      "Systems Manager", "/ptrack-ui/images/inbox.gif", true);
  public final static RoleType QA_MANAGER = new RoleType("ROLE_SYSTEMS_MANAGER", "QA Manager",
      "/ptrack-ui/images/inbox.gif", true);
  private String iconUrl = null;

  private static EnumManager enumManager;

  static
  {
    enumManager = new EnumManager();
    enumManager.addInstance(PROJECT_MANAGER);
    enumManager.addInstance(BUSINESS_ANALYST);
    enumManager.addInstance(DEVELOPMENT_MANAGER);
    enumManager.addInstance(SYSTEMS_MANAGER);
    enumManager.addInstance(QA_MANAGER);
    enumManager.addInstance(UPPER_MANAGER); // missing :-)
    enumManager.addInstance(PROJECT_CREATOR); 
    enumManager.addInstance(PROJECT_APPROVER);
    enumManager.addInstance(PROJECT_VIEWER);
  }

  private RoleType(String value, String description, String iconUrl, boolean ptrackRole)
  {
    super(value, description);
    this.iconUrl = iconUrl;
	this.ptrackRole = ptrackRole;
  }

  public static EnumManager getEnumManager()
  {
    return enumManager;
  }

  public void setIconUrl(String iconUrl)
  {
    this.iconUrl = iconUrl;
  }

  public String getIconUrl()
  {
    return iconUrl;
  }

public boolean isPtrackRole() {
	return ptrackRole;
 }
}
