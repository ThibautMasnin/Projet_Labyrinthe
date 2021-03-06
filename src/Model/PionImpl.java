package Model;

public class PionImpl implements Pion {
    private Couleur couleur;
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;

    public PionImpl(Couleur couleur, Plateau plateau, Position positionInitiale) {
        this.couleur = couleur;
        this.plateau = plateau;
        this.positionInitiale = positionInitiale;
        this.positionCourante = positionInitiale;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    @Override
    public Objectif deplacer(Position pos) {
        // TODO Auto-generated method stub
        return null;
    }

    public static void poserA(Position pos) {
        // TODO
    }
}
