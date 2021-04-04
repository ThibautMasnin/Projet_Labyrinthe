package View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyleConstants;

import Controller.SelectPlayersController;

public class SelectPlayersView {

    public SelectPlayersView(JFrame frame) {
        showView(frame, 2, 7, 7, 7, 7);
    }

    public SelectPlayersView(JFrame frame, int nbJoueurs, int age1, int age2, int age3, int age4) {
        showView(frame, nbJoueurs, age1, age2, age3, age4);
    }

    public void showView(JFrame frame, int nbJoueurs, int age1, int age2, int age3, int age4) {

		JLabel lblnbJoueurs = JComponentBuilder.labelBuilder("Nombre de joueurs :", SwingConstants.CENTER);
        JComboBox<Integer> cbNbJoueurs = new JComboBox<>(new Integer[] { 2,3,4 });
        cbNbJoueurs.setSelectedItem(nbJoueurs);
        cbNbJoueurs.setName("nbJoueurs");
        cbNbJoueurs.setPreferredSize(new Dimension(200, 30));
        Box vbNbJoueur = JComponentBuilder.vboxBuilder(lblnbJoueurs, cbNbJoueurs);


        JLabel lblJoueur1 = JComponentBuilder.labelBuilder("Joueur 1 :", SwingConstants.CENTER, Color.RED);
		JTextPane txtAge1 = JComponentBuilder.textBuilder("Age : ", StyleConstants.ALIGN_RIGHT);
        JSpinner spnAge1 = JComponentBuilder.spinnerBuilder(7, 99, age1);

		/** GROUPE LE TEXTE ET LE SPINNER 1 **/ 
		Box hbAge1 = JComponentBuilder.hboxBuilder(txtAge1, spnAge1);
        
		/** GROUPE L'ENTREE D'AGE 1 ET LE TITRE **/ 
		Box vbJoueur1 = JComponentBuilder.vboxBuilder(lblJoueur1, hbAge1);


		JLabel lblJoueur2 = JComponentBuilder.labelBuilder("Joueur 2 :", SwingConstants.CENTER, Color.ORANGE);
		JTextPane txtAge2 = JComponentBuilder.textBuilder("Age : ", StyleConstants.ALIGN_RIGHT);
        JSpinner spnAge2 = JComponentBuilder.spinnerBuilder(7, 99, age2);

		/** GROUPE LE TEXTE ET LE SPINNER 2 **/ 
		Box hbAge2 = JComponentBuilder.hboxBuilder(txtAge2, spnAge2);
        
		/** GROUPE L'ENTREE D'AGE 2 ET LE TITRE **/ 
		Box vbJoueur2 = JComponentBuilder.vboxBuilder(lblJoueur2, hbAge2);

        /** GROUPE LES JOUEURS 1 & 2 **/
        Box hbJoueur12 = JComponentBuilder.hboxBuilder(10, vbJoueur1, vbJoueur2);


		JLabel lblJoueur3 = JComponentBuilder.labelBuilder("Joueur 3 :", SwingConstants.CENTER, Color.BLUE);
		JTextPane txtAge3 = JComponentBuilder.textBuilder("Age : ", StyleConstants.ALIGN_RIGHT, 30, 30);
        JSpinner spnAge3 = JComponentBuilder.spinnerBuilder(7, 99, age3);

		/** GROUPE LE TEXTE ET LE SPINNER 3 **/ 
		Box hbAge3 = JComponentBuilder.hboxBuilder(txtAge3, spnAge3);
        
		/** GROUPE L'ENTREE D'AGE 3 ET LE TITRE **/ 
		Box vbJoueur3 = JComponentBuilder.vboxBuilder(lblJoueur3, hbAge3);

		JLabel lblJoueur4 = JComponentBuilder.labelBuilder("Joueur 4 :", SwingConstants.CENTER, Color.GREEN);
		JTextPane txtAge4 = JComponentBuilder.textBuilder("Age : ", StyleConstants.ALIGN_RIGHT);
        JSpinner spnAge4 = JComponentBuilder.spinnerBuilder(7, 99, age4);

		/** GROUPE LE TEXTE ET LE SPINNER 4 **/ 
		Box hbAge4 = JComponentBuilder.hboxBuilder(txtAge4, spnAge4);
        
		/** GROUPE L'ENTREE D'AGE 4 ET LE TITRE **/ 
		Box vbJoueur4 = JComponentBuilder.vboxBuilder(lblJoueur4, hbAge4);

        /** GROUPE LES JOUEURS 3 & 4 **/
		Box hbJoueur34 = JComponentBuilder.hboxBuilder(0);   
        // Affiche le joueur 3 si il y a 3 joueurs
        if(nbJoueurs == 3) {
            hbJoueur34 = JComponentBuilder.hboxBuilder(10, vbJoueur3, JComponentBuilder.horizontalSpace(362));
        }      
        // Sinon affiche le joueur 3 et 4 si il y a 4 joueurs
        else if(nbJoueurs == 4) {
            hbJoueur34 = JComponentBuilder.hboxBuilder(10, vbJoueur3, vbJoueur4);
        }

        cbNbJoueurs.addActionListener(e -> {
            new SelectPlayersController(frame, (int)cbNbJoueurs.getSelectedItem(), (int)spnAge1.getValue(), (int)spnAge2.getValue(), (int)spnAge3.getValue(), (int)spnAge4.getValue()).actionPerformed(e);
        });
        
		/** BOUTON JOUER **/
        JButton btnJouer = JComponentBuilder.buttonBuilder("Jouer", e -> {
            new SelectPlayersController(frame, (int)cbNbJoueurs.getSelectedItem(), (int)spnAge1.getValue(), (int)spnAge2.getValue(), (int)spnAge3.getValue(), (int)spnAge4.getValue()).actionPerformed(e);
        });
        
		/** BOUTON RETOUR **/
        JButton btnRetour = JComponentBuilder.buttonBuilder("Retour", new SelectPlayersController(frame));


		/** GROUPE LES BOUTONS **/
		Box hbButtons = JComponentBuilder.hboxBuilder(25, btnRetour, btnJouer);

		/** GROUPE LES HBOX **/
		Box vb = JComponentBuilder.vboxBuilder(100, vbNbJoueur, hbJoueur12, hbJoueur34, hbButtons);

        
        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.hboxBuilder(10, vb));

        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.gif", panel));
        frame.setVisible(true);
    }
}
