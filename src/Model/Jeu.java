package Model;

import java.util.ArrayList;
import java.util.List;

/** INTERFACE DU JEU **/
public interface Jeu {
    public void modifierCouloirs(PositionInsertion pos, Orientation orientation);
    public void enregistrer(int nbJoueurs, int... ages);
    public ArrayList<Couloir> couloirs();
    public Plateau getPlateau();
    public CouloirMobile getSupplementaire();
    public void setSupplementaire(CouloirMobile supp);
    public void run();
    public List<String> getHolder();
    public PositionInsertion getOrigine();
    public ArrayList<Joueur> getJoueurs();
}
