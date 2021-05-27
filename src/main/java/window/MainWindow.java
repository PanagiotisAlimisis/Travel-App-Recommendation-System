package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.DefaultTableModel;

import org.it21902.City;
import org.it21902.ElderTraveller;
import org.it21902.MiddleTraveller;
import org.it21902.Traveller;
import org.it21902.YoungTraveller;

import db.connection.OracleDbConnection;

public class MainWindow {

	private static JLabel recommendedCity = new JLabel();
	private static JSplitPane splitPane ;
	private static ArrayList<Integer> termsVector = new ArrayList<>(Collections.nCopies(10, 0));
    
	
	public static void main(String[] args) {

		Connection connection=null;
		try {
			connection = OracleDbConnection.getInstance().getOracleConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		City.readCitiesFromDb(connection);
		Traveller.readTravellersFromJson();
		
        Runnable r = new Runnable() {

            public void run() {
                final JFrame frame = new JFrame("Travel Recomendation App");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                final JPanel gui = new JPanel(new BorderLayout(5,5));
                gui.setBorder( new TitledBorder("") );

				JToolBar tb = new JToolBar();
                JPanel themeUI = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3,3));
                themeUI.setBorder(new TitledBorder("Select theme UI") );

                final UIManager.LookAndFeelInfo[] lookAndFeels = UIManager.getInstalledLookAndFeels();
                String[] themes = new String[lookAndFeels.length];
                for (int ii=0; ii<lookAndFeels.length; ii++) {
                    themes[ii] = lookAndFeels[ii].getName();
                    System.out.println(themes[ii]);
                }
                final JComboBox plafChooser = new JComboBox(themes);
                themeUI.add(plafChooser);


                plafChooser.addActionListener( new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        int index = plafChooser.getSelectedIndex();
                        try {
                            UIManager.setLookAndFeel(lookAndFeels[index].getClassName());
                            SwingUtilities.updateComponentTreeUI(frame);
                            frame.pack();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                } );

                gui.add(themeUI, BorderLayout.NORTH);

                JPanel dynamicLabels = new JPanel(new BorderLayout(4,4));
                dynamicLabels.setBorder(new TitledBorder("") );
                gui.add(dynamicLabels, BorderLayout.WEST);

                final JPanel labels = new JPanel(new GridLayout(0,1,3,3));
                labels.setBorder(new TitledBorder("Select your terms") );

                JLabel labelTerms;
                ArrayList<String> terms = new ArrayList<>();
                terms.add("Museum");
                terms.add("Sea");
                terms.add("Mountain");
                terms.add("Cafe");
                terms.add("Restaurant");
                terms.add("Bar");
                terms.add("Stadium");
                terms.add("Park");
                terms.add("Statue");
                terms.add("Square");
                
                ArrayList<JSlider> sliders = new ArrayList<>();
                for (int i=0; i<10; ++i) {
                	labelTerms = new JLabel(terms.get(i));
                	sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, 0));
                	sliders.get(i).setName(terms.get(i));
                	sliders.get(i).setMajorTickSpacing(10);
                	sliders.get(i).setMinorTickSpacing(1);
                	sliders.get(i).setPaintTicks(true);
                	sliders.get(i).setPaintLabels(true);
                	
                	sliders.get(i).addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent event) {
							JSlider source = (JSlider) event.getSource();
							termsVector.set(terms.indexOf(source.getName()), source.getValue());
						}
                	});
                	labels.add(labelTerms);
                	labels.add(sliders.get(i));
                	frame.validate();
                }

                dynamicLabels.add( new JScrollPane(labels), BorderLayout.CENTER );

                
                JPanel p = new JPanel();

                String[] form = {"Full Name:", "Age:", "Current City:"};
                JTextField[] userInformation = new JTextField[3];
                for (int i=0; i<form.length; ++i) {
                	JPanel insidePanel = new JPanel();
                	JLabel lb = new JLabel(form[i]);
                	userInformation[i] = new JTextField("",15);
                	userInformation[i].setName(form[i]);
                	insidePanel.add(lb);
                	insidePanel.add(userInformation[i]);                	
                	p.add(insidePanel);
                }
                
                JPanel resultsPanel = new JPanel(new GridBagLayout());

                //TODO: display result in a beautiful way
                
                //                BufferedImage bi = new BufferedImage(500, 500,BufferedImage.TYPE_INT_ARGB);
//                Graphics2D g = bi.createGraphics();
//                GradientPaint gp = new GradientPaint(20f,20f,Color.red, 180f,180f,Color.yellow);
//                g.setPaint(gp);
//                g.fillRect(0,0,200,200);
//                
//                Font font = new Font("Arial", Font.BOLD, 22);
//                g.setFont(font);
//                g.setColor(Color.GREEN);
//                g.drawString("Hello", 100, 20);
//                
//                ImageIcon ii = new ImageIcon(bi);
//                JLabel imageLabel = new JLabel(ii);
//                resultsPanel.add( imageLabel, null );
//                
                resultsPanel.add(recommendedCity);
                
                
                splitPane = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    p,
                    new JScrollPane(resultsPanel));

                gui.add( splitPane, BorderLayout.CENTER );
                
                JButton b = new JButton("Generate results");
                b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent event) {
						String fullName="";
						Integer age=-1;
						String currentCity="";
						for (int i=0; i<userInformation.length; ++i) {
							if (userInformation[i].getText().trim().equals("")) {
								JOptionPane.showMessageDialog(frame, "Please enter all requested values.", null, JOptionPane.ERROR_MESSAGE);
								return;
							}
							if (userInformation[i].getName().equals("Age:")) {
								try {
									age = Integer.parseInt(userInformation[i].getText());
									if (age <= 0 || age > 110) {
										JOptionPane.showMessageDialog(frame, "Enter a valid age.", null, JOptionPane.ERROR_MESSAGE);
										return;
									}
								} catch (NumberFormatException e) {
									JOptionPane.showMessageDialog(frame, "Enter a valid age.", null, JOptionPane.ERROR_MESSAGE);
									return;
								}
							}
							if (userInformation[i].getName().equals("Full Name:")) {
								fullName = userInformation[i].getText();
							}
							if (userInformation[i].getName().equals("Current City:")) {
								currentCity = userInformation[i].getText();
							}
						}
						
						Traveller t = null;
						try {
							if (age < 26) {
								t = new YoungTraveller(fullName, age, currentCity, termsVector);
							} else if (age < 51) {
								t = new MiddleTraveller(fullName, age, currentCity, termsVector);
							} else {
								t = new ElderTraveller(fullName, age, currentCity, termsVector);
							}
						} catch (NullPointerException e) {
							JOptionPane.showMessageDialog(frame, "Enter another city", null, JOptionPane.ERROR_MESSAGE);
						}
						
						recommendedCity = new JLabel(t.compareCities(City.getAllCities()).getNameCity());
						
						resultsPanel.removeAll();
						
						resultsPanel.add(recommendedCity);
						resultsPanel.repaint();
						resultsPanel.revalidate();
					}
                });
                
                p.add(b);
                b = new JButton("Save my data");
                b.addActionListener(new ActionListener() {
                	@Override
					public void actionPerformed(ActionEvent event) {
                		Traveller.writeTravellersToJson();
					}
                });
                p.add(b);

                b = new JButton("Clear");
                b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						for (JSlider sl: sliders) {
							sl.setValue(0);
						}
						for (int i=0; i<userInformation.length; ++i) {
							userInformation[i].setText("");
						}
						resultsPanel.removeAll();
						resultsPanel.repaint();
						resultsPanel.revalidate();
					}
					
                });
                p.add(b);
                
                frame.setContentPane(gui);

                frame.pack();

                frame.setLocationRelativeTo(null);
                try {
                    frame.setLocationByPlatform(true);
                } catch(Throwable ignoreAndContinue) {
                
                }

                frame.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
