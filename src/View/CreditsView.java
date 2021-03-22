package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants;

import Controller.CreditsController;

public class CreditsView {
    
    public CreditsView(JFrame frame) {   
        
		/** PARAGRAPHE Credits **/
		JLabel label = JComponentBuilder.labelBuilder("Jeu développé par :", SwingConstants.CENTER);
		JTextPane text = JComponentBuilder.textBuilder("Thibaut MASNIN\n\n&\n\nKévin LEFEBVRE\n\n", StyleConstants.ALIGN_CENTER);
		
		/** BOUTON RETOUR **/
		JButton btnRetour = JComponentBuilder.buttonBuilder("Retour", new CreditsController(frame));


        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.vboxBuilder(50, label, text, btnRetour));

        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.png", panel));
        frame.setVisible(true);
    }    
}
