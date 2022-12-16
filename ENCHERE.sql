-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 16 déc. 2022 à 11:01
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ENCHERE`
--

-- --------------------------------------------------------

--
-- Structure de la table `ARTICLES_VENDUS`
--

CREATE TABLE `ARTICLES_VENDUS` (
  `no_article` int(11) NOT NULL,
  `nom_article` varchar(30) NOT NULL,
  `description` varchar(300) NOT NULL,
  `date_debut_encheres` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `date_fin_encheres` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `prix_initial` int(11) DEFAULT NULL,
  `prix_vente` int(11) DEFAULT NULL,
  `no_utilisateur` int(11) NOT NULL,
  `no_categorie` int(11) NOT NULL,
  `etat_vente` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ARTICLES_VENDUS`
--

INSERT INTO `ARTICLES_VENDUS` (`no_article`, `nom_article`, `description`, `date_debut_encheres`, `date_fin_encheres`, `prix_initial`, `prix_vente`, `no_utilisateur`, `no_categorie`, `etat_vente`) VALUES
(1, 'Serveur AWS', 'much speed ', '2022-12-19 11:00:00', '2022-12-31 11:00:00', 2000, 2000, 1, 1, 'NON_DEBUTEE'),
(2, 'Cuillère', 'Pas changer assiette pour fromage', '2022-12-16 11:00:00', '2022-12-17 11:00:00', 25, 25, 2, 2, 'NON_DEBUTEE'),
(3, 'Ceinture noire karaté', 'Top pour ninja', '2022-12-16 09:46:21', '2022-12-16 11:15:00', 150, 200, 3, 4, 'EN_COURS'),
(4, 'Yacht', 'Bateau très gros', '2022-12-16 11:00:00', '2025-12-16 11:00:00', 10000000, 10000000, 1, 4, 'NON_DEBUTEE'),
(5, 'Chaussette', '(Elle est propre)', '2022-12-16 09:55:11', '2022-12-20 11:00:00', 2, 15, 2, 3, 'EN_COURS'),
(6, 'Boulier', 'Pour compter sous', '2022-12-16 08:00:00', '2022-12-21 11:00:00', 20, 20, 2, 1, 'EN_COURS');

-- --------------------------------------------------------

--
-- Structure de la table `CATEGORIES`
--

CREATE TABLE `CATEGORIES` (
  `no_categorie` int(11) NOT NULL,
  `libelle` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `CATEGORIES`
--

INSERT INTO `CATEGORIES` (`no_categorie`, `libelle`) VALUES
(2, 'Ameublement'),
(1, 'Informatique'),
(4, 'Sport&Loisirs'),
(3, 'Vêtement');

-- --------------------------------------------------------

--
-- Structure de la table `ENCHERES`
--

CREATE TABLE `ENCHERES` (
  `no_utilisateur` int(11) NOT NULL,
  `no_article` int(11) NOT NULL,
  `date_enchere` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `montant_enchere` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ENCHERES`
--

INSERT INTO `ENCHERES` (`no_utilisateur`, `no_article`, `date_enchere`, `montant_enchere`) VALUES
(1, 3, '2022-12-16 09:46:21', 200),
(3, 5, '2022-12-16 09:55:11', 15);

-- --------------------------------------------------------

--
-- Structure de la table `RETRAITS`
--

CREATE TABLE `RETRAITS` (
  `no_article` int(11) NOT NULL,
  `rue` varchar(50) NOT NULL,
  `code_postal` varchar(5) NOT NULL,
  `ville` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `RETRAITS`
--

INSERT INTO `RETRAITS` (`no_article`, `rue`, `code_postal`, `ville`) VALUES
(1, '8 rue Maison Blanche', '75000', 'Paris'),
(2, '68 caverne d\'AliBaba', '97000', 'Perdue'),
(3, '295 Endroit Perdu', '12000', 'Creuse'),
(4, '8 rue Maison Blanche', '75000', 'Paris'),
(5, '68 caverne d\'AliBaba', '97000', 'Perdue'),
(6, '68 caverne d\'AliBaba', '97000', 'Perdue');

-- --------------------------------------------------------

--
-- Structure de la table `UTILISATEURS`
--

CREATE TABLE `UTILISATEURS` (
  `no_utilisateur` int(11) NOT NULL,
  `pseudo` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `rue` varchar(50) NOT NULL,
  `code_postal` varchar(5) NOT NULL,
  `ville` varchar(30) NOT NULL,
  `mot_de_passe` varchar(256) NOT NULL,
  `credit` int(11) NOT NULL,
  `administrateur` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `UTILISATEURS`
--

INSERT INTO `UTILISATEURS` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES
(1, 'Picsou', 'Bezos', 'Jean-Michel', 'toto@amazon.fr', '02345064213', '8 rue Maison Blanche', '75000', 'Paris', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', 99800, b'0'),
(2, 'Burgonde', 'Cuillère', 'Arthur', 'arthurcuillere@bomail.fr', '09563434563', '68 caverne d\'AliBaba', '97000', 'Perdue', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', 40, b'0'),
(3, 'Inconnu', 'Valjean', 'Jean', 'jeannovallo@notfound.fr', '06534245231', '295 Endroit Perdu', '12000', 'Creuse', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', 285, b'0');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `ARTICLES_VENDUS`
--
ALTER TABLE `ARTICLES_VENDUS`
  ADD PRIMARY KEY (`no_article`),
  ADD KEY `encheres_utilisateur_fk` (`no_utilisateur`),
  ADD KEY `articles_vendus_categories_fk` (`no_categorie`);

--
-- Index pour la table `CATEGORIES`
--
ALTER TABLE `CATEGORIES`
  ADD PRIMARY KEY (`no_categorie`),
  ADD UNIQUE KEY `libelle_unique` (`libelle`);

--
-- Index pour la table `ENCHERES`
--
ALTER TABLE `ENCHERES`
  ADD PRIMARY KEY (`no_utilisateur`,`no_article`),
  ADD KEY `encheres_articles_vendus_fk` (`no_article`);

--
-- Index pour la table `RETRAITS`
--
ALTER TABLE `RETRAITS`
  ADD PRIMARY KEY (`no_article`);

--
-- Index pour la table `UTILISATEURS`
--
ALTER TABLE `UTILISATEURS`
  ADD PRIMARY KEY (`no_utilisateur`),
  ADD UNIQUE KEY `pseudo` (`pseudo`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `ARTICLES_VENDUS`
--
ALTER TABLE `ARTICLES_VENDUS`
  MODIFY `no_article` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `CATEGORIES`
--
ALTER TABLE `CATEGORIES`
  MODIFY `no_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `UTILISATEURS`
--
ALTER TABLE `UTILISATEURS`
  MODIFY `no_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ARTICLES_VENDUS`
--
ALTER TABLE `ARTICLES_VENDUS`
  ADD CONSTRAINT `articles_vendus_categories_fk` FOREIGN KEY (`no_categorie`) REFERENCES `CATEGORIES` (`no_categorie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `encheres_utilisateur_fk` FOREIGN KEY (`no_utilisateur`) REFERENCES `UTILISATEURS` (`no_utilisateur`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `ENCHERES`
--
ALTER TABLE `ENCHERES`
  ADD CONSTRAINT `encheres_articles_vendus_fk` FOREIGN KEY (`no_article`) REFERENCES `ARTICLES_VENDUS` (`no_article`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `RETRAITS`
--
ALTER TABLE `RETRAITS`
  ADD CONSTRAINT `retraits_articles_vendus_fk` FOREIGN KEY (`no_article`) REFERENCES `ARTICLES_VENDUS` (`no_article`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
