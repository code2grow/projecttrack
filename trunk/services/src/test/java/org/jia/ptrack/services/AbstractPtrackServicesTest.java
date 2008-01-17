package org.jia.ptrack.services;

import net.chrisrichardson.ormunit.hibernate.HibernatePersistenceTests;

import org.jia.ptrack.domain.hibernate.PtrackDatabaseInitializer;

public abstract class AbstractPtrackServicesTest extends
		HibernatePersistenceTests<AbstractPtrackServicesTest> {

  protected PtrackDatabaseInitializer databaseInitializer;
  
  
	public void setDatabaseInitializer(PtrackDatabaseInitializer databaseInitializer) {
    this.databaseInitializer = databaseInitializer;
  }

	@Override
  protected void onSetUp() throws Exception {
		SecurityTestUtil.clearUser();
		super.onSetUp();
	}

  @Override
	protected void onTearDown() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDown();
	}
}
