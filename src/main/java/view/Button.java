package view;

import java.awt.Graphics;
import java.awt.event.ActionListener;

public class Button extends Drawing {
ActionListener action;
boolean unavailabele;
private Drawing unavailablePick;

	public Button(String fileName, ActionListener al) {
		super(fileName);
		action = al;
		unavailablePick = new Drawing("pics/nope.png");
	}
	
	public void click(){
		
		if(unavailabele==false) {
			
			action.actionPerformed(null);
			
		}
		
		
		
	}
	@Override
	
	public void paint(Graphics g){
		super.paint(g);
		
		if(unavailabele == true){
			
			unavailablePick.setPosition(this.position.x, this.position.y );
			unavailablePick.paint(g);
			
			
			
		}
		
	
		
	}
	

}
