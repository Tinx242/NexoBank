package model;

import java.time.LocalDate;
import java.time.Period;

public class Client {

    private int idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String adresse;
    private String codePostal;
    private String ville;
    private String email;
    private String telephone;
    private TypePiece typePiece;
    private String numeroPiece;

    public Client(String nom, String prenom, LocalDate dateNaissance, String adresse,
                  String codePostal, String ville, String email, String telephone,
                  TypePiece typePiece, String numeroPiece) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.email = email;
        this.telephone = telephone;
        this.typePiece = typePiece;
        this.numeroPiece = numeroPiece;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumeroPiece() {
        return numeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        this.numeroPiece = numeroPiece;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public TypePiece getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(TypePiece typePiece) {
        this.typePiece = typePiece;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }
}