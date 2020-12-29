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
import eu.hansolo.tilesfx.tools.FlowGridPane;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * User: hansolo Date: 19.12.16 Time: 12:54
 */
public class PingDemo extends Application {

    private static final Random RND = new Random();
    private static final double TILE_WIDTH = 250;
    private static final double TILE_HEIGHT = 250;
    private int noOfNodes = 0;

    private ChartData chartData1;
    private ChartData chartData2;
    private ChartData chartData3;
    private ChartData chartData4;

    private Tile donutChartTile;
    private Tile donutChartTile2;
//    private Tile barChartTile;
//    private Tile circularProgressTile;

    private long lastTimerCall;
    private long lastTimerCall2;
    private AnimationTimer timer;
    private AnimationTimer timer2;
    private DoubleProperty value;

    private String url1 = "192.168.0.3";
    private String url2 = "8.8.8.8";
    Integer okPing = 0;
    Integer nokPing = 0;

    Integer okPing2 = 0;
    Integer nokPing2 = 0;

    Boolean resPing = true;
    Boolean resPing2 = true;

    @Override
    public void init() {
        long start = System.currentTimeMillis();
        value = new SimpleDoubleProperty(0);

        okPing = 0;
        nokPing = 0;

        okPing2 = 0;
        nokPing2 = 0;

        resPing = true;
        resPing2 = true;

        // Chart Data
        chartData1 = new ChartData("Ping Ok", 24.0, Tile.GREEN);
        chartData2 = new ChartData("Ping NotOk", 10.0, Tile.RED);
        chartData3 = new ChartData("Ping Ok", 24.0, Tile.GREEN);
        chartData4 = new ChartData("Ping NotOk", 10.0, Tile.RED);

        donutChartTile = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title(url1)
                .text("Some text")
                .textVisible(false)
                .chartData(chartData1, chartData2)
                .build();
        donutChartTile2 = TileBuilder.create()
                .skinType(SkinType.DONUT_CHART)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title(url2)
                .text("Some text")
                .textVisible(false)
                .chartData(chartData3, chartData4)
                .build();

        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    if (now > lastTimerCall + 3_500_000_000L) {
System.out.println("==================== future1=========================");
                        // Run a task specified by a Runnable Object asynchronously.
                        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
                            @Override
                            public void run() {
                                // Simulate a long-running Job
                                System.out.println("....,, ejecutando el run");

                                try {
                                    //  TimeUnit.SECONDS.sleep(1);
                                    System.out.println("----------------------------------------------");
                                    print();
                                    System.out.println(" call ResPing = isReachable(url1);" + JavscazUtil.getFechaHoraActual());
                                    resPing = isReachable(url1);

                                    System.out.println("ResPing: " + resPing);

                                    if (resPing) {
                                        okPing += 1;
                                    } else {
                                        nokPing += 1;
                                    }
                                    chartData1.setValue(okPing);
                                    chartData2.setValue(nokPing);
                                } catch (Exception e) {
                                    throw new IllegalStateException(e);
                                }
                                System.out.println("I'll run in a separate thread than the main thread.");
                            }
                        });
                        
                        System.out.println("==================== future2=========================");
                        // Run a task specified by a Runnable Object asynchronously.
                        CompletableFuture<Void> future2 = CompletableFuture.runAsync(new Runnable() {
                            @Override
                            public void run() {
                                // Simulate a long-running Job
                                System.out.println("....,, ejecutando el run2");

                                try {
                                    //  TimeUnit.SECONDS.sleep(1);
                                    System.out.println("----------------------------------------------");
                                    print();
                                    System.out.println(" call ResPing = isReachable(url1);" + JavscazUtil.getFechaHoraActual());
                                    resPing2 = isReachable(url2);

                                    System.out.println("ResPing2: " + resPing2);

                                    if (resPing2) {
                                        okPing2 ++;
                                    } else {
                                        nokPing2 ++;
                                    }
                                    chartData3.setValue(okPing2);
                                    chartData4.setValue(nokPing2);
                                } catch (Exception e) {
                                    throw new IllegalStateException(e);
                                }
                                System.out.println("I'll run in a separate thread than the main thread.");
                            }
                        });

                        lastTimerCall = now;
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
       
        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void start(Stage stage) {
        long start = System.currentTimeMillis();

        FlowGridPane pane = new FlowGridPane(8, 6, donutChartTile, donutChartTile2);

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
       // timer2.start();

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
        List<String> command = JavscazUtil.buildCommandPing(ipAddress);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String outputLine;

            while ((outputLine = standardOutput.readLine()) != null) {
                // Picks up Windows and Unix unreachable hosts

//                if (outputLine.toLowerCase().contains("destination host unreachable")) {
                if (outputLine.toLowerCase().contains("Tiempo de espera agotado") || outputLine.toLowerCase().contains("100% packet loss")) {
                    return false;
                }
            }
        }
        System.out.println("finalizo  isReachable()");
        return true;
    }

    public static boolean isReachable2(String ipAddress) throws IOException {
        List<String> command = JavscazUtil.buildCommandPing(ipAddress);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        try (BufferedReader standardOutput = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String outputLine = "";

            while ((outputLine = standardOutput.readLine()) != null) {
                // Picks up Windows and Unix unreachable hosts

//                if (outputLine.toLowerCase().contains("destination host unreachable")) {
                if (outputLine.toLowerCase().contains("Tiempo de espera agotado") || outputLine.toLowerCase().contains("100% packet loss")) {
                    return false;
                }
            }
        }
        System.out.println("finalizo  isReachable2()");
        return true;
    }

    private void print() {
        System.out.println("-------------------------------------");
        System.out.println(url1 + "  {" + okPing + " : " + nokPing + "} " + url2 + "  {" + okPing2 + " :" + nokPing2 + " }");
        System.out.println("-------------------------------------");
    }

}
