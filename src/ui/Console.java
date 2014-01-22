package ui;

import java.io.InputStreamReader;
import java.util.Scanner;

import structure.Table;
import structure.Table.NullCellPointer;
import ui.SaveFile;

public class Console {
	
	static Table table;
	private static boolean quit = false;
	private static SaveFile saved;
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {

		table = new Table(5,5);
		String input;
			
		//table.selectCell("A1");
		//table.insertToCell("1");
		
		/*
		table.getCell(1, 1).setFormula("1+1");
		System.out.println(table.getCell(1, 1).getValue());
			
		table.getCell(1, 2).setFormula("2+3*2");
		System.out.println(table.getCell(1, 2).getValue());
		
		table.getCell(2, 2).setFormula("A1*2");
		System.out.println(table.getCell(2, 2).getValue() + " doesn't work....");
		*/
						
		welcome();
			
		while (!quit) {
				
			System.out.print("input line: ");
			input = sc.nextLine().toLowerCase();
			
			//System.out.println(input);
			switch (input) {
				case "quit":
					System.out.println("Goodbye");
					quit=true;
					break;
				case "help":
					help();
					break;
				case "save":
					System.out.println("Function not yet available.");
					break;
				case "open":
					open();
					break;
				case "display":
					table.displayTable();
					break;
				default:
					break;
			}
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
		String commands = 	"\tDisplay\n" +
							"\tOpen\n" +
							"\tSave\n" +
							"\t[letter][number] of the column and row to select a cell\n" +
							"\tQuit to quit\n" +
							"\tHelp to see this again\n";
		System.out.println(commands);
							
	}
	
	/**
	 * Open a save file
	 */
	private static void open() {
		saved = new SaveFile(table);
		boolean back = false;
		String input, msg;
		
		while (!back) {
			System.out.println("Enter the path to your save file, or \"back\":");
			input = sc.nextLine().toLowerCase();
			switch (input) {
				case "back":
					back = true;
					break;
				default:
					msg = saved.load(input);
					System.out.println(msg);
					if (msg == "Success: file loaded")
						back=true;
					break;
			}
		}		
	}
	
	private static void save() {
		// TODO Auto-generated method stub
		
	}
	
}
