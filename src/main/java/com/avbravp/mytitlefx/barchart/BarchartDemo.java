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
package com.avbravp.mytitlefx.barchart;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.FlowGridPane;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

/**
 * User: hansolo Date: 19.12.16 Time: 12:54
 */
public class BarchartDemo extends Application {

    private static final Random RND = new Random();
    private static final double TILE_WIDTH = 150;
    private static final double TILE_HEIGHT = 150;
    private int noOfNodes = 0;

    private BarChartItem barChartItem1;
    private BarChartItem barChartItem2;
    private BarChartItem barChartItem3;
    private BarChartItem barChartItem4;

    private Tile barChartTile;

    private long lastTimerCall;
    private AnimationTimer timer;
    private DoubleProperty value;

    @Override
    public void init() {
        long start = System.currentTimeMillis();
        value = new SimpleDoubleProperty(0);

        // BarChart Items
        barChartItem1 = new BarChartItem("Gerrit", 47, Tile.BLUE);
        barChartItem2 = new BarChartItem("Sandra", 43, Tile.RED);
        barChartItem3 = new BarChartItem("Lilli", 12, Tile.GREEN);
        barChartItem4 = new BarChartItem("Anton", 10, Tile.ORANGE);

        barChartItem1.setFormatString("%.1f kWh");

        barChartTile = TileBuilder.create()
                .skinType(SkinType.BAR_CHART)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("BarChart Tile")
                .text("Whatever text")
                .barChartItems(barChartItem1, barChartItem2, barChartItem3, barChartItem4)
                .decimals(0)
                .build();

//       
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > lastTimerCall + 3_500_000_000L) {
                    barChartTile.getBarChartItems().get(RND.nextInt(4)).setValue(RND.nextDouble() * 80);
                    lastTimerCall = now;
                }
            }
        };

        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void start(Stage stage) {
        long start = System.currentTimeMillis();

     FlowGridPane pane = new FlowGridPane(8, 6, barChartTile);
    
    
//        FlowGridPane pane = new FlowGridPane(8, 6,
//                                             percentageTile, clockTile, gaugeTile, sparkLineTile, areaChartTile,
//                                             lineChartTile, timerControlTile, numberTile, textTile,
//                                             highLowTile, plusMinusTile, sliderTile, switchTile, timeTile,
//                                             barChartTile, customTile, leaderBoardTile, worldTile, mapTile,
//                                             radialChartTile, donutChartTile, circularProgressTile, stockTile,
//                                             gaugeSparkLineTile, radarChartTile1, radarChartTile2,
//                                             smoothAreaChartTile, countryTile, characterTile,
//                                             flipTile, switchSliderTile, dateTile, calendarTile, sunburstTile,
//                                             matrixTile, radialPercentageTile, statusTile, barGaugeTile, imageTile,
//                                             timelineTile, imageCounterTile, ledTile, countdownTile, matrixIconTile,
//                                             cycleStepTile, customFlagChartTile, colorTile, turnoverTile, fluidTile, fireSmokeTile,
//                                             gauge2Tile, happinessTile, radialDistributionTile);

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

    }

    @Override
    public void stop() {
        // useful for jpro
        timer.stop();
        System.exit(0);
    }

    // ******************** Misc **********************************************
    private void calcNoOfNodes(Node node) {
        if (node instanceof Parent) {
            if (((Parent) node).getChildrenUnmodifiable().size() != 0) {
                ObservableList<Node> tempChildren = ((Parent) node).getChildrenUnmodifiable();
                noOfNodes += tempChildren.size();
                for (Node n : tempChildren) {
                    calcNoOfNodes(n);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
