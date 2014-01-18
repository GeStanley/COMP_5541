package gui;

import structure.Table;
import structure.Table.NullCellPointer;

public class ConsoleGUI {
	
	static Table table;
	
	public static void main(String[] args){
		try {
			table = new Table();
			table.defineArraySize(5, 5);
			
			table.getCell(1, 1).setFormula("1+1");
			System.out.println(table.getCell(1, 1).getValue());
			
			table.getCell(1, 2).setFormula("2+3*2");
			System.out.println(table.getCell(1, 2).getValue());
			
			table.getCell(2, 2).setFormula("A1*2");
			System.out.println(table.getCell(2, 2).getValue() + " doesn't work....");
			
		} catch (NullCellPointer e) {
			// TODO Auto-generated catch block
			System.out.println("error:");
			e.printStackTrace();
		}
		
	}
}
