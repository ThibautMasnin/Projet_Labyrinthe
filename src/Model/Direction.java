package Model;

/** ENUM DES DIRECTIONS VERS LESQUELLES PEUVENT ALLER LES ROUTES DES COULOIRS **/
public enum Direction {
    HAUT, BAS, DROITE, GAUCHE;    

    public Direction oppose() {
        // Retourne la direction opposee
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
