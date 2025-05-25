package controller;


import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.stage.Stage;
import javafx.util.Duration;
import model.FindLink;
import model.Point;
import model.Table;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    GridPane gridPane;
    @FXML
    Label labelTime;
    @FXML
    Button btnNew;
    @FXML
    AnchorPane endPane;
    @FXML
    Label resultLabel;
    List<Button> stack = new ArrayList<>();
    private int countdown = 120;
    private int win = 18;
    private int checkWin = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Table table = new Table(6, 6, 15);
        FindLink exploer = new FindLink(table.getArr(),6,6);
        exploer.printTable();
        int[][] arr = exploer.getAdjustedTable();
        Map<Integer, Rectangle> linkX = new HashMap<>();
        Map<Integer, Rectangle> linkY = new HashMap<>();

        //Tao moi lien ket
        for(int row = 0; row <=7 ; row++){
            for(int column = 0; column <= 7; column++){

                Rectangle rect1 = new Rectangle(92,10,Color.BLUE);
                gridPane.add(rect1,column,row);
                GridPane.setHalignment(rect1,HPos.CENTER);
                GridPane.setValignment(rect1,VPos.CENTER);
                rect1.setVisible(false);
                linkX.put(row*8+column,rect1);

                Rectangle rect2 = new Rectangle(10,82,Color.BLUE);
                gridPane.add(rect2,column,row);
                GridPane.setHalignment(rect2,HPos.CENTER);
                GridPane.setValignment(rect2,VPos.CENTER);
                rect2.setVisible(false);
                linkY.put(row*8+column,rect2);
            }
        }
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
                    if(stack.isEmpty()){
                        stack.add(btn);
                        btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                    } else if(stack.size() == 1){
                        Button btn1 = stack.get(0);
                        int col1 = GridPane.getColumnIndex(btn1);
                        int row1 = GridPane.getRowIndex(btn1);
                        int col0 = GridPane.getColumnIndex(btn);
                        int row0 = GridPane.getRowIndex(btn);

                        List<Point> way  = exploer.findWay(new Point(col0,row0),new Point(col1,row1));

                        if(col1 == col0 && row0 == row1){
                            System.out.println("Cung 1 o");
                            btn.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                            stack.clear();
                        }else if(!way.isEmpty()){
                            System.out.println("link exist");
                            btn.setStyle("-fx-border-color: blue; -fx-border-width: 5;");
                            stack.clear();
                            for(int i = 0; i < way.size()-1; i++){
                                int x1,y1,x2,y2;

                                x1 = way.get(i).getX(); y1 = way.get(i).getY();
                                x2 = way.get(i+1).getX(); y2 = way.get(i+1).getY();
                                System.out.println("("+x1+", "+y1+") -> ("+x2+", "+y2+")" );
                                if(y1 == y2){
                                    Rectangle rect = linkX.get(y1*8+x1);
                                    rect.setVisible(true);
                                    if(x1 < x2){
                                        GridPane.setMargin(rect, new Insets(0, 0, 0, 82));
                                    }else{
                                        GridPane.setMargin(rect, new Insets(0, 83, 0, 0));
                                    }
                                    // Tạo PauseTransition để đợi  giây
                                    PauseTransition pause = new PauseTransition(Duration.millis(200));
                                    pause.setOnFinished(e -> {
                                        // Ẩn hình chữ nhật sau giây
                                        rect.setVisible(false);
                                    });
                                    pause.play(); // chạy đếm thời gian
                                }
                                if(x1==x2){
                                    Rectangle rect = linkY.get(y1*8+x1);
                                    rect.setVisible(true);
                                    if (y1 < y2) {
                                        GridPane.setMargin(rect, new Insets(72, 0, 0, 0));
                                    }else{
                                        GridPane.setMargin(rect, new Insets(0, 0, 72, 0));

                                    }

                                    // Tạo PauseTransition để đợi  0.5giây
                                    PauseTransition pause = new PauseTransition(Duration.millis(200));
                                    pause.setOnFinished(e -> {
                                        // Ẩn hình chữ nhật sau
                                        rect.setVisible(false);
                                    });
                                    pause.play(); // chạy đếm thời gian
                                }

                            }


                            btn1.setVisible(false);
                            btn.setVisible(false);
                            checkWin++;
                            if(checkWin == 18){
                                endPane.setVisible(true);
                            }


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

        //Dem thoi gian
        TimeCount();
    }

    private void TimeCount(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            countdown--;
            labelTime.setText(String.valueOf(countdown));
            if (countdown <= 0) {
                // Dừng timeline khi hết giờ
                ((Timeline) event.getSource()).stop();
                endPane.setVisible(true);
                resultLabel.setText("YOU LOSE");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void onRestartButtonClick(ActionEvent event) {
        try {
            // Load lại file FXML (UI gốc)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/pikachu.fxml"));
            Parent root = loader.load();

            // Lấy Stage hiện tại từ sự kiện nút
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Gán lại scene mới vào stage
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
