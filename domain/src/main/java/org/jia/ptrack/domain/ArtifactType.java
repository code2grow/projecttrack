package org.jia.ptrack.domain;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ArtifactType extends EnumeratedType
{
  public final static ArtifactType PROPOSAL = new ArtifactType(0, "Proposal document");
  public final static ArtifactType PROJECT_PLAN =  new ArtifactType(3, "Project plan");
  public final static ArtifactType ARCHITECTURE =  new ArtifactType(5, "Architecture specification");
  public final static ArtifactType TEST = new ArtifactType(10, "Test plan");
  public final static ArtifactType DEPLOYMENT = new ArtifactType(15, "Deployment guidelines");
  public final static ArtifactType MAINTENANCE = new ArtifactType(20, "Maintenance documentation");
  public final static ArtifactType USER = new ArtifactType(25, "User documentation");

  private static EnumManager enumManager;

  static
  {
    enumManager = new EnumManager();
    enumManager.addInstance(PROPOSAL);
    enumManager.addInstance(PROJECT_PLAN);
    enumManager.addInstance(ARCHITECTURE);
    enumManager.addInstance(TEST);
    enumManager.addInstance(DEPLOYMENT);
    enumManager.addInstance(MAINTENANCE);
    enumManager.addInstance(USER);
  }

  public static EnumManager getEnumManager()
  {
    return enumManager;
  }

  private ArtifactType(int value, String description)
  {
    super(value, description);
  }
}
