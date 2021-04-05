package Model;

/** INTERFACE DES PIONS **/
public interface Pion {
    public Objectif deplacer(Position pos);
    public void poserA(Position pos);
    public Couleur getCouleur();
    public Position getPositionInitiale();
    public Position getPositionCourante();
    public void setPositionCourante(Position pos);
}
