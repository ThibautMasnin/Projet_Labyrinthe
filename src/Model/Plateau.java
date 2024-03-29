package Model;

import java.util.ArrayList;
import java.util.Random;

public class Plateau {
    private final Couloir[][] couloirs;

    public Plateau() {
        this.couloirs = new Couloir[7][7];
        
        // Initialisation des couloirs fixes et ajout au plateau
        couloirs[0][0] = CouloirImpl.couloirFixeBuilder(Forme.L, Orientation.DROITE);
        couloirs[0][2] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.HAUT, Objectif.LIVRE);
        couloirs[0][4] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.HAUT, Objectif.BOURSE);
        couloirs[0][6] = CouloirImpl.couloirFixeBuilder(Forme.L, Orientation.BAS);
        
        couloirs[2][0] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.GAUCHE, Objectif.CARTE);
        couloirs[2][2] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.GAUCHE, Objectif.COURONNE);
        couloirs[2][4] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.HAUT, Objectif.CLES);
        couloirs[2][6] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.DROITE, Objectif.CRANE);
        
        couloirs[4][0] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.GAUCHE, Objectif.BAGUE);
        couloirs[4][2] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.BAS, Objectif.TRESOR);
        couloirs[4][4] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.DROITE, Objectif.EMERAUDE);
        couloirs[4][6] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.DROITE, Objectif.EPEE);
        
        couloirs[6][0] = CouloirImpl.couloirFixeBuilder(Forme.L, Orientation.HAUT);
        couloirs[6][2] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.BAS, Objectif.CHANDELIER);
        couloirs[6][4] = CouloirImpl.couloirFixeBuilder(Forme.T, Orientation.BAS, Objectif.CASQUE);
        couloirs[6][6] = CouloirImpl.couloirFixeBuilder(Forme.L, Orientation.GAUCHE);
    }

    /** AJOUTE LES COULOIRS MOBILES ALEATOIREMENT SUR LE PLATEAU, RETOURNE LE COULOIR SUPPLEMENTAIRE **/
    public CouloirMobile addCouloirsMobiles(ArrayList<CouloirMobile> couloirsMobiles) {
        for(int i=0; i<7; i++) {
            for(int j=0; j<7; j++) {
                if(i%2!=0 || j%2!=0) {
                    couloirs[i][j] = couloirsMobiles.remove((int) (new Random().nextDouble() * couloirsMobiles.size()));
                    ((CouloirMobile)couloirs[i][j]).poser();
                }
            }
        }
        return couloirsMobiles.get(0);
    }

    /** INSERE LE COULOIR SUPPLEMENTAIRE SUR LE PLATEAU A LA POSITION ET RETOURNE UN NOUVEAU COULOIR SUPPLEMENTAIRE **/
    public CouloirMobile modifierCouloirs(PositionInsertion position, CouloirMobile supplementaire) {
        int i, j;
        CouloirMobile tmpCouloir;
        supplementaire.poser();
        switch(position) {
            case H1:
            case H3:
            case H5:
                j = Character.getNumericValue(position.name().charAt(1));
                // Boucle sur la colonne pour deplacer les couloirs
                for(i=0; i<7; i++) {
                    tmpCouloir = (CouloirMobile)couloirs[i][j];
                    couloirs[i][j] = supplementaire;
                    supplementaire = tmpCouloir;
                    // Met a jour la position du couloir deplace
                    for(Pion p : couloirs[i][j].getPions()) {
                        p.setPositionCourante(new Position(i,j));
                    }
                }
                break;
            case D1:
            case D3:
            case D5:
                i = Character.getNumericValue(position.name().charAt(1));
                // Boucle sur la ligne pour deplacer les couloirs
                for(j=6; j>=0; j--) {
                    tmpCouloir = (CouloirMobile)couloirs[i][j];
                    couloirs[i][j] = supplementaire;
                    supplementaire = tmpCouloir;
                    // Met a jour la position du couloir deplace
                    for(Pion p : couloirs[i][j].getPions()) {
                        p.setPositionCourante(new Position(i,j));
                    }
                }
                break;
            case B1:
            case B3:
            case B5:           
                j = Character.getNumericValue(position.name().charAt(1)); 
                // Boucle sur la colonne pour deplacer les couloirs
                for(i=6; i>=0; i--) {
                    tmpCouloir = (CouloirMobile)couloirs[i][j];
                    couloirs[i][j] = supplementaire;
                    supplementaire = tmpCouloir;
                    // Met a jour la position du couloir deplace
                    for(Pion p : couloirs[i][j].getPions()) {
                        p.setPositionCourante(new Position(i,j));
                    }
                }
                break;
            case G1:
            case G3:
            case G5:
                i = Character.getNumericValue(position.name().charAt(1));     
                // Boucle sur la ligne pour deplacer les couloirs       
                for(j=0; j<7; j++) {
                    tmpCouloir = (CouloirMobile)couloirs[i][j];
                    couloirs[i][j] = supplementaire;
                    supplementaire = tmpCouloir;
                    // Met a jour la position du couloir deplace
                    for(Pion p : couloirs[i][j].getPions()) {
                        p.setPositionCourante(new Position(i,j));
                    }
                }
                break;
        }
        // Transmet les pions du nouveau couloir supplementaire a l'ancien
        for(Pion pion : supplementaire.getPions()) {
            switch(position) {
                case H1:
                case H3:
                case H5:
                    i = Character.getNumericValue(position.name().charAt(1));        
                    if(new Position(6, i).equals(pion.getPositionCourante())) {
                        pion.setPositionCourante(new Position(0, i));
                        couloirs[0][i].getPions().add(pion);
                    }
                    break;
                case B1:
                case B3:
                case B5:     
                    i = Character.getNumericValue(position.name().charAt(1));        
                    if(new Position(0, i).equals(pion.getPositionCourante())) {
                        pion.setPositionCourante(new Position(6, i));
                        couloirs[6][i].getPions().add(pion);
                    }
                    break;
                case D1:
                case D3:
                case D5:
                    i = Character.getNumericValue(position.name().charAt(1));        
                    if(new Position(i, 0).equals(pion.getPositionCourante())) {
                        pion.setPositionCourante(new Position(i, 6));
                        couloirs[i][6].getPions().add(pion);
                    }       
                    break;
                case G1:
                case G3:
                case G5:
                    i = Character.getNumericValue(position.name().charAt(1));
                    if(new Position(i, 6).equals(pion.getPositionCourante())) {
                        pion.setPositionCourante(new Position(i, 0));
                        couloirs[i][0].getPions().add(pion);
                    }
                    break;      
            }
        }
        supplementaire.retirer();
        return supplementaire;
    }
    
    /** TESTE SI LA DESTINATION EST ATTEIGNABLE DEPUIS L'ORIGINE **/
    public boolean estAtteignable(Position orig, Position dest) {
        ArrayList<Position> atteignables = getAtteignables(orig, new ArrayList<>());
        if(atteignables.contains(dest)) {
            return true;
        }
        return false;
    }

    /** RETOURNE LA LISTE DES POSITIONS ATTEIGNABLES DEPUIS L'ORIGINE **/
    private ArrayList<Position> getAtteignables(Position orig, ArrayList<Position> atteignables) {
        //Ajoute l'origine a la liste
        atteignables.add(orig);
        ArrayList<Direction> directions = couloirs[orig.getX()][orig.getY()].getDirections();
        Position posCible;
        // Boucle sur les directions que peut atteindre les couloir d'origine
        for(Direction direction : directions) {
            // Recupere la position a la direction actuelle
            switch(direction) {
                case HAUT:
                    posCible = new Position(orig.getX()-1, orig.getY());
                    break;
                case DROITE:
                    posCible = new Position(orig.getX(), orig.getY()+1);
                    break;
                case BAS:
                    posCible = new Position(orig.getX()+1, orig.getY());
                    break;
                case GAUCHE:
                    posCible = new Position(orig.getX(), orig.getY()-1);
                    break;
                default:
                    posCible = null;
            }
            // Teste si la position est sur le plateau, est atteignable et n'est pas encore dans la liste
            if(posCible != null && posCible.getX() <= 6  && posCible.getY() <= 6 &&  posCible.getX() >= 0 && posCible.getY() >= 0 && 
                couloirs[posCible.getX()][posCible.getY()].getDirections().contains(direction.oppose()) && !atteignables.contains(posCible)) {
                //Appelle recursivement depuis les nouvelles positions atteignable
                getAtteignables(posCible, atteignables);
            }
        }
        return atteignables;
    }

    /** RETOURNE LA LISTE DES COULOIRS DU PLATEAU **/
    public ArrayList<Couloir> getCouloirs() {
        ArrayList<Couloir> list = new ArrayList<>();
        for(int i=0; i<7; i++) {
            for(int j=0; j<7; j++) {
                list.add(couloirs[i][j]);
            }
        }
        return list;
    }

    public Couloir getCouloir(Position pos) {
        return couloirs[pos.getX()][pos.getY()];
    }

    @Override
    public String toString() {
        String result="";
        for(int i=0; i<7; i++) {
            result += "\n|\t";
            for(int j=0; j<7; j++) {
                result += couloirs[i][j].toString();
                if(couloirs[i][j].getOrientation()==Orientation.BAS || couloirs[i][j].getOrientation()==Orientation.HAUT) {
                    result += "\t";
                }
                result += "\t|\t";
            }
        }
        return result + "\n";
    }
}
