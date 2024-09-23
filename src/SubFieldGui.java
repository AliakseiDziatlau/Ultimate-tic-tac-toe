import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubFieldGui implements ActionListener {
    public ICheckWinner iCheckWinner=null;
    private JFrame frame;
    private JPanel panelParent;
    private JPanel panel;
    public MyCustomButton[][] buttons = new MyCustomButton[3][3];
    public Point locTurn;
    private int winner;
    public JPanel movesPanel;
    public MainCoordinate mainCoordinate=null;
    public boolean isAvaliable=true;
    public boolean withComputer=false;

    public SubFieldGui(JFrame frame, JPanel panelParent,  Point locTurn, JPanel movesPanel) {
        this.winner=0;
        this.movesPanel=movesPanel;
        this.panelParent = panelParent;
        this.frame = frame;
        this.locTurn = locTurn;
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new MyCustomButton("");
                buttons[i][j].row=i;
                buttons[i][j].column=j;
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        panelParent.add(panel, BorderLayout.CENTER);
    }

    public void setCheckWinner(ICheckWinner iCheckWinner) {
        this.iCheckWinner=iCheckWinner;
    }

    public int getWinner() {
        return winner;
    }

    public void addMoveRecord(JPanel movesPanel, String moveText) {
        JLabel moveLabel = new JLabel(moveText);
        movesPanel.add(moveLabel);
        movesPanel.revalidate();
        movesPanel.repaint();
    }

    public String GetLine(int bigRow, int bigColumn, int row, int column, char symbol){
        return bigRow +","+ bigColumn +","+row+","+column+","+symbol;
    }

    public void actionPerformed(ActionEvent e) {
        if(withComputer==false){
            MyCustomButton button = (MyCustomButton) e.getSource();
            if(isAvaliable==false){
                return;
            }

            if (locTurn.GetTurn()) {
                button.setText("X");
                button.setColor(new Color(206, 164, 1));
                button.setForeground(Color.RED);
            } else {
                button.setText("O");
                button.setColor(new Color(206, 164, 1));
                button.setForeground(Color.BLUE);
            }
            moveAndCheckState(button);
        }
        else{
            MyCustomButton button = (MyCustomButton) e.getSource();
            if(isAvaliable==false){
                return;
            }
            button.setText("O");
            button.setColor(new Color(206, 164, 1));
            button.setForeground(Color.BLUE);
            moveAndCheckState(button);
        }
    }

    private void moveAndCheckState(MyCustomButton button){
        button.setEnabled(false);
        locTurn.SetTurn( !locTurn.GetTurn());
        checkForWinner();
        int bigColumn=mainCoordinate.column;
        int bigRow=mainCoordinate.row;
        mainCoordinate.column=button.column;
        mainCoordinate.row=button.row;
        if(iCheckWinner!=null){
            iCheckWinner.checkForWinner();
            iCheckWinner.addRecord(bigRow, bigColumn, button.row, button.column, button.getText().charAt(0));
            addMoveRecord(movesPanel, GetLine(bigRow, bigColumn, button.row, button.column, button.getText().charAt(0)));
            iCheckWinner.setAvaliable();
        }
    }



    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) && buttons[i][0].getText().equals(buttons[i][2].getText()) && !buttons[i][0].isEnabled()){
                if(buttons[i][0].getText().equals("X")) {
                    winner = 1;
                    for(int r=0;r<3;r++){
                        for(int c=0;c<3;c++){
                            buttons[r][c].setText("X");
                            buttons[r][c].setColor(new Color(206, 164, 1));
                            buttons[r][c].setForeground(Color.RED);
                            buttons[r][c].setEnabled(false);
                        }
                    }
                }
                else if(buttons[i][0].getText().equals("O")) {
                    winner = 2;
                    for(int r=0;r<3;r++){
                        for(int c=0;c<3;c++){
                            buttons[r][c].setText("O");
                            buttons[r][c].setColor(new Color(206, 164, 1));
                            buttons[r][c].setForeground(Color.BLUE);
                            buttons[r][c].setEnabled(false);
                        }
                    }
                }
                return;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) && buttons[0][i].getText().equals(buttons[2][i].getText()) && !buttons[0][i].isEnabled()) {
                if(buttons[0][i].getText().equals("X")) {
                    winner = 1;
                    for(int r=0;r<3;r++){
                        for(int c=0;c<3;c++){
                            buttons[r][c].setText("X");
                            buttons[r][c].setColor(new Color(206, 164, 1));
                            buttons[r][c].setForeground(Color.RED);
                            buttons[r][c].setEnabled(false);
                        }
                    }
                }
                else if(buttons[0][i].getText().equals("O")) {
                    winner = 2;
                    for(int r=0;r<3;r++){
                        for(int c=0;c<3;c++){
                            buttons[r][c].setText("O");
                            buttons[r][c].setColor(new Color(206, 164, 1));
                            buttons[r][c].setForeground(Color.BLUE);
                            buttons[r][c].setEnabled(false);
                        }
                    }
                }
                return;
            }
        }

        // Check first diagonal
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) && buttons[0][0].getText().equals(buttons[2][2].getText()) && !buttons[0][0].isEnabled()) {
            if(buttons[0][0].getText().equals("X")) {
                winner = 1;
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        buttons[r][c].setText("X");
                        buttons[r][c].setColor(new Color(206, 164, 1));
                        buttons[r][c].setForeground(Color.RED);
                        buttons[r][c].setEnabled(false);
                    }
                }
            }
            else if(buttons[0][0].getText().equals("O")) {
                winner = 2;
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        buttons[r][c].setText("O");
                        buttons[r][c].setColor(new Color(206, 164, 1));
                        buttons[r][c].setForeground(Color.BLUE);
                        buttons[r][c].setEnabled(false);
                    }
                }
            }
            return;
        }


        //Check second diagonal
        if (buttons[0][2].getText().equals(buttons[1][1].getText()) && buttons[0][2].getText().equals(buttons[2][0].getText()) && !buttons[0][2].isEnabled()) {
            if(buttons[0][2].getText().equals("X")) {
                this.winner = 1;
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        buttons[r][c].setText("X");
                        buttons[r][c].setColor(new Color(206, 164, 1));
                        buttons[r][c].setForeground(Color.RED);
                        buttons[r][c].setEnabled(false);
                    }
                }
            }
            else if(buttons[0][2].getText().equals("O")) {
                this.winner = 2;
                for(int r=0;r<3;r++){
                    for(int c=0;c<3;c++){
                        buttons[r][c].setText("O");
                        buttons[r][c].setColor(new Color(206, 164, 1));
                        buttons[r][c].setForeground(Color.BLUE);
                        buttons[r][c].setEnabled(false);
                    }
                }
            }
            return;
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].isEnabled()) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            this.winner = 0;
        }
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setColor(new Color(247, 218, 5));
            }
        }
        locTurn.SetTurn(true);
    }
}
