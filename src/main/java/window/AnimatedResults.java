package window;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnimatedResults extends JButton{

	private BufferedImage buttonImage = null;
	
	public AnimatedResults(String label) {
		super(label);
		setOpaque(false);
	}
	public void paint(Graphics g) {
		// Create an image for the button graphics if necessary
		if (buttonImage == null || buttonImage.getWidth() != getWidth() || buttonImage.getHeight() != getHeight()) {
			buttonImage = (BufferedImage) createImage(getWidth(), getHeight());
		}
		Graphics gButton = buttonImage.getGraphics();
		gButton.setClip(g.getClip());
		// Have the superclass render the button for us
		super.paint(gButton);
		// Make the graphics object sent to this
		// paint() method translucent
		Graphics2D g2d = (Graphics2D)g;
		AlphaComposite newComposite = AlphaComposite.getInstance(
		AlphaComposite.SRC_OVER, .5f);
		g2d.setComposite(newComposite);
		g2d.drawImage(buttonImage, 0, 0, null);
	}
}