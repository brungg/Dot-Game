package JavaVS;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Game extends JPanel {
    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    final int SCALE = 100;

    ArrayList<int[]> circles = new ArrayList<>();
    ArrayList<int[]> lines = new ArrayList<>();

    double radius;
    int hits = 0;
    int misses = 0;
    double angle = 0;

    boolean first = true;

    public Game() {
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);

        System.out.print("Enter target radius: ");
        radius = input.nextDouble();
        input.nextLine();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(150, 250, 350, 250);
        g.drawLine(250, 150, 250, 350);

        int x = rand.nextInt(200);
        int y = rand.nextInt(200);
        circles.add(new int[] {x-100, y-100});

        for(int[] a: circles) {
            g.setColor(Color.YELLOW);
            g.fillOval(a[0] + 250, a[1] + 250, (int) (radius * SCALE), (int) (radius * SCALE));
        }
        if(first)
            first = false;
        else {
            lines.add(drawOutput(g, circles.get(circles.size() - 2)[0], circles.get(circles.size() - 2)[1], (int) (radius * SCALE)));
        }
        for(int[] a: lines) {
            g.setColor(Color.BLACK);
            g.drawLine(250, 250, a[0]+250, a[1]+250);
            check(g, circles.get(lines.indexOf(a))[0], circles.get(lines.indexOf(a))[1], 
                a[0], a[1], (int) (radius * SCALE));
        }

        repaint();
    }

    private void check(Graphics g, int x, int y, int newX, int newY, int radius) {
        if(newX > x && newX < x + radius && newY > y && newY < y + radius) {
            g.setColor(Color.GREEN);
            g.fillOval(x + 250, y + 250, radius, radius);
            // hits++;
        }
        else {
            g.setColor(Color.RED);
            g.fillOval(x + 250, y + 250, radius, radius);
            // misses++;
        }
    }

    private int[] drawOutput(Graphics g, int x, int y, int radius) {
        System.out.println("Hits: " + hits + "         Misses: " + misses);
        System.out.print("Enter angle of trajectory (deg), (x to exit): ");
        String a = input.nextLine();
        int newX = 0;
        int newY = 0;

        if(a.equals("x")) {
            System.exit(0);
        }
        else {
            angle = -(Math.PI/180 * Double.parseDouble(a));//Double.parseDouble(a)
            int length = (int) Math.round(Math.sqrt((x + radius/2) * (x + radius/2) + (y + radius/2) * (y + radius/2)));
            newX = (int) Math.round(length * (Math.cos(angle)));
            newY = (int) Math.round(length * (Math.sin(angle)));
        }
        int[] ret = {newX, newY};
        return ret;
    }
}