package structure;

public class Table {
	
	private int row;
    private int column;
    private Cell[][]myTable;
	
    
    public Table(){
		this.row=1;
		this.column=1;
		for(int rowCounter=0;rowCounter<11;rowCounter++){
			for(int columnCounter=0;columnCounter<10;columnCounter++)
				myTable[rowCounter][columnCounter]=new Cell();
		}
	}
    
    
    public void set_cell(int row, int column, String format, String formula ){
    	myTable[row][column].set_format(format);
    	myTable[row][column].set_formula(formula);
    }
    public void set_cell(int row,int column,double myValue){
    	myTable[row][column].setValue(myValue);
    }
    public Cell get_cell(int row, int column){
    	return myTable[row][column];
    }
    
}
