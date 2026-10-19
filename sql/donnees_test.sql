-- Données de test pour Nexo Bank (à exécuter après schema.sql)
-- Les soldes sont cohérents avec l'historique des opérations.
USE nexobank;

INSERT INTO client (nom_client, prenom_client, date_naissance_client, adresse_client,
                    code_postal_client, ville_client, email_client, telephone_client,
                    type_piece_client, numero_piece_client)
VALUES
('Dupont',  'Marie', '1990-04-12', '12 rue des Lilas',      '91220', 'Brétigny-sur-Orge', 'marie.dupont@mail.fr', '0612345678', 'CNI',       '190491234567'),
('Martin',  'Paul',  '1985-09-30', '5 avenue Victor Hugo',  '75016', 'Paris',             'paul.martin@mail.fr',  NULL,         'PASSEPORT', '85FA12345'),
('Bernard', 'Sofia', '2001-01-22', '8 place du Marché',     '69002', 'Lyon',              NULL,                   '0698765432', 'CNI',       '010169876543');

INSERT INTO compte (numero_compte, type_compte, solde_compte, date_ouverture_compte,
                    decouvert_autorise_compte, taux_interet_compte, id_client)
VALUES
('FR7630001000011234567890189', 'COURANT',  300.00, '2024-01-15', 200.00, 0.0000, 1),
('FR7630001000011234567890257', 'EPARGNE', 1020.00, '2024-01-15',   0.00, 0.0200, 1),
('FR7630001000019876543210123', 'COURANT',  300.00, '2024-03-02', 100.00, 0.0000, 2),
('FR7630001000015555666677788', 'COURANT',  -50.00, '2025-06-20', 150.00, 0.0000, 3);

INSERT INTO operation (type_operation, montant_operation, date_operation, libelle_operation, numero_compte)
VALUES
-- Compte courant de Marie : 500 - 80 - 120 = 300
('DEPOT',            500.00, '2024-01-15 10:30:00', 'Dépôt initial',          'FR7630001000011234567890189'),
('RETRAIT',           80.00, '2024-02-03 18:12:00', 'Retrait distributeur',   'FR7630001000011234567890189'),
('VIREMENT_SORTANT', 120.00, '2024-03-10 09:00:00', 'Virement vers Paul',     'FR7630001000011234567890189'),
-- Compte épargne de Marie : 1000 + 20 = 1020
('DEPOT',           1000.00, '2024-01-15 10:35:00', 'Dépôt initial épargne',  'FR7630001000011234567890257'),
('INTERETS',          20.00, '2024-12-31 23:59:00', 'Intérêts annuels',       'FR7630001000011234567890257'),
-- Compte courant de Paul : 180 + 120 = 300
('DEPOT',            180.00, '2024-03-02 14:00:00', 'Dépôt initial',          'FR7630001000019876543210123'),
('VIREMENT_ENTRANT', 120.00, '2024-03-10 09:00:00', 'Virement reçu de Marie', 'FR7630001000019876543210123'),
-- Compte courant de Sofia : 100 - 150 = -50 (découvert autorisé : 150)
('DEPOT',            100.00, '2025-06-20 11:00:00', 'Dépôt initial',          'FR7630001000015555666677788'),
('RETRAIT',          150.00, '2025-07-01 16:45:00', 'Retrait distributeur',   'FR7630001000015555666677788');
