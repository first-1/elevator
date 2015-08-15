package utils;

import screens.GameScreen;

/**
 * Created by timi on 15.08.2015.
 */
public class RandomCreator {

    private static GameScreen gameScreen;
    private static float wantedY;
    private static float maxDist = 5*Const.SCREEN_HEIGHT; // Distanta maxima dintre obstacole(intre care se alege random)
    private static float minDist = Const.SCREEN_HEIGHT / 2; // Distanta minima dintr obstacole(ca sa nu ajungem la obstacole suprapuse)

    public static void set(GameScreen gameScreen) // initializam
    {
        RandomCreator.gameScreen = gameScreen;
        wantedY = (float)Math.random() * maxDist + Const.zero;
    }

    private static float getRandomPart() // Partea stanga sau dreapta a ecranului
    {
        if (Math.random() < 0.5f)
            return 0;
        return Const.SCREEN_WIDTH / 2f;
    }

    private static int getRandomTicks() { // Cate tickuri sa aiba obstacolul
        return  (int)(Math.random()*5) + 1;  // NOTE: Inlocuieste 5 cu maximul suportat
    }

    public static void update() {
        // Adaugam un nou obstacol daca ar trebui sa fie in ecran
        if (Const.zero + Const.SCREEN_HEIGHT >= wantedY) {
            gameScreen.addNewObstacol(getRandomPart(), wantedY, Const.SCREEN_WIDTH / 2f, 8, getRandomTicks());
            wantedY = (float)Math.random() * maxDist + wantedY; // Generam nou y la care va fi obstacol
            maxDist = Math.max(maxDist * 0.8f, minDist); // Actualizam distanta maxima pentru a avea obstacole mai dese
        }
        // DEBUG:
        System.out.println((wantedY-Const.zero)/Const.SCREEN_HEIGHT + " untill next barrier");

    }

}
