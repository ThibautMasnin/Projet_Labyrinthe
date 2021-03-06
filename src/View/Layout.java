package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;

public interface Layout {

	/** RETOURNE UN BORDERPANE AVEC BACKGROUND **/
    public static BorderPane bpBuilder() {
        BorderPane bp = new BorderPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double imageWidth;
        double imageHeight;
        if(screenBounds.getWidth()/screenBounds.getHeight()>=690f/388f) {
            imageWidth = screenBounds.getWidth();
            imageHeight = screenBounds.getWidth()*(388f/690f);
        }
        else {
            imageWidth = screenBounds.getHeight()*(690f/388f);
            imageHeight = screenBounds.getHeight();
        }
        // Image image = new Image("Resources/background.gif",screenBounds.getWidth()*690/388,screenBounds.getHeight(),true,true);
        Image image = new Image("Resources/background.png",imageWidth,imageHeight,false,true);
        BackgroundImage myBI= new BackgroundImage(image ,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,BackgroundSize.DEFAULT); 
        bp.setBackground(new Background(myBI));
        return bp;
    }

	/** RETOURNE UN BOUTON STYLISE **/
    public static Button buttonBuilder(String name, EventHandler<ActionEvent> handler) {
		Button btn = new Button();
		btn.setId(name);
		btn.setMinWidth(328);
		btn.setMinHeight(70);
		btn.setStyle("-fx-background-image: url('Resources/btn" + name + ".png'); -fx-background-color: rgba(0, 0, 0, 0);");
		btn.setOnAction(handler);
        return btn;
    }
    
	/** RETOURNE UN TEXT STYLISE **/
    public static Text textBuilder(String string, TextAlignment ta) {
        Text text = new Text(string);
		text.setFont(new Font("Arial", 24));
		text.setFill(Color.WHITE);
		text.setWrappingWidth(1000);
        text.setTextAlignment(ta);
        return text;
	}
    
	/** RETOURNE UN LABEL STYLISE **/
    public static Label labelBuilder(String string, Pos pos) {
        Label label = new Label(string);
		label.setFont(new Font("Viner Hand ITC", 32));
		label.setTextFill(Color.WHITE);
		label.setPrefWidth(1000);
        label.setAlignment(pos);
        return label;
	}

}
