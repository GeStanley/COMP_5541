package tableLoadandSave;

import java.io.*;

//import Table_Cell.Cell;
import Table_Cell.Table;

public class myTableSaver {
	public myTableSaver() throws IOException {
		// Check whether the file exist.

		File txtFile = new File("outputOfTable.txt");
		/**
		 * Check whether the file exist.If not, create one.
		 */
		if (txtFile.exists()) {
			System.out
					.println("File exist! The new content will be appended to the end of the file ");
			// Allow each time the content to be written at the end of file
			txtFileWriter = new FileWriter(txtFile, false);
			// Want to use the append function
			strWriterBuffer = new BufferedWriter(txtFileWriter);
		} else {
			if (txtFile.createNewFile()) {
				System.out.println("File successfully created");
				txtFileWriter = new FileWriter(txtFile, false);
				// Want to use the append function
				strWriterBuffer = new BufferedWriter(txtFileWriter);
			} else {
				System.out.println("File created Failed");
				return;
			}
		}
	}

	/**
	 * Write content to the file here need to transfer the table object to a
	 * string str
	 * 
	 * @param str
	 */
	public void wirteTXT(Table myTable) {
				try {
					for(int rowCounter=0;rowCounter<11;rowCounter++){
						for(int columnCounter=0;columnCounter<10;columnCounter++){
							strWriterBuffer.append(myTable.get_cell(rowCounter, columnCounter).getName()+",");
							strWriterBuffer.append(myTable.get_cell(rowCounter, columnCounter).getValue()+",");
							strWriterBuffer.append(myTable.get_cell(rowCounter, columnCounter).get_format()+",");
							strWriterBuffer.append(myTable.get_cell(rowCounter, columnCounter).get_formula()+"\n");
						}
					           
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("FileWriter ERROR");
					e.printStackTrace();
				}
			}

	public void closeMyFile() throws IOException {
		strWriterBuffer.close();
	}

	private FileOutputStream outputFile;
	private File txtFile;
	private FileWriter txtFileWriter;
	private BufferedWriter strWriterBuffer;
}
