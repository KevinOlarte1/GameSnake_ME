/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package snake1;


import java.awt.Graphics;
import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


import lib.Apple;
import lib.Map;
import lib.Snake;


public class App extends JPanel {
    static Map map = new Map(15,15);
    static Snake snake = new Snake(map);
    static Apple apple = new Apple();


    public App(){
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO: Implement keyTyped method
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                snake.direction(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO: Implement keyReleased method
            }
        });
        setFocusable(true);
    }
    public static void main(String[] args) {
       
        JFrame miventana = new JFrame();
        App game = new App();
        
        miventana.add(game);
        miventana.setResizable(false);
        miventana.setSize((31*15) -14,(32*15) + 4);
        miventana.setLocation(300,200);
        miventana.setVisible(true);

        miventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        do{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {}
            map.update(snake, apple);
            //map.print();
            
            game.repaint();
            
        }while (snake.finish(map) == 0);
        miventana.dispose();
            
        
    }
    @Override 
    public void paint(Graphics grafico){
        map.paint(grafico, snake);
        
    }

}
