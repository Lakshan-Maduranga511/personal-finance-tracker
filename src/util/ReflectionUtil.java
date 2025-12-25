// Design Pattern: Reflection 
// Where used:
//   - Used in LoginService to invoke dashboard/menu methods dynamically
// Why used:
//   - Helps decouple method calls from compile-time dependencies
//   - Enables flexibility to change method targets with minimal edits
package util;

import java.lang.reflect.Method;

public class ReflectionUtil {

    
	public static boolean invokeStaticMethod(String className, String methodName) {
	    try {
	        Class<?> clazz = Class.forName(className);
	        Method method = clazz.getDeclaredMethod(methodName);
	        method.setAccessible(true);
	        method.invoke(null); // Static method
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}


    
    public static void invokeInstanceMethod(String className, String methodName) {
        try {
            Class<?> cls = Class.forName(className);
            Object instance = cls.getDeclaredConstructor().newInstance();
            Method method = cls.getDeclaredMethod(methodName);
            method.invoke(instance);
        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
        }
    }
    
}

