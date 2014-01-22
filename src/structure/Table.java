package structure;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;


/**
 * This class manages the cells in the spreadsheet. It is also designed to be able to interact with a user interface.
 * A user will be able to retrieve and edit information contained within a cell by interacting the the Table class. It also can
 * display the contents of the cells to the user interface.
 * 
 * Finally, it can create an array of cells by loading information from a text file as well as save information input into 
 * the spreadsheet to a text file.
 * 
 * @author GeStanley
 * @author Mike
 */
public class Table {
	
    private Cell[][] cells;
    private String selectedCell;
    
	
    
    /**
     * Default constructor method. Builds a 1 by 1 spreadsheet.
     */
    public Table() { 
    	this(1,1);
    }
    
    /**
     * Constructor method
     * 
     * @param rows int number of rows in the sheet
     * @param cols int number of columns in the sheet
     */
    public Table(int rows, int cols) {
    	this.cells = new Cell[rows][cols];
    	selectedCell = null;
    }
 
    /**
     * Insert values into the row at position pos
     * 
     * Spreadsheet will automatically be resized to accomodate the new 
     * row position and width
     * 
     * @param pos The row position at which to insert
     * @param row An array of cells to insert
     * @return The former contents of the row
     */
    public Cell[] updateRow(int pos, Cell[] row) {
    	Cell[] ret;
    	if (pos >= cells.length || row.length > cells[0].length)
    		expandTable(new Cell[pos+1][row.length]);
    	ret = cells[pos];
    	for (int i=0; i<row.length; i++) {
    		cells[pos][i] = row[i];
    	}
    	return ret;
    }
    
    /**
     * This method adds rows to the bottom of the spreadsheet.
     * 
     * @param count Integer number of rows to add
     * @return New number of rows in the spreadsheet 
     */
    public int appendRow(int count) {
    	expandTable(new Cell[cells.length+count][cells[0].length]);
    	return cells.length;
    }
    
    /**
     * This method adds columns to the far right of the spreadsheet.
     * 
     * @param count Integer number of columns to add
     * @return New number of columns in the spreadsheet
     */
    public int appendColumn(int count) {
    	expandTable(new Cell[cells.length][cells[0].length+count]);
    	return cells[0].length;
    }
    
    /**
     * Helper method for the appendColumn and appendRow methods
     * 
     * @param tmp A new array of cells to expand into
     */
    private void expandTable(Cell[][] tmp) {
    	for(int i=0;i<cells.length;i++) {
    		for(int j=0;j<cells[0].length;j++) {
    			tmp[i][j] = cells[i][j];
    		}
    	}
    	
    	cells = tmp;
    }
    
    /**
     * This method transforms a Character value into the equivalent interger for row addressing within the spreadsheet.
     * In other words, it converts A into 0, B into 1 and so on.
     * 
     * @param row A character representation of the row.
     * @return An Integer representation of the row.
     */
    public int getColumnIndex(char row){
    	return ((int) row) - ((int) 'A');
    }
    
    /**
     * Accessor method for an entire row
     * 
     * @param pos Which row to return
     * @return The row or null if there is no row to return
     */
    public Cell[] getRow(int pos) {
    	if (pos >= cells.length)
    		return null;
    				
    	// TODO return a copy of the row contents instead of the row itself
    	return cells[pos];
    }
    
    /**
     * This method retrieves a cell at a given row and column address.
     * 
     * @param row An integer representation of the row address.
     * @param column  An character representation of the column address.
     * @return The desired Cell.
     * @throws NullCellPointer
     */
    public Cell getCell(int row, char column) throws NullCellPointer{
    	return cells[row][getColumnIndex(column)];
    }
    
    /**
     * This method retrieves a cell at a given row and column address.
     * 
     * @param row An integer representation of the row address.
     * @param column An integer representation of the column address.
     * @return The desired Cell.
     * @throws NullCellPointer
     */
    public Cell getCell(int row, int column) throws NullCellPointer{
    	return cells[row][column];
    }
    
    
    /** 
     * This method loads data contained within the spreadsheet to a text file.
     * 
     * This file will delimit cells with a semicolon and information contained within an individual cell by a comma.
     * The first two pieces of information should be the cell address, the third the cell formula.
     * 
     * ex: 0,0,1+0;0,1,1+1;
     * 
     * @param fileName The path and name of file to be saved. eg: c:/.../textfile.txt
     * @throws NullCellPointer 
     * @throws FileNotFoundException 
     */
    public void saveToFile(String fileName) throws NullCellPointer, FileNotFoundException{
    	PrintWriter output = new PrintWriter(fileName);
    	
    	for(int i=0;i<cells.length;i++){
    		String data="";
    		for(int j=0;j<cells[i].length;j++)
    			data = data + i + "," + j + "," + getCell(i,j).getFormula() + ";";
    		
    		output.println(data);
    		data=null;
    	}
    	
    	output.close();
    }
    
    
    /**
     * This method will print out the grid to the command line.
     * 
     */
    public void displayTable() {
    	int row, col, ch = (int) 'A'; // row and column count and character int value of col
    	String header = "A";
    	String grid = "";
    	Cell active = null;
    	
    	// Do nothing if the table is empty
    	if (cells.length == 0) {
    		System.out.println("No table data");
    		return;
    	}
    	
    	for (col = 0; col < cells[0].length; col++) {
    		header += "\t\t" + ((char) ++ch);
    	}
    	
    	for (row = 0; row < cells.length; row++) {
    		// Add the column values
    		for (col = 0; col < cells[0].length; col++) {
    			active = cells[row][col];
    			if (active == null)
    				grid += "\t\t";
    			else
    				grid += active.getValueString() + "\t\t";
    		}
    		// New line!
    		grid += "\n";
    	}
    	
    	System.out.println(header);
    	System.out.println(grid);
    }
    
    public class NullCellPointer extends Exception {
    	
        public NullCellPointer(){
            super("Cell was not found.");
    }
    }
    
    /**
     * This method allows you to check if a cell is selected
     * @return true if a cell is selected, false if no cell is selected yet
     */
    public boolean isCellSelected(){
    	if (selectedCell != null) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * This method selects a cell from a table
     * @param cell
     */
    public void selectCell(String cell) {
    	selectedCell = cell;
    }
    
    /**
     * Insert the formula into the selected cell, and it unselects the cell when it's done
     * 
     * @param formula
     * @throws NumberFormatException
     * @throws NullCellPointer
     */
    public void insertToCell(String formula) throws NumberFormatException, NullCellPointer{
    	int i = Integer.parseInt(selectedCell.substring(1)) ;
    	Cell cell = getCell(i-1, selectedCell.charAt(0));
    	cell.setFormula(formula) ;
    	try {
			cell.setValue(getValue(formula));
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	selectedCell = null;
    }

    /**
     * Get the length (number of rows) of the spreadsheet
     */
    public int getLength() {
    	return cells.length;
    }
    
    /**
     * Get the width (number of columns) of the spreadsheet
     */
    public int getWidth() {
    	if (cells.length > 0)
    		return cells[0].length;
    	else
    		return 0;
    }
    
    /**
     * Calculates the value of a formula
     * @param formula
     * @return value of the calculated formula
     * @throws ScriptException
     */
    public double getValue(String formula) throws ScriptException{
    	
    	ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
    	Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("A1", 1); 
        // will need to fetch those actively from the cells, this is temporary testing.
        vars.put("A2", 2);
        vars.put("A3", 3);
        Object x = engine.eval(formula, new SimpleBindings(vars));
        System.out.println("formula = "+ x.toString());
        double i = Double.parseDouble( x.toString() );
		return i;
    }
    
    
    
}
