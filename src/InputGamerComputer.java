import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputGamerComputer extends JDialog {
    JTextField fieldGamer1, gameName;
    public boolean isInput = false;

    public InputGamerComputer(JFrame parent) {
        super(parent, "Input Information", ModalityType.DOCUMENT_MODAL);

        JLabel nameLabel = new JLabel("Game Name: ");
        JLabel gamer1Label = new JLabel("Gamer 1: ");
        JLabel gamer2Label = new JLabel("Gamer 2: ");

        gameName = new JTextField(30);
        gameName.setToolTipText("Game Name");
        fieldGamer1 = new JTextField(30);
        fieldGamer1.setToolTipText("Gamer 1");

        MyCustomButton button = new MyCustomButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldGamer1.getText().isEmpty() || gameName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(InputGamerComputer.this, "All fields must be filled");
                    return;
                }
                isInput = true;
                InputGamerComputer.this.dispose();
            }
        });

        JPanel contents = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        contents.add(nameLabel, gbc);

        gbc.gridx = 1;
        contents.add(gameName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contents.add(gamer1Label, gbc);

        gbc.gridx = 1;
        contents.add(fieldGamer1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contents.add(gamer2Label, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contents.add(button, gbc);

        setContentPane(contents);

        setSize(500, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
