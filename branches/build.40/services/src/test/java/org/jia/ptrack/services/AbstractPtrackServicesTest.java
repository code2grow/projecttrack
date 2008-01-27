package org.jia.ptrack.services;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public abstract class AbstractPtrackServicesTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	protected void onSetUpInTransaction() throws Exception {
		SecurityTestUtil.clearUser();
		super.onSetUpInTransaction();
	}

	protected void onTearDownInTransaction() throws Exception {
		SecurityTestUtil.clearUser();
		super.onTearDownInTransaction();
	}
}
