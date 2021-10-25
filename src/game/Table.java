package game;

import com.bethecoder.ascii_table.ASCIITable;

public class Table {

    String[] header;
    String[][] data;

    public Table(String[] options){
        header = new String[options.length + 1];
        data = new String[options.length][options.length+1];
        System.arraycopy(options, 0, header, 1, options.length);
        header[0]="Left win top";
        for (int i=0; i<data.length; i++){
            for (int j=0; j<data[i].length; j++) data[i][j] = "X";
            data[i][0] = header[i+1];
        }
    }
    public void TableCalculate() {
        for (int i=0; i<data.length; i++){
            for (int j=1; j<data[i].length; j++) {
                if (i!=j-1){
                    if (Rules.CheckWin(i,j-1)) data[i][j]="Yes";
                    else data[i][j]="No";
                }
            }
        }
    }

    public void showTable() {
        ASCIITable.getInstance().printTable(header, data);
    }
}
