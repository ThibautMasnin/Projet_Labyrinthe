package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.HomeController;

public class HomeView {

    public HomeView(JFrame frame) {   
        
        JLabel title = new JLabel(JComponentBuilder.imageIconBuilder("Images/title.png"));

        JButton btnJouer = JComponentBuilder.buttonBuilder("Jouer", new HomeController(frame));
        JButton btnRegles = JComponentBuilder.buttonBuilder("Regles", new HomeController(frame));        
        JButton btnCredits = JComponentBuilder.buttonBuilder("Credits", new HomeController(frame));        
        JButton btnParametres = JComponentBuilder.buttonBuilder("Parametres", new HomeController(frame));        
        JButton btnQuitter = JComponentBuilder.buttonBuilder("Quitter", new HomeController(frame));


        JPanel panel = new JPanel();
        panel.add(JComponentBuilder.vboxBuilder(10, JComponentBuilder.verticalSpace(50), title, JComponentBuilder.verticalSpace(100), btnJouer, btnRegles, btnCredits, btnParametres, btnQuitter));
        
        frame.setContentPane(JComponentBuilder.paneBuilder("Images/background.png", panel));
        frame.setVisible(true);
    }
}
