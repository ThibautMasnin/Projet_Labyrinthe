package Model;

import java.util.ArrayList;

public interface Jeu {
    public void modifierCouloirs(PositionInsertion pos, Orientation orientation);
    public void enregistrer(int nbJoueurs, int... ages);
    public ArrayList<Couloir> couloirs();
    public Plateau getPlateau();
}
