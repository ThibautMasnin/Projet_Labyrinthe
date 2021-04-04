package View;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import Controller.BoardController;
import Model.Couloir;
import Model.CouloirImpl;
import Model.Jeu;
import Model.Joueur;
import Model.Orientation;
import Model.Position;
import Model.PositionInsertion;

public class BoardView {

    public BoardView(JFrame frame, Jeu jeu, Joueur joueur) { 
        showView(frame, jeu, joueur, null);
    }
    
    public BoardView(JFrame frame, Jeu jeu, Joueur joueur, String choice) {   
        showView(frame, jeu, joueur, choice);
    }
    
    public void showView(JFrame frame, Jeu jeu, Joueur joueur, String choice) {     
		JLabel lblChoixSupp = JComponentBuilder.labelBuilder("Orientation :", SwingConstants.CENTER);

        Couloir supplementaire = jeu.getSupplementaire();
        JButton haut = JComponentBuilder.couloirSuppBuilder(CouloirImpl.couloirMobileBuilder(supplementaire.getForme(), Orientation.HAUT, supplementaire.getObjectif()) , new BoardController(frame, jeu, joueur, choice), choice==null || "suppH".equals(choice));
        JButton droite = JComponentBuilder.couloirSuppBuilder(CouloirImpl.couloirMobileBuilder(supplementaire.getForme(), Orientation.DROITE, supplementaire.getObjectif()), new BoardController(frame, jeu, joueur, choice), choice==null || "suppD".equals(choice));
        JButton bas = JComponentBuilder.couloirSuppBuilder(CouloirImpl.couloirMobileBuilder(supplementaire.getForme(), Orientation.BAS, supplementaire.getObjectif()), new BoardController(frame, jeu, joueur, choice), choice==null || "suppB".equals(choice));
        JButton gauche = JComponentBuilder.couloirSuppBuilder(CouloirImpl.couloirMobileBuilder(supplementaire.getForme(), Orientation.GAUCHE, supplementaire.getObjectif()), new BoardController(frame, jeu, joueur, choice), choice==null || "suppG".equals(choice));
        
		/** GROUPE LES DIFFERENTES ORIENTATION DU COULOIR SUPPLEMENTAIRE **/
		Box vbGauche = JComponentBuilder.vboxBuilder(50, lblChoixSupp, haut, droite, bas, gauche);

        /** CREATION DU PLATEAU **/
        JPanel plateau = plateauBuilder(frame, jeu, joueur, choice);
 
		JLabel lbltour = JComponentBuilder.labelBuilder("Au tour de :", SwingConstants.CENTER);
        JLabel lblJoueur = null;
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

        
		JLabel lblObjectif = JComponentBuilder.labelBuilder("Objectif :", SwingConstants.CENTER);
        JLabel objectif = null;
        if(joueur.getObjectifs().isEmpty()) {
            objectif = new JLabel(JComponentBuilder.imageIconBuilder("Couloirs/spawn" + joueur.getPion().getCouleur().name() + ".png", 100, 100));
        }
        else {
            objectif = new JLabel(JComponentBuilder.imageIconBuilder("Objectifs/" + joueur.getObjectifActuel().name().toLowerCase() + ".png", 100, 100));
        }
		JLabel lblRestant = JComponentBuilder.labelBuilder("Restant : " + joueur.getObjectifs().size(), SwingConstants.CENTER);

		/** BOUTON RETOUR **/
		JButton btnQuitter = JComponentBuilder.buttonBuilder("Quitter", new BoardController(frame));
        
		Box vbDroite = JComponentBuilder.vboxBuilder(10, lbltour, lblJoueur, JComponentBuilder.verticalSpace(25), lblObjectif, objectif, lblRestant, JComponentBuilder.verticalSpace(25), btnQuitter);
        
        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.hboxBuilder(50, vbGauche, plateau, vbDroite));


        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.gif", panel));
        frame.setVisible(true);
    }        

    private JPanel plateauBuilder(JFrame frame, Jeu jeu, Joueur joueur, String choice) {
        
        JPanel plateau = new JPanel();
        plateau.setOpaque(false);
        plateau.setLayout(new GridLayout(9,9));

        //Ajout des fleches du haut
        plateau.add(Box.createHorizontalBox());
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.H1, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.H3, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.H5, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(Box.createHorizontalBox());
        
        ArrayList<Couloir> couloirs = jeu.couloirs();
        for(int i=0; i<7; i++) {
            //Ajout des fleches de gauche
            switch(i) {
                case 1:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.G1, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                case 3:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.G3, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                case 5:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.G5, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                default:
                    plateau.add(Box.createHorizontalBox());
            }
            //Ajout des couloirs
        for(int j=0; j<7; j++) {
                plateau.add(JComponentBuilder.couloirBuilder(i, j, jeu.getJoueurs(), couloirs.remove(0), new BoardController(frame, jeu, joueur), jeu.getPlateau().estAtteignable(joueur.getPion().getPositionCourante(), new Position(i, j))));
            }
            //Ajout des fleches de droite
            switch(i) {
                case 1:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.D1, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                case 3:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.D3, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                case 5:
                    plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.D5, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
                    break;
                default:
                    plateau.add(Box.createHorizontalBox());
            }
        }
        
        //Ajout des fleches du bas
        plateau.add(Box.createHorizontalBox());
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.B1, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.B3, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(JComponentBuilder.buttonFlecheBuilder(PositionInsertion.B5, new BoardController(frame, jeu, joueur, choice), jeu.getOrigine()));
        plateau.add(Box.createHorizontalBox());
        plateau.add(Box.createHorizontalBox());
        
        return plateau;
    }
}
