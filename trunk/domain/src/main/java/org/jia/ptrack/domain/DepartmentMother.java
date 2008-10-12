package org.jia.ptrack.domain;

public class DepartmentMother {

  static Department makeMarketingDepartment() {
    return new Department("Marketing");
  }

  static Department makeItDepartment() {
    return new Department("IT");
  }

}
