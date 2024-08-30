package model;

public class Point {
    private int x;
    private int y;

    public Point(){

    }
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }


    public static Point max(Point p1, Point p2){
        if(p1.y > p2.y) return p1;
        else if(p1.y < p2.y) return p2;
        else{
            if(p1.x > p2.x) return p1;
            else if(p1.x < p2.x)  return p2;
            else return p1;
        }
    }
    public static Point min(Point p1, Point p2){
        if(p1.y > p2.y) return p2;
        else if(p1.y < p2.y) return p1;
        else{
            if(p1.x > p2.x) return p2;
            else if(p1.x < p2.x)  return p1;
            else return p1;
        }
    }

    public boolean compare(Point p){
        if(this.x == p.getX() && this.y == p.getY()) return true;
        else return false;
    }
    public void printPoint(){
        System.out.print("(" + x + "," + y + ")");
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
