CREATE TABLE CATEGORIES (
    no_categorie   INTEGER AUTO_INCREMENT NOT NULL,
    libelle        VARCHAR(30) NOT NULL,
    Constraint categorie_pk PRIMARY KEY (no_categorie)
);

CREATE TABLE ENCHERES (
    no_utilisateur   INTEGER NOT NULL,
    no_article       INTEGER NOT NULL,
    date_enchere     TIMESTAMP NOT NULL,
	montant_enchere  INTEGER NOT NULL,
    Constraint enchere_pk PRIMARY KEY (no_utilisateur, no_article)
); 

CREATE TABLE RETRAITS (
	no_article         INTEGER NOT NULL,
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(15) NOT NULL,
    ville            VARCHAR(30) NOT NULL
);

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_article);

CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER AUTO_INCREMENT NOT NULL,
    pseudo           VARCHAR(30) UNIQUE NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) UNIQUE NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(50) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(256) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL,
    CONSTRAINT utilisateur_pk PRIMARY KEY (no_utilisateur)
);

CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER AUTO_INCREMENT NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description                   VARCHAR(300) NOT NULL,
	date_debut_encheres           TIMESTAMP NOT NULL,
    date_fin_encheres             TIMESTAMP NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
    etat_vente                    VARCHAR(30) NOT NULL,
    CONSTRAINT articles_vendus_pk PRIMARY KEY (no_article)
);

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY (no_article)
        REFERENCES ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE RETRAITS
    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY (no_article)
        REFERENCES ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action ;

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY (no_utilisateur)
        REFERENCES utilisateurs (no_utilisateur)
ON DELETE NO ACTION 
    ON UPDATE no action
