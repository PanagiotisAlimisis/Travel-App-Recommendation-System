package window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class WelcomePanel extends JPanel{
	
	public WelcomePanel() {
		this.setLayout(new FlowLayout(0,0,35));
	}

	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D g2d = (Graphics2D) graphics.create();
		Font font = new Font("Comic Sans MS", Font.BOLD, 30);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(Color.BLUE);
		g2d.setFont(font);
		g2d.drawString("Get travel recommendations for your vacation", this.getWidth()/2-390, this.getHeight()/2+13);
	}
	
}
