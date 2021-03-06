package View;

import Controller.HomeController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeView {

	public HomeView(Stage stage) {
        BorderPane bp = Layout.bpBuilder();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(bp, screenBounds.getWidth(), screenBounds.getHeight(), Color.BLACK); 

		
		/** IMAGE LABYRINTHE SI L'ECRAN EST ASSEZ GRAND **/
		Label espace = new Label("");
		if(screenBounds.getHeight()>=750) {
			if(screenBounds.getHeight()>900) {
				espace.setPrefHeight(400);
			}
			espace.setMinHeight(264);
			espace.setMinWidth(1045);
			espace.setStyle("-fx-background-image: url('Resources/title.png'); -fx-background-repeat: no-repeat;");
		}

		/** BOUTON JOUER **/
		Button btnJouer = Layout.buttonBuilder("Jouer", new HomeController<>(stage));

		/** BOUTON REGLEMENT **/
		Button btnRegles = Layout.buttonBuilder("Regles", new HomeController<>(stage));

		/** BOUTON CREDITS **/
		Button btnCredits = Layout.buttonBuilder("Credits", new HomeController<>(stage));

		/** BOUTON PARAMETRES **/
		Button btnParametres = Layout.buttonBuilder("Parametres", new HomeController<>(stage));

		/** BOUTON QUITTER **/
		Button btnQuitter = Layout.buttonBuilder("Quitter", new HomeController<>(stage));


		/** GROUPE L'IMAGE ET LES BOUTONS **/
		VBox vb = new VBox(10);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(espace, btnJouer, btnRegles, btnCredits, btnParametres, btnQuitter);


		bp.setCenter(vb);

		stage.setScene(scene);
		stage.show();
	}
    
}
