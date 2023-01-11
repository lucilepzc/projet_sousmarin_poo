import java.lang.Math;

public class Torpille {

    // *------------------------------------------------------*
    //                         Attributs
    // *------------------------------------------------------*

    String nomSousMarin = "";
    int vitesse = 1;
    int autonomie = 200;
    int[] position = {0,0,0};
    int[] inclinaison = {0,0,0};
    SousMarin cible;

    //
    //                          Méthodes
    //

    public void Deplacement(int x, int y, int z, SousMarin cible)
    {

    //calcul du vecteur d'orientation
    inclinaison[0] = position[0] - x;
    inclinaison[1] = position[1] - y;
    inclinaison[2] = position[2] - z;

        for (int i = 0; i < autonomie; i++) 
        {
            // déplace la torpille

            while (position[0] != x)
            {
                if (position[0] < x)
                {
                    position[0] += vitesse;
                }
                else
                {
                    position[0]-=vitesse;
                }
            }
            while (position[1] != y)
            {
                if (position[1] < y)
                {
                    position[1] += vitesse;
                }
                else
                {
                    position[1]-=vitesse;
                }
            }
            while (position[2] != z)
            {
                if (position[2] < z)
                {
                    position[2] += vitesse;
                }
                else
                {
                    position[2]-=vitesse;
                }
            }
             

            // teste la collision avec la cible
            if (cible.isTouche(position[0], position[1], position[2]) == true)
            {
                cible.setCoule(true);
                System.out.print("La partie est gagnée !");
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "La torpille se déplace à la vitesse " + vitesse + ", a une autonomie de " + autonomie + " pixels, se trouve à la position (" + position[0] + ";" + position[1] + ";" + position[2] + "), vient du sous-marin " + nomSousMarin + " et cible le sous-marin " + cible.nom + ".";
    }
}
