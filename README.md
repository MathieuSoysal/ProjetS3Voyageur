# ![](logo-voyageur.png) Le voyageur de commerce 
### IUT Montpellier-Sète – Département Informatique
* **Eléves:** [Zaid Nadir](mailto:nadir.zaid@umontpellier.fr), [Soysal Mathieu](mailto:mathieu.soysal@umontpellier.fr), [Delon Matthias](mailto:matthias.delon@umontpellier.fr), [Ravet-lecourt Florian](mailto:florian.ravet-lecourt@umontpellier.fr)
* **Enseignant:** [Coletta Rémi](mailto:remi.coletta@umontpellier.fr)

Réunion du 18 septembre 2019
----------------------------

### Cahier des charges

**Définition du cahier des charges :**

  * **Énoncé du problème :** nous devons trouver avec un nombre de ville quelconque supérieur ou égale à 4, répartie de manière aléatoire sans doublons le parcours le plus optimal. 

***Les différentes méthodes de résolution :***

  * **Méthode heuristique :** ce programme recherche le plus proche voisins qui va lui-même rechercher le plus proche voisin et ainsi de suite.
  * **Méthode de l’arbre couvrant minimal (dit Minimum Spanning Tree) :** recherche la plus courte distance pour relier toutes les villes.
  * **Méthode de la recherche locale :** le programme recherche la solution la plus optimale parmi les solutions proposées.


***Cahier des charges :***

  * **Bruteforce :** programme récursif qui teste toutes les possibilités bêtement. 
  * **Backtrack :** programme récursif qui teste les possibilités de manière un peu plus optimisé. Par exemple si le programme à déjà tester une branche qui a une distance de 52 et qu’au commencement d’une nouvelle branche celle-ci à déjà cette valeur le programme passe à la branche suivante.
  
  
  **Travail à faire pour la prochaine fois :**
  
  * Réaliser un Bruteforce.
  
  
   Réunion du 2 octobre 2019 
  -------------------------
  
   **Contenue de la réunion :**
   
   * Explication détaillé des différents BrutForce réalisé
   * Mise en valeur des avantage/inconvéniants des deux algorithmes.
  
   **Travail à faire pour la prochaine fois :**
   
   * Vériffication/Amélioration du Bruteforce.
   * Effectuer plusieurs tests.
   * Comparé les résultats (calcul de trajet) obtenue des différents algos BrutForce
   
  Réunion du 9 octobre 2019 
  -------------------------  
  
   **Contenue de la réunion :**
   
   * Compte rendue des algorithmes de recherches réalisé lors de la semaine :
       - BackTrack
       - Heuristique
   * Mise en démonstration sur tableau blanc de l'idée du futur algo
   * Initation et démonstration sur tableau du minimum spanning tree
  
  **Travail à faire pour la prochaine fois :**

* Réaliser un l'agorithme BackTrack combiné avec l'algorithme heuristique
* Réaliser le minimum spending tree.
* Mesurer le temps CPU des programmes au lieu du temps de la machine.
* Faire en sorte que le programme stock lui-même le parcours dans un fichier CSV.
* Réaliser un graphique linéaire comparatif de ces deux programmes en fonction du temps CPU et du nombre de ville.





