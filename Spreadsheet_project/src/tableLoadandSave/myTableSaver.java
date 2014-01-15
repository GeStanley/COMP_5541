package tableLoadandSave;

import java.io.*;

public class myTableSaver {
	public myTableSaver() throws IOException {
		// Check whether the file exist.

		File txtFile = new File("outputOfTable.txt");
		/**
		 * Check whether the file exist.If not, create one.
		 */
		if (txtFile.exists()){
			System.out
					.println("File exist! The new content will be appended to the end of the file ");
			//Allow each time the content to be written at the end of file 
			txtFileWriter=new FileWriter(txtFile,true);
			//Want to use the append function
			strWriterBuffer=new BufferedWriter(txtFileWriter);
		}
		else {
			if (txtFile.createNewFile()){
				System.out.println("File successfully created");
				//Allow each time the content to be written at the end of file 
				txtFileWriter=new FileWriter(txtFile,true);
				//Want to use the append function
				strWriterBuffer=new BufferedWriter(txtFileWriter);
			}
			else{
				System.out.println("File created Failed");
				return;
				}
			}
		}
		/**
		 * Write content to the file
		 * here need to transfer the table object to a string str
		 * @param str
		 */
		public void wirteTXT(String str) {
				try {
					strWriterBuffer.append(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("FileWriter ERROR");
					e.printStackTrace();
				}
			}
		public void closeMyFile() throws IOException{
			strWriterBuffer.close();
		}
		private FileOutputStream outputFile;
		private File txtFile;
		private FileWriter txtFileWriter;
		private BufferedWriter strWriterBuffer;
}
