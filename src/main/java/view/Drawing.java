package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.applet.*;


public class Drawing {
	Image img;
	static GameView parent;
	Point size;
	Point cellsize;
	Point currcell;
	Point position;

	String fileName;
	private boolean hovered;
	protected boolean selected;

	public Drawing(String fileName) {

		size = new Point();
		cellsize = new Point(0, 0);
		currcell = new Point(0, 0);

		position = new Point(0, 0);

		loadImage(fileName);

	}

	Drawing(String fileName, int posx, int posy, int currcellx, int currcelly, int cellwidth, int cellheight) {
		size = new Point();
		cellsize = new Point(cellwidth, cellheight);
		currcell = new Point(currcellx, currcelly);

		position = new Point(0, 0);

		loadImage(fileName);
	}

	public static void setApplet(GameView a) {
		parent = a;
	}

	public void setPosition(int x, int y) {

		position.x = x;
		position.y = y;

	}

	public void loadImage(String fileName) {
		File file = new File(fileName);
		try {
			img = ImageIO.read(file);
			while (img.getWidth(null) == -1 || img.getHeight(null) == -1)
				;
			size.x = img.getWidth(null);
			size.y = img.getHeight(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		int imgLeft;
		int imgTop;
		int imgRight;
		int imgBottom;
		int picLeft;
		int picTop;
		int picRight;
		int picBottom;

		imgLeft = position.x;
		imgTop = position.y;
		imgRight = size.x;
		imgBottom = size.y;
		picLeft = currcell.x;
		picTop = currcell.y;
		picRight = size.x;
		picBottom = size.y;

		if (hovered == true) {
			g.setColor(Color.blue);
			int width = 2;
			g.fillRect(imgLeft - width, imgTop - width, size.x + width * 2, size.y + width * 2);
		}
		if (selected == true) {
			g.setColor(Color.red);
			int width = 2;
			g.fillRect(imgLeft - width, imgTop - width, size.x + width * 2, size.y + width * 2);
		}
		g.drawImage(img, imgLeft, imgTop, null);
	}

	public boolean collide(Drawing other) {
		int thisLeft = position.x;
		int thisRight = position.x + cellsize.x;
		int thisTop = position.y;
		int thisBottom = position.y + cellsize.y;

		int otherLeft = other.position.x;
		int otherRight = other.position.x + other.cellsize.x;
		int otherTop = other.position.y;
		int otherBottom = other.position.y + other.cellsize.y;

		if (thisRight > otherLeft && thisLeft < otherRight)
			if (thisBottom > otherTop && thisTop < otherBottom)
				return true;

		return false;
	}

	public boolean mouseCollide(int posx, int posy) {
		if (posx > position.x && posx < position.x + size.x && posy > position.y && posy < position.y + size.y) {
			return true;
		}
		return false;
	}

	public void triggerHovered() {
		hovered = true;

	}

	public void notHovered() {
		hovered = false;

	}

	public void selected() {
		selected = !selected;

	}

	public boolean isSelected() {

		return selected;

	}

	public void notSelected() {
		selected = false;

	}

}
