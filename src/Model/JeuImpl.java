package Model;

import java.util.ArrayList;

public class JeuImpl implements Jeu {
    CouloirMobile supplementaire;
    PositionInsertion positionOrigine;
    ArrayList<Joueur> joueurs;
    ArrayList<Pion> pions;
    ArrayList<Objectif> objectifs;
    ArrayList<CouloirMobile> couloirsMobiles;
    Plateau plateau;

    @Override
    public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enregistrer(Joueur joueur, Couleur couleur) {
        // TODO Auto-generated method stub

    }

    @Override
    public ArrayList<Couloir> couloirs() {
        // TODO Auto-generated method stub
        return null;
    }
}
