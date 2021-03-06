package Model;

import java.util.ArrayList;

public class JoueurImpl implements Joueur {
    private Pion pion;
    private int age;
    private Jeu jeu;
    private ArrayList<Objectif> objectifs;

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
}
