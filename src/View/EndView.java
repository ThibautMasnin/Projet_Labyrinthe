package View;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.EndController;
import Model.Joueur;

public class EndView {

    public EndView(JFrame frame, Joueur joueur) {   
        
		/** PARAGRAPHE joueur gagnant **/        
		JLabel lblFin = JComponentBuilder.labelBuilder("Fin de la partie", SwingConstants.CENTER);
        JLabel lblJoueur=null;
        switch(joueur.getPion().getCouleur()) {
            case ROUGE:
                lblJoueur = JComponentBuilder.labelBuilder("Joueur 1", SwingConstants.CENTER, Color.RED);
                break;
            case JAUNE:
                lblJoueur = JComponentBuilder.labelBuilder("Joueur 2", SwingConstants.CENTER, Color.ORANGE);
                break;
            case BLEU:
                lblJoueur = JComponentBuilder.labelBuilder("Joueur 3", SwingConstants.CENTER, Color.BLUE);
                break;
            case VERT:
                lblJoueur = JComponentBuilder.labelBuilder("Joueur 4", SwingConstants.CENTER, Color.GREEN);
                break;
        }
		JLabel lblGagne = JComponentBuilder.labelBuilder("a gagné la partie !", SwingConstants.CENTER);

		/** BOUTON Quitter **/
		JButton btnQuitter = JComponentBuilder.buttonBuilder("Quitter", new EndController(frame));


        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.vboxBuilder(25, lblFin, JComponentBuilder.verticalSpace(100), lblJoueur, lblGagne, JComponentBuilder.verticalSpace(100), btnQuitter));

        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.gif", panel));
        frame.setVisible(true);
    }    
}
