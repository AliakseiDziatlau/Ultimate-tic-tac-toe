import javax.swing.*;
import java.awt.*;

public class SaveDialog extends JDialog {
    private boolean saveToFile = false;
    private boolean restartGame = false;

    public SaveDialog(JFrame parent) {
        super(parent, "Save to file?", true);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Save to file?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        MyCustomButton yesButton = new MyCustomButton("Yes");
        MyCustomButton noButton = new MyCustomButton("No");
        MyCustomButton restartButton = new MyCustomButton("Restart");

        yesButton.addActionListener(e -> {
            saveToFile = true;
            dispose();
        });

        noButton.addActionListener(e -> {
            saveToFile = false;
            dispose();
        });

        restartButton.addActionListener(e -> {
            restartGame = true;
            dispose();
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.add(restartButton);
        panel.add(buttonPanel);

        getContentPane().add(panel);
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public boolean isSaveToFile() {
        return saveToFile;
    }
    public boolean isRestartGame() {
        return restartGame;
    }
}
