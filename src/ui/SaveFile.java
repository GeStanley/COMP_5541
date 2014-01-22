package ui;

import java.io.File;
import java.util.*;
import structure.Cell;
import structure.Table;

public class SaveFile {
	private Scanner saveFile;
	private Table target;
	
	/**
	 * Constructor for the save file
	 * 
	 * @param table The target table
	 */
	public SaveFile(Table table) {
		saveFile = null;
		target = table;
	}
	
	/**
	 * Load the given file
	 * 
	 * @param path path to the file
	 * @return A message about the operation
	 */
	public String load(String path) {
		String msg = "No message";
		boolean success = open(path);
		if (success) {
			try {
				parse();
			}
			catch (Exception e) {
				msg = "Error: file parsing - " + e.getMessage();
			}
			close();
			if (msg == "No message")
				msg = "Success: file loaded";
		}
		else
			msg = "Error: file could not be opened.";
		return msg;
	}
	
	/**
	 * Try to open the listed file
	 *
	 * @param file The target file location
	 * @return Whether or not the file was successfully opened
	 */
	private boolean open(String file) {
		boolean success = false;
		
		try {
			saveFile = new Scanner (new File (file));
			success = true;
		}
		
		catch (Exception e) {}

		return success;
	}

	/**
	 * Attempt to parse the file
	 * 
	 * @throws Exception
	 */
	private void parse() throws Exception {
		String line;
		int pos = 0;
		while (saveFile.hasNextLine()) {
			try {
				line = saveFile.nextLine().trim();
				parseLine(pos, line);
				pos++;
			}
			catch (Exception e) {
				throw new Exception("Error parsing save file at line " + (pos+1));
			}
		}
	}
	
	/**
	 * Parse a line of CSV into the target table
	 * 
	 * @param pos Which row to update
	 * @param line A string of CSV text
	 * @return The parsed line as an array of cells
	 */
	public Cell[] parseLine(int pos, String line) {
		// TODO make this method private once we can test the parse method directly
		Cell[] row;
		String[] parts;
		int count;
		
		// Remove leading and trailing quotes and commas
		if (line.charAt(0) == '"')
			line = line.substring(1);
		if (line.charAt(line.length()-1) == ',')
			line = line.substring(0,line.length()-1);
		if (line.charAt(line.length()-1) == '"')
			line = line.substring(0,line.length()-1);
		
		parts = line.split("\",\"");
		row = new Cell[parts.length];
		
		for (count=0; count<parts.length; count++) {
			row[count] = new Cell(target, parts[count]);
		}
		
		target.updateRow(pos, row);
		
		return row;
	}
	
	/**	
	 * Close saveFile
	 */
	private void close() {
		saveFile.close();
	}
	
}
