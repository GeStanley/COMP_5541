package ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class contains the menu items of the program, such as Create New, Save, Save As, and Load
 * 
 * @author mike
 *
 */
public class ButtonComponent extends JPanel implements ActionListener{

	private JButton save;
	private JButton saveAs;
	private JButton load;
	private JButton createNew;
	
	private String filename;
	private String directory;
	private String fullSaveLocation;
	
	/**
	 * Constructor for the Button component.
	 */
	public ButtonComponent() {
		
		int ICON_HEIGHT = 20;
		int ICON_WIDTH = 20;
		
		ImageIcon newIco = new ImageIcon( "res/img/create_new.png" );
		Image newImg = newIco.getImage() ;  
		Image resizedNewImg = newImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		newIco = new ImageIcon( resizedNewImg );
		createNew = new JButton( newIco );
		createNew.addActionListener( this );
				
		ImageIcon saveIco = new ImageIcon( "res/img/save.png" );
		Image saveImg = saveIco.getImage() ;  
		Image resizedSaveImg = saveImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		saveIco = new ImageIcon( resizedSaveImg );
		save = new JButton( saveIco );
		save.addActionListener( this );
		 
		ImageIcon saveAsIco = new ImageIcon( "res/img/save_as.png" );
		Image saveAsImg = saveAsIco.getImage() ;  
		Image resizedSaveAsImg = saveAsImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		saveAsIco = new ImageIcon( resizedSaveAsImg );
		saveAs = new JButton( saveAsIco );
		saveAs.addActionListener( this );
		
		ImageIcon loadIco = new ImageIcon( "res/img/load.png" );
		Image loadImg = loadIco.getImage() ;  
		Image resizedLoadImg = loadImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		loadIco = new ImageIcon( resizedLoadImg );
		load = new JButton( loadIco );
		load.addActionListener( this );
		
		add(createNew);
		add(save);
		add(saveAs);
		add(load);
	}
	
	/**
	 * Handles the button click actions. Fires a property change.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == save) {
			if ( fullSaveLocation == null ){
				if ( getFileLocation("Save") != null ){
					this.firePropertyChange("save", false, fullSaveLocation);
				}
			} else {
				this.firePropertyChange("save", false, fullSaveLocation);
			}
			
		} else if (src == saveAs) {
			if ( getFileLocation("Save") != null ){
				this.firePropertyChange("save", false, fullSaveLocation);
			}
			
		} else if (src == load) {
			if ( getFileLocation("Open") != null ){
				this.firePropertyChange("load", false, fullSaveLocation);
			}
		} else if (src == createNew) {
			fullSaveLocation = null;
			this.firePropertyChange("createNew", false, null);
		}
		
	}
	
	/**
	 * Creates a dialog where one can select a file.
	 * 
	 * @return returns the location of a selected file.
	 */
	public String getFileLocation(String saveOrOpen) {
		JFileChooser JFC = new JFileChooser();
		int action = 0;
		
		FileFilter CSV = new FileNameExtensionFilter("CSV (.csv)", "csv");
		JFC.addChoosableFileFilter(CSV);
		JFC.setFileFilter(CSV);
		
		
		if (saveOrOpen.equals("Save"))
			action = JFC.showSaveDialog(null);
		else if (saveOrOpen.equals("Open"))
			action = JFC.showOpenDialog(null);
		
		if ( action == JFileChooser.APPROVE_OPTION ){
			filename  = JFC.getSelectedFile().getName();
			directory = JFC.getCurrentDirectory().toString();
			
			fullSaveLocation = directory + "/";
			
			if ( filename.endsWith(".csv") ) {
				fullSaveLocation = fullSaveLocation + filename;
			} else {
				fullSaveLocation = fullSaveLocation + filename + ".csv";
			}
			
			System.out.println( fullSaveLocation );
			return fullSaveLocation;
			
		} else if ( action == JFileChooser.CANCEL_OPTION ) {
			return null;
		}
		return null;	
	}
}
