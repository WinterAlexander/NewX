package me.winter.newx.adventure;

import me.winter.newx.adventure.physics.Location;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;

public class Drawer
{
	private AdventureState state;
	private Graphics g;
	
	public Drawer(AdventureState state)
	{
		this.state = state;
		this.g = null;
	}
	
	public void setGraphics(Graphics g)
	{
		this.g = g;
	}
	
	public void drawArc(double posx, double posy, double width, double height, double start, double end)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y , w, h))
			if(this.g != null)
				this.g.drawArc(x, y, w, h, (int)start, (int)end);
	}

	public void drawRect(double posx, double posy, double width, double height)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.drawRect(x, y, w, h);
		
	}
	
	public void fillOval(double posx, double posy, double width, double height)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.fillOval(x, y, w, h);
	}
	
	public void fillArc(double posx, double posy, double width, double height, double start, double end)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.fillArc(x, y, w, h, (int)start, (int)end);
	}
	
	public void fillRoundRect(double posx, double posy, double width, double height, int angle)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.fillRoundRect(x, y, w, h, angle);
	}

	
	public void drawImage(Image image, double posx, double posy, double width, double height)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				image.draw(x, y, w, h);
	}

	public void drawLine(Location start, Location end)
	{
		drawLine(start.getX(), start.getY(), end.getX(), end.getY());
	}
	
	public void drawLine(double x, double y, double x2, double y2)
	{
		if(this.g == null)
			return;


		this.g.drawLine(this.getCamera().toXPixel(x), this.getCamera().toYPixel(y), this.getCamera().toXPixel(x2), this.getCamera().toYPixel(y2));
	}

	public void fillRect(double posx, double posy, double width, double height)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.fillRect(x, y, w, h);
	}
	
	public void fillAngledRect(double posx, double posy, double posx2, double posy2, double posx3, double posy3, double posx4, double posy4)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy);
		int x2 = this.getCamera().toXPixel(posx2);
		int y2 = this.getCamera().toYPixel(posy2);
		int x3 = this.getCamera().toXPixel(posx3);
		int y3 = this.getCamera().toYPixel(posy3);
		int x4 = this.getCamera().toXPixel(posx4);
		int y4 = this.getCamera().toYPixel(posy4);
		
		if(this.g != null)
			this.g.fill(new Polygon(new float[]{x, y, x2, y2, x3, y3, x4, y4}));
	}
	
	public void fillTriangle(double posx, double posy, double posx2, double posy2, double posx3, double posy3)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy);
		int x2 = this.getCamera().toXPixel(posx2);
		int y2 = this.getCamera().toYPixel(posy2);
		int x3 = this.getCamera().toXPixel(posx3);
		int y3 = this.getCamera().toYPixel(posy3);
		
		if(this.g != null)
			this.g.fill(new Polygon(new float[]{x, y, x2, y2, x3, y3}));
	}
	
	public void mark(double posx, double posy)
	{
		int x = this.getCamera().toXPixel(posx) - 10;
		int y = this.getCamera().toYPixel(posy) - 10;
		
		if(this.getCamera().isOnScreen(x, y, 20, 20))
			if(this.g != null)
				this.g.drawRect(x, y, 20, 20);
	}

	public void drawOval(double posx, double posy, double width, double height)
	{
		int x = this.getCamera().toXPixel(posx);
		int y = this.getCamera().toYPixel(posy + height);
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
			
		if(this.getCamera().isOnScreen(x, y, w, h))
			if(this.g != null)
				this.g.drawOval(x, y, w, h);
	}
	
	public void drawString(double posx, double posy, String string)
	{
		if(this.g != null)
		{
			int x = this.getCamera().toXPixel(posx);
			int y = this.getCamera().toYPixel(posy) - this.g.getFont().getHeight(string);
			this.g.drawString(string, x, y);
		}
	}
	
	public void drawTiles()
	{
		if(this.g != null)
		{
			double minX = (int)this.getCamera().toXReal(0) - 1;
			double minY = (int)this.getCamera().toYReal(this.getCamera().getScreenHeight()) - 1;
			
			double maxX = (int)this.getCamera().toXReal(this.getCamera().getScreenWidth()) + 1;
			double maxY = (int)this.getCamera().toYReal(0) + 1;
			
			for(double x = minX; x <= maxX; x++)
			{
				this.drawLine(x, minY, x, maxY);
			}
			
			for(double y = minY; y <= maxY; y++)
			{
				this.drawLine(minX, y, maxX, y);
			}
		}
	}
	
	public void drawMosaic(Image image, double posx, double posy, double width, double height, double tileWidth, double tileHeight)
	{
		if(this.g != null)
		{
			int x = this.getCamera().toXPixel(posx);
			int y = this.getCamera().toYPixel(posy + height);
			
			int tw = this.getCamera().toPixel(tileWidth);
			int th = this.getCamera().toPixel(tileHeight);
			
			for(double relX = 0; relX < width; relX+= tileWidth)
			{
				for(double relY = 0; relY < height; relY+= tileHeight)
				{
					int currentTw = tw;
					int currentTh = th;
					while(this.getCamera().toPixel(relX + tileWidth) > this.getCamera().toPixel(relX) + currentTw)
						currentTw++;
					while(this.getCamera().toPixel(relY + tileHeight) > this.getCamera().toPixel(relY) + currentTh)
						currentTh++;
					
					image.draw(x + this.getCamera().toPixel(relX), y + this.getCamera().toPixel(relY), currentTw, currentTh);
				}
			}
		}
	}
	
	public void drawBorderedMosaic(Image[] image, double posx, double posy, double width, double height, double tileWidth, double tileHeight)
	{
		if(this.g != null || image.length != 9)
		{
			int x = this.getCamera().toXPixel(posx);
			int y = this.getCamera().toYPixel(posy + height);
			
			int tw = this.getCamera().toPixel(tileWidth);
			int th = this.getCamera().toPixel(tileHeight);
			
			for(double relX = 0; relX < width; relX+= tileWidth)
			{
				for(double relY = 0; relY < height; relY+= tileHeight)
				{
					int currentTw = tw;
					int currentTh = th;
					while(this.getCamera().toPixel(relX + tileWidth) > this.getCamera().toPixel(relX) + currentTw)
						currentTw++;
					while(this.getCamera().toPixel(relY + tileHeight) > this.getCamera().toPixel(relY) + currentTh)
						currentTh++;
					
					Image imageToPaste;
					if(relX == 0)
					{
						if(relY == 0)
						{
							imageToPaste = image[0];
						}
						else if(relY >= height - tileHeight)
						{
							imageToPaste = image[1];
						}
						else
						{
							imageToPaste = image[2];
						}
					}
					else if(relX >= width - tileWidth)
					{
						if(relY == 0)
						{
							imageToPaste = image[3];
						}
						else if(relY == height - tileHeight)
						{
							imageToPaste = image[4];
						}
						else
						{
							imageToPaste = image[5];
						}
					}
					else
					{
						if(relY == 0)
						{
							imageToPaste = image[6];
						}
						else if(relY >= height - tileHeight)
						{
							imageToPaste = image[7];
						}
						else
						{
							imageToPaste = image[8];
						}
					}
					
						imageToPaste.draw(x + this.getCamera().toPixel(relX), y + this.getCamera().toPixel(relY), currentTw, currentTh);
				}
			}
		}
	}
	
	public void drawRotationImage(Image image, double centerX, double centerY, double width, double height, int degree)
	{
		int w = this.getCamera().toPixel(width);
		int h = this.getCamera().toPixel(height);
		int cX = this.getCamera().toXPixel(centerX);
		int cY = this.getCamera().toYPixel(centerY);
		
		if(this.g != null)
		{
			Image copy = image.getScaledCopy(w, h);
			copy.setCenterOfRotation(w / 2, h / 2);
			copy.setRotation(degree);
			copy.drawCentered(cX, cY);
		}
	}
	public void setFont(Font font)
	{
		this.g.setFont(font);
	}
	
	public void setColor(Color color)
	{
		if(this.g != null)
			this.g.setColor(color);
	}
	
	public Color getColor()
	{
		if(this.g != null)
			return this.g.getColor();
		return null;
	}
	
	public Camera getCamera()
	{
		return this.state.getCamera();
	}
}
