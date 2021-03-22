package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;

import Controller.ParametresController;

public class ParametresView {
    
    public ParametresView(JFrame frame) {   

		/** TEXTE En dev **/
		JTextPane text = JComponentBuilder.textBuilder("FONCTIONNALITÉ EN DEVELOPPEMENT", StyleConstants.ALIGN_CENTER);
        
		/** BOUTON RETOUR **/
		JButton btnRetour = JComponentBuilder.buttonBuilder("Retour", new ParametresController(frame));


        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.vboxBuilder(50, text, btnRetour));
        
        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.png", panel));
        frame.setVisible(true);
    }    
}
