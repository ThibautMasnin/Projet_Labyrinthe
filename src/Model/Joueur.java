package Model;

import java.util.ArrayList;
import java.util.Collection;

/** INTERFACE DES JOUEURS **/
public interface Joueur {
    public int getAge();
    public void modifieCouloir();
    public void deplacePion();
    public void fixerObjectifs(ArrayList<Objectif> objectifs);
    public void recevoirPion(Pion p);
    public Collection<Objectif> getObjectifs();
    public Objectif getObjectifActuel();
    public Pion getPion();
}
