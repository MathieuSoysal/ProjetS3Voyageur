CREATE DATABASE IF NOT EXISTS Voyageur;

CREATE TABLE IF NOT EXISTS `Algo` (
                                      `nom` varchar(100) DEFAULT NULL,
                                      `nbVille` int(4) NOT NULL,
                                      `temps` time

) ENGINE = InnoDB  DEFAULT CHARSET = utf8  ;

INSERT INTO `Algo` ( `nom`, `nbVille`, `temps`) VALUES
('BruteForce', 12, '30.44'),
('BruteForce_V2', 12, '25.44'),
('BackTrackv1', 12, '20.44'),
('BackTrackv2', 12, '17.44'),
('BackTrackV2.1', 12, '15.45');


/* https://www.viralpatel.net/java-load-csv-file-to-database/ */

/*
1) réaliser un test de lecture/ecriture depuis une table quelconque avec JDBC.*/

CREATE DATABASE IF NOT EXISTS Voyageur;

CREATE TABLE TEST3(
                      id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                      nom VARCHAR(30) NOT NULL,
                      prenom VARCHAR(30) NOT NULL

);


	/*2) proposer un format de table pour une carte, puis un parcours */

CREATE TABLE  Carte(
                       idCarte INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
                       nomCarte VARCHAR(35),
                       longueur INT(5) NOT NULL,
                       largeur INT(5) NOT NULL

);

CREATE TABLE ParcoursX(
                         idParcoursX INT(6) AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                         /*nomParcours VARCHAR(35) NOT NULL,*/
                         nbVille INT(4) NOT NULL,
                         temps TIME NOT NULL
);

CREATE  TABLE ParcoursY(
                        idParcours INT(6) AUTO_INCREMENT NOT NULL  PRIMARY KEY ,
                        ordreVille VARCHAR,




)