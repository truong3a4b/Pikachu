import model.FindLink;
import model.Point;
import model.Table;

import java.util.*;

public class Test {
    public final static int ROW = 6;
    public final static int COLUMN = 6;
    public final static int NUM_ICON = 10;
    public static void main(String[] args) {


        List<Integer> arr = new ArrayList<>();
        Collections.addAll(arr,2, 4, 0 ,5 ,2, 5, 6 ,4 ,0 ,6 ,1, 4, 1 ,3, 0 ,4, 1 ,4, 3 ,5 ,6, 3 ,3 ,3 ,1, 3 ,2 ,1 ,2 ,5, 4 ,2 ,2, 7, 6, 1 );

        arr.clear();
        Table board = new Table(ROW,COLUMN,NUM_ICON);
        arr = board.getArr();

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(arr.get(i*COLUMN+j) + " ");

            }
            System.out.println();
        }

        FindLink engine = new FindLink(arr, ROW, COLUMN);
        engine.printTable();

        Scanner scanner = new Scanner(System.in);

            System.out.println("Nhap toa do A: ");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            Point p1 = new Point(x1,y1);
            System.out.println("Nhap toa do B:");
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            Point p2 = new Point(x2,y2);

            scanner.close();
            List<Point> way = engine.findWay(p1,p2);
            System.out.println(way.size());
            System.out.println();
            for(Point p: way){
                p.printPoint();
                System.out.print("  ");
            }


    }
}
