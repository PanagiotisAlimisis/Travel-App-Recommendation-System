package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

public class ResultsPanel extends JPanel{
 
	private String text;
	
	public ResultsPanel(String text, GridBagLayout l) {
		this.text = text;
		this.setLayout(l);
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		
		Graphics2D g2 = (Graphics2D) gr;

        Random rnd = new Random();
       
		
		GradientPaint blackToGray = new GradientPaint(0, this.getWidth()/2, new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), this.getWidth()/2, this.getWidth(), new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        g2.setPaint(blackToGray);
        g2.fill(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));

        
        int fontSize = 60;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setFont(f);
        if (this.text==null) {
        	return;
        }
       
        g2.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

        g2.drawString(text, this.getWidth()/2-60,this.getHeight()/2);
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		repaint();
	}
}
