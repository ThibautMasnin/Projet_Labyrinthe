package Controller;

import View.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReglesController<T extends ActionEvent> implements EventHandler<T> {

    private Stage stage;

    public ReglesController(Stage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public void handle(T event) {
		if(event.getSource() instanceof Button && "Retour".equals(((Button) event.getSource()).getId())) {
            new HomeView(stage);
        }
        else {
            System.out.println("Erreur d'event dans le reglement");
        }
    }
}
