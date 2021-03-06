package View;

import Controller.ReglesController;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ReglesView {

	public ReglesView(Stage stage) {
        BorderPane bp = Layout.bpBuilder();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(bp, screenBounds.getWidth(), screenBounds.getHeight(), Color.BLACK); 

			
		/** PARAGRAPHE But du jeu **/
		Label labelBut = Layout.labelBuilder("But du jeu", Pos.TOP_LEFT);
		Text textBut = Layout.textBuilder("Dans un labyrinthe enchanté, les joueurs partent à la chasse aux objets et aux créatures magiques." +
			"Chacun cherche à se frayer un chemin jusqu’à eux en faisant coulisser astucieusement les couloirs." +
			"Le premier joueur à découvrir tous ses secrets et à revenir à son point de départ remporte cette passionnante chasse aux trésors.\n", TextAlignment.JUSTIFY);
		
		/** PARAGRAPHE Preparation **/
		Label labelPreparation = Layout.labelBuilder("Préparation", Pos.TOP_LEFT);
		Text textPreparation = Layout.textBuilder("Les plaques sont mélangées face cachée, puis placées sur les emplacements libres du plateau pour créer un labyrinthe aléatoire. La plaque supplémentaire servira à faire coulisser les couloirs du labyrinthe.\n" + 
		"Les 24 cartes objectifs sont mélangées puis distribuées aux joueurs qui les gardent devant eux sans les regarder.\n" +
		"Chaque joueur choisit un pion qui se placera sur sa case Départ dans un coin du plateau.\n", TextAlignment.JUSTIFY);

		/** PARAGRAPHE Deroulement de la partie **/
		Label labelDeroulement = Layout.labelBuilder("Déroulement de la partie", Pos.TOP_LEFT);
		Text textDeroulement = Layout.textBuilder("Chaque joueur regarde secrètement la carte supérieure de sa pile. Le plus jeune joueur commence. La partie se poursuit dans le sens des aiguilles d’une montre.\n" + 
		"À son tour de jeu, le joueur doit essayer d’atteindre la plaque représentant le même dessin que celui sur la carte supérieure de sa pile. Pour cela il commence toujours par faire coulisser une rangée ou une colonne du labyrinthe en insérant la plaque supplémentaire du bord vers l’intérieur du plateau, puis il déplace son pion.\n" +
		"Ainsi, un tour se compose toujours de deux phases :\n" +		
		" - Modification des couloirs (Introduction de la carte couloir supplémentaire)\n" +
		" - Déplacement du pion\n", TextAlignment.JUSTIFY);

		/** PARAGRAPHE Phase 1 **/
		Label labelModification = Layout.labelBuilder("Phase 1 : Modification des couloirs", Pos.TOP_LEFT);
		Text textModification = Layout.textBuilder("12 flèches sont dessinées en bordure de plateau. Elles indiquent les rangées et colonnes où peut être insérée la plaque supplémentaire pour modifier les couloirs du labyrinthe.\n" + 
		"Quand vient son tour, le joueur choisit l’une de ces rangées ou colonnes et pousse la plaque supplémentaire vers l’intérieur du plateau jusqu’à ce qu’une nouvelle plaque soit expulsée à l’opposé. La plaque expulsée reste au bord du plateau jusqu’à ce qu’elle soit réintroduite à un autre endroit par le joueur suivant.\n" +
		"Ce dernier n’a cependant pas le droit de réintroduire la plaque Couloir à l’endroit d’où elle vient d’être expulsée !\n" +		
		"Un joueur est toujours obligé de modifier le labyrinthe avant de déplacer son pion, même s’il aurait pu atteindre le dessin recherché sans déplacer les couloirs.\n" +
		"Si, en faisant coulisser les couloirs du labyrinthe, un joueur expulse son pion ou un pion adverse du plateau, il est alors replacé à l’opposé, sur la plaque qui vient d’être introduite.\n" +
		"Mais ceci n’est pas considéré comme un déplacement du pion !\n", TextAlignment.JUSTIFY);

		/** PARAGRAPHE Phase 2 **/
		Label labelDeplacement = Layout.labelBuilder("Phase 2 : Déplacement du pion", Pos.TOP_LEFT);
		Text textDeplacement = Layout.textBuilder("Dès qu’il a modifié le labyrinthe, le joueur peut déplacer son pion. Il peut le déplacer aussi loin qu’il veut jusqu’à n’importe quelle plaque en suivant un couloir ininterrompu.\n" + 
		"Un joueur peut même s’arrêter sur une case déjà occupée. S’il veut, il peut aussi choisir de rester sur place ; il n’est pas obligé de se déplacer.\n" +
		"Si le joueur n’atteint pas le dessin recherché (= celui figurant sur la carte supérieure de sa pile), il peut déplacer son pion aussi loin qu’il veut de manière à être en bonne position pour le prochain tour.\n" +		
		"S’il atteint le dessin recherché, il retourne sa carte à côté de sa pile. Il peut immédiatement regarder secrètement la carte suivante de sa pile pour connaître son prochain objectif.\n" +
		"C’est maintenant au tour du joueur suivant de jouer. Lui aussi procède de la même façon : il introduit la carte Couloir supplémentaire, puis déplace son pion en essayant d’atteindre son objectif.\n", TextAlignment.JUSTIFY);

		/** PARAGRAPHE Fin de partie **/
		Label labelFin = Layout.labelBuilder("Fin de partie", Pos.TOP_LEFT);
		Text textFin = Layout.textBuilder("La partie s’arrête dès qu’un joueur a atteint tous ses objectifs et qu’il est revenu à son point de départ. C’est lui qui a su se déplacer le mieux dans le labyrinthe et il remporte la partie !\n", TextAlignment.JUSTIFY);
		

		/** BOUTON RETOUR **/
		Button btnRetour = Layout.buttonBuilder("Retour", new ReglesController<>(stage));


		/** GROUPE LES PARAGRAPHES **/
		VBox rules = new VBox(10);
		rules.setAlignment(Pos.CENTER);
		rules.getChildren().addAll(labelBut, textBut, labelPreparation, textPreparation, labelDeroulement, textDeroulement, labelModification, textModification, labelDeplacement, textDeplacement, labelFin, textFin);
		
		/** SCROLLPANE DU GROUPE **/
		ScrollPane sp = new ScrollPane();
		sp.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-padding: 0px 0px 0px 25px; -fx-border-color: white;"); 
		sp.setMaxWidth(1200);
		sp.setPrefHeight(screenBounds.getHeight()-300);
		sp.setContent(rules);

		/** GROUPE LE SCROLLPANE ET LE BOUTON **/
		VBox vb = new VBox(50);
		vb.setAlignment(Pos.CENTER);
		vb.getChildren().addAll(sp, btnRetour);


		bp.setCenter(vb);

		stage.setScene(scene);
		stage.show();
	}
}
