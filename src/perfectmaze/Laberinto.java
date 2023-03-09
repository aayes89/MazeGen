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

public class Laberinto {

    private int rows, cols;
    private boolean[][] horiz, vert, visited;
    private Random rand;

    public Laberinto(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        horiz = new boolean[rows + 1][cols];
        vert = new boolean[rows][cols + 1];
        visited = new boolean[rows][cols];
        rand = new Random();
        generarLaberinto();
    }

    public void generarLaberinto() {
        ArrayList<Point> list = new ArrayList<>();
        Point start = new Point(rand.nextInt(rows), rand.nextInt(cols));
        list.add(start);

        while (!list.isEmpty()) {
            int index = rand.nextInt(list.size());
            Point current = list.remove(index);
            visited[current.x][current.y] = true;

            ArrayList<Point> neighbors = new ArrayList<>();

            if (current.x > 0 && !visited[current.x - 1][current.y]) {
                neighbors.add(new Point(current.x - 1, current.y));
            }
            if (current.x < rows - 1 && !visited[current.x + 1][current.y]) {
                neighbors.add(new Point(current.x + 1, current.y));
            }
            if (current.y > 0 && !visited[current.x][current.y - 1]) {
                neighbors.add(new Point(current.x, current.y - 1));
            }
            if (current.y < cols - 1 && !visited[current.x][current.y + 1]) {
                neighbors.add(new Point(current.x, current.y + 1));
            }

            if (!neighbors.isEmpty()) {
                index = rand.nextInt(neighbors.size());
                Point neighbor = neighbors.get(index);
                list.add(current);
                if (neighbor.x == current.x - 1) {
                    horiz[current.x][current.y] = true;
                } else if (neighbor.x == current.x + 1) {
                    horiz[current.x + 1][current.y] = true;
                } else if (neighbor.y == current.y - 1) {
                    vert[current.x][current.y] = true;
                } else {
                    vert[current.x][current.y + 1] = true;
                }
                list.add(neighbor);
                visited[neighbor.x][neighbor.y] = true;
            }
        }
    }

    public void generarLaberinto1() {
    int rows = visited.length;
    int cols = visited[0].length;
    ArrayList<Point> list = new ArrayList<>();
    Point start = new Point(rand.nextInt(rows), rand.nextInt(cols));
    list.add(start);

    boolean[][] visited = new boolean[rows][cols];
    boolean[][] horiz = new boolean[rows][cols];
    boolean[][] vert = new boolean[rows][cols];

    while (!list.isEmpty()) {
        int index = rand.nextInt(list.size());
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
            index = rand.nextInt(neighbors.size());
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
    public void dibujarLaberinto(Graphics g, int cellSize) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, cols * cellSize, rows * cellSize);

        g.setColor(Color.BLACK);
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (horiz[x][y]) {
                    g.drawLine(x * cellSize, y * cellSize, (x + 1) * cellSize, y * cellSize);
                }
                if (vert[x][y]) {
                    g.drawLine(x * cellSize, y * cellSize, x * cellSize, (y + 1) * cellSize);
                }
            }
        }
    }
}
