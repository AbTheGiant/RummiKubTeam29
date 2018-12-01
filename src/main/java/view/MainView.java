package view;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainView extends JFrame {

	JPanel currentView;
	public void init(JPanel initialView) {
	this.getContentPane().removeAll();	
		
		currentView = initialView;
		this.setSize(1200, 850);
		this.setLayout(new BorderLayout());
	    this.add(currentView, BorderLayout.CENTER);
	    currentView.setFocusable(true);
	    revalidate();
	    repaint();
	    currentView.repaint();
	}
	
	
	

}
