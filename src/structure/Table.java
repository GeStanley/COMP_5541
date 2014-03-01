package structure;

import java.util.ArrayList;
import java.util.Stack;


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
    private Cell selectedCell;
    private Stack<Cell> cellStack = new Stack<Cell>();
    private ArrayList<Cell> visitedCells;
        
    
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
     * resets all the cells of a table.
     */
    public void createNew() {
    	System.out.println("zero all cells");
    	for(int i=0;i<cells.length;i++) {
    		for(int j=0;j<cells[0].length;j++) {
    			cells[i][j] = new Cell(this);
    		}
    	}
    }
    
    /**
     * Populate this table with empty cells
     */
    public void populate() {	
    	for(int i=0;i<cells.length;i++) {
    		for(int j=0;j<cells[0].length;j++) {
    			if (cells[i][j] == null)
    				cells[i][j] = new Cell(this);
    		}
    	}
	}
    
    /**
     * Resets the selected cell to a zero value, will be called when some errors occur.
     */
    public void resetSelectedCell() {
    	selectedCell.resetCell();
    	selectedCell = null;
    }
    
    /**
     * Resets the selected cell to a zero value, will be called when some errors occur.
     */
    public void clearSelectedCell() {
    	selectedCell.resetCell();
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
     * This method transforms a Character value into the equivalent integer for row addressing within the spreadsheet.
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
     * @param column  A character representation of the column address.
     * @return The desired Cell.
     */
    public Cell getCell(int row, char column) {
    	return cells[row][getColumnIndex(column)];
    }
    
    public double getValue(int row, int column) {
    	return cells[row][column].getValue();
    }
    
    
    /**
     * This method retrieves a cell at a given row and column address.
     * 
     * @param row An integer representation of the row address.
     * @param column An integer representation of the column address.
     * @return The desired Cell.
     */
    public Cell getCell(int row, int column) {
    	return cells[row][column];
    }    
    
    public Cell getSelectedCell(){
    	return selectedCell;
    }
    /**
     * Select cell, create one if none exists at the desired location
     * 
     * @param address String address of the cell
     * @return The desired cell or null
     */
    public Cell selectCell(String address) {
    	int col = 0, row = -1;
    	char lastChar = '!';
    	//System.out.println("Select cell at " + address);
    	// Always return null when the spreadsheet has 0 dimensions
    	if (cells.length == 0)
    		return null;
    	    	    	
    	// Try to get useable row and column indices
    	while (row < 0) {
    		col += getColumnIndex(address.charAt(0));
    		// This conditional is how we deal with multi letter addresses
    		if (lastChar != '!')
    			col += (int) lastChar;
    		lastChar = address.charAt(0);
    		address = address.substring(1);
    		try {
    			row = Integer.parseInt(address) - 1;
    		}
    		catch (NumberFormatException e) {
    			if (address.length() > 0)
    				row = -1;
    			else
    				return null;
    		}
    	}
    	
    	return selectCell(row, col);
    }
  
    /**
     * Select cell, create one if none exists at the desired location
     * 
     * @param row row index of the cell
     * @param col column index of the cell
     * @return The desired cell or null
     */
    public Cell selectCell(int row, int col) {    	    	
    	// First check that row and column indices are within
    	// the bounds of the spreadsheet
    	if (row < cells.length && col < cells[0].length) {
    		if (cells[row][col] == null)
    			cells[row][col] = new Cell(this);
    		selectedCell = cells[row][col];
    		return selectedCell;
    	}
    	else
    		return null;
    }
    
    /**
     * This method deals with adding cells to the cell stack, and includes logic to detect Circular 
     * references, and topological traversal, by checking the visited cell list.
     * 
     * @param refs is a list of references in a cell
     */
    public boolean addRefsToStack(ArrayList<String> refs) throws Exception{
    	boolean addedRefs = false;
    	while ( !refs.isEmpty() ){
    		Cell c = selectCell(refs.remove(0));
    		
    		//check for circular reference
    		for (Cell s: cellStack){
    			if (c.equals(s)){
    				throw new Exception("Circular Reference");
    			}
    		}
    		
    		//check if the cell was already calculated
    		boolean wasVisited = false;
    		for (Cell v: visitedCells){
    			if (c.equals(v)){
    				wasVisited = true;
    				break;
    			}
    		}
    		
    		//add the referenced cell to the stack
    		if (c != null & !wasVisited){
    			cellStack.add( c );
    			addedRefs = true;
    		}
    	}
    	return addedRefs;
    }
    /**
     * This method calculates the values of the whole table in a topological manner,
     * by using a stack and a visited cell list.
     * 
     * @throws NumberFormatException
     * @throws Exception
     */
    public void computeTable() throws NumberFormatException, Exception {
    	visitedCells = new ArrayList<Cell>();
    	cellStack = new Stack<Cell>();
    	
    	for(int i=0;i<cells.length;i++) {
    		for(int j=0;j<cells[0].length;j++) {
    			if (cells[i][j] != null){
    				Cell c = cells[i][j];
    				c.getValue(true);
    				ArrayList<String> nextRefs = c.getFormulaObject().getReferences();
    				addRefsToStack(nextRefs);
    	
    				while (!cellStack.isEmpty()){
    					
    					c = cellStack.pop();
    					c.getValue(true);
    					nextRefs = c.getRefs();
    					
						if (nextRefs.isEmpty()){
    						c.getValue(true);
    						visitedCells.add(c);
    					} else {
    						cellStack.add(c);
    						if (!addRefsToStack(nextRefs) ){
    							c = cellStack.pop();
    							c.getValue(true);
        						visitedCells.add(c);
    						}
    					}	
    				}
    			}
    		}
    	}
    }
    
    
    
    /**
     * This method will print out the grid to the command line.
     */
    public void displayTable() {
    	int row, col, ch = (int) 'A'; // row and column count and character int value of col
    	String header = "";
    	String grid = "";
    	Cell active = null;
    	
    	// Do nothing if the table is empty
    	if (cells.length == 0) {
    		System.out.println("No table data");
    		return;
    	}
    	header = "   ";
    	for (col = 0; col < cells[0].length; col++) {
    		header += String.format("%12c", ((char) ch++)) ;
    	}
    	
    	for (row = 0; row < cells.length; row++) {
    		// Add the column values
    		for (col = 0; col < cells[0].length; col++) {
    			
    			if (col == 0){
    			grid += String.format("%2d ", row+1);	
    			}
    			
    			active = cells[row][col];
    			
    			Double value = (active != null) ? active.getValue() : 0;
    			
    			if(value>99999999D)
    				grid += String.format("%e", value);
    			else
    				grid += String.format("%12.2f", value);
    		}
    		// New line!
    		grid += "\n";
    	}
    	
    	System.out.println(header);
    	System.out.println(grid);
    }
    
    /**
     * This method allows you to check if a cell is selected
     * @return true if a cell is selected, false if no cell is selected yet
     */
    public boolean isCellSelected(){
    	return (selectedCell != null);
    }
    
    /**
     * Insert the formula into the selected cell, and it unselects the cell when it's done
     * 
     * @param formula
     * @throws Exception 
     */
    public void insertToCell(String formula) throws Exception{
    	if (formula.equals("")){
    		System.out.println("Value of cell was not changed");
    		selectedCell = null;
    		return;
    	}
    	selectedCell.setFormula(formula);
    	computeTable();
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
	 * Tables are equal if all their cells are equal
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Table other;
		boolean checkCells = true;
		if (this == obj)  // The same object
			return true;
		if (obj == null) 
			return false;
		if (obj.getClass() != getClass())  // Not the same class
			return false;
		
		other = (Table) obj; // Cast obj to cell
		
		// Check the cell dimensions
		if (other.getLength() == getLength()) {
			if (getLength() == 0) // Trivially equal
				return true;
			else if (other.getWidth() != getWidth())
				return false;
		}
		
		// Iterate through every cell
		for (int i=0; i<cells.length; i++) {
			for (int j=0; j<cells[0].length; j++) {
				//System.out.print("Check " + other.cells[i][j] + " against " + cells[i][j]);
				if (other.cells[i][j] != null && !other.cells[i][j].equals(cells[i][j])) 
					checkCells = false;
				
				else if (other.cells[i][j] == null && cells[i][j] != null) 
					checkCells = false;
				//System.out.println(": " + checkCells);
			}
		}
		return checkCells;
	}
    
    /**
     * Create an exception class for null cell pointers
     */
    public class NullCellPointer extends Exception {
    	
        public NullCellPointer(){
            super("Cell was not found.");
        }
    }    
    
}
