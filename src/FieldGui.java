import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class FieldGui implements ICheckWinner{
    private SubFieldGui[][] subFields;
    private JFrame frame;
    private InfoGame infoGame;
    public MainCoordinate mainCoordinate=null;
    public boolean loadingForComputer=false;
    public boolean withComputer=false;
    public boolean hardMode;


    public FieldGui(SubFieldGui[][] subFields, JFrame frame, InfoGame infoGame){
        this.subFields=subFields;
        this.frame=frame;
        this.infoGame=infoGame;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                subFields[i][j].setCheckWinner(this);
            }
        }
    }

    public void Restart(){
        for(int BR=0; BR<3; BR++){
            for(int BC=0; BC<3; BC++){
                subFields[BR][BC].isAvaliable=true;
                for(int R=0; R<3; R++){
                    for(int C=0; C<3; C++){
                        subFields[BR][BC].buttons[R][C].setText("");
                        subFields[BR][BC].buttons[R][C].setEnabled(true);
                        subFields[BR][BC].buttons[R][C].setColor(new Color(247, 218, 5));
                    }
                }
            }
        }
    }

    public void Computer(){
        if(withComputer){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    subFields[i][j].withComputer=true;
                }
            }
        }
    }

    public void NoComputer(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                subFields[i][j].withComputer=false;
            }
        }
    }

    private void moveAndUpdateGameState(int r, int c){
        subFields[mainCoordinate.row][mainCoordinate.column].buttons[r][c].setText("X");
        subFields[mainCoordinate.row][mainCoordinate.column].buttons[r][c].setColor(new Color(206, 164, 1));
        subFields[mainCoordinate.row][mainCoordinate.column].buttons[r][c].setForeground(Color.RED);
        subFields[mainCoordinate.row][mainCoordinate.column].buttons[r][c].setEnabled(false);
        subFields[mainCoordinate.row][mainCoordinate.column].checkForWinner();
        int bigRow = mainCoordinate.row;
        int bigCol = mainCoordinate.column;
        mainCoordinate.row=r;
        mainCoordinate.column=c;
        if(subFields[bigRow][bigCol].iCheckWinner!=null){
            subFields[bigRow][bigCol].iCheckWinner.checkForWinner();
            for(int k=0; k<3; k++){
                for(int j=0; j<3; j++){
                    if(k!=mainCoordinate.row || j!=mainCoordinate.column){
                        subFields[k][j].isAvaliable=false;
                    }
                    else subFields[k][j].isAvaliable=true;
                }
            }
            subFields[bigRow][bigCol].iCheckWinner.addRecord(bigRow, bigCol, r, c, 'X');
            subFields[bigRow][bigCol].addMoveRecord(subFields[bigRow][bigCol].movesPanel, subFields[bigRow][bigCol].GetLine(bigRow, bigCol, r, c, 'X'));
        }
    }

    private boolean checkLastMove(String symbol){
        //vertical
        for(int i=0; i<3; i++){
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][2].isEnabled()){
                moveAndUpdateGameState(i,2);
                return true;
            }
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][1].isEnabled()){
                moveAndUpdateGameState(i, 1);
                return true;
            }
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][0].isEnabled()){
                moveAndUpdateGameState(i,0);
                return true;
            }
        }
        //horizontal
        for(int i=0; i<3; i++){
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][i].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][i].getText().equals("X") && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][i].isEnabled()){
                moveAndUpdateGameState(2, i);
                return true;
            }
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][i].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][i].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][i].isEnabled()){
                moveAndUpdateGameState(1, i);
                return true;
            }
            if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][i].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][i].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][i].isEnabled()){
                moveAndUpdateGameState(0, i);
                return true;
            }
        }

        //left diagonal
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][2].isEnabled()){
            moveAndUpdateGameState(2,2);
            return true;
        }
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].isEnabled()){
            moveAndUpdateGameState(1,1);
            return true;
        }
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][0].isEnabled()){
            moveAndUpdateGameState(0,0);
            return true;
        }
        //right diagonal
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][2].isEnabled()){
            moveAndUpdateGameState(0,2);
            return true;
        }
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][0].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].isEnabled()){
            moveAndUpdateGameState(1,1);
            return true;
        }
        if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[0][2].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].getText().equals(symbol) && subFields[mainCoordinate.row][mainCoordinate.column].buttons[2][0].isEnabled()){
            moveAndUpdateGameState(2,0);
            return true;
        }
        return false;
    }

    private void playWithComputerHard(){
        if(checkLastMove("X")==true){
            return;
        }

        else if(checkLastMove("O")==true){
            return;
        }

        else if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[1][1].isEnabled()){
            moveAndUpdateGameState(1,1);
        }

        else{
            playWithComputer();
        }
    }

    private void playWithComputer() {
        Random rand = new Random();
        int randRow = rand.nextInt(3);
        int randCol = rand.nextInt(3);
        boolean isFree=false;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(subFields[mainCoordinate.row][mainCoordinate.column].buttons[i][j].isEnabled()){
                    isFree=true;
                }
            }
        }
        if(isFree==true){
            while(!subFields[mainCoordinate.row][mainCoordinate.column].buttons[randRow][randCol].isEnabled()){
                randRow = rand.nextInt(3);
                randCol = rand.nextInt(3);
            }
        }
        else if(isFree==false){
            int BR=rand.nextInt(3);
            int BC=rand.nextInt(3);
            while(!subFields[BR][BC].buttons[randRow][randCol].isEnabled()){
                BR=rand.nextInt(3);
                BC=rand.nextInt(3);
                randRow = rand.nextInt(3);
                randCol = rand.nextInt(3);
            }
            mainCoordinate.row=BR;
            mainCoordinate.column=BC;
        }
        moveAndUpdateGameState(randRow,randCol);
    }

    public void setAvaliable(){
        if(!withComputer){
            updateFieldAvailability();
        }
        else {
            updateFieldAvailability();
            if(hardMode==true){
                if(loadingForComputer==false){
                    playWithComputerHard();
                }
            }
            else if(hardMode==false){
                if(loadingForComputer==false){
                    playWithComputer();
                }
            }
            updateFieldAvailability();
        }
    }

    private void updateFieldAvailability(){
        if(mainCoordinate==null) return;
        int rBuf=-1;
        int cBuf=-1;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(i!=mainCoordinate.row || j!=mainCoordinate.column){
                    subFields[i][j].isAvaliable=false;
                    for(int r=0; r<3; r++){
                        for(int c=0; c<3; c++){
                            subFields[i][j].buttons[r][c].setColor(new Color(206, 164, 1));
                        }
                    }
                }
                else{
                    subFields[i][j].isAvaliable=true;
                    for(int r=0; r<3; r++){
                        for(int c=0; c<3; c++){
                            if(subFields[i][j].buttons[r][c].getText().equals("")){
                                subFields[i][j].buttons[r][c].setColor(new Color(247, 218, 5));
                            }
                        }
                    }
                    rBuf=i;
                    cBuf=j;
                }
            }
        }
        boolean flag=false;
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                if(subFields[rBuf][cBuf].buttons[i][j].isEnabled()){
                    flag=true;
                }
            }
        }
        if(flag==false){
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    if(i!=rBuf || j!=cBuf){
                        subFields[i][j].isAvaliable=true;
                        for(int r=0; r<3; r++){
                            for(int c=0; c<3; c++){
                                subFields[i][j].buttons[r][c].setColor(new Color(247, 218, 5));
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void addRecord(int bR, int bC, int R, int C, char text) {
        Record record = new Record();
        record.bigRow =bR;
        record.bigColumn =bC;
        record.row =R;
        record.column=C;
        record.symbol =text;
        infoGame.rec.add(record);
    }

    public void checkForWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (subFields[i][0].getWinner()==subFields[i][1].getWinner() && subFields[i][0].getWinner()==subFields[i][2].getWinner() && (subFields[i][0].getWinner()==1 || subFields[i][0].getWinner()==2)) {
                if(subFields[i][0].getWinner()==1) {
                    JOptionPane.showMessageDialog(frame, "Winner is X");
                }
                else if(subFields[i][0].getWinner()==2) {
                    JOptionPane.showMessageDialog(frame, "Winner is O");
                }

                for (int k=0; k<3; k++){
                    for (int j=0; j<3; j++){
                        subFields[k][j].resetGame();
                    }
                }
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (subFields[0][i].getWinner()==subFields[1][i].getWinner() && subFields[0][i].getWinner()==subFields[2][i].getWinner() &&(subFields[0][i].getWinner()==1 || subFields[0][i].getWinner()==2) ) {
                if(subFields[0][i].getWinner()==1) {
                    JOptionPane.showMessageDialog(frame, "Winner is X");
                }
                else if(subFields[0][i].getWinner()==2) {
                    JOptionPane.showMessageDialog(frame, "Winner is O");
                }

                for (int j=0; j<3; j++){
                    for (int j2=0; j2<3; j2++){
                        subFields[j][j2].resetGame();
                    }
                }
            }
        }


        //Check first diagonal
        if (subFields[0][0].getWinner()==subFields[1][1].getWinner() && subFields[0][0].getWinner()==subFields[2][2].getWinner() && (subFields[0][0].getWinner()==1 || subFields[0][0].getWinner()==2)) {
            if(subFields[0][0].getWinner()==1) {
                JOptionPane.showMessageDialog(frame, "Winner is X");
            }
            else if(subFields[0][0].getWinner()==2) {
                JOptionPane.showMessageDialog(frame, "Winner is O");
            }

            for (int j=0; j<3; j++){
                for (int j2=0; j2<3; j2++){
                    subFields[j][j2].resetGame();
                }
            }
        }

        //Check second diagonal
        if (subFields[0][2].getWinner()==subFields[1][1].getWinner() && subFields[0][2].getWinner()==subFields[2][0].getWinner() && (subFields[0][2].getWinner()==1 || subFields[0][2].getWinner()==2)) {
            if(subFields[0][2].getWinner()==1) {
                JOptionPane.showMessageDialog(frame, "Winner is X");
            }
            else if(subFields[0][2].getWinner()==2) {
                JOptionPane.showMessageDialog(frame, "Winner is O");
            }

            for (int j=0; j<3; j++){
                for (int j2=0; j2<3; j2++){
                    subFields[j][j2].resetGame();
                }
            }
        }

        // Check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            if (subFields[i][0].getWinner()==subFields[i][1].getWinner() && subFields[i][0].getWinner()==subFields[i][2].getWinner() && (subFields[i][0].getWinner()==1 || subFields[i][0].getWinner()==2)) {
              tie=false;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (subFields[0][i].getWinner()==subFields[1][i].getWinner() && subFields[0][i].getWinner()==subFields[2][i].getWinner() &&(subFields[0][i].getWinner()==1 || subFields[0][i].getWinner()==2) ) {
                tie=false;
            }
        }
        if (subFields[0][0].getWinner()==subFields[1][1].getWinner() && subFields[0][0].getWinner()==subFields[2][2].getWinner() && (subFields[0][0].getWinner()==1 || subFields[0][0].getWinner()==2)) {
            tie=false;
        }
        if (subFields[0][2].getWinner()==subFields[1][1].getWinner() && subFields[0][2].getWinner()==subFields[2][0].getWinner() && (subFields[0][2].getWinner()==1 || subFields[0][2].getWinner()==2)) {
            tie=false;
        }
        boolean isDone=true;
        for(int BR=0;BR<3;BR++){
            for(int BC=0;BC<3;BC++){
                for (int R=0;R<3;R++){
                    for (int C=0;C<3;C++){
                        if(subFields[BR][BC].buttons[R][C].isEnabled()){
                            isDone=false;
                        }
                    }
                }
            }
        }
        if (tie==true && isDone==true){
            JOptionPane.showMessageDialog(frame, "Tie game!");
            for(int i = 0; i<3; i++){
                for(int j = 0; j<3; j++){
                    subFields[i][j].resetGame();
                }
            }
        }
    }
}