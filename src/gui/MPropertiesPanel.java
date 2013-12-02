/**
 * CSCI 205 - Software Design and Engineering
 * Name: Arjuna Kankipati
 * Semester: Fall 2013
 * Work: FinalProject
 * Created: Nov 15, 2013, 10:13:41 AM
 */
package gui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import monopoly.tiles.Property;

public class MPropertiesPanel extends JPanel 
{	
	JLabel name;
	JLabel price;
	JLabel mortgageVal;
	JLabel owner;
	JLabel isMortgaged;
	JLabel rent;
	JButton action;
	
	Font boldFont = new Font("Tahoma", Font.BOLD, 11);
	
	private Property property;
	
	public MPropertiesPanel(Property p)
	{
		this.setLayout(new GridLayout(13, 1, 0, 0));
		
		JLabel nameLabel = createLabel("Name:");
		
		name = createLabel(p.getName());
		name.setFont(boldFont);
		
		JLabel priceLabel = createLabel("Price");
		
		price = createLabel("");
		price.setFont(boldFont);
		
		JLabel mortgageValLbl = createLabel("Mortgaged Value:");
		
		mortgageVal = createLabel("");
		mortgageVal.setFont(boldFont);
		
		JLabel ownerLbl = createLabel("Owned by:");
		
		owner = createLabel("");
		owner.setFont(boldFont);
		
		JLabel mortgageLbl = createLabel("Is Mortgaged?");
		
		isMortgaged = createLabel("");
		isMortgaged.setFont(boldFont);
		
		JLabel rentLbl = createLabel("Rent:");
		
		rent = createLabel("");
		rent.setFont(boldFont);
		
		action = new JButton("Buy!");
		action.setSize(50, 30);
		
		this.add(nameLabel);
		this.add(name);
		this.add(priceLabel);
		this.add(price);
		this.add(mortgageValLbl);
		this.add(mortgageVal);
		this.add(ownerLbl);
		this.add(owner);
		this.add(mortgageLbl);
		this.add(isMortgaged);
		this.add(rentLbl);
		this.add(rent);
		this.add(action);
		
		updateProperty(p);
	}
	
	public void updateProperty(Property p)
	{
		this.property = p;
		updatePanel();
	}
	
	private void updatePanel()
	{
		this.name.setText(this.property.getName());
		this.price.setText(Double.toString(this.property.getPrice()));
		this.mortgageVal.setText(Double.toString(this.property.getMortgagedValue()));
		if(this.property.isOwned()) { this.owner.setText("Owned!"); }
		else { this.owner.setText("Not owned"); }
		this.isMortgaged.setText(Boolean.toString(this.property.isMortgaged()));
		this.rent.setText(Double.toString(this.property.getRent()));
		
		if(this.property.isOwned()) { action.setEnabled(false); }
		else { action.setEnabled(true); }
	}
	
	public JLabel createLabel(String text)
	{
		JLabel temp = new JLabel(text);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		return temp;
	}
}

