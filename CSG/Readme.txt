PastequeCSG

Victor Murris / Rémi Routier

################################################################################################

Le logiciel PastequeCSG est un outil de création et de manipulation de formes géométriques.
 
PastequeCSG permet :
- créer des rectangles
- créer des cercles (fonctionnalité original)
- Faire l'intersection de deux rectangles
- Faire l'union de plusieurs formes
- Faire la différence de deux rectangles 
- Redimensioner les formes 
- Déplacer les formes
- Enregistrer vos formes en local (Aussi Enregistrer sous)
- Ouvrir de precedent fichiers
- Supprimer une forme du fichier

################################################################################################

Exemple de bug :

Lorsqu'on déplacer ou redimensionner une forme l'app n'était pas fluide alors on a décider d'ajouter une nouvelle forme avec les nouvelles dimensions ou coordonnées à une liste tiers qu'on ajoute ensuite à la liste utilisée pour l'affichage sans oublier de supprimer la forme du départ à cette même liste. Cela nous permet d'avoir une fonctionnalité de souris glissé pour redimensionner et bouger.

################################################################################################