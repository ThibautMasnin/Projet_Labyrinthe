package Model;

import java.util.ArrayList;

public interface Jeu {
    public void modifierCouloirs(PositionInsertion pos, Orientation orientation);
    public void enregistrer(Joueur joueur, Couleur couleur);
    public ArrayList<Couloir> couloirs();
}
