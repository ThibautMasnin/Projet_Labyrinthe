package Model;

/** ENUM DES POSITIONS AUXQUELLES LE JOUEUR PEUT INSERER LE COULOIR SUPPLEMENTAIRE **/
public enum PositionInsertion {
    H1, H3, H5, B1, B3, B5, D1, D3, D5, G1, G3, G5;

    public PositionInsertion oppose() {
        switch(this) {
            case H1:
                return B1;
            case H3:
                return B3;
            case H5:
                return B5;
            case B1:
                return H1;
            case B3:
                return H3;
            case B5:
                return H5;
            case D1:
                return G1;
            case D3:
                return G3;
            case D5:
                return G5;
            case G1:
                return D1;
            case G3:
                return D3;
            case G5:
                return D5;
            default:
                return null;
        }        
    }
}
