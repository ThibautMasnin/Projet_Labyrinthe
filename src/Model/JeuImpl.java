package Model;

import View.BoardView;
import View.EndView;

import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class JeuImpl extends Thread implements Jeu {
    private final List<String> holder = new LinkedList<>();
    private final JFrame frame;
    private static final Random RAND = new Random();
    private CouloirMobile supplementaire;
    private PositionInsertion positionOrigine = null;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private ArrayList<Objectif> objectifs = new ArrayList<>();
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<>();
    private final Plateau plateau = new Plateau();


    public JeuImpl(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public List<String> getHolder() {
        return this.holder;
    }

    @Override
    public void modifierCouloirs(PositionInsertion position, Orientation orientation) {
        supplementaire.changerOrientation(orientation);
        this.positionOrigine = position.oppose();
        this.supplementaire = plateau.modifierCouloirs(position, supplementaire);
    }

    @Override
    public void enregistrer(int nbJoueurs, int... ages) {
        if(positionOrigine==null) {
            for(int i=0; i<nbJoueurs; i++) {
                Joueur joueur = new JoueurImpl(this, ages[i]);
                joueur.recevoirPion(new PionImpl(Couleur.values()[i], this.plateau));
                this.joueurs.add(joueur);
            }
        }
    }

    @Override
    public ArrayList<Couloir> couloirs() {
        return plateau.getCouloirs();
    }

    private void preparer() {

	    /** CREATION DES COULOIRS MOBILES **/
        Orientation[] orientations = Orientation.values();
        for(int i=0; i<12; i++) {
            couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.I, orientations[RAND.nextInt(orientations.length)]));
        }
        for(int i=0; i<10; i++) {
            couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)]));
        }
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.LEZARD));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.HIBOUX));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.SCARABEE));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.PAPILLON));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.ARAIGNEE));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.L, orientations[RAND.nextInt(orientations.length)], Objectif.SOURIS));
        
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.FEE));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.FANTOME));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.CHAUVE_SOURIS));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.GENIE));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.DRAGON));
        couloirsMobiles.add(CouloirImpl.couloirMobileBuilder(Forme.T, orientations[RAND.nextInt(orientations.length)], Objectif.GNOME));

	    /** AJOUT DES COULOIRS MOBILES AU PLATEAU **/
        this.supplementaire = this.plateau.addCouloirsMobiles(couloirsMobiles);
        this.positionOrigine=null;

	    /** CREATION DES OBJECTIFS **/
        Objectif[] tabObjectifs = Objectif.values();
        for(int i=0; i < tabObjectifs.length; i++) {
                this.objectifs.add(tabObjectifs[i]);
        }

	    /** DISTRIBUTION ALEATOIRE DES OBJECTIFS **/
        for(int i=0; i<joueurs.size(); i++) {
            ArrayList<Objectif> objectifsJoueur = new ArrayList<>();
            for(int j=0; j<tabObjectifs.length/joueurs.size(); j++) {
                objectifsJoueur.add(this.objectifs.remove((int)(RAND.nextDouble() * this.objectifs.size())));
            }
            joueurs.get(i).fixerObjectifs(objectifsJoueur);
        }
    }

    @Override
    public void run() {
        boolean first=true;

        preparer();
        Joueur joueur=joueurs.get(0);
        //Selectionne le joueur le plus jeune
        for(int i=1; i<joueurs.size(); i++) {
            if(joueurs.get(i).getAge()<joueur.getAge()) {
                joueur=joueurs.get(i);
            }
        }
        //Met le joueur le plus jeune au debut de la liste
        while(!joueur.equals(prochainJoueur()));
        
        for(Joueur j : joueurs) {
            plateau.getCouloir(j.getPion().getPositionCourante()).getPions().add(j.getPion());
        }

        //Boucle tant qu'aucun joueur n'a gagne
        do {
            if(!first) {            
                joueur = prochainJoueur();
            }
            else {
                first = false;
            }
            new BoardView(frame, this, joueur);
            joueur.modifieCouloir();
            new BoardView(frame, this, joueur);
            joueur.deplacePion();
        } while(!aGagne(joueur));
        new EndView(frame, joueur);
    }

    private Joueur prochainJoueur(){
        joueurs.add(joueurs.remove(0));
        return joueurs.get(0);
    }

    private boolean aGagne(Joueur joueur){
        return joueur.getObjectifs().isEmpty() && joueur.getPion().getPositionCourante().equals(joueur.getPion().getPositionInitiale());
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

    @Override
    public CouloirMobile getSupplementaire() {
        return this.supplementaire;
    }

    @Override
    public void setSupplementaire(CouloirMobile supplementaire) {
        this.supplementaire = supplementaire;
    }

    @Override
    public PositionInsertion getOrigine() {
        return this.positionOrigine;
    }

    @Override
    public ArrayList<Joueur> getJoueurs() {
        return this.joueurs;
    }
}
