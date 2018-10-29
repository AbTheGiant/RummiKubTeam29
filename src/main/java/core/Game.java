package core;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
	private ArrayList<Meld> melds=new ArrayList<Meld>();
	private Player []players=new Player[4];
	private Deck deck=new Deck(); 
	
	private boolean stopGame;
	Game()
	{
		 players[3]=new Player(new HumanStrategy());
		 players[3].setName("human");
		 players[0]=new Player(new AIStrategy3());
		 players[0].setName("p3");		 
		 players[1]=new Player(new AIStrategy2());
		 players[1].setName("p2");
		 players[2]=new Player(new AIStrategy1());
		 players[2].setName("p1");
		 deck.shuffle();
		 for(int i=0;i<4;i++)
		 {
			 this.addObserver(players[i]);
			 for(int i1=0;i1<14;i1++)
			 {
				 players[i].addCard(deck.deal());		 
			 }
		 }		 
	}
	
	Game(Deck deck,Player []players)
	{
		 this.players=players;
		 this.deck=deck; 
		 for(int i=0;i<4;i++)
		 {
			 this.addObserver(players[i]);			 
		 }		 
	}
	
	
	public  void addMeld(Meld meld)
	{
		melds.add(meld);
	}
	
	
	public boolean validTable()
	{
		for(Meld meld:melds)
		{
			if(!meld.isValid())
				return false;
		}
		return true;
	}
	
	public  void play()
	{
		while(!stopGame)
		{
			setChanged();
			this.notifyObservers(this);
		}
		System.out.println(this);
		for(int i=0;i<4;i++)
		{
			if(players[i].getSize()==0)
			{
				System.out.println("Player "+players[i].getName()+" win");
			}				
		}
	
	}
	@Override
	public String toString() {
		String result="";
		for(Meld meld:melds)
		{
			result+=meld.toString()+"\n";
		}
		return result;
	}
	
	public ArrayList<Meld> getMelds() {
		return melds;
	}

	public void setMelds(ArrayList<Meld> melds) {
		this.melds = melds;
	}

	public static void main(String []args)
	{
		Game game=new Game();
		game.play();
	}

	public Deck getDeck() {
		return deck;
	}

	public boolean isStopGame() {
		return stopGame;
	}

	public void setStopGame(boolean stopGame) {
		this.stopGame = stopGame;
	}

	public Player[] getPlayers() {
		return players;
	}	
	
	
}
