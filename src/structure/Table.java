package structure;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Table { 
	
    private Cell[][] cells;
	
    
    public Table(){
    	this.cells = new Cell[0][0];
	}
    
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
    
    public int getRowIndex(char row){
    	return ((int) row) - ((int) 'A');
    }
    
    public Cell getCell(int row, char column) throws NullCellPointer{
    	return cells[row][getRowIndex(column)];
    }
    
    public Cell getCell(int row, int column) throws NullCellPointer{
    	return cells[row][column];
    }
    
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
    
}
