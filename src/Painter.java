import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import javax.swing.JComponent;

/**
 * A JComponent to paint an apple tree
 */
public class Painter extends JComponent
{
	private static final long serialVersionUID = 4629982296076518312L;
	
	private final int ANGLE = 20; //24 is nice	
	private final double V_POSITION_FACTOR = 0.9;
	private final double APPLE_RADIUS_FACTOR = 0.25;
	
	private final int SHADOW_SIZE = 10; // Default: 10
	private final int SHADOW_X = -4;
	private final double SHADOW_Y = -0.5;
	private final int SHADOW_WIDTH = 8;
	
	private final int AGESTRING_X = -20;
	private final int AGESTRING_Y = 40;
	
	private int age = 1;
	private int appleRadius = 2;
	private Turtle turt;
	private Stroke fatStroke;

	public Painter()
	{
		fatStroke = new BasicStroke(2, BasicStroke.JOIN_MITER, BasicStroke.CAP_SQUARE, 0,new float[] { 3, 0 }, 0);
	}
	
	/**
	 * Create a Turtle Object
	 * Paint an apple tree
	 */
	@Override
	public void paintComponent(Graphics g)
	{		
		Graphics2D g2 = (Graphics2D)g;
		turt = new Turtle();
		appleRadius = (int)Math.abs(turt.getStepLenght()*APPLE_RADIUS_FACTOR);
		g2.setStroke(fatStroke);
		
		g2.setBackground(Color.YELLOW);
		g2.translate(this.getWidth()/2, (int)(this.getHeight()*V_POSITION_FACTOR));
		g2.fillOval(SHADOW_X*SHADOW_SIZE, (int)(SHADOW_SIZE*SHADOW_Y), SHADOW_WIDTH*SHADOW_SIZE, SHADOW_SIZE);
		g2.drawString("Age: "+age, AGESTRING_X, AGESTRING_Y);

		//** Age=1: Draw a branch with an apple **//
		turt.turn(90);
		turt.move(age);
		turt.draw(g2);
		if(age == 0)
		{
			g2.fillOval((int)Math.round(turt.getTarget().x-appleRadius), (int)Math.round(-turt.getTarget().y-appleRadius), appleRadius*2, appleRadius*2);
		}
		turt.turn(ANGLE);
		addBranches(g2, turt, age-1);
	}

	/**
	 * 
	 * @param g2 A Graphics2D object
	 * @param turt The Turtle to use
	 * @param level The number of steps to move (+/-)
	 */
	public void addBranches(Graphics2D g2, Turtle turt, int level)
	{				
		if(level <= 0) 
		{
			turt.drawApple(g2, appleRadius);
		}
		else
		{
			turt.move(level);
			turt.draw(g2);
			turt.turn(ANGLE);

			addBranches(g2, turt,level-1); //left
			
			turt.turn(-ANGLE);
			turt.move(-level);
			turt.turn(-2*ANGLE);
			turt.move(level);
			turt.draw(g2);
			turt.turn(ANGLE);


			addBranches(g2, turt,level-1); //right

			turt.turn(-ANGLE);
			turt.move(-level);
			turt.turn(2*ANGLE);
		}
	}

	/**
	 * Add a year
	 */
	public void grow()
	{
		age++;
		repaint();
	}

	/**
	 * Subtract a year
	 */
	public void shrink()
	{
		if(age>1)
		age--;
		repaint();
	}

	/**
	 * Set the turtle's step length
	 */
	public void setTurtleStep(int step)
	{
		turt.setStepLength(step);
		repaint();
	}
	
	
	public void setAppleRadius(int radius)
	{
		appleRadius = radius;
	}
	
	public int getAppleRadius()
	{
		return appleRadius;
	}
}