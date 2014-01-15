package Table_Cell;

public class Table {
	public Table(){
		this.row=1;
		this.column=1;
		Cell myTable[][]= new Cell[11][10];
	}
    private int row;
    private int column;
    public void set_cell(int row, int column, String format, String formula ){
    	myTable[row][column].format=format;
    	myTable[row][column].formula=formula;
    }
    public Cell get_cell(int row, int column){
    	return myTable[row][column];
    }
    
}
