package tableLoadandSave;

import java.io.*;


import Table_Cell.Table;

public class myTableLoader {
	public myTableLoader(Reader importFile) throws IOException{
		this.tempStr=null;
		this.strContentReader=null;
		this.reader=importFile;
		this.Table=new Table();
		
	}
	public void txtFileReader(){
		
	}
	public void fileReader() throws IOException {
		// Initialize the bufferReader ,load the file reader's instance
		this.strContentReader = new BufferedReader(this.reader);
	
		// Check whether the file is empty,if not, read string line by line
		while ((tempStr = strContentReader.readLine()) != null) {
			// delete the head and tail whitespaces which were read by readline function
			tempStr = tempStr.trim();
			//Read-in string checker
			//
			.......reader
			//
			}
		this.tableLoadandSave.myTableSaver.closeMyFile();
		}
	/**
	 * Parameters
	 */
	private BufferedReader strContentReader;
	public String tempStr;
	private Reader reader;
	private Table Table;
}
