package Controller;

import javax.swing.JButton;
import javax.swing.JFrame;

import View.HomeView;

import java.awt.event.*;

public class ParametresController implements ActionListener {

    private JFrame frame;

    public ParametresController(JFrame frame) {
        super();
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            // Lance la vue d'acceuil si l'utilisateur a clique sur Retour
            if("Retour".equals(((JButton) event.getSource()).getName())) {
                new HomeView(frame);
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
