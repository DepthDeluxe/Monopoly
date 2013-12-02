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

import monopoly.tiles.ITile;
import monopoly.tiles.Property;
import monopoly.tiles.TileType;

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
	
	private ITile property;
	
	public MPropertiesPanel(ITile p)
	{
		this.setLayout(new GridLayout(13, 1, 0, 0));
		
		name = createLabel("");
		name.setFont(boldFont);
		
		price = createLabel("");
		price.setFont(boldFont);
		
		mortgageVal = createLabel("");
		mortgageVal.setFont(boldFont);
		
		owner = createLabel("");
		owner.setFont(boldFont);
		
		isMortgaged = createLabel("");
		isMortgaged.setFont(boldFont);
		
		rent = createLabel("");
		rent.setFont(boldFont);
		
		action = new JButton("Buy!");
		action.setSize(50, 30);
		
		updateProperty(p);	
	}
	
	public void updateProperty(ITile p)
	{
		this.property = p;
		updatePanel();
	}
	
	private void updatePanel()
	{
		this.removeAll();
		TileType type = this.property.getTileType();
		if(type == TileType.TAX)
		{
			this.setLayout(new GridLayout(1,1,0,0));
			JLabel temp = createLabel("Pay Tax!");
			this.add(temp);
		}
		else if(type == TileType.CHANCE || type == TileType.COMMUNITY_CHEST)
		{
			this.setLayout(new GridLayout(1,1,0,0));
			JLabel temp = createLabel("See your card!");
			this.add(temp);
		}
		else if(type == TileType.PROPERTY || type == TileType.UTILITY || type == TileType.RAILROAD)
		{
			this.setLayout(new GridLayout(13, 1, 0, 0));
			this.rebuild((Property) this.property);
		}
		else if(type == TileType.GO_TO_JAIL || type == TileType.JAIL)
		{
			this.setLayout(new GridLayout(1, 1, 0, 0));
			JLabel temp = createLabel("Jail time!");
			this.add(temp);
		}
		else if(type == TileType.GO || type == TileType.FREE_PARKING)
		{
			this.setLayout(new GridLayout(1, 1, 0, 0));
			JLabel temp = createLabel("Free money!");
			this.add(temp);
		}
	}
	
	public JLabel createLabel(String text)
	{
		JLabel temp = new JLabel(text);
		temp.setHorizontalAlignment(SwingConstants.CENTER);
		return temp;
	}
	
	private void rebuild(Property p)
	{
		JLabel nameLabel = createLabel("Name:");
		name.setText(p.getName());
		
		JLabel priceLabel = createLabel("Price");
		price.setText(Double.toString(p.getPrice()));
		
		JLabel mortgageValLbl = createLabel("Mortgaged Value:");
		mortgageVal.setText(Double.toString(p.getMortgagedValue()));
		
		JLabel ownerLbl = createLabel("Owned by:");
		if(p.isOwned()) { owner.setText(p.getOwner().getName()); }
		else { owner.setText("Not Owned!"); }
		
		JLabel mortgageLbl = createLabel("Is Mortgaged?");	
		isMortgaged.setText(Boolean.toString(p.isMortgaged()));
		
		JLabel rentLbl = createLabel("Rent:");
		rent.setText(Double.toString(p.getRent()));
		
		if(p.isOwned()) { action.setEnabled(false); }
		else { action.setEnabled(true); }
		
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
	}
}

