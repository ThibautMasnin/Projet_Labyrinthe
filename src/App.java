import View.HomeView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        stage.setTitle("Labyrinthe");
        stage.setX(screenBounds.getMinX()-8);
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth()+16);
        stage.setHeight(screenBounds.getHeight()-32);
        new HomeView(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
