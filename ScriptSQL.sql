CREATE TABLE Coordonnees(
   COOR_ID INT AUTO_INCREMENT,
   COOR_email VARCHAR(50) NOT NULL,
   COOR_telephone VARCHAR(50) NOT NULL,
   PRIMARY KEY(COOR_ID),
   UNIQUE(COOR_email)
);

CREATE TABLE Categorie(
   CAT_ID INT AUTO_INCREMENT,
   CAT_nom VARCHAR(25) NOT NULL,
   PRIMARY KEY(CAT_ID)
);

CREATE TABLE Region(
   REG_ID INT AUTO_INCREMENT,
   REG_nomRegion VARCHAR(50) NOT NULL,
   PRIMARY KEY(REG_ID),
   UNIQUE(REG_nomRegion)
);

CREATE TABLE Medicament(
   MED_ID INT AUTO_INCREMENT,
   MED_nom VARCHAR(50) NOT NULL,
   MED_miseEnService DATE NOT NULL,
   MED_quantite VARCHAR(50),
   MED_prix VARCHAR(50),
   CAT_ID INT NOT NULL,
   PRIMARY KEY(MED_ID),
   FOREIGN KEY(CAT_ID) REFERENCES Categorie(CAT_ID)
);

CREATE TABLE Ville(
   VIL_ID INT AUTO_INCREMENT,
   VIL_nomVille VARCHAR(50) NOT NULL,
   VIL_codePostal INT NOT NULL,
   REG_ID INT NOT NULL,
   PRIMARY KEY(VIL_ID),
   UNIQUE(VIL_nomVille),
   FOREIGN KEY(REG_ID) REFERENCES Region(REG_ID)
);

CREATE TABLE Adresses(
   ADRES_ID INT AUTO_INCREMENT,
   ADRES_rue VARCHAR(50) NOT NULL,
   VIL_ID INT NOT NULL,
   PRIMARY KEY(ADRES_ID),
   FOREIGN KEY(VIL_ID) REFERENCES Ville(VIL_ID)
);

CREATE TABLE Mutuelle(
   MUT_ID INT AUTO_INCREMENT,
   MUT_nom VARCHAR(50) NOT NULL,
   MUT_TxPriseEnCharge INT,
   ADRES_ID INT,
   COOR_ID INT,
   PRIMARY KEY(MUT_ID),
   UNIQUE(ADRES_ID),
   UNIQUE(COOR_ID),
   FOREIGN KEY(ADRES_ID) REFERENCES Adresses(ADRES_ID),
   FOREIGN KEY(COOR_ID) REFERENCES Coordonnees(COOR_ID)
);

CREATE TABLE Medecin(
   MEDE_ID INT AUTO_INCREMENT,
   MEDE_numAgreement VARCHAR(25),
   MEDE_specialite VARCHAR(25),
   MEDE_nom VARCHAR(25) NOT NULL,
   MEDE_prenom VARCHAR(50),
   ADRES_ID INT,
   COOR_ID INT,
   PRIMARY KEY(MEDE_ID),
   UNIQUE(ADRES_ID),
   UNIQUE(COOR_ID),
   UNIQUE(MEDE_numAgreement),
   FOREIGN KEY(ADRES_ID) REFERENCES Adresses(ADRES_ID),
   FOREIGN KEY(COOR_ID) REFERENCES Coordonnees(COOR_ID)
);

CREATE TABLE Client(
   CLI_ID INT AUTO_INCREMENT,
   CLI_nom VARCHAR(25) NOT NULL,
   CLI_prenom VARCHAR(25) NOT NULL,
   CLI_dateNaissance DATE NOT NULL,
   CLI_numero_de_securite_social VARCHAR(15) NOT NULL,
   COOR_ID INT NOT NULL,
   ADRES_ID INT NOT NULL,
   MUT_ID INT,
   MEDE_ID INT,
   PRIMARY KEY(CLI_ID),
   UNIQUE(COOR_ID),
   UNIQUE(ADRES_ID),
   UNIQUE(CLI_numero_de_securite_social),
   FOREIGN KEY(COOR_ID) REFERENCES Coordonnees(COOR_ID),
   FOREIGN KEY(ADRES_ID) REFERENCES Adresses(ADRES_ID),
   FOREIGN KEY(MUT_ID) REFERENCES Mutuelle(MUT_ID),
   FOREIGN KEY(MEDE_ID) REFERENCES Medecin(MEDE_ID)
);

CREATE TABLE AchatDirect(
   ACH_ID INT AUTO_INCREMENT,
   ACH_date DATE NOT NULL,
   CLI_ID INT NOT NULL,
   PRIMARY KEY(ACH_ID),
   FOREIGN KEY(CLI_ID) REFERENCES Client(CLI_ID)
);

CREATE TABLE Ordonnances(
   ORDO_ID INT AUTO_INCREMENT,
   ORDO_date DATE NOT NULL,
   MEDE_ID INT NOT NULL,
   CLI_ID INT NOT NULL,
   PRIMARY KEY(ORDO_ID),
   FOREIGN KEY(MEDE_ID) REFERENCES Medecin(MEDE_ID),
   FOREIGN KEY(CLI_ID) REFERENCES Client(CLI_ID)
);

CREATE TABLE demande(
   ORDO_ID INT,
   MED_ID INT,
   quantite INT NOT NULL,
   PRIMARY KEY(ORDO_ID, MED_ID),
   FOREIGN KEY(ORDO_ID) REFERENCES Ordonnances(ORDO_ID),
   FOREIGN KEY(MED_ID) REFERENCES Medicament(MED_ID)
);

CREATE TABLE commande(
   MED_ID INT,
   ACH_ID INT,
   quantite INT NOT NULL,
   PRIMARY KEY(MED_ID, ACH_ID),
   FOREIGN KEY(MED_ID) REFERENCES Medicament(MED_ID),
   FOREIGN KEY(ACH_ID) REFERENCES AchatDirect(ACH_ID)
);


INSERT INTO Region (REG_nomRegion) VALUES
('Auvergne-Rhône-Alpes'),
('Bourgogne-Franche-Comté'),
('Bretagne'),
('Centre-Val de Loire'),
('Corse'),
('Grand Est'),
('Hauts-de-France'),
('Île-de-France'),
('Normandie'),
('Nouvelle-Aquitaine'),
('Occitanie'),
('Pays de la Loire'),
('Provence-Alpes-Côte d\'Azur'),
('Guadeloupe'),
('Martinique'),
('Guyane'),
('La Réunion'),
('Mayotte');
