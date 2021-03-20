package Model;

public class PionImpl implements Pion {
    private Couleur couleur;
    private Plateau plateau;
    private Position positionInitiale;
    private Position positionCourante;

    public PionImpl(Couleur couleur, Plateau plateau) {
        this.couleur = couleur;
        this.plateau = plateau;
        if(couleur==Couleur.ROUGE) {
            this.positionInitiale = new Position(1,1);
            this.positionCourante = new Position(1,1);           
        }
        else if(couleur==Couleur.VERT) {
            this.positionInitiale = new Position(7,7);
            this.positionCourante = new Position(7,7);  
        }
        else if(couleur==Couleur.BLEU) {
            this.positionInitiale = new Position(1,7);
            this.positionCourante = new Position(1,7);  
        }
        else {
            this.positionInitiale = new Position(7,1);
            this.positionCourante = new Position(7,1);  
        }
    }
    
    @Override
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
