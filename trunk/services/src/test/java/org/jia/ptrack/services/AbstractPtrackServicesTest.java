package org.jia.ptrack.services;

import org.jia.ptrack.domain.hibernate.PtrackDatabaseInitializer;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class AbstractPtrackServicesTest extends
		AbstractTransactionalDataSourceSpringContextTests {

  protected PtrackDatabaseInitializer databaseInitializer;
  
  
	public void setDatabaseInitializer(PtrackDatabaseInitializer databaseInitializer) {
    this.databaseInitializer = databaseInitializer;
  }

  protected void onSetUpInTransaction() throws Exception {
		SecurityTestUtil.clearUser();
		super.onSetUpInTransaction();
	}

	protected void onTearDownInTransaction() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDownInTransaction();
	}
}
