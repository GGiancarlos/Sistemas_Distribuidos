package com.srpc.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.srpc.exception.DuplicateServiceException;

public class ServiceManager {
	
	private  Map<String, Class<? extends Service>> services;

	public ServiceManager() {
		services = new HashMap<String, Class<? extends Service>>();
	}
	
	public void addService(String name, Class<? extends Service> servicioImpl) 
			throws DuplicateServiceException {
		synchronized (services) {
			if(getService(name) != null) {
				throw new DuplicateServiceException();
			}
			
			services.put(name, servicioImpl);
		}
	}
	
	public Class<? extends Service> getService(String name) {
		synchronized (services) {
			return services.get(name);
		}
	}
	
	public Object executeMethod(String serviceName, String methodName, List<String> argsType, List<Object> args) 
			throws NoSuchMethodException, SecurityException, 
			InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException {
		Class<? extends Service> service = getService(serviceName);
		
		Method method = getMethod(service, methodName, argsType);
		Object inst = getInstance(service);
		
		return method.invoke(inst, args.toArray());
	}
	
	private Method getMethod(Class<? extends Service> service, String methodName, List<String> args) 
			throws NoSuchMethodException, SecurityException {
		List<Class<?>> argsType = new ArrayList<Class<?>>();
		
		for (String type : args) {
			argsType.add(getClass(type));
		}
		
		Class<?>[] newArgs = new Class<?>[argsType.size()];
		argsType.toArray(newArgs);
		
		Method m = service.getDeclaredMethod(methodName, newArgs);
		return m;
	}
	
	private Object getInstance(Class<? extends Service> service) 
			throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, 
			IllegalArgumentException, InvocationTargetException {
		Constructor<?> ctor = service.getDeclaredConstructor(new Class[]{});
		return ctor.newInstance(new Object[]{});
	}	
	
	private Class<?> getClass(String type) {
		switch (type) {
		case "int":			
			return int.class;
			
		case "Integer":		
			return Integer.class;
		
		case "String":
			return String.class;
		
		case "float":
			return float.class;
			
		case "Float":
			return Float.class;
			
		case "boolean":
			return boolean.class;
			
		case "Boolean":
			return Boolean.class;
			
		default:
			return null;
		}
	}

}
