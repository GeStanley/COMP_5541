package structure;

public class Cell {
	
	private String test;
	private String formula;
	private String format;
	private double value;
	private String name;
	public Cell() {
		this.setValue(0.0);
		this.formula="0";
		this.format="";
	}
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
	public void setName(int row,int column) {
		StringBuilder sb=new StringBuilder();
		switch(column){
		case 0:
			sb.append("A");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 1:
			sb.append("B");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 2:
			sb.append("C");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 3:
			sb.append("D");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 4:
			sb.append("E");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 5:
			sb.append("F");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 6:
			sb.append("G");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 7:
			sb.append("H");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 8:
			sb.append("I");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 9:
			sb.append("J");
			sb.append(row);
			this.name=sb.toString();
			break;
		case 10:
			sb.append("K");
			sb.append(row);
			this.name=sb.toString();
			break;
		}
	}
}
