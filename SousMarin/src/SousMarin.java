import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class SousMarin {

    // *------------------------------------------------------*
    //                         Attributs
    // *------------------------------------------------------*

    protected String nom = "Basique";

    protected int longueur; // en pixels

    protected String deplacement = "plongee";

    protected double vitesse; // en pixels

    protected boolean coule; // sous marin coulé ?

    protected int autonomie; // nb de tours max

    protected int[] position = {0,0,100}; // position du centre du sous marin au départ

    protected int[] inclinaison = {0,0,0}; // vecteur d'inclinaison

    protected int profondeurmax = 400;

    protected int nbtorpilles;

    protected int nbtorpillesmax;

    protected int[] positionEnnemi = {0,0,0};

    private int randomNum; // variable dont la valeur sera randomisée pour les déplacements, entre 0 et 3 inclus

    private Random rand;
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
        rand = new Random();
        randomNum = 1+rand.nextInt(0,4); // génère un nombre entre 0 et 3 compris - Test OK
        // calcul du nombre de pixels
        double marcheav = vitesse*(randomNum/3);
        return marcheav;
    }

    public double marcheArriere(double vitesse)
    {
        rand = new Random();
        randomNum = 1+rand.nextInt(0,4); // génère un nombre entre 0 et 3 compris
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

    public int[] deplacement(double vitesse)
    {
        /* Remarque : il faut savoir si on se déplace vers l'avant ou l'arrière !*/
        // déplace le sous-marin:
         //- Fonction de la vitesse et du vecteur inclinaison, Détermine la nouvelle position du sous-marin après le déplacement (on joue sur l'index 0, 1 ou 2 de position)
        double deplaceav = marcheAvant(vitesse);
        double deplacearr = marcheArriere(vitesse);
        System.out.println("marcheav : " + deplaceav + ", marchearr : " + deplacearr);
        Scanner scan = new Scanner(System.in);
        System.out.println("Voulez vous aller vers l'avant (F) ou vers l'arriere (B)");
        String direction = scan.nextLine();
        switch(inclinaison[2])
        {
            case 0:
                // horizontal
                // si on va vers l'avant utiliser marcheAvant, sinon marcheArrière
                if (direction.equalsIgnoreCase("F"))
                {
                    position[0] += deplaceav;
                }
                else if (direction.equalsIgnoreCase("B"))
                {
                    position[0] += deplacearr;
                }
                else
                {
                    System.out.println("Erreur dans la direction !");
                }
                break;
            case 1:
                if (direction.equalsIgnoreCase("F"))
                {
                    position[0] += deplaceav;
                    System.out.println("test partiellement réussi");
                    if (position[2] < (profondeurmax - longueur/2))
                    {
                        System.out.println("test réussi");
                        position[2] -= deplaceav;
                    }
                }
                else if (direction.equalsIgnoreCase("B"))
                {
                    position[0] += deplacearr;
                    if (position[2] < (profondeurmax - longueur/2))
                    {
                        position[2] -= deplacearr;
                    }
                }
                else
                {
                    System.out.println("Erreur dans la direction !");
                }
                break;
            case -1:
                if (direction.equalsIgnoreCase("F"))
                {
                    position[0] += deplaceav;
                    if (position[2] < (profondeurmax - longueur/2))
                    {
                        position[2] += deplaceav;
                    }
                }
                else if (direction.equalsIgnoreCase("B"))
                {
                    position[0] += deplacearr;
                    if (position[2] < (profondeurmax - longueur/2))
                    {
                        position[2] += deplacearr;
                    }
                }
                else
                {
                    System.out.println("Erreur dans la direction !");
                }
                break;
            default:
                System.out.println("Erreur rencontrée");
        }

        return position;
         //- Notion d'aléatoire / imprécision liée à la vitesse du sous marin
        // plus la vitesse est haute plus la précision est haute pour la position déterminée,
        // donc en gros faire des switch case selon la vitesse et randomiser la position ?

        

    }

    public void listenPositionEnnemie(int x, int y, int z)
    {
        positionEnnemi[0] = x;
        positionEnnemi[1] = y;
        positionEnnemi[2] = z;
    }

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

    public void tirerTorpille(int x, int y, int z, SousMarin cible)
    {
        // tire une torpille à la position indiquée.
        Torpille torpille = new Torpille();
        torpille.Deplacement(x, y, z, cible);
        
        //diminue le nombre de torpilles de 1.
        nbtorpilles -=1;

        
    }

    // --------------------------------------
    //             Redéfinition de
    //             méthodes utiles
    // --------------------------------------

    @Override
    public String toString() {
        return "Le sous marin possède encore " + nbtorpilles + " torpilles sur " + nbtorpillesmax +
        ", se trouve à la position (" + position[0] + ";" + position[1] + ";" + position[2] +
        "), il lui reste " + autonomie + " coups à jouer.";
    }

    @Override
    public boolean equals(Object obj) {
        /*
        Deux sous-marins sont égaux si et seulement si :
        - ils ont le même nom (uniquement)
*/
        if (obj instanceof SousMarin)
        {
            if (nom.equals(((SousMarin) obj).getNom()))
            {
                return true;
            }
        }
       return false;
    }

    // --------------------------------------
    //             Statut du
    //             sous-marin
    // --------------------------------------

    public boolean isTouche(int x,int y,int z)
    {
        // calcule si la torpille dont les coordonnées sont en paramètres
        // est dans sa sphère de vulnérabilité.
        boolean vulne = false;
        int deltax = Math.abs(x - position[0]);
        int deltay = Math.abs(x - position[1]);
        int deltaz = Math.abs(x - position[2]);
        if (deltax <= 1 && deltay <= 1 && deltaz <= 1)
        {
            vulne = true;
        }
        return vulne;
    }

    public void setCoule(boolean coule) {
        this.coule = coule;
    }

    public boolean isCoule() {
        return coule;
    }

    public String getNom() {
        return nom;
    }

    public void setAutonomie(int autonomie) {
        this.autonomie = autonomie;
    }

    public int[] getInclinaison() {
        return inclinaison;
    }
}
