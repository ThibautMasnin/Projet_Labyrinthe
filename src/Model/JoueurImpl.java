package Model;

import java.util.ArrayList;
import java.util.Collection;

public class JoueurImpl implements Joueur {
    private Jeu jeu;
    private Pion pion;
    private int age;
    private Collection<Objectif> objectifs;


    public JoueurImpl(Jeu jeu, int age) {
        this.age = age;
        this.jeu = jeu;
        this.objectifs = new ArrayList<>();
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void joue() {
        // TODO Auto-generated method stub
    }

    @Override
    public void fixerObjectifs(ArrayList<Objectif> objectifs) {
        this.objectifs=objectifs;
    }

    @Override
    public void recevoirPion(Pion p) {
        this.pion=p;
    }

    private PositionInsertion choisirPositionInsertionCouloir() {
        // TODO
        return null;
    }

    private Orientation choisirOrientationCouloir() {
        // TODO
        return null;
    }

    @Override
    public String toString() {
        return "Age : " + this.age + ", Couleur : " + this.pion.getCouleur() ;
    }
}
