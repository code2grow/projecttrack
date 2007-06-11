package org.jia.ptrack.jmx;

import java.lang.reflect.Method;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;

public class ExampleMBeanImpl implements ExampleMBean {

	private static final String AVERAGE_TIME_SUFFIX = "AverageTime";

	private static final String FAILURES_SUFFIX = "Failures";

	private Class interfaceType;

	private MethodCountingAspect methodCounter;

	public ExampleMBeanImpl(MethodCountingAspect methodCounter,
			Class interfaceType) {
		this.interfaceType = interfaceType;
		this.methodCounter = methodCounter;
	}

	// Mostly boring stuff
	public void setAttribute(Attribute attribute) {
		throw new UnsupportedOperationException();
	}

	public AttributeList getAttributes(String[] attributes) {
		AttributeList result = new AttributeList();
		for (String attribute : attributes) {
			result.add(new Attribute(attribute, getAttribute(attribute)));
		}
		return result;
	}

	public AttributeList setAttributes(AttributeList attributes) {
		throw new UnsupportedOperationException();
	}

	public Object invoke(String actionName, Object[] params, String[] signature) {
		throw new UnsupportedOperationException();
	}

	private MBeanOperationInfo[] getOperationInfo() {
		return new MBeanOperationInfo[0];
	}

	private MBeanNotificationInfo[] getNotificationInfo() {
		return new MBeanNotificationInfo[0];
	}

	private MBeanConstructorInfo[] getConstructorInfo() {
		return new MBeanConstructorInfo[0];
	}

	public MBeanInfo getMBeanInfo() {
		try {
			MBeanInfo result = new MBeanInfo(getClass().getName(),
					"MyExampleMBean", getAttributeInfo(), getConstructorInfo(),
					getOperationInfo(), getNotificationInfo());
			return result;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	// The interesting stuff

	private MBeanAttributeInfo[] getAttributeInfo() {
		Method[] methods = interfaceType.getDeclaredMethods();
		MBeanAttributeInfo[] result = new MBeanAttributeInfo[methods.length * 3];
		int j = 0;
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			result[j++] = new MBeanAttributeInfo(method.getName(), Long.class
					.getName(), method.getName(), true, false, false);
			result[j++] = new MBeanAttributeInfo(method.getName()
					+ FAILURES_SUFFIX, Long.class.getName(),
					method.getName(), true, false, false);
			result[j++] = new MBeanAttributeInfo(method.getName()
					+ AVERAGE_TIME_SUFFIX, Double.class.getName(), method
					.getName(), true, false, false);
		}
		return result;
	}

	public Object getAttribute(String attribute) {
		if (attribute.endsWith(FAILURES_SUFFIX))
			return methodCounter.getFailedCallCount(interfaceType, withoutPrefix(attribute, FAILURES_SUFFIX));
		else if (attribute.endsWith(AVERAGE_TIME_SUFFIX))
			return methodCounter.getAverageTime(interfaceType, withoutPrefix(attribute, AVERAGE_TIME_SUFFIX));
		else
			return methodCounter.getCallCount(interfaceType, attribute);
	}

	private String withoutPrefix(String attribute, String suffix) {
		return attribute.substring(0, attribute.length() - suffix.length());
	}

}
