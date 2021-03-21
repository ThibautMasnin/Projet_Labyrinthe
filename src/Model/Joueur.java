package Model;

import java.util.ArrayList;
import java.util.Collection;

public interface Joueur {
    public int getAge();
    public void joue();
    public void fixerObjectifs(ArrayList<Objectif> objectifs);
    public void recevoirPion(Pion p);
    public Collection<Objectif> getObjectifs();
}
