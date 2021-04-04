package Model;

public enum Direction {
    HAUT, BAS, DROITE, GAUCHE;    

    public Direction oppose() {
        switch(this) {
            case HAUT:
                return BAS;
            case DROITE:
                return GAUCHE;
            case BAS:
                return HAUT;
            case GAUCHE:
                return DROITE;
        }
        return null;
    }
}
