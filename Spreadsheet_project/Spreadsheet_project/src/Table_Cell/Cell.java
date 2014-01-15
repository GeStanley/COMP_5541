package Table_Cell;

public class Cell {
	public Cell() {
		this.setValue(0.0);
		this.setName("A1");
		this.formula="0";
		this.format="";
	}
	private String formula;
	private String format;
	private double value;
	private String name;
	public void set_formula(String readInData){
		this.formula=readInData;
		}
	public void set_format(String readInData){
		this.format=readInData;
		}
	public String get_formula(){
		return this.formula;
		}
	public String get_format(){
		return this.format;
		}
	public double getValue() {
		return this.value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
