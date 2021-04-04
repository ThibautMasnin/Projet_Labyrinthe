package Model;

public class CouloirFixe extends CouloirImpl {

    protected CouloirFixe(Forme forme, Orientation orientation, Objectif objectif) {
        super(forme, orientation, objectif);
    }

    protected CouloirFixe(Forme forme, Orientation orientation) {
        super(forme, orientation);
    }
}
