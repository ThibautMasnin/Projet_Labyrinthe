package Model;

import java.util.ArrayList;

public class CouloirImpl implements Couloir {
    private Forme forme;
    private Orientation orientation;
    private Objectif objectif;

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
        // TODO Auto-generated method stub
        return null;
    }

    public static void decaler(Orientation orientation) {
        // TODO
    }
}
