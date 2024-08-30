package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Table {
    private int row;
    private int column;

    private int sizeTable;
    private int numIcon;

    private List<Integer> arr;

    public  Table(int row, int column, int numIcon){
        this.row = row;
        this.column = column;
        this.sizeTable = row*column;
        this.numIcon = numIcon;
        this.arr = new ArrayList<>();
        this.arr = createNewTable();
    }

    public List<Integer> createNewTable(){
        arr.clear();

        int avgIcon = sizeTable/numIcon;
        if((avgIcon % 2) == 1) avgIcon++;
        System.out.println(avgIcon);

        Random ran = new Random();
        for(int i = 1; i <= numIcon; i++){
            int num = ran.nextInt(2,avgIcon+3);
            if(num % 2 != 0) num++;
            for(int j = 1; j <= num; j++){
                arr.add(i);
            }
        }

        if(arr.size() < sizeTable){
            for(int i = arr.size(); i < sizeTable; i+=2){
                int a = ran.nextInt(1,numIcon+1);
                arr.add(a);
                arr.add(a);
            }
        }else {
            for(int i = arr.size()-1; i >= sizeTable; i--){
                arr.remove(i);
            }
        }

        arr = rollTable(1000);
        return arr;
    }


    public List<Integer> rollTable(int time){
        Random ran = new Random();
        for(int i = 0; i < time; i++){
            int sum = arr.size();
            int x = ran.nextInt(0,sum);
            int y = ran.nextInt(0,sum);
            Collections.swap(arr,x,y);
        }
        return arr;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getNumIcon() {
        return numIcon;
    }

    public void setNumIcon(int numIcon) {
        this.numIcon = numIcon;
    }

    public List<Integer> getArr() {
        return arr;
    }
}
