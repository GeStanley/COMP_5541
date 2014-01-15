package Table_Cell;

public class Table {
	public Table(){
		this.row=1;
		this.column=1;
		Cell mycell[][]= new Cell[11][10];
	}
    private int row;
    private int column;
    public void set_cell(int row, int column, String format, String formula ){
    	mycell[row][column].format=format;
    	mycell[row][column].formula=formula;
    	
    }
    
    
}
