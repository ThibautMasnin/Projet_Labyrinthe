package Model;

import java.util.ArrayList;

public class CouloirImpl implements Couloir {

    private Forme forme;
    private Orientation orientation;
    private Objectif objectif;
    private ArrayList<Pion> pions;

    protected CouloirImpl(Forme forme, Orientation orientation, Objectif objectif) {
        this.forme = forme;
        this.orientation = orientation;
        this.objectif = objectif;
        this.pions = new ArrayList<>();
    }

    protected CouloirImpl(Forme forme, Orientation orientation) {
        this.forme = forme;
        this.orientation = orientation;
        this.objectif = null;
        this.pions = new ArrayList<>();
    }

    public static CouloirFixe couloirFixeBuilder(Forme forme, Orientation orientation, Objectif objectif) {
        return new CouloirFixe(forme, orientation, objectif);
    }

    public static CouloirFixe couloirFixeBuilder(Forme forme, Orientation orientation) {
        return new CouloirFixe(forme, orientation);
    }

    public static CouloirMobile couloirMobileBuilder(Forme forme, Orientation orientation) {
        return new CouloirMobile(forme, orientation);
    }

    public static CouloirMobile couloirMobileBuilder(Forme forme, Orientation orientation, Objectif objectif) {
        return new CouloirMobile(forme, orientation, objectif);
    }


    @Override
    public Forme getForme() {
        return forme;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Objectif getObjectif() {
        return objectif;
    }

    @Override
    public ArrayList<Pion> getPions() {
        return pions;
    }
    
    @Override
    public ArrayList<Direction> getDirections() {
        ArrayList<Direction> directions = new ArrayList<>();
        switch(forme) {
            case T:
                switch(orientation) {
                    case HAUT:
                        directions.add(Direction.BAS);
                        directions.add(Direction.GAUCHE);
                        directions.add(Direction.DROITE);
                        break;
                    case DROITE:
                        directions.add(Direction.BAS);
                        directions.add(Direction.HAUT);
                        directions.add(Direction.GAUCHE);
                        break;
                    case BAS:
                        directions.add(Direction.HAUT);
                        directions.add(Direction.DROITE);
                        directions.add(Direction.GAUCHE);
                        break;
                    case GAUCHE:
                        directions.add(Direction.BAS);
                        directions.add(Direction.HAUT);
                        directions.add(Direction.DROITE);
                        break;
                }
                break;
            case I:
                switch(orientation) {
                    case HAUT:
                    case BAS:
                        directions.add(Direction.BAS);
                        directions.add(Direction.HAUT);
                        break;
                    case DROITE:
                    case GAUCHE:
                        directions.add(Direction.DROITE);
                        directions.add(Direction.GAUCHE);
                        break;
                }
                break;
            case L:
                switch(orientation) {
                    case HAUT:
                        directions.add(Direction.HAUT);
                        directions.add(Direction.DROITE);
                        break;
                    case DROITE:
                        directions.add(Direction.BAS);
                        directions.add(Direction.DROITE);
                        break;
                    case BAS:
                        directions.add(Direction.BAS);
                        directions.add(Direction.GAUCHE);
                        break;
                    case GAUCHE:
                        directions.add(Direction.HAUT);
                        directions.add(Direction.GAUCHE);
                        break;
                }
                break;
        }
        return directions;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return this.forme.toString() + " " + this.orientation.toString();
    }
    
}
