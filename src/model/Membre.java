package model;
import java.util.ArrayList;

public class Membre {
    private String prenom;
    private double prixSeuil;
    private ArrayList<Billet> billets;

    public Membre(String prenom, double prixSeuil) {
        this.prenom = prenom;
        this.prixSeuil = prixSeuil;
        this.billets = new ArrayList<Billet>();
    }

    public String getPrenom() {
        return prenom;
    }

    public double getPrixSeuil() {
        return prixSeuil;
    }

    public void addBillet(Billet b) {
        this.billets.add(b);
    }

    public void removeBillet(Billet b) {
        this.billets.remove(b);
    }

    public ArrayList<Billet> getBillets() {
        return billets;
    }

    public String billetsToString() {
        String s = this.prenom + " a acheté les billets suivants : \n";
        for (Billet b : this.billets) {
            s += b.toString() + "\n";
        }
        return s;
    }
}
