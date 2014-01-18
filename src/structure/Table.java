package structure;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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
	
    
    /**
     * This is the constructor method. It building a 1 by 1 spreadsheet.
     * @throws NullCellPointer 
     * @throws NumberFormatException 
     */
    public Table() throws NumberFormatException, NullCellPointer{ 
    	this.cells = new Cell[1][1];
    	cells[0][0] = new Cell(this);
    }
    
    /**
     * This method will allow you to change the size of the spreadsheet. It will keep the data that has been input into the cells
     * so long as the size of the array is increasing. If you are decreasing the size of the array the information that was outside
     * the dimensions passed to the array will be lost.
     * 
     * @param rowQty A integer parameter that determines how many rows will be in the array.
     * @param columnQty A integer parameter that determines how many columns will be in the array.
     * @throws NullCellPointer 
     * @throws NumberFormatException 
     */
    public void defineArraySize(int rowQty, int columnQty) throws NumberFormatException, NullCellPointer{
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++){
    			temp[i][j] = new Cell(this);
    			temp[i][j].setFormula(cells[i][j].getFormula());
    			}
    	
    	cells = new Cell[rowQty][columnQty];
    	
    	for(int i=0;i<rowQty;i++)
    		for(int j=0;j<columnQty;j++){
    			cells[i][j] = new Cell(this);
    			}
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j].setFormula(temp[i][j].getFormula());
    }
    
    /**
     * This method adds a row to the bottom of the spreadsheet.
     * @throws NullCellPointer 
     * @throws NumberFormatException 
     */
    public void insertRow() throws NumberFormatException, NullCellPointer{
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++){
    			temp[i][j] = new Cell(this);
    			temp[i][j].setFormula(cells[i][j].getFormula());
    			}
    	
    	cells = new Cell[rows+1][columns];
    	
    	for(int i=0;i<rows+1;i++)
    		for(int j=0;j<columns;j++){
    			cells[i][j] = new Cell(this);
    			}
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j].setFormula(temp[i][j].getFormula());
    }
    
    /**
     * This method adds a column to the far right of the spreadsheet.
     * @throws NullCellPointer 
     * @throws NumberFormatException 
     */
    public void insertColumn() throws NumberFormatException, NullCellPointer{
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++){
    			temp[i][j] = new Cell(this);
    			temp[i][j].setFormula(cells[i][j].getFormula());
    			}
    	
    	cells = new Cell[rows][columns+1];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns+1;j++){
    			cells[i][j] = new Cell(this);
    			}
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j].setFormula(temp[i][j].getFormula());
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
     * This method load data from a file and creates a spreadsheet according to the specification in the file.
     * 
     * This file should delimit cells with a semicolon and information contained within an individual cell by a comma.
     * The first two pieces of information should be the cell address, the third the cell formula.
     * 
     * ex: 0,0,1+0;0,1,1+1;
     * 
     * @param fileName The path and name of file to be loaded. eg: c:/.../textfile.txt
     * @throws FileNotFoundException
     * @throws NullCellPointer 
     * @throws NumberFormatException 
     */
    public void loadFromFile(String fileName) throws FileNotFoundException, NumberFormatException, NullCellPointer{
    	Scanner input = new Scanner(fileName);
    	
    	int rows=1;
    	
    	String row = input.next();
    	String[] data = row.split(";");
    	
    	int columns = data.length;
    	
    	while(input.hasNext()){
    		input.next();
    		rows++;
    	}
    	
    	cells = new Cell[rows][columns];
    	
    	input.close();
    	input = new Scanner(fileName);
    	
    	while(input.hasNext()){
    		row = input.next();
    		data = row.split(";");
    		
    		for(int i=0;i<data.length;i++){
    			String[] cellData = data[i].split(",");
    			Cell cell = getCell(Integer.parseInt(cellData[0]),Integer.parseInt(cellData[1]));
    			cell.setFormula(cellData[2]);
    		}
    	}
    	
    	input.close();
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
    public void displayTable() throws NullCellPointer{
    	char c = 'A';
    	String header = "";
    	String[] displayedGrid = new String[cells[0].length];
    	
    	for (int i = 0; i < cells.length; i++){
    		header += "\t\t" + (char)(c + i) ;
    		for (int j = 0; j < cells[i].length; j++){
    			if (i == 0){
    				displayedGrid[j] = (j+1) + "\t\t";
    			}
    			displayedGrid[j] += cells[i][j].getValue() + "\t\t";
    		}
    	}
    	
    	System.out.println(header);
    	for (int j = 0; j < displayedGrid.length; j++){
    		System.out.println(displayedGrid[j]);
    	}
    }
    
    public class NullCellPointer extends Exception {
    	
        public NullCellPointer(){
            super("Cell was not found.");
    }
    }
}
