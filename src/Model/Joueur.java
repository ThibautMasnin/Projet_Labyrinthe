package Model;

import java.util.ArrayList;

public interface Joueur {
    public int getAge();
    public void joue();
    public void fixerObjectifs(ArrayList<Objectif> objectifs);
    public void recevoirPion(Pion p);
}
