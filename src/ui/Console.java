package ui;

import java.io.InputStreamReader;
import java.util.Scanner;

import structure.Table;
import structure.Table.NullCellPointer;
import structure.Cell;
import ui.SaveFile;

public class Console {
	
	static Table table;
	private static boolean quit = false;
	private static SaveFile saved = null;
	private static Scanner sc = new Scanner(System.in);
	private static Cell selected = null;
	
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
					save();
					break;
				case "open":
					open();
					break;
				case "display":
					table.displayTable();
					break;
				default:
					select(input);
					break;
			}
		}	
			

		
	}

	/**
	 * Try to select a cell
	 * 
	 * @param address The address of the cell
	 */
	private static void select(String address) {
		address = address.toUpperCase();
		selected = table.selectCell(address);
		String input;
		if (selected == null)
			System.out.println("Could not select this cell.");
		else {
			System.out.println(address + " selected");
			System.out.print("Enter a value or formula:");
			input = sc.nextLine().toUpperCase();
			System.out.println();
			try {
				table.insertToCell(input);
			}
			catch (NullCellPointer e) {
				System.out.print("ERROR: ");
				System.out.println(e.getMessage());
			}
			catch (NumberFormatException e) {
				System.out.print("ERROR: ");
				System.out.println(e.getMessage());
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
							"\tQuit\n" +
							"\t\"Help\" to see this again\n";
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
	
	/**
	 * Save a spreadsheet
	 */
	private static void save() {
		if (saved == null)
			saved = new SaveFile(table);
		boolean back = false;
		String input, msg;
		
		while (!back) {
			System.out.println("Enter the path to save to, or \"back\":");
			input = sc.nextLine().toLowerCase();
			switch (input) {
				case "back":
					back = true;
					break;
				default:
					msg = saved.save(input);
					System.out.println(msg);
					if (msg == "Success: file saved")
						back=true;
					break;
			}
		}
	}
	
}
