package gui;

import structure.Table;
import structure.Table.NullCellPointer;

public class ConsoleGUI {
	
	static Table table;
	
	public static void main(String[] args){
		try {
			table = new Table();
		
			System.out.println(table.getCell(0, 0).getValue());
		} catch (NullCellPointer e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String[][] test = new String[1][1];
//		test[0][0]="testing";
		
		System.out.println(Character.getNumericValue('Z'));

		//TODO prompt user for input and get them to interact with the table.
	}
}
