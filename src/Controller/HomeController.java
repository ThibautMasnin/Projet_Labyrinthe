package Controller;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;

import View.CreditsView;
import View.ParametresView;
import View.ReglesView;
import View.SelectPlayersView;

public class HomeController implements ActionListener {

    private JFrame frame;

    public HomeController(JFrame frame) {
        super();
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            if("Jouer".equals(((JButton) event.getSource()).getName())) {
                new SelectPlayersView(frame);
            }
            else if("Regles".equals(((JButton) event.getSource()).getName())) {
                new ReglesView(frame);
            }
            else if("Credits".equals(((JButton) event.getSource()).getName())) {
                new CreditsView(frame);
            }
            else if("Parametres".equals(((JButton) event.getSource()).getName())) {
                new ParametresView(frame);
            }
            else  if("Quitter".equals(((JButton) event.getSource()).getName())) {
                System.exit(0);
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
