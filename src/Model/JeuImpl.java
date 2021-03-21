package Model;

import java.util.ArrayList;
import java.util.Collection;

public class JeuImpl implements Jeu {
    private CouloirMobile supplementaire;
    PositionInsertion positionOrigine;
    private Collection<Joueur> joueurs = new ArrayList<>();
    private Objectif[] objectifs;
    private ArrayList<CouloirMobile> couloirsMobiles;
    private Plateau plateau;

    @Override
    public void modifierCouloirs(PositionInsertion pos, Orientation orientation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void enregistrer(int nbJoueurs, int... ages) {
        for(int i=0; i<nbJoueurs; i++) {
            Joueur joueur = new JoueurImpl(this, ages[i]);
            joueur.recevoirPion(new PionImpl(Couleur.values()[i], this.plateau));
            joueurs.add(joueur);
            System.out.println(joueur.toString());
        }
    }

    @Override
    public ArrayList<Couloir> couloirs() {
        // TODO Auto-generated method stub
        return null;
    }

    private void preparer() {
        this.plateau = new Plateau();
        this.objectifs = Objectif.values();
        // TODO
    }

    void jouer() {
        // TODO
        preparer();
        Joueur joueur = ((ArrayList<Joueur>) this.joueurs).get(0);
        do{
            joueur=prochainJoueur(joueur);
            joueur.joue();

        }while(!aGagne(joueur));
    }

    private Joueur prochainJoueur(Joueur joueur){
        // TODO
        return ((ArrayList<Joueur>) this.joueurs).get(((((ArrayList<Joueur>) this.joueurs).indexOf(joueur))+1) % (this.joueurs.size()));
    }

    private boolean aGagne(Joueur joueur){
        return joueur.getObjectifs().isEmpty();
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
