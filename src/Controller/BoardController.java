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
    private String choice;

    public BoardController(JFrame frame) {
        super();
        this.frame = frame;
        this.jeu = null;
        this.joueur = null;
        this.choice = null;
    }
    public BoardController(JFrame frame, Jeu jeu, Joueur joueur) {
        super();
        this.frame = frame;
        this.jeu = jeu;
        this.joueur = joueur;
        this.choice = null;
    }

    public BoardController(JFrame frame, Jeu jeu, Joueur joueur, String choice) {
        super();
        this.frame = frame;
        this.jeu = jeu;
        this.joueur = joueur;
        this.choice = choice;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            String btnName = ((JButton)event.getSource()).getName();
            if("Quitter".equals(btnName)) {
                new HomeView(frame);
            }
            else if("suppH".equals(btnName) || "suppD".equals(btnName) || "suppB".equals(btnName) || "suppG".equals(btnName)) {
                synchronized (jeu.getHolder()) {
                    jeu.getHolder().add(String.valueOf(btnName.charAt(4)));
                    jeu.getHolder().notifyAll();            
                }
                new BoardView(frame, jeu, joueur, btnName);    
            }
            else if(choice!=null) {            
                synchronized (jeu.getHolder()) {
                    jeu.getHolder().add(btnName);
                    jeu.getHolder().notifyAll();             
                }
            }
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
