import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Falling Squares with Tank");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gamePanel.startGame();
    }
}

class GamePanel extends JPanel {
    private static final int squareSize = 50;
    private static final int squareNum = 3;
    private static final int tries = 3;
    private Square[] squares;
    private SquareThread[] squareThreads;
    private Tank tank;
    private Threadish threadish;
    private int restartsLeft = tries;
    private int score = 0;

    public GamePanel() {
        setFocusable(true);
        squares = new Square[squareNum];
        squareThreads = new SquareThread[squareNum];
        tank = new Tank(375, 550, this);
        threadish = new Threadish(squares, tank, this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    tank.move(-20);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    tank.move(20);
                } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    tank.shoot();
                }
            }
        });
    }

    public void startGame() {
        for (int i = 0; i < squareNum; i++) {
            squares[i] = new Square(100 + i * 200, 0, squareSize, this);
            squareThreads[i] = new SquareThread(squares[i]);
            squareThreads[i].start();
        }
        threadish.start();
    }

    public void restartGame() {
        if (restartsLeft > 0) {
            restartsLeft--;
            score = 0;
            for (Square square : squares) {
                square.reset();
            }
            for (SquareThread squareThread : squareThreads) {
                squareThread.restart();
            }
            tank.reset();
            threadish = new Threadish(squares, tank, this);
            threadish.start();
        }
    }

    public void incrementScore() {
        score++;
    }

    public void stopGame() {
        for (SquareThread st : squareThreads) {
            st.interrupt();
        }
        threadish.interrupt();
        JOptionPane.showMessageDialog(this, "Collision Detected! Game Over!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square square : squares) {
            square.draw(g);
        }
        tank.draw(g);
        g.drawString("Score: " + score, 10, 10);
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
                y = 0;
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

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public boolean getrun() {
        return run;
    }

    public void stop() {
        run = false;
    }
}

class SquareThread extends Thread {
    private Square square;
    private boolean stoprun = false;
    private int speed;

    public SquareThread(Square square) {
        this.square = square;
        this.speed = new Random().nextInt(5) + 1;
    }

    public synchronized void restart() {
        stoprun = false;
        notify();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    while (stoprun) {
                        wait();
                    }
                }
                square.move(speed);
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Tank {
    private int x, y;
    private GamePanel panel;
    private List<Shell> shells;

    public Tank(int x, int y, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
        this.shells = new ArrayList<>();
    }

    public void move(int dx) {
        x += dx;
        if (x < 0) {
            x = 0;
        } else if (x > panel.getWidth() - 50) {
            x = panel.getWidth() - 50;
        }
        panel.repaint();
    }

    public void shoot() {
        Shell shell = new Shell(x + 20, y - 20, panel);
        shells.add(shell);
        ShellThread shellThread = new ShellThread(shell);
        shellThread.start();
    }

    public void reset() {
        x = 375;
        shells.clear();
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 50, 20);
        for (Shell shell : shells) {
            shell.draw(g);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 20);
    }

    public List<Shell> getShells() {
        synchronized (shells) {
            return new ArrayList<>(shells);
        }
    }
}

class Shell {
    private int x, y;
    private GamePanel panel;
    private boolean active = true;

    public Shell(int x, int y, GamePanel panel) {
        this.x = x;
        this.y = y;
        this.panel = panel;
    }

    public void move() {
        if (active) {
            y -= 10;
            if (y < 0) {
                active = false;
            }
            panel.repaint();
        }
    }

    public void draw(Graphics g) {
        if (active) {
            g.fillRect(x, y, 10, 10);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 10, 10);
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        active = false;
    }
}

class ShellThread extends Thread {
    private Shell shell;

    public ShellThread(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void run() {
        try {
            while (shell.isActive()) {
                shell.move();
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Threadish extends Thread {
    private Square[] squares;
    private Tank tank;
    private GamePanel panel;

    public Threadish(Square[] squares, Tank tank, GamePanel panel) {
        this.squares = squares;
        this.tank = tank;
        this.panel = panel;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (Square square : squares) {
                    if (square.getrun() && tank.getBounds().intersects(square.getBounds())) {
                        panel.stopGame();
                        return;
                    }
                    for (Shell shell : tank.getShells()) {
                        if (shell.isActive() && square.getBounds().intersects(shell.getBounds())) {
                            square.stop();
                            shell.deactivate();
                            panel.incrementScore();
                        }
                    }
                }
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
