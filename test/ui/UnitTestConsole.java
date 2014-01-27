package ui;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.Test;
import structure.Cell;
import structure.Table;

public class UnitTestConsole {
	
	@Test
	public void testCreateNew() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method expandMethod = getMethodOfClass(Console.class, "createNew");
	    expandMethod.invoke(Console.class);
		//assertEquals(10, console.getTable().getLength());
	}
	
	@Test
	public void testSelect() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String address = "A1";
	    Method selectMethod = getMethodOfClass(Console.class, "select");
	    selectMethod.invoke(Console.class, address);
	}
	
	@Test
	public void testWelcome() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method welcomeMethod = getMethodOfClass(Console.class, "welcome");
	    welcomeMethod.invoke(Console.class);
	}
	
	@Test
	public void testHelp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method helpMethod = getMethodOfClass(Console.class, "help");
	    helpMethod.invoke(Console.class);
	}
	
	@Test
	public void testOpen() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method openMethod = getMethodOfClass(Console.class, "open");
	    openMethod.invoke(Console.class);
	}
	
	@Test
	public void testSave() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	    Method saveMethod = getMethodOfClass(Console.class, "save");
	    saveMethod.invoke(Console.class);
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
