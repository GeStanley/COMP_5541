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

public class ButtonComponent extends JPanel implements ActionListener{

	private JButton save;
	private JButton saveAs;
	private JButton load;
	
	private String filename;
	private String directory;
	
	public ButtonComponent() {
		
		int ICON_HEIGHT = 20;
		int ICON_WIDTH = 20;
				
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
		
		add(save);
		add(saveAs);
		add(load);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		if (src == save) {
			JFileChooser saveJFC = new JFileChooser();
			
			FileFilter CSV = new FileNameExtensionFilter("CSV (.csv)", "csv");
			saveJFC.addChoosableFileFilter(CSV);
			saveJFC.setFileFilter(CSV);
			
			int action = saveJFC.showSaveDialog(null);
			
			if ( action == JFileChooser.APPROVE_OPTION ){
				filename  = saveJFC.getSelectedFile().getName();
				directory = saveJFC.getCurrentDirectory().toString();
				
				
				
				System.out.println( directory + " " + filename );
				
				this.firePropertyChange("save", false, null);
			} else if ( action == JFileChooser.CANCEL_OPTION ) {
				System.out.println("Cancel");
			}
			
			
			
			
			
			
		} else if (src == saveAs) {
			
			
			this.firePropertyChange("saveAs", false, null);
		} else if (src == load) {
			
			
			this.firePropertyChange("load", false, null);
		}
		
	}
}
