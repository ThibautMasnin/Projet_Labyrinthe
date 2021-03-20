package View;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.SelectPlayersController;

public class SelectPlayersView {
    
    public SelectPlayersView(JFrame frame) {
        showView(frame, 2, 7, 7, 7, 7);
    }

    public SelectPlayersView(JFrame frame, int nbJoueurs, int age1, int age2) {
        showView(frame, nbJoueurs, age1, age2, 7, 7);
    }

    public SelectPlayersView(JFrame frame, int nbJoueurs, int age1, int age2, int age3, int age4) {
        showView(frame, nbJoueurs, age1, age2, age3, age4);
    }

    public void showView(JFrame frame, int nbJoueurs, int age1, int age2, int age3, int age4) {
        System.out.println(nbJoueurs);

        JComboBox<Integer> cbNbJoueurs = new JComboBox<>(new Integer[] { 2,3,4 });
        cbNbJoueurs.setSelectedItem(nbJoueurs);
        cbNbJoueurs.setName("nbJoueurs"); 		
        cbNbJoueurs.addItemListener(new SelectPlayersController(frame));

        JButton btnJouer = JComponentBuilder.buttonBuilder("Jouer", new SelectPlayersController(frame));
        JButton btnRetour = JComponentBuilder.buttonBuilder("Retour", new SelectPlayersController(frame));

        JLabel title = new JLabel(""+nbJoueurs);

        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.hboxBuilder(10, cbNbJoueurs, title, btnJouer, btnRetour));

        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.png", panel));
        frame.setVisible(true);
    }

}
