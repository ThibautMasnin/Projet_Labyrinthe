package Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import Model.JeuImpl;
import View.HomeView;
import View.JComponentBuilder;
import View.SelectPlayersView;

import java.awt.event.*;

public class SelectPlayersController implements ActionListener {

    private JFrame frame;
    int nbJoueurs;
    private int age1;
    private int age2;
    private int age3;
    private int age4;

    public SelectPlayersController(JFrame frame) {
        super();
        this.frame = frame;
    }

    public SelectPlayersController(JFrame frame, int nbJoueurs, int age1, int age2, int age3, int age4) {
        super();
        this.frame = frame;
        this.nbJoueurs = nbJoueurs;
        this.age1 = age1;
        this.age2 = age2;
        this.age3 = age3;
        this.age4 = age4;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            if("Jouer".equals(((JButton) event.getSource()).getName())) { 
                JeuImpl jeu = new JeuImpl(frame);
                jeu.enregistrer(nbJoueurs, age1, age2, age3, age4);
                jeu.start();  
            }
            else if("Retour".equals(((JButton) event.getSource()).getName())) {
                new HomeView(frame);
            }
            else {
                System.out.println("Erreur d'event dans : " + this.getClass().toString());
                System.out.println("Event : " + event.toString());
            }
        }
        else if(event.getSource() instanceof JComboBox) {
            if("nbJoueurs".equals(((JComboBox) event.getSource()).getName())) {
                new SelectPlayersView(JComponentBuilder.frameBuilder(), nbJoueurs, age1, age2, age3, age4);
                frame.dispose();
            }
            else {
                System.out.println("Erreur d'event dans : " + this.getClass().toString());
                System.out.println("Event : " + event.toString());
            }
        }
        else {
            System.out.println("Erreur d'event dans : " + this.getClass().toString());
            System.out.println("Event : " + event.toString());
        }
    }
    
}
