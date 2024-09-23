public class Record {
    public int bigColumn, bigRow;
    public int column, row;
    public char symbol;

    public String GetLine(){
        return bigRow +","+ bigColumn +","+row+","+column+","+symbol;
    }

    public void SetLine(String str){
        String[] values = str.split(",");
        bigRow = Integer.parseInt(values[0].trim());
        bigColumn = Integer.parseInt(values[1].trim());
        row = Integer.parseInt(values[2].trim());
        column = Integer.parseInt(values[3].trim());
        symbol = values[4].trim().charAt(0);
    }
}
