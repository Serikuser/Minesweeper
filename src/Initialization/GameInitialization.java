package Initialization;

import Game.Setup;
import Utils.Coord;
import Utils.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameInitialization extends JFrame implements KeyListener {

    private JPanel panel;
    private JLabel label;
    private final int COLS = 20;
    private final int ROWS = 20;
    private final int IMAGE_SIZE = 20;
    private final int BOMBS = 40;

    Setup newGame = new Setup(COLS, ROWS, BOMBS);

    public GameInitialization() {
        newGame.start(newGame);
        setImages();
        addKeyListener(this);
        initLable();
        initPanel();
        initFrame();
    }


    public void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simple Minesweeper");
        setIconImage(getImage("icon"));
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (KeyEvent.getKeyText(e.getKeyCode()).equals("R"))
            newGame.start(newGame);
        panel.repaint();
        label.setText(getMessage());
    }

    public void initPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                for (Coord coord : newGame.getRanges().getAllcoords()) {
                    g.drawImage((Image) newGame.getField(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    newGame.pressLeftButton(coord, newGame);
                if (e.getButton() == MouseEvent.BUTTON2)
                    newGame.pressMiddleButton(coord, newGame);
                if (e.getButton() == MouseEvent.BUTTON3)
                    newGame.pressRightButton(coord, newGame);
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                newGame.getRanges().getSize().x * IMAGE_SIZE,
                newGame.getRanges().getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private String getMessage() {
        switch (newGame.getState()) {
            case PLAYING:
                return "Be careful... Press 'R' to restart.";
            case WIN:
                return "Congratulations! Press 'R' to restart.";
            case EXPLOSION:
                return "You lose! Press 'R' to restart.";
            default:
                return " ";
        }

    }

    public void initLable() {
        label = new JLabel("New Game Started");
        add(label, BorderLayout.SOUTH);
    }

    public void setImages() {
        for (Field box : Field.values())
            box.image = getImage(box.name());
    }

    private Image getImage(String name) {
        String filename = "/images/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

}
