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
package com.avbravo.mytitlefx;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import eu.hansolo.tilesfx.tools.Helper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
public class PingDemo extends Application {

    private static final Random RND = new Random();
    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;
    private int noOfNodes = 0;

      private ChartData       chartData1;
    private ChartData       chartData2;
    private ChartData       chartData3;
    private ChartData       chartData4;
    private ChartData       chartData5;
    private ChartData       chartData6;
    private ChartData       chartData7;
    private ChartData       chartData8;
    
  

    private Tile            donutChartTile;
//    private Tile barChartTile;
//    private Tile circularProgressTile;

 

    private long lastTimerCall;
    private AnimationTimer timer;
    private DoubleProperty value;

    Integer okPing = 0;
    Integer NokPing = 0;
    Boolean ResPing = true;

    @Override
    public void init() {
        long start = System.currentTimeMillis();
        value = new SimpleDoubleProperty(0);

        // Chart Data
        chartData1 = new ChartData("Ping Ok", 24.0, Tile.GREEN);
        chartData2 = new ChartData("Ping NotOk", 10.0, Tile.RED);
        


   
      donutChartTile = TileBuilder.create()
                                     .skinType(SkinType.DONUT_CHART)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("DonutChart Tile")
                                     .text("Some text")
                                     .textVisible(false)
                                     .chartData(chartData1, chartData2)
                                     .build();
      
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                if (now > lastTimerCall + 3_500_000_000L) {
                  
                     chartData1.setValue(okPing);
                    chartData2.setValue(NokPing);

                    try {
                      //  ResPing = isReachable("192.168.0.5");
                      //  ResPing = isReachable("192.168.11.1");
                        ResPing = isReachable("192.168.0.3");
                        System.out.println("ResPing: "+ ResPing);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    if (ResPing) {
                        okPing += 1;
                    } else {
                        NokPing += 1;
                    }
                    System.out.println("okPing = " + okPing);
                    System.out.println("NokPing" + NokPing);

                    lastTimerCall = now;
                }
            }
        };

        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void start(Stage stage) {
        long start = System.currentTimeMillis();

        FlowGridPane pane = new FlowGridPane(8, 6, donutChartTile);

    
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

    public static boolean isReachable(String ipAddress) throws IOException {
        List<String> command = buildCommand(ipAddress);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String outputLine;
            
            while ((outputLine = standardOutput.readLine()) != null) {
                // Picks up Windows and Unix unreachable hosts
       
//                if (outputLine.toLowerCase().contains("destination host unreachable")) {
                if (outputLine.toLowerCase().contains("Tiempo de espera agotado") || outputLine.toLowerCase().contains("100% packet loss")  ) {
                    return false;
}
            }
        }

        return true;
    }

    private static List<String> buildCommand(String ipAddress) {
        String SO = System.getProperty("os.name").toLowerCase();

      
        List<String> command = new ArrayList<>();
        command.add("ping");

        if (SO.indexOf("win") >= 0) // if (SystemUtils.IS_OS_WINDOWS)
        {
            command.add("-n");
            //} else if (SystemUtils.IS_OS_UNIX)
        } else if (SO.indexOf("nix") >= 0 || SO.indexOf("linux")>=0) {
            command.add("-c");
        } else {
            throw new UnsupportedOperationException("Unsupported operating system");
        }

        command.add("1");
        command.add(ipAddress);

        
        

        return command;
    }
}
