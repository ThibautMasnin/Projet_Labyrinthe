package Controller;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import View.HomeView;
import View.JComponentBuilder;
import View.SelectPlayersView;

import java.awt.event.*;

public class SelectPlayersController implements ActionListener, ItemListener {

    private JFrame frame;

    public SelectPlayersController(JFrame frame) {
        super();
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() instanceof JButton) {
            if("Jouer".equals(((JButton) event.getSource()).getName())) {
                System.out.println("Jouer");
            }
            else if("Retour".equals(((JButton) event.getSource()).getName())) {
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

    @Override
    public void itemStateChanged(ItemEvent event) {
        if(event.getStateChange()==ItemEvent.SELECTED) {
            if(event.getSource() instanceof JComboBox) {
                new SelectPlayersView(JComponentBuilder.frameBuilder(), ((Integer)((JComboBox)event.getSource()).getSelectedItem()).intValue(), 7, 7);
                frame.dispose();
            }
            else {
                System.out.println("Erreur d'event dans : " + this.getClass().toString());
                System.out.println("Event : " + event.toString());
            } 
        }
    }
    
}
