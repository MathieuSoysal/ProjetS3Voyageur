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