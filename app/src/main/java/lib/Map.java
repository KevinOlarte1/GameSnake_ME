package lib;

import java.awt.Graphics;
import java.util.ArrayList;

import org.checkerframework.checker.units.qual.s;

import java.awt.Color;

public class Map {
    private final int width;
    private final int height;
    private final int[][] map;
    
    ArrayList<String> emptyPosition = new ArrayList<>();
    
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    //Variable for print the map
    private final int widthBlock = 30;
    private final int heightBlock = 30;
    private final int movimentBlock = 30;

    private int apples = 0;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new int[width][height];
        resetMap();
    }

    /**
     * Method for reset the map.
     */
    public void resetMap(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                    emptyPosition.add( i + "," + j);
                }
            }
        }
    
    }
    /**
     * Method for paint the map.
     * @param g
     * @param snake
     */
    public void paint(Graphics g, Snake snake){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 1) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                    g.setColor(new Color(255,255,255));
                    g.drawRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                }
                if (map[i][j] == 2) {

                    emptyPosition.remove(i + "-" + j);
                    g.setColor(new Color(229, 142, 7));
                    g.fillRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                    g.setColor(new Color(255,255,255));
                    g.drawRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                }
                if (map[i][j] == 0) {
                    if (emptyPosition.indexOf(i + "," + j) == -1) {
                        emptyPosition.add(i + "," + j);
                    }
                    g.setColor(new Color(64,64,64));
                    g.fillRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                    g.setColor(new Color(255,255,255));
                    g.drawRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                }
                if (map[i][j] == 3) {
                    emptyPosition.remove(i + "-" + j);
                    g.setColor(new Color(204,36,2));
                    g.fillRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                    g.setColor(new Color(255,255,255));
                    g.drawRect(j*movimentBlock, i*movimentBlock, widthBlock, heightBlock);
                }
            }
        }
    }


    public void update(Snake snake, Apple apple) {
        int x[] = snake.getBodyx();
        int y[] = snake.getBodyy();

        int lastPositionX = x[snake.getLenght()-1];
        int lastPositionY = y[snake.getLenght()-1];
        int size = snake.getLenght();
        
        
        //Update the snake for the moviment
        snake.move();
      
        
        map[lastPositionY][lastPositionX] = 0;  
            //System.out.println("lastPositionX: " + lastPositionX + " lastPositionY: " + lastPositionY);
            //scanner.nextLine();
            
            if (apples < 1) {
                //Metodo que devuelve la posicion de la aparicion de la manzana
                String posManzana = apple.appear(emptyPosition);
                apples++;
                map[Integer.parseInt(posManzana.substring(0, posManzana.indexOf(",")))][Integer.parseInt(posManzana.substring(posManzana.indexOf(",") +1, posManzana.length()))] = 3;
            }
        for  (int i = 0; i < size; i++) {
            if(map[y[i]][x[i]] == 3){
                apples--;
                map[y[i]] [x[i]]= 2;
                snake.increment((apple.getIncrement()));
            }
            else
                map[y[i]] [x[i]]= 2;   
             
        }
    }
    public void print(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
