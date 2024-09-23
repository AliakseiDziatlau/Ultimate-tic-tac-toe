public class Point {
    private int step;

    Point(){
        step = 0;
    }
    private boolean xTurn;

    public void SetTurn(boolean turn){
        xTurn = turn;
        step++;
    }

    public boolean GetTurn(){
        return xTurn;
    }
}
