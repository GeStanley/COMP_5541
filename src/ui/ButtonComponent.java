package ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonComponent extends JPanel {

	public ButtonComponent() {
		
		int ICON_HEIGHT = 20;
		int ICON_WIDTH = 20;
				
		ImageIcon saveIco = new ImageIcon( "res/img/save.png" );
		Image saveImg = saveIco.getImage() ;  
		Image resizedSaveImg = saveImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		saveIco = new ImageIcon( resizedSaveImg );
		JButton save = new JButton( saveIco );
		 
		ImageIcon saveAsIco = new ImageIcon( "res/img/save_as.png" );
		Image saveAsImg = saveAsIco.getImage() ;  
		Image resizedSaveAsImg = saveAsImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		saveAsIco = new ImageIcon( resizedSaveAsImg );
		JButton saveAs = new JButton( saveAsIco );
		
		ImageIcon loadIco = new ImageIcon( "res/img/load.png" );
		Image loadImg = loadIco.getImage() ;  
		Image resizedLoadImg = loadImg.getScaledInstance( ICON_WIDTH, ICON_HEIGHT,  java.awt.Image.SCALE_SMOOTH ) ;  
		loadIco = new ImageIcon( resizedLoadImg );
		JButton load = new JButton( loadIco );
		
		add(save);
		add(saveAs);
		add(load);
		
		
	}
	
}
