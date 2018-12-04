package view;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.List;

public class AIButton extends Button {
ActionListener action;
boolean unavailabele;
List<Button> aiButtons;
private Drawing unavailablePick;

	public AIButton(String fileName, ActionListener al, List<Button> aiButtons) {
		super(fileName, al);
		action = al;
		this.aiButtons = aiButtons;
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
		int i=0;
		for (Button b : aiButtons) {
			b.setPosition(this.position.x - 170 * i -180, this.position.y);
			b.paint(g);
			i++;
		}
		
	
		
	}
	public List<Button> getAiButtons(){
		
		return aiButtons;
		
		
	}

}
