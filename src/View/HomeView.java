package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.HomeController;

public class HomeView {

    public HomeView(JFrame frame) {   
        
		/** TITRE **/
        JLabel title = new JLabel(JComponentBuilder.imageIconBuilder("Images/title.png"));

		/** LISTE DES BOUTONS DU MENU **/
        JButton btnJouer = JComponentBuilder.buttonBuilder("Jouer", new HomeController(frame));
        JButton btnRegles = JComponentBuilder.buttonBuilder("Regles", new HomeController(frame));        
        JButton btnCredits = JComponentBuilder.buttonBuilder("Credits", new HomeController(frame));        
        JButton btnParametres = JComponentBuilder.buttonBuilder("Parametres", new HomeController(frame));        
        JButton btnQuitter = JComponentBuilder.buttonBuilder("Quitter", new HomeController(frame));


		/** GROUPE ET AFFICHE VERTICALEMENT LE TITRE ET LES BOUTONS **/
        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.vboxBuilder(10, JComponentBuilder.verticalSpace(50), title, JComponentBuilder.verticalSpace(100), btnJouer, btnRegles, btnCredits, btnParametres, btnQuitter));
        
        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.gif", panel));
        frame.setVisible(true);
    }
}
