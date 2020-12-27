/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2016-2020 Gerrit Grunwald.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.avbravp.mytitlefx;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.addons.HappinessIndicator;
import eu.hansolo.tilesfx.addons.HappinessIndicator.Happiness;
import eu.hansolo.tilesfx.addons.Indicator;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.icons.Flag;

import eu.hansolo.tilesfx.tools.Country;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.tools.Helper;
import eu.hansolo.tilesfx.tools.Location;
import eu.hansolo.tilesfx.tools.MatrixIcon;
import eu.hansolo.tilesfx.tools.Rank;
import eu.hansolo.tilesfx.tools.Ranking;
import eu.hansolo.tilesfx.tools.TreeNode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static javafx.application.Application.launch;
import javafx.scene.paint.Stop;

/**
 * User: hansolo Date: 19.12.16 Time: 12:54
 */
public class LeaderBoardItem extends Application {

    private static final Random RND = new Random();
    private static final double TILE_WIDTH = 150;
    private static final double TILE_HEIGHT = 150;
    private int noOfNodes = 0;


    private eu.hansolo.tilesfx.skins.LeaderBoardItem leaderBoardItem1;
    private eu.hansolo.tilesfx.skins.LeaderBoardItem leaderBoardItem2;
    private eu.hansolo.tilesfx.skins.LeaderBoardItem leaderBoardItem3;
    private eu.hansolo.tilesfx.skins.LeaderBoardItem leaderBoardItem4;

    private ChartData       chartData1;
    private ChartData       chartData2;
    private ChartData       chartData3;
    private ChartData       chartData4;
    private ChartData       chartData5;
    private ChartData       chartData6;
    private ChartData       chartData7;
    private ChartData       chartData8;



    private Rank            firstRank;

    private Tile            percentageTile;
    private Tile            clockTile;
    private Tile            gaugeTile;
    private Tile            sparkLineTile;


    private Tile            highLowTile;
    private Tile            timerControlTile;
    private Tile            numberTile;
    private Tile            textTile;
    private Tile            plusMinusTile;
    private Tile            sliderTile;
    private Tile            switchTile;
    private Tile            worldTile;
    private Tile            timeTile;
    private Tile            barChartTile;
    private Tile            customTile;
    private Tile            leaderBoardTile;
    private Tile            mapTile;
    private Tile            radialChartTile;
    private Tile            donutChartTile;
    private Tile            circularProgressTile;
    private Tile            stockTile;
    private Tile            gaugeSparkLineTile;
    private Tile            radarChartTile1;
    private Tile            radarChartTile2;

    private Tile            countryTile;
    private Tile            characterTile;
    private Tile            flipTile;
    private Tile            switchSliderTile;
    private Tile            dateTile;
    private Tile            calendarTile;
    private Tile            sunburstTile;
    private Tile            matrixTile;

    private Tile            statusTile;
    private Tile            barGaugeTile;
    private Tile            imageTile;
    private Tile            timelineTile;
    private Tile            imageCounterTile;
    private Tile            ledTile;
    private Tile            countdownTile;
    private Tile            matrixIconTile;
    private Tile            cycleStepTile;
    private Tile            customFlagChartTile;
    private Tile            colorTile;
    private Tile            turnoverTile;
    private Tile            fluidTile;

//    private Tile            gauge2Tile;




    private long            lastTimerCall;
    private AnimationTimer  timer;
    private DoubleProperty  value;


    @Override public void init() {
        long start = System.currentTimeMillis();


        value = new SimpleDoubleProperty(0);





        // WorldMap Data
        for (int i = 0; i < Country.values().length ; i++) {
            double value = RND.nextInt(10);
            Color  color;
            if (value > 8) {
                color = Tile.RED;
            } else if (value > 6) {
                color = Tile.ORANGE;
            } else if (value > 4) {
                color = Tile.YELLOW_ORANGE;
            } else if (value > 2) {
                color = Tile.GREEN;
            } else {
                color = Tile.BLUE;
            }
            Country.values()[i].setColor(color);
        }

        // TimeControl Data
        TimeSection timeSection = TimeSectionBuilder.create()
                                        .start(LocalTime.now().plusSeconds(20))
                                        .stop(LocalTime.now().plusHours(1))
                                        //.days(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
                                        .color(Tile.GRAY)
                                        .highlightColor(Tile.RED)
                                        .build();

        timeSection.setOnTimeSectionEntered(e -> System.out.println("Section ACTIVE"));
        timeSection.setOnTimeSectionLeft(e -> System.out.println("Section INACTIVE"));

       

        // LeaderBoard Items
        leaderBoardItem1 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Gerrit", 47);
        leaderBoardItem2 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Sandra", 43);
        leaderBoardItem3 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Lilli", 12);
        leaderBoardItem4 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Anton", 8);




        // Creating Tiles







//
        textTile = TileBuilder.create()
                              .skinType(SkinType.TEXT)
                              .prefSize(TILE_WIDTH, TILE_HEIGHT)
                              .title("Text Tile")
                              .text("Whatever text")
                              .description("May the force be with you\n...always")
                              .descriptionAlignment(Pos.TOP_LEFT)
                              .textVisible(true)
                              .build();



     



        leaderBoardTile = TileBuilder.create()
                                     .skinType(SkinType.LEADER_BOARD)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("LeaderBoard Tile")
                                     .text("Whatever text")
                                     .leaderBoardItems(leaderBoardItem1, leaderBoardItem2, leaderBoardItem3, leaderBoardItem4)
                                     .build();




        Indicator leftGraphics = new Indicator(Tile.RED);
        leftGraphics.setOn(true);

        Indicator middleGraphics = new Indicator(Tile.YELLOW);
        middleGraphics.setOn(true);

        Indicator rightGraphics = new Indicator(Tile.GREEN);
        rightGraphics.setOn(true);





        Label     name      = new Label("Name");
        name.setTextFill(Tile.FOREGROUND);
        name.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(name, Priority.NEVER);

        Region spacer = new Region();
        spacer.setPrefSize(5, 5);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label views = new Label("Cases / Deaths");
        views.setTextFill(Tile.FOREGROUND);
        views.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(views, Priority.NEVER);

        HBox header = new HBox(5, name, spacer, views);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setFillHeight(true);







        HappinessIndicator happy   = new HappinessIndicator(Happiness.HAPPY, 0.67);
        HappinessIndicator neutral = new HappinessIndicator(Happiness.NEUTRAL, 0.25);
        HappinessIndicator unhappy = new HappinessIndicator(Happiness.UNHAPPY, 0.08);

        HBox happiness = new HBox(unhappy, neutral, happy);
        happiness.setFillHeight(true);

        HBox.setHgrow(happy, Priority.ALWAYS);
        HBox.setHgrow(neutral, Priority.ALWAYS);
        HBox.setHgrow(unhappy, Priority.ALWAYS);



        List<ChartData> glucoseData = new ArrayList<>();
        for (int i = 0 ; i < 288; i++) {
            glucoseData.add(new ChartData("", RND.nextDouble() * 300 + 50));
        }



        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall + 3_500_000_000L) {
                   


                    leaderBoardTile.getLeaderBoardItems().get(RND.nextInt(3)).setValue(RND.nextDouble() * 80);

                             
                    lastTimerCall = now;
                }
            }
        };

        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override public void start(Stage stage) {
        long start = System.currentTimeMillis();

        FlowGridPane pane = new FlowGridPane(8, 6,
                                           leaderBoardTile);

        pane.setHgap(5);
        pane.setVgap(5);
        pane.setAlignment(Pos.CENTER);
        pane.setCenterShape(true);
        pane.setPadding(new Insets(5));
        //pane.setPrefSize(800, 600);
        pane.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));

        PerspectiveCamera camera = new PerspectiveCamera();
        camera.setFieldOfView(10);

        Scene scene = new Scene(pane);
        scene.setCamera(camera);

        stage.setTitle("TilesFX");
        stage.setScene(scene);
        stage.show();

        System.out.println("Rendering     : " + (System.currentTimeMillis() - start) + "ms");

        // Calculate number of nodes
        calcNoOfNodes(pane);
        System.out.println("Nodes in Scene: " + noOfNodes);

        timer.start();
//
//        mapTile.addPoiLocation(new Location(51.85, 7.75, "Test"));
//        mapTile.removePoiLocation(new Location(51.85, 7.75, "Test"));


    }

    @Override public void stop() {

        // useful for jpro
        timer.stop();
        clockTile.setRunning(false);


        System.exit(0);
    }




    // ******************** Misc **********************************************
    private void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) { calcNoOfNodes(n); }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
