-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 02 Avril 2014 à 11:20
-- Version du serveur: 5.5.33-1-log
-- Version de PHP: 5.5.8-3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `Cadene_Panou`
--

-- --------------------------------------------------------

--
-- Structure de la table `Albums`
--

CREATE TABLE IF NOT EXISTS `Albums` (
  `id` int(11) NOT NULL,
  `title` varchar(45) COLLATE utf8_bin NOT NULL,
  `desc` text COLLATE utf8_bin NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Albums_Users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `Albums_Images`
--

CREATE TABLE IF NOT EXISTS `Albums_Images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album_id` int(11) NOT NULL,
  `image_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Albums_Images_Albums1_idx` (`album_id`),
  KEY `fk_Albums_Images_Images1_idx` (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Friends`
--

CREATE TABLE IF NOT EXISTS `Friends` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  `created` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Followers_Users1_idx` (`user_id`),
  KEY `fk_Followers_Users2_idx` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `Hashtags`
--

CREATE TABLE IF NOT EXISTS `Hashtags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_bin NOT NULL,
  `created` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQUE` (`title`),
  KEY `fk_Hashtags_Users1_idx` (`created_by`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Hashtags_Pwitts`
--

CREATE TABLE IF NOT EXISTS `Hashtags_Pwitts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hashtag_id` int(11) NOT NULL,
  `pwitt_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Hashtages_Pwitts_Pwitts1_idx` (`pwitt_id`),
  KEY `fk_Hashtages_Pwitts_Hashtags1_idx` (`hashtag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Images`
--

CREATE TABLE IF NOT EXISTS `Images` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(100) COLLATE utf8_bin NOT NULL,
  `tinyUrl` varchar(45) COLLATE utf8_bin NOT NULL,
  `created` int(11) NOT NULL,
  `desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Images_Pwitts`
--

CREATE TABLE IF NOT EXISTS `Images_Pwitts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image_id` int(11) NOT NULL,
  `pwitt_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Images_Pwitts_Images1_idx` (`image_id`),
  KEY `fk_Images_Pwitts_Pwitts1_idx` (`pwitt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `MRDocs`
--

CREATE TABLE IF NOT EXISTS `MRDocs` (
  `id` int(11) NOT NULL,
  `content` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `MRDocs_MRWords`
--

CREATE TABLE IF NOT EXISTS `MRDocs_MRWords` (
  `id` int(11) NOT NULL,
  `mrword_id` int(11) NOT NULL,
  `mrdoc_id` int(11) NOT NULL,
  `freq` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_MRDocs_MRWords_mrword_id` (`mrword_id`),
  KEY `fk_MRDocs_MRWords_mrdoc_id` (`mrdoc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `MRWords`
--

CREATE TABLE IF NOT EXISTS `MRWords` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `nbdocs` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `Notifs`
--

CREATE TABLE IF NOT EXISTS `Notifs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `isViewed` tinyint(4) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created` int(11) NOT NULL,
  `viewed` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Notifs_Users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Pwitts`
--

CREATE TABLE IF NOT EXISTS `Pwitts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin NOT NULL,
  `created` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Pwitts_Users1_idx` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(45) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(70) COLLATE utf8_bin NOT NULL,
  `isValid` tinyint(4) DEFAULT NULL,
  `created` int(11) NOT NULL,
  `lastUpdate` int(11) DEFAULT NULL,
  `lastModify` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  `session` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lastLogin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `FK_profil_image_idx` (`image_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=15 ;

--
-- Contenu de la table `Users`
--

INSERT INTO `Users` (`id`, `password`, `firstname`, `lastname`, `email`, `isValid`, `created`, `lastUpdate`, `lastModify`, `image_id`, `session`, `lastLogin`) VALUES
(12, 'philvirg', 'Remi', 'Cadene', 'remicadene@laposte.net', NULL, 1396392645, NULL, NULL, NULL, NULL, NULL),
(13, 'azerty', 'David', 'David', 'david@panou.fr', NULL, 1396393105, NULL, NULL, NULL, NULL, NULL),
(14, 'azerty', 'Alex', 'Ndoye', 'Alex@Ndoye.fr', NULL, 1396393275, NULL, NULL, NULL, NULL, NULL);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Albums`
--
ALTER TABLE `Albums`
  ADD CONSTRAINT `fk_Albums_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Albums_Images`
--
ALTER TABLE `Albums_Images`
  ADD CONSTRAINT `fk_Albums_Images_Albums1` FOREIGN KEY (`album_id`) REFERENCES `Albums` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Albums_Images_Images1` FOREIGN KEY (`image_id`) REFERENCES `Images` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD CONSTRAINT `fk_Followers_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Followers_Users2` FOREIGN KEY (`friend_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Hashtags`
--
ALTER TABLE `Hashtags`
  ADD CONSTRAINT `fk_Hashtags_Users1` FOREIGN KEY (`created_by`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Hashtags_Pwitts`
--
ALTER TABLE `Hashtags_Pwitts`
  ADD CONSTRAINT `fk_Hashtages_Pwitts_Hashtags1` FOREIGN KEY (`hashtag_id`) REFERENCES `Hashtags` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Hashtages_Pwitts_Pwitts1` FOREIGN KEY (`pwitt_id`) REFERENCES `Pwitts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Images_Pwitts`
--
ALTER TABLE `Images_Pwitts`
  ADD CONSTRAINT `fk_Images_Pwitts_Images1` FOREIGN KEY (`image_id`) REFERENCES `Images` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Images_Pwitts_Pwitts1` FOREIGN KEY (`pwitt_id`) REFERENCES `Pwitts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `MRDocs_MRWords`
--
ALTER TABLE `MRDocs_MRWords`
  ADD CONSTRAINT `fk_MRDocs_MRWords_mrword` FOREIGN KEY (`mrword_id`) REFERENCES `MRWords` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_MRDocs_MRWords_mrdoc` FOREIGN KEY (`mrdoc_id`) REFERENCES `MRDocs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Notifs`
--
ALTER TABLE `Notifs`
  ADD CONSTRAINT `fk_Notifs_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Pwitts`
--
ALTER TABLE `Pwitts`
  ADD CONSTRAINT `fk_Pwitts_Users1` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Users`
--
ALTER TABLE `Users`
  ADD CONSTRAINT `FK_profil_image` FOREIGN KEY (`image_id`) REFERENCES `Images` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
