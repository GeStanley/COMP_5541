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
	
	@Test
	public void testSelect() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
		String address = "A1";
	    Method selectMethod = getMethodOfClass(Console.class, "select");
	    selectMethod.invoke(console, address);
	}
	
	@Test
	public void testWelcome() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
	    Method welcomeMethod = getMethodOfClass(Console.class, "welcome");
	    welcomeMethod.invoke(console);
	}
	
	@Test
	public void testHelp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
	    Method helpMethod = getMethodOfClass(Console.class, "help");
	    helpMethod.invoke(console);
	}
	
	@Test
	public void testOpen() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
	    Method openMethod = getMethodOfClass(Console.class, "open");
	    openMethod.invoke(console);
	    console.getTable().displayTable();
	}
	
	@Test
	public void testSave() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		console = new Console();
	    Method saveMethod = getMethodOfClass(Console.class, "save");
	    saveMethod.invoke(console);
	    console.getTable().displayTable();
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
