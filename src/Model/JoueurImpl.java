package Model;

import java.util.ArrayList;
import java.util.Collection;

public class JoueurImpl implements Joueur {
    private Jeu jeu;
    private Pion pion;
    private int age;
    private Collection<Objectif> objectifs;


    public JoueurImpl(Jeu jeu, int age) {
        this.age = age;
        this.jeu = jeu;
        this.objectifs = new ArrayList<>();
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public Pion getPion() {
        return this.pion;
    }

    @Override
    public void modifieCouloir() {
        PositionInsertion pos = null;
        Orientation ori = null;
        String holder = null;
        while(ori==null || pos==null) {
            synchronized (jeu.getHolder()) {
                while (jeu.getHolder().isEmpty()) {
                    try {
                        jeu.getHolder().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
                holder = jeu.getHolder().remove(0);
            }   
            if(choisirOrientationCouloir(holder)!=null) {
                ori = choisirOrientationCouloir(holder);
            }
            else if(ori!=null && choisirPositionInsertionCouloir(holder)!=null) {
                pos = choisirPositionInsertionCouloir(holder);
            } 
        }
        jeu.modifierCouloirs(pos, ori);
    }

    @Override
    public void deplacePion() {
        String sPos = null;
        while(sPos==null || sPos.length()!=3 || sPos.charAt(1)!=',' || !jeu.getPlateau().estAtteignable(pion.getPositionCourante(), new Position(Character.getNumericValue(sPos.charAt(0)), Character.getNumericValue(sPos.charAt(2))))) {
            synchronized (jeu.getHolder()) {
                while (jeu.getHolder().isEmpty()) {
                    try {
                        jeu.getHolder().wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
                sPos = jeu.getHolder().remove(0);
            }    
        }
        Objectif obj = this.pion.deplacer(new Position(Character.getNumericValue(sPos.charAt(0)), Character.getNumericValue(sPos.charAt(2))));
        if(!objectifs.isEmpty() && obj == getObjectifActuel()) {
            objectifs.remove(obj);
        }
    }

    @Override
    public void fixerObjectifs(ArrayList<Objectif> objectifs) {
        this.objectifs=objectifs;
    }

    @Override
    public void recevoirPion(Pion p) {
        this.pion=p;
    }

    private Orientation choisirOrientationCouloir(String sOri) {
        if("H".equals(sOri)) {
            return Orientation.HAUT;
        }
        else if("B".equals(sOri)) {
            return Orientation.BAS;
        }
        else if("G".equals(sOri)) {
            return Orientation.GAUCHE;
        }
        else if("D".equals(sOri)) {
            return Orientation.DROITE;
        }
        return null;
    }

    private PositionInsertion choisirPositionInsertionCouloir(String sPos) {
        if("H1".equals(sPos)) {
            return PositionInsertion.H1;
        }
        else if("H3".equals(sPos)) {
            return PositionInsertion.H3;
        }
        else if("H5".equals(sPos)) {
            return PositionInsertion.H5;
        }
        else if("D1".equals(sPos)) {
            return PositionInsertion.D1;
        }
        else if("D3".equals(sPos)) {
            return PositionInsertion.D3;
        }
        else if("D5".equals(sPos)) {
            return PositionInsertion.D5;
        }
        else if("B1".equals(sPos)) {
            return PositionInsertion.B1;
        }
        else if("B3".equals(sPos)) {
            return PositionInsertion.B3;
        }
        else if("B5".equals(sPos)) {
            return PositionInsertion.B5;
        }
        else if("G1".equals(sPos)) {
            return PositionInsertion.G1;
        }
        else if("G3".equals(sPos)) {
            return PositionInsertion.G3;
        }
        else if("G5".equals(sPos)) {
            return PositionInsertion.G5;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Age : " + this.age;
    }

    public Collection<Objectif> getObjectifs(){
        return this.objectifs;
    }

    public Objectif getObjectifActuel(){
        return this.objectifs.iterator().next();
    }
}
