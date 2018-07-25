/*
 * Ejemplo básico en Java2D
 * 
 * Tomado de el Tutorial de Java2D de ZetTutorial: http://zetcode.com/tutorials/java2dtutorial/
 * 
 * Java tiene un tutorial oficial para Java2D: http://docs.oracle.com/javase/tutorial/2d/index.html
 */
package points;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Points extends JPanel {
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);

        // dibuja marco
        g2d.drawLine(0, 0, 400, 0);

        // dibuja plano
        g2d.drawLine(200, 100, 200, 300);
        g2d.drawLine(100, 200, 300, 200);

        g2d.setColor(Color.blue);

        // lineas de prueba
        drawLine2(g2d, 0, 0, 100, 100);
        drawLine2(g2d, 0, 0, 100, 50);
        drawLine2(g2d, 0, 0, 50, 100);
        drawLine2(g2d, -100, 100, 0, 0);
        drawLine2(g2d, 0, 0, -100, -100);

        drawLine2(g2d, 50, -50, 50, 100);
        drawLine2(g2d, -50, 100, -50, -50);
        drawLine2(g2d, -60, 100, -60, 100);
    }

    //                                    2       3       4       6
    public void drawLine2(Graphics2D g, int x1, int y1, int x2, int y2) {
        
        if (x1 == x2) {
            if (y1 >= y2) {
                for (int i = y1; i >= y2; i--) {
                    g.drawLine(x1 + 200, 200 - i, x1 + 200, 200 - i);
                }
                return;
            } else {
                for (int i = y1; i <= y2; i++) {
                    g.drawLine(x1 + 200, 200 - i, x1 + 200, 200 - i);
                }
                return;
            }

        }

        if (x1 > x2) {
            int aux = x2;
            x2 = x1;
            x1 = aux;

            aux = y2;
            y2 = y1;
            y1 = aux;

        }

        int dy = y2 - y1;
        int dx = x2 - x1;

        int incYi, incXi, incYr, incXr;
        if (dy >= 0) {
            incYi = 1;
        } else {
            dy = dy * -1;
            incYi = -1;
        }

        if (dx >= 0) {
            incXi = 1;
        } else {
            dx = dx * -1;
            incXi = -1;
        }

        if (dx >= dy) {
            incYr = 0;
            incXr = incXi;
        } else {
            incXr = 0;
            incYr = incYi;

            int temp = dx;
            dx = dy;
            dy = temp;
        }

        int x = x1;
        int y = y1;
        int avR = (2 * dy);
        int av = (avR - dx);
        int avI = (av - dx);

        do {
            g.drawLine(x + 200, 200 - y, x + 200, 200 - y);
            // System.out.println("av: " + av);

            if (av >= 0) {
                x = x + incXi;
                y = y + incYi;
                av = av + avI;
            } else {
                x = x + incXr;
                y = y + incYr;
                av = av + avR;

            }

        } while (x < x2);

    }

    public static void main(String[] args) {
        // Crear un nuevo Frame
        JFrame frame = new JFrame("Points");
        // Al cerrar el frame, termina la ejecución de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        frame.add(new Points());
        // Asignarle tamaño
        frame.setSize(400, 400);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);
    }
}
