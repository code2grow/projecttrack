package org.jia.ptrack.domain;

import java.util.HashSet;
import java.util.Set;

public class UserMother {

	public static User makeProjectManager(Department department) {
		return new User("proj_mgr", "Sean", "Sullivan", "faces",
				makeRoleTypeSet(RoleType.PROJECT_MANAGER,
						RoleType.PROJECT_APPROVER, RoleType.PROJECT_CREATOR,
						RoleType.PROJECT_VIEWER), department);
	}

	public static User makeSystemsManager(Department department) {
		return new User("sys_mgr", "Ed", "LaCalle", "faces", makeRoleTypeSet(
				RoleType.SYSTEMS_MANAGER, RoleType.PROJECT_APPROVER,
				RoleType.PROJECT_VIEWER), department);
	}

	public static User makeQAManager(Department department) {
		return new User("qa_mgr", "Tracey", "Burroughs", "faces",
				makeRoleTypeSet(RoleType.QA_MANAGER, RoleType.PROJECT_APPROVER,
						RoleType.PROJECT_VIEWER), department);
	}

	public static User makeDevelopmentManager(Department department) {
		return new User("dev_mgr", "Devora", "Shapiro", "faces",
				makeRoleTypeSet(RoleType.DEVELOPMENT_MANAGER,
						RoleType.PROJECT_APPROVER, RoleType.PROJECT_VIEWER),
				department);
	}

	public static User makeBusinessAnalyst(Department department) {
		return new User("analyst", "Marvin", "Walton", "faces",
				makeRoleTypeSet(RoleType.BUSINESS_ANALYST,
						RoleType.PROJECT_APPROVER, RoleType.PROJECT_VIEWER),
				department);
	}

	public static User makeUpperManager() {
		return new User(
				"upper_mgr",
				"Casey",
				"Langer",
				"faces",
				makeRoleTypeSet(RoleType.UPPER_MANAGER, RoleType.PROJECT_VIEWER),
				null);
	}

  public static User makeMarketingDepartmentProjectManager(
      Department department) {
    return new User("proj_mgr2", "John", "Doe", "faces",
				makeRoleTypeSet(RoleType.PROJECT_MANAGER,
						RoleType.PROJECT_APPROVER, RoleType.PROJECT_CREATOR),
				department);
  }

	private static Set makeRoleTypeSet(RoleType role1, RoleType role2,
			RoleType role3) {
		Set r = makeRoleTypeSet(role1, role2);
		r.add(role3);
		return r;
	}

	private static Set makeRoleTypeSet(RoleType role1, RoleType role2) {
		Set r = new HashSet();
		r.add(role1);
		r.add(role2);
		return r;
	}

	private static Set makeRoleTypeSet(RoleType role1,
			RoleType role2, RoleType role3,
			RoleType role4) {
		Set result = makeRoleTypeSet(role1, role2);
		result.addAll(makeRoleTypeSet(role3, role4));
		return result;
	}

  public static User makeProjectManager() {
  	return makeProjectManager(new Department());
  }
}
