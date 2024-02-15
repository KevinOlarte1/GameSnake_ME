package lib;

import java.awt.event.KeyEvent;



public class Snake {
    private  int[] bodyx;
    private  int[] bodyy;
    private final int maxSize;
    
    private int length;
    private int direcction;

    
    public Snake(Map map) {
        
        this.maxSize = (map.getWidth() -2) * (map.getHeight() -2);
        this.bodyx = new int[maxSize];
        this.bodyy = new int[maxSize];
        this.bodyx[0] = ((map.getWidth() -2) / 2);
        this.bodyy[0] = ((map.getHeight() -2) / 2);
        this.direcction = 2;
        this.length = 1;
    }


    /**
     * Method for get the direction of the snake.
     * @param e Input event, kyboard.
     */
    public void direction(KeyEvent e) {
        if (e.getKeyCode() == 65 && direcction != 1) { 
            direcction = -1; //Left
        }
        else if (e.getKeyCode() == 68 && direcction != -1) { 
            direcction = 1; //Right
        }
        else if (e.getKeyCode() == 83 && direcction != 2) { 
            direcction = -2; //Down
        }
        else if (e.getKeyCode() == 87 && direcction != -2) { 
            direcction = 2; //Up
        }
    }

    /**
     * Method for move the snake.
     */
    public void move(){
        int x = bodyx[0];
        int y = bodyy[0];
        /* 
        switch (direcction) {
            case 1:
            
                bodyx[0] += 1;
                break;
            case -1:
                bodyx[0] -= 1;
                break;
            case 2:
                bodyy[0] -= 1;
                break;
            case -2:
                bodyy[0] += 1;
                break;
        }
        */
        //The movimento for the 2 option, the optimization option
        switch (direcction) {
            case 1:
            
                x = bodyx[0] + 1;
                break;
            case -1:
                x = bodyx[0] - 1;
                break;
            case 2:
                y = bodyy[0] - 1;
                break;
            case -2:
                y = bodyy[0] + 1;
                break;
        }
        
        // Crafting a new array for the optimization the snake.
        System.arraycopy(bodyx, 0, bodyx, 1, bodyx.length - 1); 
        System.arraycopy(bodyy, 0, bodyy, 1, bodyy.length - 1);
        bodyx [0] = x;
        bodyy[0] = y;
        
       
       /* 
        //Move the body with the snake head.
        for (int i = length; i > 0; i--) {
            if(i == 1){
                bodyx[i] = x;
                bodyy[i] = y;
            }
            else
            {
                bodyx[i] = bodyx[i-1];
                bodyy[i] = bodyy[i-1]; 
            }
            
        }
        */
    }

    /**
     * Method for detect if the game is over or win or continue.
     * @param map map of the game.
     * @return 1 if the game is over, 2 if the game is win, 0 if the game continue.
     */
    public int finish(Map map){
        if (bodyx[0] == 0 || bodyx[0] == map.getWidth() -1 || bodyy[0] == 0 || bodyy[0] == map.getHeight() -1){
                //System.out.println("Wall");
            return 1; //Game Over
        }
            
        if (length == maxSize){
                //System.out.println("Win");
            return 2; //Win
        }
            
        for (int i = 1; i < length; i++) {
            if (bodyx[0] == bodyx[i] && bodyy[0] == bodyy[i]){
                    //System.out.println("Touch");
                return 1; //Game Over
            }

                
        }
            //System.out.println("Continue");
        return 0; //Continue
    }   


    public void increment(int size) {
        this.length = this.length + size;
    }
    public int getLenght() {
        return length;
    }

    public int[] getBodyx() {
        return bodyx;
    }

    public int[] getBodyy() {
        return bodyy;
    }

}
