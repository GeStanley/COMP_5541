package structure;

import java.io.FileInputStream;
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
 *
 */
public class Table {
	
    private Cell[][] cells;
	
    
    /**
     * This is the constructor method. It building a 1 by 1 spreadsheet.
     */
    public Table(){ 
    	this.cells = new Cell[0][0]; 
    }
    
    /**
     * This method will allow you to change the size of the spreadsheet. It will keep the data that has been input into the cells
     * so long as the size of the array is increasing. If you are decreasing the size of the array the information that was outside
     * the dimensions passed to the array will be lost.
     * 
     * @param rowQty A integer parameter that determines how many rows will be in the array.
     * @param columnQty A integer parameter that determines how many columns will be in the array.
     */
    public void defineArraySize(int rowQty, int columnQty){
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			temp[i][j]=cells[i][j];
    	
    	cells = new Cell[rowQty][columnQty];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j]=temp[i][j];
    }
    
    /**
     * This method adds a row to the bottom of the spreadsheet.
     */
    public void insertRow(){
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			temp[i][j]=cells[i][j];
    	
    	cells = new Cell[rows+1][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j]=temp[i][j];
    }
    
    /**
     * This method adds a column to the far right of the spreadsheet.
     */
    public void insertColumn(){
    	int rows = cells.length;
    	int columns = cells[0].length;
    	
    	Cell[][] temp = new Cell[rows][columns];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			temp[i][j]=cells[i][j];
    	
    	cells = new Cell[rows][columns+1];
    	
    	for(int i=0;i<rows;i++)
    		for(int j=0;j<columns;j++)
    			cells[i][j]=temp[i][j];
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
     * The first two pieces of information should be the cell address, the third the cells value and the fourth the cell
     * formula.
     * 
     * ex: 0,0,1.0,1+0;0,1,2.0,1+1;
     * 
     * @param fileName The path and name of file to be loaded. eg: c:/.../textfile.txt
     * @throws FileNotFoundException
     */
    public void loadFromFile(String fileName) throws FileNotFoundException{
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
    			cell.setValue(cellData[2]);
    			cell.setFormula(cellData[3]);
    		}
    	}
    	
    	input.close();
    }
    
    /** 
     * This method loads data contained within the spreadsheet to a text file.
     * 
     * This file will delimit cells with a semicolon and information contained within an individual cell by a comma.
     * The first two pieces of information should be the cell address, the third the cells value and the fourth the cell
     * formula.
     * 
     * ex: 0,0,1.0,1+0;0,1,2.0,1+1;
     * 
     * @param fileName The path and name of file to be saved. eg: c:/.../textfile.txt
     */
    public void saveToFile(String fileName){
    	PrintWriter output = new PrintWriter(fileName);
    	
    	for(int i=0;i<cells.length;i++){
    		String data;
    		for(int j=0;j<cells[i].length;j++)
    			data = data + i + "," + j + "," + getCell(i,j).getValue() + "," + getCell(i,j).getFormula() + ";";
    		
    		output.println(data);
    		data=null;
    	}

    }
    
    public void displayTable(){
    	
    }
    
}
