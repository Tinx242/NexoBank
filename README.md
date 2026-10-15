# Nexo Bank

Application bancaire développée en **Java** et **MySQL**, conçue pas à pas en **méthode agile** (Scrum simplifié, en solo).

Ce projet a un double objectif : réaliser une application complète et **consolider les bases de la programmation** (variables, opérateurs, conditions, boucles, classes, encapsulation, héritage, interfaces, exceptions, SQL, JDBC, interface graphique).

> **État du projet : en cours.** La phase de conception est avancée, le développement n'a pas encore commencé. Voir [l'avancement](#avancement).

---

## Sommaire

- [Fonctionnalités](#fonctionnalités)
- [Règles de gestion](#règles-de-gestion)
- [Technologies](#technologies)
- [Conception](#conception)
- [Structure du dépôt](#structure-du-dépôt)
- [Méthode de travail](#méthode-de-travail)
- [Avancement](#avancement)
- [Auteur](#auteur)

---

## Fonctionnalités

Deux acteurs utilisent l'application : le **client** et le **conseiller bancaire**.

| Fonctionnalité | Client | Conseiller |
|---|:---:|:---:|
| Créer un client | | ✔ |
| Ouvrir un compte (courant ou épargne) | | ✔ |
| Ajouter les intérêts aux comptes épargne | | ✔ |
| Consulter les comptes | ✔ | ✔ |
| Consulter l'historique des opérations | ✔ | ✔ |
| Déposer de l'argent | ✔ | ✔ |
| Retirer de l'argent | ✔ | ✔ |
| Faire un virement | ✔ | ✔ |

Deux cas d'utilisation optionnels s'ajoutent : **exporter l'historique** et **refuser une opération** (solde insuffisant).

## Règles de gestion

1. Un client possède zéro, un ou plusieurs comptes.
2. Un compte appartient à un seul client.
3. Il existe deux types de comptes : **courant** et **épargne**.
4. Un compte courant peut être à découvert, dans la limite d'un montant autorisé.
5. Un compte épargne ne peut jamais être à découvert et produit des intérêts.
6. Un montant déposé, retiré ou viré doit être strictement positif.
7. Chaque opération est enregistrée avec sa date et son montant.
8. Un virement retire l'argent d'un compte et le dépose sur un autre : si l'une des deux étapes échoue, rien n'est modifié.

## Technologies

| Domaine | Outil |
|---|---|
| Langage | Java (JDK 17 ou plus) |
| Base de données | MySQL / MariaDB via XAMPP et phpMyAdmin |
| IDE | IntelliJ IDEA |
| Versionnement | Git et GitHub Desktop |
| Modélisation | PowerDesigner (MCD), Lucidchart (UML) |

Prévus aux étapes suivantes : JDBC, Swing ou JavaFX, JUnit.

## Conception

La conception est réalisée avant d'écrire le code.

### Dictionnaire de données

Liste de toutes les informations à mémoriser, avec leur type, leur taille et leurs contraintes :
[`docs/dictionnaire-donnees.pdf`](docs/dictionnaire-donnees.pdf)

### MCD et MLD

Trois entités (`CLIENT`, `COMPTE`, `OPERATION`) et deux associations : un client **possède** des comptes, un compte **concerne** des opérations.

Modèle logique de données (clés primaires en début de liste, clés étrangères précédées de `#`) :

```
CLIENT    (id_client, nom_client, prenom_client, date_naissance_client,
           adresse_client, code_postal_client, ville_client, email_client,
           telephone_client, type_piece_client, numero_piece_client)

COMPTE    (numero_compte, type_compte, solde_compte, date_ouverture_compte,
           decouvert_autorise_compte, taux_interet_compte, #id_client)

OPERATION (id_operation, type_operation, montant_operation, date_operation,
           libelle_operation, #numero_compte)
```

### Cas d'utilisation

Chaque fonctionnalité du tableau ci-dessus est un cas d'utilisation. Des relations `include` et `extend` précisent les dépendances :

- **include** : *Faire un virement* utilise *Retirer de l'argent* et *Déposer de l'argent* ; *Retirer de l'argent* utilise *Vérifier le solde*.
- **extend** : *Refuser l'opération* étend *Vérifier le solde* (solde insuffisant) ; *Exporter l'historique* étend *Consulter l'historique*.

### Diagramme de classes

- `Compte` est une **classe abstraite** dont héritent `CompteCourant` (découvert autorisé) et `CompteEpargne` (taux d'intérêt).
- `Compte` implémente l'interface `Virable`.
- `Client` possède plusieurs `Compte`, et un `Compte` concerne plusieurs `Operation`.
- Deux exceptions personnalisées gèrent les erreurs métier : `SoldeInsuffisantException` et `MontantInvalideException`.
- Deux énumérations : `TypePiece` et `TypeOperation`.

*Les schémas seront ajoutés prochainement.*

## Structure du dépôt

```
nexo-bank/
├── README.md
├── docs/
|   ├── images/
│   │   ├── mcd.png
│   │   ├── mld.png
│   │   └── mpd.png
│   └── dictionnaire-donnees.pdf
├── sql/                         code Sql (à venir)
└── src/                         code Java (à venir)
```

## Méthode de travail

Le projet suit une démarche agile adaptée à un travail en solo :

- un **backlog** de user stories, chacune devenant un cas d'utilisation ;
- des **sprints** d'une à deux semaines, avec un planning au début et une rétrospective à la fin ;
- un tableau **Kanban** (À faire, En cours, Terminé) ;
- un **commit Git** par fonctionnalité terminée.

**Definition of Done :** le code compile, la fonctionnalité est testée, les erreurs sont gérées, le code est commité.

## Avancement

- [x] Cadrage et règles de gestion
- [x] Backlog agile
- [x] Dictionnaire de données
- [x] MCD
- [x] MLD
- [] Diagramme de cas d'utilisation
- [] Diagramme de classes
- [ ] MPD et base de données MySQL
- [ ] Application Java en console
- [ ] Connexion MySQL (JDBC et DAO)
- [ ] Interface graphique
- [ ] Tests JUnit
- [ ] Finition et documentation

## Auteur

**[LIKOUANGA CLOTAIRE MARTHIN]** - [lien vers mon profil GitHub](https://github.com/Tinx242)
