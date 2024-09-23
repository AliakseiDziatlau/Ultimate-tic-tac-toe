import javax.swing.*;
import java.awt.*;

public class ModeDialog extends JDialog {
    private boolean hardMode;

    public ModeDialog(JFrame parent) {
        super(parent, "Choose a mode", true);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Choose a mode");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        MyCustomButton easyButton = new MyCustomButton("Easy");
        MyCustomButton hardButton = new MyCustomButton("Hard");

        easyButton.addActionListener(e -> {
            hardMode = false;
            dispose();
        });

        hardButton.addActionListener(e -> {
            hardMode = true;
            dispose();
        });

        buttonPanel.add(easyButton);
        buttonPanel.add(hardButton);
        panel.add(buttonPanel);

        getContentPane().add(panel);
        setSize(300, 150);
        setLocationRelativeTo(parent);
    }

    public boolean isHardMode() {
        return hardMode;
    }
}
