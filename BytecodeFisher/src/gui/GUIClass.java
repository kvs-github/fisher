package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

public class GUIClass {
	
	public static boolean start;
	public static JTextField optionTF;
	
	public GUIClass() {
		JFrame frame = new JFrame("BC Fisher");

			frame.setSize(200, 200);
			JFrame.setDefaultLookAndFeelDecorated(true);
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			
			JPanel panel = new JPanel(new GridLayout(0, 1));
			panel.add(new JLabel("Settings"));
			
			JPanel optionPanel = new JPanel(new GridLayout(1, 0));
			JLabel optionLabel = new JLabel("Fishing option: ");
			optionPanel.add(optionLabel);
			
			optionTF = new JTextField("1");
			optionPanel.add(optionTF);
			panel.add(optionPanel);
			
			JButton startButton = new JButton("Start");
			startButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent g) {
					start = true;
					frame.dispose();
					
				}
			});
			
			panel.add(startButton);
			frame.add(panel);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
					} catch (Exception e) { }
					frame.setVisible(true);
				}
			});
	}
	
	
	public static String getOptionTF() {
		return optionTF.getText();
	}

}
