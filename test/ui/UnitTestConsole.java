package ui;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import structure.Cell;
import structure.Table;

public class UnitTestConsole {

	Console console = new Console();
	
	@Test
	public void testCreateNew() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
	    Method expandMethod = getMethodOfClass(Console.class, "createNew");
	    expandMethod.invoke(console);
		assertEquals(10, console.getTable().getLength());
	}

	private Method getMethodOfClass(Class argClass, String argMethodName) {
	    Method[] methods = argClass.getDeclaredMethods();
	    for (Method method : methods) {
	        if (method.getName().equals(argMethodName)) {
	            method.setAccessible(true);
	            return method;
	        }
	    }
	    throw new NoSuchMethodError("couldn't find " + argMethodName + " on class " + argClass);
	}
}
