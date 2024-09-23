import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    public static void MainMenu(){
        InfoGame infoGame = new InfoGame();
        JFrame frame = new JFrame("Game Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        BackgroundPanel backgroundPanel = new BackgroundPanel("Tic-Tac-Toe.png");
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        MyCustomButton play1by1Button = new MyCustomButton("Play 1 by 1");
        play1by1Button.setPreferredSize(new Dimension(400, 50));
        play1by1Button.setFont(new Font("Arial", Font.BOLD, 24));
        play1by1Button.addActionListener(e -> {
            InputGamer ig = new InputGamer(frame);
            if (ig.isInput) {
                infoGame.gamer1 = ig.fieldGamer1.getText();
                infoGame.gamer2 = ig.fieldGamer2.getText();
                StringBuilder sb = new StringBuilder(ig.gameName.getText());
                sb.append(".txt");
                infoGame.fileName = sb.toString();

                frame.dispose();

                Games.Game(infoGame, infoGame.gamer1, infoGame.gamer2);
            }
        });
        gbc.gridx=0;
        gbc.gridy = 0;
        gbc.insets.top = 100;
        backgroundPanel.add(play1by1Button, gbc);

        MyCustomButton playWithComputerButton = new MyCustomButton("Play With Computer");
        playWithComputerButton.setPreferredSize(new Dimension(400, 50));
        playWithComputerButton.setFont(new Font("Arial", Font.BOLD, 24));
        playWithComputerButton.addActionListener(e -> {
            ModeDialog md = new ModeDialog(frame);
            md.setVisible(true);
            boolean isHardMode = md.isHardMode();
            InputGamerComputer igc = new InputGamerComputer(frame);
            if (igc.isInput) {
                infoGame.gamer1 = igc.fieldGamer1.getText();
                infoGame.gamer2="Computer";
                StringBuilder sb = new StringBuilder(igc.gameName.getText());
                sb.append(".txt");
                infoGame.fileName = sb.toString();
                frame.dispose();
                Games.GameWithComputer(infoGame, isHardMode, infoGame.gamer1);
            }
        });
        gbc.gridy = 1;
        gbc.insets.top = 50;
        backgroundPanel.add(playWithComputerButton, gbc);

        MyCustomButton loadGameButton = new MyCustomButton("Load Game 1 by 1");
        loadGameButton.setPreferredSize(new Dimension(400, 50));
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        loadGameButton.addActionListener(e ->{
            LoadGameDialog lg = new LoadGameDialog(frame);
            if(!lg.file.equals("")){
                infoGame.fileName=lg.file;
                infoGame.gamer1=lg.gamer1;
                infoGame.gamer2=lg.gamer2;
                ArrayList<Record> records = new ArrayList<>();
                try(BufferedReader br = new BufferedReader(new FileReader(lg.file))){
                    String line;
                    while ((line = br.readLine()) != null) {
                        Record record = new Record();
                        record.SetLine(line);
                        records.add(record);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
                Games.GameFromFile(infoGame, records, infoGame.gamer1, infoGame.gamer2);
            }
        });
        gbc.gridy = 2;
        gbc.insets.top = 50;
        backgroundPanel.add(loadGameButton, gbc);

        MyCustomButton loadGameWithComputerButton = new MyCustomButton("Load Game With Computer");
        loadGameWithComputerButton.setPreferredSize(new Dimension(400, 50));
        loadGameWithComputerButton.setFont(new Font("Arial", Font.BOLD, 24));
        loadGameWithComputerButton.addActionListener(e ->{
            LoadGameDialogForComputer lgfc = new LoadGameDialogForComputer(frame);
            if(!lgfc.file.equals("")){
                infoGame.fileName=lgfc.file;
                infoGame.gamer1=lgfc.gamer1;
                infoGame.gamer2="Computer";
                ArrayList<Record> records = new ArrayList<>();
                try(BufferedReader br = new BufferedReader(new FileReader(lgfc.file))){
                    String line;
                    while ((line = br.readLine()) != null) {
                        Record record = new Record();
                        record.SetLine(line);
                        records.add(record);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose();
                if(lgfc.mode.equals("H")){
                    Games.GameWithComputerFromFile(infoGame, records, true, infoGame.gamer1);
                }
                else {
                    Games.GameWithComputerFromFile(infoGame, records, false, infoGame.gamer1);
                }
            }
        });
        gbc.gridy = 3;
        gbc.insets.top = 50;
        backgroundPanel.add(loadGameWithComputerButton, gbc);

        MyCustomButton exitButton = new MyCustomButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setPreferredSize(new Dimension(400, 50));
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 4;
        gbc.insets.top = 50;
        backgroundPanel.add(exitButton, gbc);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }
}
