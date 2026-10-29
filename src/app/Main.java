package app;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;
import model.Client;
import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import model.Operation;
import model.TypePiece;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Client> clients = new ArrayList<>();
    private static final List<Compte> comptes = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        chargerDonneesTest();

        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireEntier("Votre choix : ");
            try {
                switch (choix) {
                    case 1 -> creerClient();
                    case 2 -> ouvrirCompte();
                    case 3 -> listerComptes();
                    case 4 -> deposer();
                    case 5 -> retirer();
                    case 6 -> virer();
                    case 7 -> afficherHistorique();
                    case 0 -> continuer = false;
                    default -> System.out.println("Choix inconnu.");
                }
            } catch (MontantInvalideException | SoldeInsuffisantException
                     | IllegalArgumentException | DateTimeException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
        System.out.println("Merci d'avoir choisi Nexo Bank. À bientôt !");
    }

    private static void afficherMenu() {
        System.out.println("\n=== NEXO BANK ===");
        System.out.println("1. Créer un client");
        System.out.println("2. Ouvrir un compte");
        System.out.println("3. Lister les comptes");
        System.out.println("4. Déposer");
        System.out.println("5. Retirer");
        System.out.println("6. Faire un virement");
        System.out.println("7. Afficher l'historique d'un compte");
        System.out.println("0. Quitter");
    }

    private static void chargerDonneesTest() {
        Client marie = new Client("Dupont", "Marie", LocalDate.of(1990, 4, 12), "12 rue des Lilas",
                "91220", "Brétigny-sur-Orge", "marie@mail.fr", "0612345678",
                TypePiece.CNI, "190491234567");
        clients.add(marie);
        comptes.add(new CompteCourant("FR001", new BigDecimal("300.00"), LocalDate.now(),
                marie, new BigDecimal("200.00")));
        comptes.add(new CompteEpargne("FR002", new BigDecimal("1000.00"), LocalDate.now(),
                marie, new BigDecimal("0.02")));
    }

    private static String lireTexte(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    private static int lireEntier(String message) {
        try {
            return Integer.parseInt(lireTexte(message));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static BigDecimal lireMontant(String message) {
        try {
            return new BigDecimal(lireTexte(message).replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Montant non numérique");
        }
    }

    private static Compte trouverCompte(String numero) {
        for (Compte c : comptes) {
            if (c.getNumero().equalsIgnoreCase(numero)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Compte introuvable : " + numero);
    }

    private static TypePiece lireTypePiece() {
        while (true) {
            String saisie = lireTexte("Type de pièce (CNI, PASSEPORT ou TITRE_SEJOUR) : ").toUpperCase();
            try {
                return TypePiece.valueOf(saisie);
            } catch (IllegalArgumentException e) {
                System.out.println("Type inconnu : choisissez CNI, PASSEPORT ou TITRE_SEJOUR.");
            }
        }
    }

    private static void creerClient() {
        String nom = lireTexte("Nom : ");
        String prenom = lireTexte("Prénom : ");
        LocalDate naissance = LocalDate.parse(lireTexte("Date de naissance (AAAA-MM-JJ) : "));
        String adresse = lireTexte("Adresse : ");
        String codePostal = lireTexte("Code postal : ");
        String ville = lireTexte("Ville : ");
        String email = lireTexte("Email : ");
        String telephone = lireTexte("Téléphone : ");
        TypePiece typePiece = lireTypePiece();
        String numeroPiece = lireTexte("Numéro de pièce : ");

        Client client = new Client(nom, prenom, naissance, adresse, codePostal, ville,
                email, telephone, typePiece, numeroPiece);
        if (client.getAge() < 18) {
            throw new IllegalArgumentException("Le client doit être majeur");
        }
        clients.add(client);
        System.out.println("Client créé : " + client.getNomComplet());
    }

    private static void ouvrirCompte() {
        if (clients.isEmpty()) {
            System.out.println("Créez d'abord un client.");
            return;
        }
        for (int i = 0; i < clients.size(); i++) {
            System.out.println((i + 1) + ". " + clients.get(i).getNomComplet());
        }
        int choixClient = lireEntier("Numéro du client : ");
        if (choixClient < 1 || choixClient > clients.size()) {
            throw new IllegalArgumentException("Client inexistant");
        }
        Client client = clients.get(choixClient - 1);

        String numero = lireTexte("Numéro du nouveau compte : ");
        for (Compte c : comptes) {
            if (c.getNumero().equalsIgnoreCase(numero)) {
                throw new IllegalArgumentException("Ce numéro de compte existe déjà");
            }
        }

        int type = lireEntier("Type de compte (1 = courant, 2 = épargne) : ");
        Compte compte;
        if (type == 1) {
            BigDecimal decouvert = lireMontant("Découvert autorisé : ");
            compte = new CompteCourant(numero, BigDecimal.ZERO, LocalDate.now(), client, decouvert);
        } else if (type == 2) {
            BigDecimal taux = lireMontant("Taux d'intérêt (ex. 0.02) : ");
            compte = new CompteEpargne(numero, BigDecimal.ZERO, LocalDate.now(), client, taux);
        } else {
            throw new IllegalArgumentException("Type de compte inconnu");
        }
        comptes.add(compte);
        System.out.println("Compte ouvert : " + compte);
    }

    private static void listerComptes() {
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte.");
            return;
        }
        for (Compte c : comptes) {
            System.out.println(c);
        }
    }

    private static void deposer() throws MontantInvalideException {
        Compte c = trouverCompte(lireTexte("Numéro du compte : "));
        c.deposer(lireMontant("Montant : "));
        System.out.println("Nouveau solde : " + c.getSolde() + " EUR");
    }

    private static void retirer() throws SoldeInsuffisantException, MontantInvalideException {
        Compte c = trouverCompte(lireTexte("Numéro du compte : "));
        c.retirer(lireMontant("Montant : "));
        System.out.println("Nouveau solde : " + c.getSolde() + " EUR");
    }

    private static void virer() throws SoldeInsuffisantException, MontantInvalideException {
        Compte source = trouverCompte(lireTexte("Compte source : "));
        Compte destination = trouverCompte(lireTexte("Compte destination : "));
        if (source == destination) {
            throw new IllegalArgumentException("Les deux comptes doivent être différents");
        }
        source.virer(destination, lireMontant("Montant : "));
        System.out.println("Solde de " + source.getNumero() + " : " + source.getSolde() + " EUR");
        System.out.println("Solde de " + destination.getNumero() + " : " + destination.getSolde() + " EUR");
    }

    private static void afficherHistorique() {
        Compte c = trouverCompte(lireTexte("Numéro du compte : "));
        List<Operation> historique = c.getHistorique();
        if (historique.isEmpty()) {
            System.out.println("Aucune opération.");
            return;
        }
        for (Operation o : historique) {
            System.out.println(o);
        }
    }
}