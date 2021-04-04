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
            this.positionInitiale = new Position(0,0);
            this.positionCourante = new Position(0,0);           
        }
        else if(couleur==Couleur.VERT) {
            this.positionInitiale = new Position(6,6);
            this.positionCourante = new Position(6,6);  
        }
        else if(couleur==Couleur.BLEU) {
            this.positionInitiale = new Position(0,6);
            this.positionCourante = new Position(0,6);  
        }
        else {
            this.positionInitiale = new Position(6,0);
            this.positionCourante = new Position(6,0);  
        }
    }
    
    @Override
    public Couleur getCouleur() {
        return this.couleur;
    }
    
    @Override
    public Position getPositionCourante() {
        return this.positionCourante;
    }
    
    @Override
    public Position getPositionInitiale() {
        return this.positionInitiale;
    }

    @Override
    public Objectif deplacer(Position pos) {
        plateau.getCouloir(positionCourante).getPions().remove(this);
        positionCourante = pos;
        plateau.getCouloir(positionCourante).getPions().add(this);
        return plateau.getCouloir(pos).getObjectif();
    }

    @Override
    public void poserA(Position pos) {
        plateau.getCouloir(positionCourante).getPions().remove(this);
        positionCourante = pos;
        plateau.getCouloir(positionCourante).getPions().add(this);
    }
    
    @Override
    public void setPositionCourante(Position pos) {
        this.positionCourante = pos;
    }
}
