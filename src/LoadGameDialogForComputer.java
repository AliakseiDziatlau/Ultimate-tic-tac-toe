import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoadGameDialogForComputer extends JDialog {
    public String file="";
    public String gamer1="";
    public String mode="";
    public LoadGameDialogForComputer(JFrame parentFrame) {
        super(parentFrame, "Select a Saved Game", true);
        setSize(300, 200);
        setLayout(new BorderLayout());

        ArrayList<String> savedGameFiles = loadSavedGameFiles("FilesWithComputer.txt");
        ArrayList<String> savedGameFilesG1 = loadSavedGameFilesGamer1("FilesWithComputer.txt");
        ArrayList<String> savedGameFilesMode = loadSavedGameFilesMode("FilesWithComputer.txt");

        JList<String> gameList = new JList<>(savedGameFiles.toArray(new String[0]));
        gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(gameList);
        add(scrollPane, BorderLayout.CENTER);

        MyCustomButton selectButton = new MyCustomButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGame = gameList.getSelectedValue();
                if (selectedGame!= null) {
                    file = selectedGame;
                    for(int i=0;i<savedGameFiles.size();i++) {
                        if(savedGameFiles.get(i).equals(file)) {
                            gamer1 = savedGameFilesG1.get(i);
                            mode = savedGameFilesMode.get(i);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(LoadGameDialogForComputer.this, "No game selected!");
                }
                dispose();
            }
        });
        add(selectButton, BorderLayout.SOUTH);

        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private ArrayList<String> loadSavedGameFiles(String filePath) {
        ArrayList<String> fileNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values =line.split(" ");
                fileNames.add(values[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load saved game files", e);
        }
        return fileNames;
    }

    private ArrayList<String> loadSavedGameFilesGamer1(String filePath) {
        ArrayList<String> gamer1Names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values =line.split(" ");
                gamer1Names.add(values[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load saved game files", e);
        }
        return gamer1Names;
    }

    private ArrayList<String> loadSavedGameFilesMode(String filePath) {
        ArrayList<String> gamer2Names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values =line.split(" ");
                gamer2Names.add(values[2]);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load saved game files", e);
        }
        return gamer2Names;
    }
}
