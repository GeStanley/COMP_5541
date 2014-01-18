package gui;

import java.io.InputStreamReader;
import java.util.Scanner;

import structure.Table;
import structure.Table.NullCellPointer;

public class ConsoleGUI {
	
	static Table table;
	private static boolean quit = false;
	
	public static void main(String[] args){
		try {
			table = new Table();
			table.defineArraySize(5, 5);
			
			table.getCell(1, 1).setFormula("1+1");
			System.out.println(table.getCell(1, 1).getValue());
			
			table.getCell(1, 2).setFormula("2+3*2");
			System.out.println(table.getCell(1, 2).getValue());
			
			//table.getCell(2, 2).setFormula("A1*2");
			//System.out.println(table.getCell(2, 2).getValue() + " doesn't work....");
			
			table.displayTable();
			
			
			welcome();
			
			while (!quit) {
				
				System.out.print("input line: ");
				Scanner sc = new Scanner(System.in);
				String input = sc.nextLine();
				
				//System.out.println(input);
				
				if (input.equalsIgnoreCase("Quit")){
					System.out.println("Goodbye");
					quit = true;
					
				} else if (input.equalsIgnoreCase("Help")){
					help();
					
				}else if (input.equalsIgnoreCase("Save")){
					System.out.println("Filename: ");
					
				}else if (input.equalsIgnoreCase("Open")){
					System.out.println("Filename: ");
					
				}else{
					continue;
				}
			}
			
			
			
			
		} catch (NullCellPointer e) {
			// TODO Auto-generated catch block
			System.out.println("error:");
			e.printStackTrace();
		}
		
	}
	/**
	 * Self explanatory welcome message.
	 */
	private static void welcome() {
		String programName = 	"**************************************************************\n" +
								"*                       CALCUL-O-MATIC                       *\n" +
								"*                             by                             *\n" +
								"*                           TEAM 2                           *\n" +
								"**************************************************************\n";
		
		System.out.println(programName);
		help();
		
	}
	
	/**
	 * This is the help menu, to get a quick idea of the commands available.
	 */
	private static void help(){
		String commands = 	"\tOpen [file name]\n\tSave [file name]\n\t[letter][number] of the column and row" +
							"to select a cell\n\tQuit to quit\n\tHelp to see this again\n";
		System.out.println(commands);
							
	}
	
}
