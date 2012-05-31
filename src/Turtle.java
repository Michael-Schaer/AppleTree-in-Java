import java.awt.Color;
import java.awt.Graphics2D;

/**
 * A Turtle has a direction and is turnable.
 * It has a location and a target so it can move. The stepLength defines how many pixels it moves
 */
public class Turtle 
{
	private int direction; // angle [degree]
	private DoublePoint location, target;
	public static int stepLength = 20;

	public Turtle()
	{
		direction = 0;
		location = new DoublePoint(0,0);
		target = new DoublePoint(0,0);
	}

	public void turn(int turnAngle)
	{
		direction += turnAngle;	
	}

	public void move(int factor)
	{
		location = new DoublePoint(target.x, target.y);
		target = calcDirection(stepLength*factor);
	}

	/**
	 * calculates a new Point with the direction and the stepLenght
	 */
	public DoublePoint calcDirection(int lenght)
	{
		DoublePoint p = new DoublePoint(target.x+lenght*Math.cos(direction*Math.PI/180),target.y+lenght*Math.sin(direction*Math.PI/180));
		return p;
	}

	public void draw(Graphics2D g2)
	{
		Color col = new Color(120,80,30);
		g2.setColor(col);
		g2.drawLine((int)Math.round(location.x), (int)Math.round(-location.y), (int)Math.round(target.x), (int)Math.round(-target.y));
	}
	public void drawApple(Graphics2D g2, int radius)
	{
		g2.setColor(Color.RED);
		g2.fillOval((int)Math.round(target.x-radius), (int)Math.round(-target.y-radius), radius*2, radius*2);
	}

	/* Getters and Setters */
	
	public int getDirection()
	{
		return direction;
	}

	public DoublePoint getLocation()
	{
		return location;
	}

	public DoublePoint getTarget()
	{
		return target;
	}
	
	public void setStepLength(int step)
	{
		stepLength = step;
	}
	
	public int getStepLenght()
	{
		return stepLength;
	}
}