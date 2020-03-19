package battleship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Menu {
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel infoLabel;
    private JButton playButton;
    private JButton exitButton;
    private JButton hostButton;
    private JButton findButton;
    private boolean connection = false;

    public Menu() {
    }

    public void launchMenu(final Gameboard board, final Network network) {

        //Overrall window create and init
        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        //Create and init inner panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1)); //using simple grid layout for top to bottom list of buttons and labels. Has infinite rows and 1 column.

        //Create and init label
        titleLabel = new JLabel("BATTLESHIP", SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        infoLabel = new JLabel();
        mainPanel.add(infoLabel);
        //Create and init buttons
        playButton = new JButton("PLAY");
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(connection) {
                    launchGame(board);
                }
                else {
                    infoLabel.setText("ERROR - No connection");
                }
               
            }          
         });
        mainPanel.add(playButton);

        hostButton = new JButton("Host Game");
        hostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               network.launchHost();
            }          
        });
        mainPanel.add(hostButton);

        findButton = new JButton("Find Game");
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               network.findHost();
            }          
        });
        mainPanel.add(findButton);

        exitButton = new JButton("EXIT TO DESKTOP");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               closeGame();
            }          
         });
        mainPanel.add(exitButton);

        //Adding components to each other from inside to out
        createWindow();
        return;
    }

    private void launchGame(Gameboard board) {
        frame.setVisible(false);
        board.start();
        Battleship.createShips(board);
        return;
    }

    private void closeGame() {
        System.exit(0);
        return;
    }

    private void createWindow() {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        return;
    }

}