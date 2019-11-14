CREATE DATABASE IF NOT EXISTS Voyageur;




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
                       ListeVille VARCHAR NOT NULL,
                       idVille INT(5),
                       FOREIGN KEY (idVille) REFERENCES Ville(idVille)

);
CREATE TABLE Ville(
                        idVille INT (5) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                        X INT(5) NOT NULL,
                        Y INT(5) NOT NULL
);

CREATE TABLE Parcours(
                         idParcours INT(6) AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                         idVille INT(5) ,
                         FOREIGN KEY (idVille) REFERENCES Ville(idVille),
                         idCarte INT(5),
                         FOREIGN KEY (idCarte) REFERENCES Carte(idCarte)
);
------------------------------------------------------------------------------------------------
//correction



CREATE TABLE  Carte(
                       idCarte INT(5) NOT NULL PRIMARY KEY AUTO_INCREMENT ,
                       nbVille INT(5)



);
CREATE TABLE Ville(
                      idVille INT (5) NOT NULL  PRIMARY KEY AUTO_INCREMENT ,
                      X INT(5) NOT NULL,
                      Y INT(5) NOT NULL,
                      idCArte INT(5),
                      FOREIGN KEY (idCarte) REFERENCES Carte(idCarte)

);

CREATE TABLE Parcours(
                         idCarte INT(6) AUTO_INCREMENT NOT NULL PRIMARY KEY ,
                         FOREIGN KEY (idCarte) REFERENCES Carte(idCarte),
                         nomAlgo VARCHAR(35) NOT NULL,
                         isFinished BOOLEAN NOT NULL,
                         ordreVilles VARCHAR NOT NULL,
                         cost INT NOT NULL
);

Carte(idCarte,nbVilles)

     Ville(idVille, x, y, #idCarte )

              Parcours(#idCarte, nomAlgo, isFinished, ordreVilles, cost)

