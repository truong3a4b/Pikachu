package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.FindLink;
import model.Point;
import model.Table;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    GridPane gridPane;

    List<Button> stack = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Table table = new Table(6, 6, 15);
        FindLink exploer = new FindLink(table.getArr(),6,6);
        exploer.printTable();
        int[][] arr = exploer.getAdjustedTable();

        for(int row = 1; row < 7; row ++){
            for(int column =1; column < 7; column++){
                ImageView img = new ImageView();
                img.setFitHeight(60);
                img.setFitWidth(60);
                String indexImage = String.valueOf(arr[row][column]);
                String url = "./image/image" + indexImage+ ".jpg";
                img.setImage(new Image(url));


                Button btn = new Button("", img);
                btn.setPrefSize(60,60);
                btn.setMaxSize(60,60);
                btn.setOnAction(event -> {
//                    btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                    if(stack.size() == 0){
                        stack.add(btn);
                        btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                    } else if(stack.size() == 1){
                        Button btn1 = stack.get(0);
                        int col1 = gridPane.getColumnIndex(btn1);
                        int row1 = gridPane.getRowIndex(btn1);
                        int col0 = gridPane.getColumnIndex(btn);
                        int row0 = gridPane.getRowIndex(btn);

                        List<Point> way  = exploer.findWay(new Point(col0,row0),new Point(col1,row1));

                        if(col1 == col0 && row0 == row1){
                            System.out.println("Cung 1 o");
                            btn.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                            stack.clear();
                        }else if(way.size() > 0){
                            System.out.println("OK");
                            btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                            stack.clear();
                            btn1.setVisible(false);
                            btn.setVisible(false);
                            exploer.updateTable(row0,col0);
                            exploer.updateTable(row1,col1);
                        }else{
                            System.out.println("next btn");
                            btn1.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                            stack.clear();
                            btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                            stack.add(btn);
                        }

                    }

                });

                gridPane.add(btn, column, row);
            }
        }
    }

    public void onClickButton(){

    }
}
