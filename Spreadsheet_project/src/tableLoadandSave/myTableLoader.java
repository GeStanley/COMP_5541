package tableLoadandSave;

import java.io.*;


import Table_Cell.Table;

public class myTableLoader {
	private BufferedReader strContentReader;
	public String tempStr;
	private FileReader myFileReader;
	private Table MyTable;
	private StringBuilder sb;
	//private String myformat;
	//private String myformula;
	
	public myTableLoader() throws IOException{
		this.tempStr=null;
		this.strContentReader=null;
		this.myFileReader=new FileReader("outputOfTable.txt");
		this.MyTable=new Table();
		this.sb=new StringBuilder();
		
	}
	public void txtFileReader(){
		
	}
	public void fileReader() throws IOException {
		// Initialize the bufferReader ,load the file reader's instance
		this.strContentReader = new BufferedReader(this.myFileReader);
	   int commaCounter=0;
	   int mycolumn=0;
	   int myrow=0;
	   double myValue=0;
	   String myformat=new String();
	   String myformula=new String();
	   
		// Check whether the file is empty,if not, read string line by line
		while ((tempStr = strContentReader.readLine()) != null) {
			// delete the head and tail whitespaces which were read by readline function
			tempStr = tempStr.trim();
			//Read-in string checker
			//
			for(int tempCounter=0;tempCounter<tempStr.length();tempCounter++){
				if(tempStr.charAt(tempCounter)!=','){
					sb.append(tempStr.charAt(tempCounter));
				}
				else
					switch(commaCounter){
					case 0:
						switch(sb.substring(0)){
						case "A":
							mycolumn=0;
						    break;
						case "B":
							mycolumn=1;
						    break;
						case "C":
							mycolumn=2;
						    break;
						case "D":
							mycolumn=3;
						    break;
						case "E":
							mycolumn=4;
						    break;
						case "F":
							mycolumn=5;
						    break;
						case "G":
							mycolumn=6;
						    break;
						case "H":
							mycolumn=7;
						    break;
						case "I":
							mycolumn=8;
						    break;
						case "J":
							mycolumn=9;
						    break;
						case "K":
							mycolumn=10;
						    break;
						}
						myrow=Integer.valueOf(sb.substring(1, sb.length()-1)).intValue();
						break;
					case 1:
						myValue=Double.valueOf(sb.substring(0,sb.length()-1)).doubleValue();
						MyTable.set_cell(myrow, mycolumn, myValue);
						break;
					case 2:
						myformat=sb.substring(0,sb.length()-1);
						break;
					case 3:
						myformula=sb.substring(0,sb.length()-1);
					    MyTable.set_cell(myrow, mycolumn, myformat, myformula);
						break;
					}
				sb.delete(0, sb.length()-1);
			}
			//
			}
		this.strContentReader.close();
		}
	/**
	 * Parameters
	 */

}
