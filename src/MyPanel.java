import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * A Panel that provides Buttons and a Slider to set the Age and branch size of an apple tree
 */
public class MyPanel extends JPanel
{
	private static final long serialVersionUID = 2825592802871403004L;
	private final int MAX_STEP = 71;
	private final int DEFAULT_STEP = 20;
	private int step;
	private boolean drawSlider;

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); 
		this.setBackground(new Color(150,180,255));
	}

	public MyPanel()
	{
		step = DEFAULT_STEP;
		drawSlider = true;
		final Painter applePainter = new Painter();

		this.setLayout(new BorderLayout());
		this.add(applePainter,BorderLayout.CENTER);

		final JTextField stepSizeText = new JTextField();
		final JSlider stepSizeSlider = new JSlider(1,MAX_STEP);

		stepSizeSlider.setValue(DEFAULT_STEP);
		stepSizeSlider.setMajorTickSpacing(MAX_STEP/7);
		stepSizeSlider.setPaintTicks(true);
		stepSizeSlider.setPaintLabels(true);
		stepSizeSlider.setToolTipText("Branch size");

		stepSizeSlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent evt) 
			{
				if(!drawSlider)
				{
					drawSlider=true;
				}
				else
				{
					step = stepSizeSlider.getValue();
					stepSizeText.setText(String.valueOf(step));
					applePainter.setTurtleStep(step);
				}
			}
		});

		stepSizeText.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					step = Integer.parseInt(stepSizeText.getText());
				}
				catch(NumberFormatException exc)
				{
					System.out.println("Step must be a number");
					//step = 2;
				}

				stepSizeText.setText(String.valueOf(step));
				drawSlider = false;
				stepSizeSlider.setValue(step);
				applePainter.setTurtleStep(step);
			}
		});

		JButton grow,shrink;
		grow = new JButton("Grow");
		grow.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				applePainter.grow();
			}
		});

		shrink = new JButton("Shrink");
		shrink.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				applePainter.shrink();
			}
		});

		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(1,3));
		menu.add(grow);	
		menu.add(stepSizeText);
		menu.add(stepSizeSlider);
		menu.add(shrink);

		this.add(menu,BorderLayout.SOUTH);
	}
}