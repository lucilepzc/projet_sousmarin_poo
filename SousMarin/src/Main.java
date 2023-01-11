import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) {
        /* Le joueur 1 a le sous marin lanceur d'engins, le deuxi�me le sous marin d'attaque tir� au sort*/
        Triomphant J1 = new Triomphant();
        int random = (int)(Math.random() * 2);
        System.out.println(random);
        /*if (random == 1)
        {
            Suffren J2 = new Suffren();
        }
        else
        {
            Rubis J2 = new Rubis();
        }*/

        Suffren J2 = new Suffren();

        System.out.println("La partie va commencer ! Rappel : il est impossible de tirer un missile � plus de 40m de profondeur et si vous n'�tes pas en position horizontale.'");
        
        // Tant que les sous marins ne sont pas coulés et que l'autonomie des sous-marins n'est pas � 0
        // on continue de jouer.

        while (J1.autonomie != 0 && J2.autonomie != 0 && !J1.isCoule() && !J2.isCoule())
        {
            System.out.println("C'est au tour de J1 !");
            // Premierement on lance les missiles si on le peut / si on le souhaite
            System.out.println("J1 veut-il lancer un missile ? O / N");
            Scanner sc = new Scanner(System.in);
            String ans1 = sc.nextLine();
            if (ans1.equals("o") || ans1.equals("O"))
            {
                if (J1.position[2] < 40 && J1.inclinaison[2] == 0)
                {
                    System.out.println("Entrer la coordonn�e sur l'axe x.");
                    String c1 = sc.nextLine();
                    int x1 = parseInt(c1);
                    System.out.println("Entrer la coordonn�e sur l'axe y.");
                    String c2 = sc.nextLine();
                    int y1 = parseInt(c2);
                    System.out.println("Entrer la coordonn�e sur l'axe z.");
                    String c3 = sc.nextLine();
                    int z1 = parseInt(c3);

                    J1.tirerTorpille(x1,y1,z1,J2);
                }
            }
            // Puis on joue 
            System.out.println("Voulez-vous vous d�placer ? O/N");
            String ans2 = sc.nextLine();
            if (ans2.equals("o") || ans2.equals("O"))
            {
                    System.out.println("Voulez vous changer l'inclinaison du sous-marin ? o/n");
                    String ans4 = sc.nextLine();
                    if (ans4.equals("o") || ans4.equals("O"))
                    {
                        System.out.println("Voulez-vous relever le sous-marin (+45), le mettre � l'horizontale (0) ou l'abaisser (-45) ?");
                        String ans5 = sc.nextLine();
                        switch (ans5) {
                            case "+45" -> J1.remonter();
                            case "0" -> J1.stabiliser();
                            case "-45" -> J1.plonger();
                        }
                    }


                int anciennepos[] = J1.position;
                int nouvellepos[] = J1.deplacement(J1.vitesse);
                System.out.println("La nouvelle position du sous-marin " + J1.nom + " est : (" + nouvellepos[0] + ";" + nouvellepos[1] + ";" + nouvellepos[2] + ")");
                System.out.println("La nouvelle inclinaison du sous-marin " + J2.nom + " est : (" + J1.getInclinaison()[0] + ";" + J1.getInclinaison()[1] + ";" + J1.getInclinaison()[2] + ")");
                J2.listenPositionEnnemie(nouvellepos[0], nouvellepos[1], nouvellepos[2]);
                J1.setAutonomie(J1.autonomie-1);
            }

            System.out.println("C'est au tour de J2 !");
            // idem pour J2
            // Premierement on lance les missiles si on le peut / si on le souhaite
            System.out.println("J2 veut-il lancer un missile ? O / N");
            String ans21 = sc.nextLine();
            if (ans21.equals("o") || ans21.equals("O"))
            {
                if (J2.position[2] < 40 && J2.inclinaison[2] == 0)
                {
                    System.out.println("Entrer la coordonnee sur l'axe x.");
                    String c21 = sc.nextLine();
                    int x21 = parseInt(c21);
                    System.out.println("Entrer la coordonnee sur l'axe y.");
                    String c22 = sc.nextLine();
                    int y21 = parseInt(c22);
                    System.out.println("Entrer la coordonnee sur l'axe z.");
                    String c23 = sc.nextLine();
                    int z21 = parseInt(c23);

                    J1.tirerTorpille(x21,y21,z21,J2);
                }
            }
            // Puis on joue
            System.out.println("Voulez-vous vous d�placer ? O/N");
            String ans22 = sc.nextLine();
            if (ans22.equals("o") || ans22.equals("O"))
            {
                System.out.println("Voulez vous changer l'inclinaison du sous-marin ? o/n");
                String ans24 = sc.nextLine();
                if (ans24.equals("o") || ans24.equals("O"))
                {
                    System.out.println("Voulez-vous relever le sous-marin (+45), le mettre � l'horizontale (0) ou l'abaisser (-45) ?");
                    String ans25 = sc.nextLine();
                    switch (ans25) {
                        case "+45" -> J2.remonter();
                        case "0" -> J2.stabiliser();
                        case "-45" -> J2.plonger();
                    }
                }

                int anciennepos2[] = J2.position;
                int nouvellepos2[] = J2.deplacement(J2.vitesse);
                System.out.println("La nouvelle position du sous-marin " + J2.nom + " est : (" + nouvellepos2[0] + ";" + nouvellepos2[1] + ";" + nouvellepos2[2] + ")");
                System.out.println("La nouvelle inclinaison du sous-marin " + J2.nom + " est : (" + J2.getInclinaison()[0] + ";" + J2.getInclinaison()[1] + ";" + J2.getInclinaison()[2] + ")");
                J1.listenPositionEnnemie(nouvellepos2[0], nouvellepos2[1], nouvellepos2[2]);
                J2.setAutonomie(J2.autonomie-1);
            }
        }
        

    }
}