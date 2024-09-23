import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Games {
    public static void GameFromFile(InfoGame infoGame, ArrayList<Record> records, String gamer1, String gamer2){
        MainCoordinate M_COORD = new MainCoordinate();

        JFrame frame = new JFrame("Tic-Tac-Toe");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setBorder(BorderFactory.createTitledBorder("Moves"));

        JScrollPane scrollPane = new JScrollPane(movesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        frame.add(scrollPane, BorderLayout.EAST);

        addMoveRecord(movesPanel, gamer1+" - O");
        addMoveRecord(movesPanel, gamer2+" - X");
        addMoveRecord(movesPanel, " ");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveDialog saveDialog = new SaveDialog(frame);
                saveDialog.setVisible(true);
                if (saveDialog.isSaveToFile()) {
                    saveToFile(infoGame);
                }
                frame.dispose();
                Menu.MainMenu();
            }
        });

        frame.add(panel);

        Point turn = new Point();

        final int N_SUBFIELD = 3;
        SubFieldGui subfieldGuis[][] = new SubFieldGui[N_SUBFIELD][N_SUBFIELD];
        for (int i = 0; i < N_SUBFIELD; i++) {
            for (int j = 0; j < N_SUBFIELD; j++) {
                subfieldGuis[i][j] = new SubFieldGui(frame, panel, turn, movesPanel);
                subfieldGuis[i][j].mainCoordinate = M_COORD;
            }
        }

        FieldGui fieldGui = new FieldGui(subfieldGuis, frame, infoGame);
        fieldGui.mainCoordinate = M_COORD;
        frame.setSize(903, 903);
        for (int i = 0; i < records.size(); i++) {
            for (int BR = 0; BR < N_SUBFIELD; BR++) {
                for (int BC = 0; BC < N_SUBFIELD; BC++) {
                    for (int R = 0; R < 3; R++) {
                        for (int C = 0; C < 3; C++) {
                            if(records.get(i).bigRow==BR && records.get(i).bigColumn==BC && records.get(i).row==R && records.get(i).column==C){
                                subfieldGuis[BR][BC].buttons[R][C].doClick();
                            }
                        }
                    }
                }
            }
        }
        frame.setVisible(true);
    }

    public static void GameWithComputerFromFile(InfoGame infoGame, ArrayList<Record> records, boolean hardMode, String gamer1){
        MainCoordinate M_COORD = new MainCoordinate();

        JFrame frame = new JFrame("Tic-Tac-Toe");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setBorder(BorderFactory.createTitledBorder("Moves"));

        JScrollPane scrollPane = new JScrollPane(movesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        frame.add(scrollPane, BorderLayout.EAST);

        addMoveRecord(movesPanel, gamer1+" - O");
        addMoveRecord(movesPanel, "Computer - X");
        addMoveRecord(movesPanel, " ");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.add(panel);

        Point turn = new Point();

        final int N_SUBFIELD = 3;
        SubFieldGui subfieldGuis[][] = new SubFieldGui[N_SUBFIELD][N_SUBFIELD];
        for (int i = 0; i < N_SUBFIELD; i++) {
            for (int j = 0; j < N_SUBFIELD; j++) {
                subfieldGuis[i][j] = new SubFieldGui(frame, panel, turn, movesPanel);
                subfieldGuis[i][j].mainCoordinate = M_COORD;
            }
        }

        FieldGui fieldGui = new FieldGui(subfieldGuis, frame, infoGame);
        fieldGui.mainCoordinate = M_COORD;
        fieldGui.withComputer=true;
        fieldGui.Computer();
        fieldGui.hardMode=hardMode;
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveDialog saveDialog = new SaveDialog(frame);
                saveDialog.setVisible(true);
                if (saveDialog.isSaveToFile()) {
                    saveToFile(infoGame);
                    frame.dispose();
                    Menu.MainMenu();
                } else if (saveDialog.isRestartGame()) {
                    fieldGui.Restart();
                } else {
                    frame.dispose();
                    Menu.MainMenu();
                }
            }
        });

        frame.setSize(1100, 903);
        fieldGui.loadingForComputer=true;
        fieldGui.NoComputer();
        for (int i = 0; i < records.size(); i++) {
            for (int BR = 0; BR < N_SUBFIELD; BR++) {
                for (int BC = 0; BC < N_SUBFIELD; BC++) {
                    for (int R = 0; R < 3; R++) {
                        for (int C = 0; C < 3; C++) {
                            if(records.get(i).bigRow==BR && records.get(i).bigColumn==BC && records.get(i).row==R && records.get(i).column==C){
                                subfieldGuis[BR][BC].buttons[R][C].doClick();
                            }
                        }
                    }
                }
            }
        }
        fieldGui.Computer();
        fieldGui.loadingForComputer=false;
        frame.setVisible(true);
    }

    public static void Game(InfoGame infoGame, String gamer1, String gamer2){
        MainCoordinate M_COORD = new MainCoordinate();

        JFrame frame = new JFrame("Tic-Tac-Toe");
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel, BorderLayout.CENTER);

        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setBorder(BorderFactory.createTitledBorder("Moves"));

        JScrollPane scrollPane = new JScrollPane(movesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        frame.add(scrollPane, BorderLayout.EAST);

        addMoveRecord(movesPanel, gamer1+" - O");
        addMoveRecord(movesPanel, gamer2+" - X");
        addMoveRecord(movesPanel, " ");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Point turn = new Point();

        final int N_SUBFIELD = 3;
        SubFieldGui subfieldGuis[][] = new SubFieldGui[N_SUBFIELD][N_SUBFIELD];
        for (int i = 0; i < N_SUBFIELD; i++) {
            for (int j = 0; j < N_SUBFIELD; j++) {
                subfieldGuis[i][j] = new SubFieldGui(frame, panel, turn, movesPanel);
                subfieldGuis[i][j].mainCoordinate = M_COORD;
            }
        }
        FieldGui fieldGui = new FieldGui(subfieldGuis, frame, infoGame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveDialog saveDialog = new SaveDialog(frame);
                saveDialog.setVisible(true);
                if (saveDialog.isSaveToFile()) {
                    saveToFile(infoGame);
                    saveNamesOfFiles(infoGame.fileName + " " + infoGame.gamer1 + " " + infoGame.gamer2);
                    frame.dispose();
                    Menu.MainMenu();
                } else if (saveDialog.isRestartGame()) {
                    fieldGui.Restart();
                } else {
                    frame.dispose();
                    Menu.MainMenu();
                }
            }
        });
        fieldGui.mainCoordinate = M_COORD;
        frame.setSize(1100, 903);
        frame.setVisible(true);
    }

    public static void GameWithComputer(InfoGame infoGame, boolean hardMode, String gamer1){
        MainCoordinate M_COORD = new MainCoordinate();

        JFrame frame = new JFrame("Tic-Tac-Toe");
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel movesPanel = new JPanel();
        movesPanel.setLayout(new BoxLayout(movesPanel, BoxLayout.Y_AXIS));
        movesPanel.setBorder(BorderFactory.createTitledBorder("Moves"));

        JScrollPane scrollPane = new JScrollPane(movesPanel);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        frame.add(scrollPane, BorderLayout.EAST);


        addMoveRecord(movesPanel, gamer1+" - O");
        addMoveRecord(movesPanel, "Computer - X");
        addMoveRecord(movesPanel, " ");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.add(panel);

        Point turn = new Point();

        final int N_SUBFIELD = 3;
        SubFieldGui subfieldGuis[][] = new SubFieldGui[N_SUBFIELD][N_SUBFIELD];
        for (int i = 0; i < N_SUBFIELD; i++) {
            for (int j = 0; j < N_SUBFIELD; j++) {
                subfieldGuis[i][j] = new SubFieldGui(frame, panel, turn, movesPanel);
                subfieldGuis[i][j].mainCoordinate = M_COORD;
            }
        }
        FieldGui fieldGui = new FieldGui(subfieldGuis, frame, infoGame);
        fieldGui.mainCoordinate = M_COORD;
        fieldGui.withComputer=true;
        fieldGui.Computer();
        fieldGui.hardMode=hardMode;
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveDialog saveDialog = new SaveDialog(frame);
                saveDialog.setVisible(true);
                if (saveDialog.isSaveToFile()) {
                    saveToFile(infoGame);
                    if(hardMode==true){
                        saveNamesOfFilesComputer(infoGame.fileName + " " + infoGame.gamer1 + " H");
                    }
                    else{
                        saveNamesOfFilesComputer(infoGame.fileName + " " + infoGame.gamer1 + " E");
                    }
                    frame.dispose();
                    Menu.MainMenu();
                } else if (saveDialog.isRestartGame()) {
                    fieldGui.Restart();
                } else {
                    frame.dispose();
                    Menu.MainMenu();
                }
            }
        });

        frame.setSize(1100, 903);
        frame.setVisible(true);
    }



    private static void addMoveRecord(JPanel movesPanel, String moveText) {
        JLabel moveLabel = new JLabel(moveText);
        movesPanel.add(moveLabel);
        movesPanel.revalidate();
        movesPanel.repaint();
    }

    private static void saveToFile(InfoGame infoGame) {
        String fileName = infoGame.fileName;
        ArrayList<Record> records = infoGame.rec;

        try (FileWriter writer = new FileWriter(fileName,true)) {
            for (Record record : records) {
                String line = record.GetLine();
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveNamesOfFiles(String fileName) {
        try(FileWriter writer = new FileWriter("Files1By1.txt", true)){
            writer.write(fileName+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveNamesOfFilesComputer(String fileName) {
        try(FileWriter writer = new FileWriter("FilesWithComputer.txt", true)){
            writer.write(fileName+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
