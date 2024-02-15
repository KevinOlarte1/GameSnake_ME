package lib;

import java.util.ArrayList;
import java.util.Random;

public class Apple {
    private int increment;

    public Apple() {
        this.increment = 1;
    }
    

    /**
     * Method for return the position where the apple can appear.
     * @param map Array with the empty position.
     * @return String with the position.
     */
    public String appear(ArrayList<String> map){
        Random rnd = new Random();
        String maps;
        try {
             maps = map.get(rnd.nextInt(1,map.size()));
             return maps;
        } catch (Exception e) {
            System.out.println("eeror");
        }

        return null;
        
        
    }


    public int getIncrement() {
        return increment;
    }

    
}
