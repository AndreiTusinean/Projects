package client.presentation.view;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //LoginUI l = new LoginUI();
        Random rand = new Random();
        int loc;

        for (int i = 0; i < 8; i++) {
            loc = rand.nextInt(2) + 1;
            System.out.println(loc);
        }
    }
}
