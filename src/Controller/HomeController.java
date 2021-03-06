package Controller;

import java.util.Optional;

import View.CreditsView;
import View.ParametresView;
import View.ReglesView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class HomeController<T extends ActionEvent> implements EventHandler<T> {

    private Stage stage;

    public HomeController(Stage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public void handle(T event) {
		if(event.getSource() instanceof Button) {
            if("Jouer".equals(((Button) event.getSource()).getId())) {
                new ParametresView(stage);
            }
            else if("Regles".equals(((Button) event.getSource()).getId())) {
                new ReglesView(stage);
            }
            else if("Credits".equals(((Button) event.getSource()).getId())) {
                new CreditsView(stage);
            }
            else if("Parametres".equals(((Button) event.getSource()).getId())) {
                new ParametresView(stage);
            }
            else if ("Quitter".equals(((Button) event.getSource()).getId())) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmer la fermeture");
				alert.setContentText("Voulez vous vraiment quitter le jeu ?");
				ButtonType btnOui = new ButtonType("Quitter");
				ButtonType btnNon = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(btnOui, btnNon);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == btnOui) {
					System.exit(0);
				}			
			}
            else {
                System.out.println("Erreur d'event dans le menu");
            }
        }
        else {
            System.out.println("Erreur d'event dans le menu");
        }
    }
}
