package Controller;

import javax.swing.JButton;
import javax.swing.JFrame;

import Model.Jeu;
import Model.Joueur;
import View.BoardView;
import View.HomeView;

import java.awt.event.*;

public class BoardController implements ActionListener {

    private JFrame frame;
    private Jeu jeu;
    private Joueur joueur;

    public BoardController(JFrame frame) {
        super();
        this.frame = frame;
        this.jeu = null;
        this.joueur = null;
    }
    public BoardController(JFrame frame, Jeu jeu, Joueur joueur) {
        super();
        this.frame = frame;
        this.jeu = jeu;
        this.joueur = joueur;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            String btnName = ((JButton)event.getSource()).getName();
            // Lance la vue d'acceuil si l'utilisateur a clique sur Quitter
            if("Quitter".equals(btnName)) {
                new HomeView(frame);
            }
            // Relance la vue du plateau avec l'orientation du couloir supplementaire sur lequel l'utilisateur a cliqué
            else if("suppH".equals(btnName) || "suppD".equals(btnName) || "suppB".equals(btnName) || "suppG".equals(btnName)) {
                synchronized (jeu.getHolder()) {
                    jeu.getHolder().add(String.valueOf(btnName.charAt(4)));
                    jeu.getHolder().notifyAll();            
                }
                new BoardView(frame, jeu, joueur, btnName);    
            }
            // Sinon si l'utilisateur a deja selectionné l'orientation de son couloir supplementaire envoie au jeu le couloir du plateau sur lequel l'utilisateur a cliqué
            else {            
                synchronized (jeu.getHolder()) {
                    jeu.getHolder().add(btnName);
                    jeu.getHolder().notifyAll();             
                }
            }          
        }
        else {
            System.out.println("Erreur d'event dans : " + this.getClass().toString());
            System.out.println("Event : " + event.toString());
        }
    }
    
}
