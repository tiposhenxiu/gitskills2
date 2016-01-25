package jp.co.sraw.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

public class ObjectUtil {

	public Object getObjectCopyValue(Object targetObject, Object sourceObject) {

		Method[] methodlist = sourceObject.getClass().getMethods();
		for (int m = 0; m < methodlist.length; m++) {
			Method getValueMethod = methodlist[m];
			String methodName = getValueMethod.getName();
			if (!Modifier.isStatic(getValueMethod.getModifiers()) && methodName.startsWith("get")
					&& getValueMethod.getParameterTypes().length == 0 && (!methodName.equals("getClass"))) {
				Method setValueMethod = null;
				Object obj = null;
				try {

					obj = getValueMethod.invoke(sourceObject);

					setValueMethod = getMethod(targetObject, methodName.replace("get", "set"),
							getValueMethod.getReturnType());

					if (setValueMethod == null) {
						setValueMethod = getMethod(targetObject, methodName.replace("get", "set"), String.class);
						if (obj != null) {
							obj = obj.toString();
						}
					}
					obj = setValueMethod.invoke(targetObject, obj);
				} catch (NullPointerException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// 型判定
					//e.printStackTrace();
					;
				}
			}
		}
		return targetObject;
	}

	private Method getMethod(Object object, String methodName, Class clazz) {
		try {
			return object.getClass().getMethod(methodName, clazz);
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() != null) {
				return getMethod(object, methodName, clazz.getSuperclass());
			} else {
				// 型判定
				//e.printStackTrace();
				;
			}
		} catch (SecurityException e) {
			// 型判定
			//e.printStackTrace();
			;
		}
		return null;
	}

	public Object setMapCopyValue(Object listObject, Map mapObject) {

		Method[] methodlist = listObject.getClass().getMethods();

		for (int m = 0; m < methodlist.length; m++) {
			Method getValueMethod = methodlist[m];
			String methodName = getValueMethod.getName();

			if (!Modifier.isStatic(getValueMethod.getModifiers()) && methodName.startsWith("get")
					&& getValueMethod.getParameterTypes().length == 0 && (!methodName.equals("getClass"))) {
				try {
					Method setValueMethod = listObject.getClass().getMethod(methodName.replace("get", "set"),
							getValueMethod.getReturnType());

					String key = getKeyName(setValueMethod.getName());

					setValueMethod.invoke(listObject, mapObject.get(key));
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// 型判定
					//e.printStackTrace();
					;
				}
			}
		}
		return listObject;
	}

	public String getKeyName(String fileName) {
		String methodName = "";
		if (fileName.startsWith("set"))
			fileName = fileName.substring(3, fileName.length());

		char[] c = fileName.toCharArray();
		String sperate = "";
		for (int i = 0; i < c.length; i++) {
			String x = "";
			if (c[i] >= 'A' && c[i] <= 'Z') {
				x = sperate + String.valueOf(c[i]).toLowerCase();
			} else {
				x = String.valueOf(c[i]).toLowerCase();
			}
			methodName = methodName + x;
			sperate = "_";
		}
		return methodName;
	}

}
