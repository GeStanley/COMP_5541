package ui;

import java.io.File;

import java.io.PrintWriter;
import java.util.Scanner;

import structure.Cell;
import structure.Table;

/**
 * This class is the object that manages the saving and loading functions of the
 * program.
 * 
 * @author Ankita Mishara, Geoffrey Stanley, Michael Burkat, Nicholas Reinlein,
 *         Sofiane Benaissa, Tengzhong Wen
 * 
 *         Date 31-03-2014
 */

public class SaveFile {
	private File saveFile;
	private Scanner readFile;
	private PrintWriter writeFile;
	private Table target;
	private static String defaultMsg = "No message";

	/**
	 * Constructor for the save file
	 * 
	 * @param table
	 *            The target table
	 */
	public SaveFile(Table table) {
		saveFile = null;
		readFile = null;
		writeFile = null;
		target = table;
	}

	/**
	 * Load the given file
	 * 
	 * @param path
	 *            path to the file
	 * @return A message about the operation
	 */
	public String load(String path) {
		String msg = defaultMsg;
		boolean success = openRead(path);
		if (success) {
			try {
				parseFile();
			} catch (Exception e) {
				msg = "Error: file parsing - " + e.getMessage();
			}
			readFile.close();
			if (msg == defaultMsg)
				msg = "Success: file loaded";
		} else
			msg = "Error: file could not be opened for reading.";
		return msg;
	}

	/**
	 * Save to the given file
	 * 
	 * @param path
	 *            path to the file
	 * @return A message about the operation
	 */
	public String save(String path) {
		String msg = defaultMsg;
		target.populate();
		boolean success = false;

		success = openWrite(path);
		if (success) {
			try {
				targetToCSV();
				writeFile.close();
				msg = "Success: file saved";
			} catch (Exception e) {
				msg = "Error: " + e.getMessage();
			}
		} else {
			msg = "Error: file could not be opened for writing.";
		}

		return msg;
	}

	/**
	 * Try to open the listed file for reading
	 * 
	 * @param file
	 *            The target file location
	 * @return Whether or not the file was successfully opened
	 */
	private boolean openRead(String file) {
		boolean success = false;

		try {
			saveFile = new File(file);
			readFile = new Scanner(saveFile);
			success = true;
		}

		catch (Exception e) {
		}

		return success;
	}

	/**
	 * Try and open a file for writing
	 * 
	 * @param file
	 *            The target file location
	 * @return Whether or not the file was successfully opened
	 */
	private boolean openWrite(String file) {
		boolean success = false;

		try {
			saveFile = new File(file);
			saveFile.createNewFile();
			writeFile = new PrintWriter(saveFile);
			success = true;
		}

		catch (Exception e) {
			success = false;
		}

		return success;

	}

	/**
	 * Attempt to parse the file
	 * 
	 * @throws Exception
	 */
	private void parseFile() throws Exception {
		String line;
		int pos = 0;
		while (readFile.hasNextLine()) {
			try {
				line = readFile.nextLine().trim();
				parseLine(pos, line);
				pos++;
			} catch (Exception e) {
				throw new Exception("Error parsing save file at line "
						+ (pos + 1));
			}
		}
	}

	/**
	 * Parse a line of CSV into the target table (already populated with empty
	 * cells)
	 * 
	 * @param pos
	 *            Which row to update
	 * @param line
	 *            A string of CSV text
	 * @return The parsed line as an array of cells
	 * @throws Exception
	 */
	public Cell[] parseLine(int pos, String line) throws Exception {
		// TODO make this method private once we can test the parse method
		// directly
		Cell[] row;
		String[] parts;
		int count;

		// Remove leading and trailing quotes and commas
		if (line.charAt(0) == '"')
			line = line.substring(1);
		if (line.charAt(line.length() - 1) == ',')
			line = line.substring(0, line.length() - 1);
		if (line.charAt(line.length() - 1) == '"')
			line = line.substring(0, line.length() - 1);

		parts = line.split("\",\"");

		row = new Cell[parts.length];

		for (count = 0; count < parts.length; count++) {
			row[count] = target.selectCell(pos, count);
			target.insertToCell(parts[count]);
		}

		return row;
	}

	/**
	 * Write the table to a CSV file
	 * 
	 * @throws Exception
	 */
	private void targetToCSV() throws Exception {
		String line;
		int rowCount = target.getLength();
		for (int i = 0; i < rowCount; i++) {
			line = rowToCSV(target.getRow(i));
			writeFile.println(line);
		}
	}

	/**
	 * Generate a line of CSV from an array of cells
	 * 
	 * @param row
	 *            An array of cells
	 * @return A string suitable for inclusion as a line in a CSV
	 * @throws Exception
	 */
	public String rowToCSV(Cell[] row) throws Exception {
		if (row == null)
			throw new Exception("Null row cannot be parsed");
		// TODO make this method private once we can test the parse method
		// directly
		String val = "\"\"", out = "";
		for (int i = 0; i < row.length; i++) {
			if (row[i] == null)
				val = "\"\"";
			else
				val = '"' + row[i].getFormula() + '"';
			out += val;
			if (i != row.length - 1)
				out += ",";
		}
		return out;
	}

}
