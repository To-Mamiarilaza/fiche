/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  to
 * Created: 30 nov. 2023
 */

CREATE DATABASE fiche_employe;

\c fiche_employe;

CREATE SEQUENCE seq_fiche;
CREATE TABLE fiche (
    id_fiche INTEGER PRIMARY KEY,
    name VARCHAR,
    firstname VARCHAR,
    address VARCHAR,
    mail VARCHAR,
    description VARCHAR,
    photo VARCHAR,
    etat INTEGER    -- 0 supprime, 10 attente, 20 valide
);

ALTER SEQUENCE seq_fiche RESTART WITH 4;
INSERT INTO fiche 
(id_fiche, name, firstname, address, mail, description, photo, etat) 
VALUES
(1, 'MAMIARILAZA', 'To Niasimandimby', 'Iavoloha 238 bis', 'to.mamiarilaza@gmail.com', 'Un étudiant motivé est passioné', 'to.png', 10),
(2, 'MAMIHARILAZA', 'Sitrakiniavo', 'Ambohitsoa', 'niavo@gmail.com', 'Ayant beaucoup de force, je serais capable de vous aidé', 'niavo.png', 10), 
(3, 'MIALITSAMBATRA', 'Tsanta', 'LTP Tsiroanomandidy', 'tsanta132@gmail.com', 'Voir votre voiture réparé est un bonheur pour moi', 'tsanta.png', 10); 

CREATE SEQUENCE seq_language;
CREATE TABLE language (
    id_language INTEGER PRIMARY KEY,
    language_name VARCHAR,
    etat INTEGER
);

ALTER SEQUENCE seq_language RESTART WITH 4;
INSERT INTO language 
(id_language, language_name, etat)
VALUES
(1, 'Malagasy', 10),
(2, 'Français', 10),
(3, 'Anglais', 10),
(4, 'Allemand', 10);

CREATE TABLE fiche_language (
    id_fiche INTEGER,
    id_language INTEGER,
    PRIMARY KEY(id_fiche, id_language),
    FOREIGN KEY(id_fiche) REFERENCES fiche(id_fiche),
    FOREIGN KEY(id_language) REFERENCES language(id_language)
);

INSERT INTO fiche_language 
(id_fiche, id_language)
VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(2, 3),
(3, 1),
(3, 2),
(3, 4);
