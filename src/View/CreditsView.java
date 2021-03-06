package View;

import Controller.CreditsController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CreditsView {

    public CreditsView(Stage stage) {
        BorderPane bp = Layout.bpBuilder();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(bp, screenBounds.getWidth(), screenBounds.getHeight(), Color.BLACK); 


		/** PARAGRAPHE Credits **/
		Label label = Layout.labelBuilder("Jeu développé par :", Pos.CENTER);
		Text text = Layout.textBuilder("Thibaut MASNIN\n\n&\n\nKévin LEFEBVRE\n\n", TextAlignment.CENTER);
		

		/** BOUTON RETOUR **/
		Button btnRetour = Layout.buttonBuilder("Retour", new CreditsController<>(stage));


		/** GROUPE LE SCROLLPANE ET LE BOUTON **/
		VBox vb = new VBox(50);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(label, text, btnRetour);


		bp.setCenter(vb);

		stage.setScene(scene);
		stage.show();
    }
}
