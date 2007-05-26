package org.jia.ptrack.domain.hibernate;

public class HibernatePTrackTestConstants {

	static public final String[] PTRACK_APP_CTXS = new String[] { 
		"appCtx/load-properties.xml",
    "appCtx/hibernate/initializer.xml",
	"appCtx/hibernate/repositories.xml",
	"appCtx/hibernate/test-transaction-manager.xml",
	"appCtx/localdatasource/local-datasource.xml",
//	"appCtxForClustering/hibernate/clustered-session-factory.xml",
	"appCtx/hibernate/session-factory.xml",
	"appCtx/testing/hsqldb-ormunit.xml",
	};

	static public final String[] PTRACK_APP_CTXS_WITH_EMPTY_DB = new String[] { 
		"appCtx/load-properties.xml",
		"appCtx/hibernate/repositories.xml",
		"appCtx/hibernate/test-transaction-manager.xml",
		"appCtx/localdatasource/local-datasource.xml",
		"appCtx/hibernate/session-factory.xml",
		"appCtx/testing/hsqldb-ormunit.xml",
	};

}
