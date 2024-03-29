package Model;

import java.util.ArrayList;

/** INTERFACE DES COULOIRS **/
public interface Couloir {
    public Orientation getOrientation();
    public Forme getForme();
    public Objectif getObjectif();
    public ArrayList<Pion> getPions();
    public void setOrientation(Orientation orientation);
    public ArrayList<Direction> getDirections();
}
