package com.example.javaappesalaf;



public class Table {
    private int id;
    private String nom;
    private String produit;
    private long credit;
    private long quantite;

    public Table(int id, String nom, String produit, long credit, long quantite) {
        this.id = id;
        this.nom = nom;
        this.produit = produit;
        this.credit = credit;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public long getCredit() {
        return credit;
    }

    public void setCredit(long credit) {
        this.credit = credit;
    }

    public long getQuantite() {
        return quantite;
    }

    public void setQuantite(long quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", author='" + produit + '\'' +
                ", produit='" + produit + '\'' +
                ", credit=" + credit +
                ", quantite=" + quantite +
                '}';
    }

}
