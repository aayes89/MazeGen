/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perfectmaze;

/**
 *
 * @author AyA
 */
import java.awt.*;
import java.util.*;
import javax.swing.JFrame;

public class PerfectMaze extends JFrame {

    private static final int CELDA = 10;
    private static final int ANCHO = 50;
    private static final int ALTO = 50;
    private boolean[][] visitado;
    private boolean[][] paredesHor;
    private boolean[][] paredesVer;
    private int inicioX, inicioY, finX, finY;
    private Random random;
    private int rows, cols;
    
    

    public PerfectMaze() {
        setTitle("Laberinto Perfecto");
        setSize((ANCHO + 1) * CELDA, (ALTO + 1) * CELDA);
        setResizable(false);
        setVisible(true);
        random = new Random();
        inicioX = 1;
        inicioY = 1;
        finX = ANCHO - 1;
        finY = ALTO - 1;
        visitado = new boolean[ANCHO][ALTO];
        paredesHor = new boolean[ANCHO][ALTO + 1];
        paredesVer = new boolean[ANCHO + 1][ALTO];
        generarLaberinto(inicioX, inicioY);
        //generarLaberinto();
        //generarLaberinto1();
    }
    

    private void generarLaberinto(int x, int y) {
        visitado[x][y] = true;
        ArrayList<Integer> direcciones = new ArrayList<Integer>();
        direcciones.add(1);
        direcciones.add(2);
        direcciones.add(3);
        direcciones.add(4);
        Collections.shuffle(direcciones, random);
        for (Integer direccion : direcciones) {
            switch (direccion) {
                case 1: // Arriba
                    if (y > 0 && !visitado[x][y - 1]) {
                        paredesHor[x][y] = true;
                        generarLaberinto(x, y - 1);
                    }
                    break;
                case 2: // Derecha
                    if (x < ANCHO - 1 && !visitado[x + 1][y]) {
                        paredesVer[x + 1][y] = true;
                        generarLaberinto(x + 1, y);
                    }
                    break;
                case 3: // Abajo
                    if (y < ALTO - 1 && !visitado[x][y + 1]) {
                        paredesHor[x][y + 1] = true;
                        generarLaberinto(x, y + 1);
                    }
                    break;
                case 4: // Izquierda
                    if (x > 0 && !visitado[x - 1][y]) {
                        paredesVer[x][y] = true;
                        generarLaberinto(x - 1, y);
                    }
                    break;
            }
        }
    }
    
    public void generarLaberinto() {
        ArrayList<Point> list = new ArrayList<>();
        Point start = new Point(random.nextInt(rows), random.nextInt(cols));
        list.add(start);

        while (!list.isEmpty()) {
            int index = random.nextInt(list.size());
            Point current = list.remove(index);
            visitado[current.x][current.y] = true;

            ArrayList<Point> neighbors = new ArrayList<>();

            if (current.x > 0 && !visitado[current.x - 1][current.y]) {
                neighbors.add(new Point(current.x - 1, current.y));
            }
            if (current.x < rows - 1 && !visitado[current.x + 1][current.y]) {
                neighbors.add(new Point(current.x + 1, current.y));
            }
            if (current.y > 0 && !visitado[current.x][current.y - 1]) {
                neighbors.add(new Point(current.x, current.y - 1));
            }
            if (current.y < cols - 1 && !visitado[current.x][current.y + 1]) {
                neighbors.add(new Point(current.x, current.y + 1));
            }

            if (!neighbors.isEmpty()) {
                index = random.nextInt(neighbors.size());
                Point neighbor = neighbors.get(index);
                list.add(current);
                if (neighbor.x == current.x - 1) {
                    paredesHor[current.x][current.y] = true;
                } else if (neighbor.x == current.x + 1) {
                    paredesHor[current.x + 1][current.y] = true;
                } else if (neighbor.y == current.y - 1) {
                    paredesVer[current.x][current.y] = true;
                } else {
                    paredesVer[current.x][current.y + 1] = true;
                }
                list.add(neighbor);
                visitado[neighbor.x][neighbor.y] = true;
            }
        }
    }

    public void generarLaberinto1() {
    int rows = visitado.length;
    int cols = visitado[0].length;
    ArrayList<Point> list = new ArrayList<>();
    Point start = new Point(random.nextInt(rows), random.nextInt(cols));
    list.add(start);

    boolean[][] visited = new boolean[rows][cols];
    boolean[][] horiz = new boolean[rows][cols];
    boolean[][] vert = new boolean[rows][cols];

    while (!list.isEmpty()) {
        int index = random.nextInt(list.size());
        Point current = list.remove(index);
        int x = current.x;
        int y = current.y;
        visited[x][y] = true;

        ArrayList<Point> neighbors = new ArrayList<>();
        if (x > 0 && !visited[x - 1][y]) {
            neighbors.add(new Point(x - 1, y));
        }
        if (x < rows - 1 && !visited[x + 1][y]) {
            neighbors.add(new Point(x + 1, y));
        }
        if (y > 0 && !visited[x][y - 1]) {
            neighbors.add(new Point(x, y - 1));
        }
        if (y < cols - 1 && !visited[x][y + 1]) {
            neighbors.add(new Point(x, y + 1));
        }

        if (!neighbors.isEmpty()) {
            index = random.nextInt(neighbors.size());
            Point neighbor = neighbors.get(index);
            int nx = neighbor.x;
            int ny = neighbor.y;
            list.add(current);
            if (nx == x - 1) {
                horiz[x][y] = true;
            } else if (nx == x + 1) {
                horiz[x + 1][y] = true;
            } else if (ny == y - 1) {
                vert[x][y] = true;
            } else {
                vert[x][y + 1] = true;
            }
            list.add(neighbor);
            visited[nx][ny] = true;
        }
    }
}

    public void paint(Graphics g) {
        for (int x = 0; x < ANCHO; x++) {
            for (int y = 0; y < ALTO; y++) {
                if (paredesHor[x][y]) {
                    g.drawLine(x * CELDA, y * CELDA, (x + 1) * CELDA, y * CELDA);
                }
                if (paredesVer[x][y]) {
                    g.drawLine(x * CELDA, y * CELDA, x * CELDA, (y + 1) * CELDA);
                }
            }
        }
    }

    public static void main(String[] args) {
        PerfectMaze pm = new PerfectMaze();
        
    }
}
