
CREATE DATABASE IF NOT EXISTS nexobank
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE nexobank;

CREATE TABLE IF NOT EXISTS client (
    id_client             INT           NOT NULL AUTO_INCREMENT,
    nom_client            VARCHAR(50)   NOT NULL,
    prenom_client         VARCHAR(50)   NOT NULL,
    date_naissance_client DATE          NOT NULL,
    adresse_client        VARCHAR(100)  NOT NULL,
    code_postal_client    CHAR(5)       NOT NULL,
    ville_client          VARCHAR(50)   NOT NULL,
    email_client          VARCHAR(100)  NULL,
    telephone_client      VARCHAR(15)   NULL,
    type_piece_client     ENUM('CNI','PASSEPORT','TITRE_SEJOUR') NOT NULL,
    numero_piece_client   VARCHAR(20)   NOT NULL,

    CONSTRAINT pk_client PRIMARY KEY (id_client),
    CONSTRAINT uq_client_email UNIQUE (email_client),
    CONSTRAINT uq_client_piece UNIQUE (numero_piece_client)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS compte (
    numero_compte             VARCHAR(27)   NOT NULL,
    type_compte               ENUM('COURANT','EPARGNE') NOT NULL,
    solde_compte              DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    date_ouverture_compte     DATE          NOT NULL,
    decouvert_autorise_compte DECIMAL(12,2) NOT NULL DEFAULT 0.00,
    taux_interet_compte       DECIMAL(5,4)  NOT NULL DEFAULT 0.0000,
    id_client                 INT           NOT NULL,

    CONSTRAINT pk_compte PRIMARY KEY (numero_compte),
    CONSTRAINT fk_compte_client FOREIGN KEY (id_client)
        REFERENCES client (id_client)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT ck_decouvert_positif  CHECK (decouvert_autorise_compte >= 0),
    CONSTRAINT ck_taux_valide        CHECK (taux_interet_compte >= 0 AND taux_interet_compte <= 1),
    CONSTRAINT ck_decouvert_courant  CHECK (type_compte = 'COURANT' OR decouvert_autorise_compte = 0),
    CONSTRAINT ck_taux_epargne       CHECK (type_compte = 'EPARGNE' OR taux_interet_compte = 0),
    CONSTRAINT ck_solde_minimum      CHECK (solde_compte >= -decouvert_autorise_compte)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS operation (
    id_operation      INT           NOT NULL AUTO_INCREMENT,
    type_operation    ENUM('DEPOT','RETRAIT','VIREMENT_ENTRANT','VIREMENT_SORTANT','INTERETS') NOT NULL,
    montant_operation DECIMAL(12,2) NOT NULL,
    date_operation    DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    libelle_operation VARCHAR(100)  NULL,
    numero_compte     VARCHAR(27)   NOT NULL,

    CONSTRAINT pk_operation PRIMARY KEY (id_operation),
    CONSTRAINT fk_operation_compte FOREIGN KEY (numero_compte)
        REFERENCES compte (numero_compte)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,

    CONSTRAINT ck_montant_positif CHECK (montant_operation > 0),
    INDEX idx_operation_compte_date (numero_compte, date_operation)
) ENGINE=InnoDB;
