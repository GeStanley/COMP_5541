package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.datatype.DatatypeConfigurationException;

public class Example extends JComponent implements ActionListener {

	//JTextField txtNodeName;
	public JComboBox<String> cbbNodeName;
	public JComboBox<String>cbbBidType;


	public Example() {
		this.setLayout(new BorderLayout());
		
		JPanel jph = new JPanel();
		jph.add(new JLabel("Node Name: "));
		String[] nodeNames = {"ABC","DEF","XYZ"};
		this.cbbNodeName = new JComboBox<String>(nodeNames);
		jph.add(this.cbbNodeName);
		
		String[] bidTypes = {"BID(DEC)","FIXED","OFFER(INC)","PRICE_SENSITIVE"};
		this.cbbBidType = new JComboBox<String>(bidTypes);
		this.cbbBidType.setSelectedIndex(0);
		jph.add(new JLabel("Bid Type: "));
		jph.add(this.cbbBidType);

		jph.add(new JLabel(" Date: "));

		this.add(jph, BorderLayout.NORTH);

		//this.bhc.add( new BidHourlyComp());
		//this.bhc.add( new BidHourlyComp());
		JPanel jpc=new JPanel();
		jpc.setLayout(new GridLayout(1, 3));
		this.add(jpc, BorderLayout.CENTER);
	}

	public void setData() throws DatatypeConfigurationException {

		
	}
	
	public void setGUIFields(String[] inputFile) throws DatatypeConfigurationException {

	}
	/**
	 * @param args
	 */
	static public void main(String[] args)
			throws DatatypeConfigurationException {
		JFrame jf = new JFrame("hourly bid");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Example bh = new Example();
		jf.add(bh);
		jf.pack();
		jf.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}