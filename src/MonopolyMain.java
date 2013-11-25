import gui.MMainFrame;
import java.awt.EventQueue;


public class MonopolyMain 
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MMainFrame frame = new MMainFrame();
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		} );
	}
}
