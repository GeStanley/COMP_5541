package ui;

import java.util.Scanner;

import structure.Cell;
import structure.Table;
import structure.Table.NullCellPointer;

/**
 * This class was the console interface for Increment1.
 * It is no longer used.
 * 
 * @author 	Ankita Mishara, Geoffrey Stanley, Michael Burkat, 
 * 			Nicholas Reinlein, Sofiane Benaissa, Tengzhong Wen
 * 
 * Date 31-03-2014
 */
public class Console {
	
	private static Table table = null;
	private static boolean quit = false;
	private static SaveFile saved = null;
	private static Scanner sc;
	private static Cell selected = null;
	
	/**
	 * Specify a test table instead of creating a new one
	 * 
	 * @param testable testing table object
	 */
	public static void testTable(Table testable) {
		table = testable;
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {

		if (table == null)
			table = new Table(10,11);
		
		String input;
		sc = new Scanner(System.in);
						
		welcome();
		table.displayTable();
		help();
			
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
					table.displayTable();
					break;
				case "display":
					table.displayTable();
					break;
				case "create new":
					createNew();
					table.displayTable();
					break;
				case "":
					help();
					break;
				default:
					select(input);
					break;
			}
		}
		
		// For testing purposes, reset the state of console at the end of the loop
		quit=false; 
		sc.close();
	}
	
	/**
	 * This method created a new table, by replacing the current table in memory.
	 */
	private static void createNew() {
		table = new Table(10,11);
	}

	/**
	 * Try to select a cell
	 * 
	 * @param address The address of the cell
	 * @throws Exception 
	 */
	private static void select(String address) {
		address = address.toUpperCase();
		selected = table.selectCell(address);
		String input;
		if (selected == null)
			System.out.println("Could not select this cell.");
		else {
			System.out.println(address + " selected, it contains: " + selected.toString());
			System.out.print("Enter a value or formula:");
			input = sc.nextLine().toUpperCase();
			System.out.println();
			try {
				table.insertToCell(input);
				table.displayTable();
			}
			catch (NullCellPointer e) {
				System.out.print("ERROR: ");
				System.out.println(e.getMessage());
			}
			catch (NumberFormatException e) {
				System.out.print("ERROR: ");
				System.out.println(e.getMessage());
			} 
			catch (Exception e) {
				System.out.print("ERROR: ");
				System.out.println(e.getMessage());
				table.resetSelectedCell();
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
		
	}
	
	/**
	 * This is the help menu, to get a quick idea of the commands available.
	 */
	private static void help(){
		String commands = 	"\n" +
							"\tDisplay\n" +
							"\tOpen\n" +
							"\tCreate New\n" +
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
