package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.fasterxml.jackson.databind.ObjectMapper;

import teleport.TeleportApi;

public class ResultsPanel extends JPanel{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String image;
	
	public ResultsPanel(String text, GridBagLayout l) {
		this.text = text;
		this.setLayout(l);
		this.image = null;
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		
		Graphics2D g2 = (Graphics2D) gr;

        Random rnd = new Random();
       
		
        if (image != null && !image.isEmpty()) {
	        BufferedImage img=null;
	        try {
	        	img = ImageIO.read(new URL(image));
	        } catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			g2.drawImage(img, 0, 0, (int) this.getWidth(),(int) this.getHeight(), null);
        } else {
        	GradientPaint gradientClr = new GradientPaint(0, this.getWidth()/2, new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)), this.getWidth()/2, this.getWidth(), new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
	        g2.setPaint(gradientClr);
	        g2.fill(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));  	
        }
		
		
        int fontSize = 60;
        Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setFont(f);
        if (this.text==null) {
        	return;
        }
       
        g2.setColor(new Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
        g2.drawString(text, this.getWidth()/2-100,this.getHeight()/2);
    }

	private void fetchImage() {
		 ObjectMapper mapper = new ObjectMapper(); 
	
		new Thread(() -> {
			 TeleportApi teleportApi = null;
			try {
				teleportApi = mapper.readValue(new URL("https://api.teleport.org/api/urban_areas/slug:"+this.text.toLowerCase()+"/images/"), TeleportApi.class);
				this.image = teleportApi.getPhotos().get(0).getImage().getMobile();				
				repaint();
				revalidate();
			} catch (Exception e) {
				this.setImage("");
				repaint();
				revalidate();
			}
		}).start();
		

	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
		repaint();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String s) {
		this.image = s;
	}
	public void setImage() {
		fetchImage();
	}
	
}
