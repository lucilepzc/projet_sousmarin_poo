import java.lang.Math;

public class SousMarin {

    // *------------------------------------------------------*
    //                         Attributs
    // *------------------------------------------------------*


    private int longueur; // en pixels

    private String deplacement = "plongee";

    private double vitesse; // en pixels

    private boolean coule; // sous marin coulé ?

    private int autonomie; // nb de tours max

    private int[] position = {0,0,0}; // position du centre du sous marin

    private int[] inclinaison = {0,0,0}; // vecteur d'inclinaison

    private int profondeurmax;

    private int nbtorpilles;

    private int nbtorpillesmax;

    private int ennposx = 0; // position sur l'axe x du sous marin ennemi
    private int ennposy = 0; // position sur l'axe y du sous-marin ennemi
    private int ennposz = 0; // position sur l'axe z du sous-marin ennemi
    private int[] positionEnnemi = {ennposx,ennposy,ennposz};

    private int randomNum; // variable dont la valeur sera randomisée pour les déplacements, entre 0 et 3 inclus

    // *------------------------------------------------------*
    //                      Constructeurs
    // *------------------------------------------------------*

    // *------------------------------------------------------*
    //                       Méthodes
    // *------------------------------------------------------*

    // --------------------------------------
    //              Déplacement
    //             du sous-marin
    //         en avant ou en arrière
    // --------------------------------------

    public double marcheAvant(double vitesse)
    {
        // randomisation de la variable pour les déplacements
        randomNum = (int)(Math.random()*4); // génère un nombre entre 0 et 3 compris - Test OK
        // calcul du nombre de pixels
        double marcheav = vitesse*(randomNum/3);
        return marcheav;
    }

    public double marcheArriere(double vitesse)
    {
        randomNum = (int)(Math.random()*4); // génère un nombre entre 0 et 3 compris
        double marchearr = vitesse*(randomNum/3)*-1;
        return marchearr;
    }
    // --------------------------------------
    //  Important : il est impossible
    //  d'aller d'avant en arrière
    //  (ou inversement) si la vitesse
    //  n'est pas à 0 !
    // --------------------------------------

    // --------------------------------------
    //              Inclinaison
    //             du sous-marin
    // --------------------------------------

    public void plonger()
    {
        // met le vecteur d'inclinaison à -1 sur les z
        inclinaison[2] = -1;
    }

    public void remonter()
    {
        // met le vecteur d'inclinaison à +1 sur les z
        inclinaison[2] = 1;
    }

    public void stabiliser()
    {
        // met le vecteur d'inclinaison à 0 sur les z
        inclinaison[2] = 0;
    }

    // --------------------------------------
    //             Action active
    //             du sous-marin
    //          (implémentation des
    //          méthodes ci-dessus)
    // --------------------------------------

    public void deplacement()
    {
        /* Remarque : il faut savoir si on se déplace vers l'avant ou l'arrière !*/
        // déplace le sous-marin:
         //- Fonction de la vitesse et du vecteur inclinaison, Détermine la nouvelle position du sous-marin après le déplacement (on joue sur l'index 0, 1 ou 2 de position)
        switch(inclinaison[2])
        {
            case 0:
                // horizontal
                // si on va vers l'avant utiliser marcheAvant, sinon marcheArrière
                break;
            case 1:
                // en remontée
                break;
            case -1:
                // en plongée
                break;
            default:
                System.out.println("Erreur rencontrée");
        }
         //- Notion d'aléatoire / imprécision liée à la vitesse du sous marin
        // plus la vitesse est haute plus la précision est basse pour la position déterminée, donc en gros faire des switch case selon la vitesse et randomiser la position ?

    }

    /*public double[] listenPositionEnnemie()
    {
        // retourne la position du sous marin ennemi.
    }*/

    public void virerDeBord(String direction)
    {
        // selon la direction (babord ou tribord) modifie la coordonnée à 45°
        if (direction.equals("babord") == true)
        {
            remonter();
        }
        else if (direction.equals("tribord") == true)
        {
            plonger();
        }
        else
        {
            System.out.println("erreur direction");
        }
    }

    public void tirerTorpille(int x, int y, int z)
    {
        // tire une torpille à la position indiquée.
        // objet Torpille à coder dans une autre classe qu'on utilisera ?

        //diminue le nombre de torpilles de 1.
        nbtorpilles -=1;

        // part de sa position d'origine, a un pas de 1px et a une autonomie de 200 px
        // => en termes mathématiques, la norme du vecteur position doit être inférieure à 200 px pour
        // que la torpille soit lançable.
        // forme de la norme à trois dimensions : racine carrée de x²+y²+z²
    }

    // --------------------------------------
    //             Redéfinition de
    //             méthodes utiles
    // --------------------------------------

    @Override
    public String toString() {
        return "Le sous marin est";
        /*
        y insérer toutes les données utiles sur le sous marin :
        - Nombre de torpilles restantes
        - Probablement la localisation
        - Nombre de coups restants à jouer (autonomie)
        */
    }

    @Override
    public boolean equals(Object obj) {
        /*
        Deux sous-marins sont égaux si et seulement si :
        - ils ont le même nom (uniquement)
*/
    }

    // --------------------------------------
    //             Statut du
    //             sous-marin
    // --------------------------------------

    public boolean isTouche(int x,int y,int z)
    {
        // calcule si la torpille dont les coordonnées sont en paramètres
        // est dans sa sphère de vulnérabilité.
        return;
    }

    public boolean isCoule() {
        return coule;
    }

}
