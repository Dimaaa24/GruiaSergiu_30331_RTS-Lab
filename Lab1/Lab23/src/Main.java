import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
            JFrame frame = new JFrame("Falling Squares");
            GamePanel gamePanel = new GamePanel();
            frame.add(gamePanel);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            gamePanel.startGame();
    }
}

class GamePanel extends JPanel {
    private static final int squareSize= 50;
    private static final int squareNum= 3;
    private static final int tries = 2;
    private Square[] squares;
    private SquareThread[] squareThreads;
    private int restartsLeft = tries;

    public GamePanel() {
        squares = new Square[squareNum];
        squareThreads = new SquareThread[squareNum];
    }

    public void startGame() {
        for (int i = 0; i < squareNum; i++) {
            squares[i] = new Square(100 + i * 200, 0, squareSize, this);
            squareThreads[i] = new SquareThread(squares[i], this);
            squareThreads[i].start();
        }
    }

    public void restartGame() {
        if (restartsLeft > 0) {
            restartsLeft--;
            for (int i = 0; i < squareNum; i++) {
                squareThreads[i].interrupt();
                squares[i].reset();
                squareThreads[i] = new SquareThread(squares[i], this);
                squareThreads[i].start();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square square : squares) {
            square.draw(g);
        }
    }
}

class Square {
    private int x, y, size;
    private GamePanel panel;
    private boolean run = true;

    public Square(int x, int y, int size, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.panel = panel;
    }

    public synchronized void move(int ymoved) {
        if (run) {
            y += ymoved;
            if (y > panel.getHeight()) {
                run = false;
                panel.restartGame();
            }
            panel.repaint();
        }
    }

    public synchronized void reset() {
        y = 0;
        run = true;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, size, size);
    }
}

class SquareThread extends Thread {
    private Square square;
    private GamePanel panel;
    private int speed;

    public SquareThread(Square square, GamePanel panel) {
        this.square = square;
        this.panel = panel;
        this.speed = new Random().nextInt(5) + 1;
    }

    @Override
    public void run() {
        try {
            while (true) {
                square.move(speed);
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}