package org.jia.ptrack.domain.hibernate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

public class HibernatePTrackTestConstants {

	static public final String[] PTRACK_APP_CTXS = new String[] {
		"classpath*:/appCtx/common/**/*.xml",
		"classpath*:/appCtx/testing/**/*.xml",
	};

	static public final String[] PTRACK_APP_CTXS_WITH_EMPTY_DB = without(PTRACK_APP_CTXS, "initializer.xml");

	private static String[] without(String[] files, String fileToSkip) {
		try {
			Set<String> foundResources = new HashSet<String>();
			for (String file : files) {
				Resource[] rl = new PathMatchingResourcePatternResolver().getResources(file);
				for (Resource resource : rl) {
					String fileName = resource.getURL().toString();
					if (!fileName.endsWith(fileToSkip))
						foundResources.add(fileName);
				}
			}
			return foundResources.toArray(new String[foundResources.size()]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
