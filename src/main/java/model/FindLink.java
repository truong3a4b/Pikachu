package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindLink {

//    private Map<Integer,List<Integer>> map;
    private int[][] adjustedTable;

    private int newRow;
    private int newColumn;

    public FindLink(List<Integer> arr, int row, int column){
//        map = new HashMap<>();
        newRow = row + 2;
        newColumn = column + 2;
        adjustedTable = new int[newRow][newColumn];
        this.setAdjustedTable(arr);
//        this.setMap(adjustedTable);
    }

//    public void setMap(int[][] arr){
//        int num = newColumn*newRow;
//        for(int i = 0; i < num; i++){
//            if(this.map.containsKey(arr.get(i))){
//                this.map.get(arr.get(i)).add(i);
//            }else{
//                List<Integer> positon = new ArrayList<>();
//                positon.add(i);
//                this.map.put(arr.get(i), positon);
//            }
//        }
//    }

   /* public Map<Integer,List<Integer>> getMap(){
        return map;
    }

    public void printMap() {
        for(int i = 1; i <= Test.NUM_ICON; i++){
            if(!map.containsKey(i)) continue;
            List<Integer> position = map.get(i);
            System.out.println("Icon "+ i + ": ");
            for(int p : position){
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }*/

    public void setAdjustedTable(List<Integer> arr){
        int k = 0;
        for(int i = 0; i < newRow; i++){
            for(int j = 0; j < newColumn; j++){
                if(i == 0 || j == 0 || i == newRow-1 || j == newColumn-1 ){
                    adjustedTable[i][j] = 0;
                }else{
                    adjustedTable[i][j] = arr.get(k);
                    k++;
                }
            }
        }
    }

    public int[][] getAdjustedTable(){
        return adjustedTable;
    }


    public void updateTable(int row,int column){
        adjustedTable[row][column] = 0;
    }


    public void printTable(){
        for (int i = 0; i < newRow; i++) {
            for (int j = 0; j < newColumn; j++) {
                System.out.print(adjustedTable[i][j] + " ");

            }
            System.out.println();
        }
    }
    public List<Point> findWay(Point point1, Point point2){
        List<Point> way = new ArrayList<>();

        if(adjustedTable[point1.getY()][point1.getX()] != adjustedTable[point2.getY()][point2.getX()]) return way;

        if(point1.compare(point2)) return way;

        Point p1 = Point.min(point1,point2);
        Point p2 = Point.max(point1,point2);

        if(p1.getY() == p2.getY()){
            System.out.println("Cung hang ngang");
            if(p1.getX()+1 == p2.getX()){
                System.out.println("OK");
                way.add(p1);
                way.add(p2);
                return way;
            }
            for(int i = p1.getX() + 1; i < p2.getX(); i++){
                if(adjustedTable[p1.getY()][i] == 0){
                    way.add(new Point(i, p1.getY()));
                }else{
                    way.clear();
                    break;
                }
            }
            if(way.size() > 0) return way;


            for(int i = p1.getY()-1; i >=0; i--){
                if(adjustedTable[i][p1.getX()] == 0 && adjustedTable[i][p2.getX()] == 0){
                    way.add(new Point(p1.getX(), i));
                    if(checkLineX(new Point(p1.getX(), i),new Point(p2.getX(),i))){
                        for(int j = p1.getX()+1; j < p2.getX(); j++){
                            System.out.println("hi");
                            way.add(new Point(j,i));
                        }
                        for(int j = i; j < p2.getY(); j++){
                            way.add(new Point(p2.getX(), j));
                        }
                        break;
                    }
                }else{
                    way.clear();
                    break;
                }
            }
            if(way.size() > 0) return way;

            for(int i = p1.getY()+1; i >= newRow; i++){
                if(adjustedTable[i][p1.getX()] == 0 && adjustedTable[i][p2.getX()] == 0){
                    way.add(new Point(p1.getX(), i));
                    if(checkLineX(new Point(p1.getX(), i),new Point(p2.getX(),i))){
                        for(int j = p1.getX()+1; j < p2.getX(); j++){
                            way.add(new Point(j,i));
                        }
                        for(int j = i; j > p2.getY(); j--){
                            way.add(new Point(p2.getX(), j));
                        }
                        break;
                    }
                }else{
                    way.clear();
                    break;
                }
            }
            if(way.size() > 0) return way;
        }


        if(p1.getX() == p2.getX()){
            System.out.println("Cung cot doc");
            if(p1.getY()+1 == p2.getY()){
                System.out.println("OK");
                way.add(p1);
                way.add(p2);
                return way;
            }

            for(int i = p1.getY()+1; i <p2.getY(); i++){
                if(adjustedTable[i][p1.getX()] == 0){
                    way.add(new Point(p1.getX(), i));
                }else{
                    way.clear();
                    break;
                }
            }
            if(way.size() > 0) return way;

            for(int i = p1.getX()+1; i<=newColumn; i++){
                if(adjustedTable[p1.getY()][i] == 0 && adjustedTable[p2.getY()][i] == 0){
                    way.add(new Point(i, p1.getY()));
                    if(checkLineY(new Point(i, p1.getY()), new Point(i,p2.getY()))){
                        for(int j = p1.getY()+1; j < p2.getY(); j++){
                            way.add(new Point(i,j));
                        }
                        for(int j = i; j > p2.getX(); j--){
                            way.add(new Point(j, p2.getY()));
                        }
                        break;
                    }
                }else{
                    way.clear();
                    break;
                }
            }
            if(way.size() > 0) return way;

            for(int i = p1.getX()-1; i>=0; i--){
                if(adjustedTable[p1.getY()][i] == 0 && adjustedTable[p2.getY()][i] == 0){
                    way.add(new Point(i, p1.getY()));
                    if(checkLineY(new Point(i, p1.getY()), new Point(i,p2.getY()))){
                        for(int j = p1.getY()+1; j < p2.getY(); j++){
                            way.add(new Point(i,j));
                        }
                        for(int j = i; j < p2.getX(); j++){
                            way.add(new Point(j, p2.getY()));
                        }
                        break;
                    }
                }else{
                    way.clear();
                    break;
                }
            }
        }

        if(p1.getX() > p2.getX()) {
            System.out.println("x1 > x2");
            for (int i = p1.getX() - 1; i >= 0; i--) {
                if (adjustedTable[p1.getY()][i] == 0) {
                    way.add(new Point(i, p1.getY()));
                    if (i > p2.getX()) {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(p2.getX() + 1, point2.getY()), new Point(i, p2.getY()))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            for (int j = i; j > p2.getX(); j--) {
                                way.add(new Point(i, p2.getY()));
                            }
                            break;
                        }
                    } else if (i == p2.getX()) {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY() - 1))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            break;
                        }
                    } else {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(i, p2.getY()), new Point(p2.getX() - 1, p2.getY()))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            for (int j = i; j < p2.getX(); j++) {
                                way.add(new Point(i, p2.getY()));
                            }
                            break;
                        }
                    }
                } else {
                    way.clear();
                    break;
                }

            }

            if (way.size() > 0) return way;

            for (int i = p1.getY()+1; i >= newRow; i++) {
                if (adjustedTable[i][p1.getX()] == 0) {
                    way.add(new Point(p1.getX(), i));
                    if (i < p2.getY()) {
                        if (checkLineX(new Point(p1.getX(),i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(),i), new Point(p2.getX(), p2.getY()-1))) {
                            for (int j = p1.getX() - 1; j > p2.getX(); j--) {
                                way.add(new Point(j,i));
                            }
                            for (int j = i; j < p2.getY(); j++) {
                                way.add(new Point(p2.getX(), j));
                            }
                            break;
                        }
                    } else if (i == p2.getY()) {
                        if (checkLineX(new Point(p1.getX(), i), new Point(p2.getX()+1, i))) {
                            for (int j = p1.getX() - 1; j > p2.getX(); j--) {
                                way.add(new Point(j,i));
                            }
                            break;
                        }
                    } else {
                        if (checkLineX(new Point(p1.getX(), i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(), i), new Point(p2.getX(), p2.getY()+1))) {
                            for (int j = p1.getX() - 1; j > p2.getX(); j--) {
                                way.add(new Point(j,i));
                            }
                            for (int j = i; j > p2.getY(); j--) {
                                way.add(new Point(p2.getX(), j));
                            }
                            break;
                        }
                    }
                }else {
                    way.clear();
                    break;
                }
            }
            if (way.size() > 0) return way;

            for(int i = p1.getY() - 1; i >=0; i--){
                if(adjustedTable[i][p1.getX()] == 0){
                    way.add(new Point(p1.getX(),i));
                    if(checkLineX(new Point(p1.getX(), i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(), i), new Point(p2.getX(), p2.getY()-1))){
                        for(int j = p1.getX()-1; j > p2.getX(); j--){
                            way.add(new Point(j,i));
                        }
                        for(int j = i; j < p2.getY(); j++){
                            way.add(new Point(p2.getX(), j));
                        }
                        break;
                    }
                }else {
                    way.clear();
                    break;
                }
            }

            if(way.size() > 0) return way;


            for(int i = p1.getX() + 1; i <= newColumn; i++){
                if(adjustedTable[p1.getY()][i] == 0){
                    way.add(new Point(i, p1.getY()));
                    if(checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(p2.getX()+1, p2.getY()), new Point(i, p2.getY()))){
                        for(int j = p1.getY()+1; j < p2.getY(); j++){
                            way.add(new Point(i,j));
                        }
                        for(int j = i; j > p2.getX(); j--){
                            way.add(new Point(j, p2.getY()));
                        }
                        break;
                    }
                }else {
                    way.clear();
                    break;
                }
            }

            if(way.size() > 0) return way;
        }

        if(p1.getX() < p2.getX()){
            System.out.println("x1 < x2");
            for (int i = p1.getX() + 1; i <= newColumn; i++) {
                if (adjustedTable[p1.getY()][i] == 0) {
                    way.add(new Point(i, p1.getY()));
                    if (i < p2.getX()) {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(i, p2.getY()), new Point(p2.getX() - 1, point2.getY()))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            for (int j = i; j < p2.getX(); j++) {
                                way.add(new Point(i, p2.getY()));
                            }
                            break;
                        }
                    } else if (i == p2.getX()) {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY() - 1))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            break;
                        }
                    } else {
                        if (checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(p2.getX() + 1, p2.getY()),new Point(i, p2.getY()))) {
                            for (int j = p1.getY() + 1; j < p2.getY(); j++) {
                                way.add(new Point(i, j));
                            }
                            for (int j = i; j > p2.getX(); j--) {
                                way.add(new Point(i, p2.getY()));
                            }
                            break;
                        }
                    }
                } else {
                    way.clear();
                    break;
                }

            }

            if (way.size() > 0) return way;

            for (int i = p1.getY()+1; i >= newRow; i++) {
                if (adjustedTable[i][p1.getX()] == 0) {
                    way.add(new Point(p1.getX(), i));
                    if (i < p2.getY()) {
                        if (checkLineX(new Point(p1.getX(),i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(),i), new Point(p2.getX(), p2.getY()-1))) {
                            for (int j = p1.getX() + 1; j < p2.getX(); j++) {
                                way.add(new Point(j,i));
                            }
                            for (int j = i; j < p2.getY(); j++) {
                                way.add(new Point(p2.getX(), j));
                            }
                            break;
                        }
                    } else if (i == p2.getY()) {
                        if (checkLineX(new Point(p1.getX(), i), new Point(p2.getX()-1, i))) {
                            for (int j = p1.getX() + 1; j < p2.getX(); j++) {
                                way.add(new Point(j,i));
                            }
                            break;
                        }
                    } else {
                        if (checkLineX(new Point(p1.getX(), i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(), i), new Point(p2.getX(), p2.getY()+1))) {
                            for (int j = p1.getX() + 1; j < p2.getX(); j++) {
                                way.add(new Point(j,i));
                            }
                            for (int j = i; j > p2.getY(); j--) {
                                way.add(new Point(p2.getX(), j));
                            }
                            break;
                        }
                    }
                }else {
                    way.clear();
                    break;
                }
            }
            if (way.size() > 0) return way;

            for(int i = p1.getY() - 1; i >=0; i--){
                if(adjustedTable[i][p1.getX()] == 0){
                    way.add(new Point(p1.getX(),i));
                    if(checkLineX(new Point(p1.getX(), i), new Point(p2.getX(), i)) && checkLineY(new Point(p2.getX(), i), new Point(p2.getX(), p2.getY()-1))){
                        for(int j = p1.getX()+1; j < p2.getX(); j++){
                            way.add(new Point(j,i));
                        }
                        for(int j = i; j < p2.getY(); j++){
                            way.add(new Point(p2.getX(), j));
                        }
                        break;
                    }
                }else {
                    way.clear();
                    break;
                }
            }

            if(way.size() > 0) return way;


            for(int i = p1.getX() - 1; i >= 0; i--){
                if(adjustedTable[p1.getY()][i] == 0){
                    way.add(new Point(i, p1.getY()));
                    if(checkLineY(new Point(i, p1.getY()), new Point(i, p2.getY())) && checkLineX(new Point(p2.getX()-1, p2.getY()), new Point(i, p2.getY()))){
                        for(int j = p1.getY()+1; j < p2.getY(); j++){
                            way.add(new Point(i,j));
                        }
                        for(int j = i; j < p2.getX(); j++){
                            way.add(new Point(j, p2.getY()));
                        }
                        break;
                    }
                }else {
                    way.clear();
                    break;
                }
            }

            if(way.size() > 0) return way;
        }
        return way;
    }

    private boolean checkLineX(Point pnt1, Point pnt2){
        Point p1 = Point.max(pnt1,pnt2);
        Point p2 = Point.min(pnt1,pnt2);

        if(p1.getY() == p2.getY()){
            for(int i = p1.getX(); i <= p2.getX(); i++){
                if(adjustedTable[p1.getY()][i] != 0) return false;
            }
            return true;
        }
        return false;
    }
    private boolean checkLineY(Point pnt1, Point pnt2){
        Point p1 = Point.max(pnt1,pnt2);
        Point p2 = Point.min(pnt1,pnt2);

        if(p1.getX() == p2.getX()){
            for(int i = p1.getY(); i <= p2.getY(); i++){
                if(adjustedTable[i][p1.getX()] != 0) return false;
            }
            return true;
        }
        return false;
    }
}

