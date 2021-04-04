package Model;

public class CouloirMobile extends CouloirImpl {

    private boolean posee = false;

    protected CouloirMobile(Forme forme, Orientation orientation, Objectif objectif) {
        super(forme, orientation, objectif);
    }

    protected CouloirMobile(Forme forme, Orientation orientation) {
        super(forme, orientation);
    }

    public void poser() {
        this.posee=true;
    }

    public void retirer() {
        getPions().clear();
        this.posee=false;
    }

    public void changerOrientation(Orientation orientation) {
        if(!posee) {
            this.setOrientation(orientation);
        }
    }
    
}
