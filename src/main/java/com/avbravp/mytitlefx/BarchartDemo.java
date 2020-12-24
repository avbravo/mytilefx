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

import eu.hansolo.tilesfx.Section;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.Tile.ChartType;
import eu.hansolo.tilesfx.Tile.ImageMask;
import eu.hansolo.tilesfx.Tile.MapProvider;
import eu.hansolo.tilesfx.Tile.SkinType;
import eu.hansolo.tilesfx.Tile.TileColor;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.addons.HappinessIndicator;
import eu.hansolo.tilesfx.addons.HappinessIndicator.Happiness;
import eu.hansolo.tilesfx.addons.Indicator;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.RadarChartMode;
import eu.hansolo.tilesfx.chart.SunburstChart.TextOrientation;
import eu.hansolo.tilesfx.chart.TilesFXSeries;
import eu.hansolo.tilesfx.colors.Bright;
import eu.hansolo.tilesfx.colors.ColorSkin;
import eu.hansolo.tilesfx.colors.Dark;
import eu.hansolo.tilesfx.events.TileEvent.EventType;
import eu.hansolo.tilesfx.icons.Flag;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.skins.LeaderBoardItem;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * User: hansolo
 * Date: 19.12.16
 * Time: 12:54
 */
public class BarchartDemo extends Application {
    private static final    Random RND = new Random();
    private static final    double TILE_WIDTH  = 150;
    private static final    double TILE_HEIGHT = 150;
    private                 int    noOfNodes = 0;

    private BarChartItem    barChartItem1;
    private BarChartItem    barChartItem2;
    private BarChartItem    barChartItem3;
    private BarChartItem    barChartItem4;
//
//    private LeaderBoardItem leaderBoardItem1;
//    private LeaderBoardItem leaderBoardItem2;
//    private LeaderBoardItem leaderBoardItem3;
//    private LeaderBoardItem leaderBoardItem4;
//
//    private ChartData       chartData1;
//    private ChartData       chartData2;
//    private ChartData       chartData3;
//    private ChartData       chartData4;
//    private ChartData       chartData5;
//    private ChartData       chartData6;
//    private ChartData       chartData7;
//    private ChartData       chartData8;
//
//    private ChartData       smoothChartData1;
//    private ChartData       smoothChartData2;
//    private ChartData       smoothChartData3;
//    private ChartData       smoothChartData4;
//
//    private Rank            firstRank;
//
//    private Tile            percentageTile;
//    private Tile            clockTile;
//    private Tile            gaugeTile;
//    private Tile            sparkLineTile;
//    private Tile            areaChartTile;
//    private Tile            lineChartTile;
//    private Tile            highLowTile;
//    private Tile            timerControlTile;
//    private Tile            numberTile;
//    private Tile            textTile;
//    private Tile            plusMinusTile;
//    private Tile            sliderTile;
//    private Tile            switchTile;
//    private Tile            worldTile;
//    private Tile            timeTile;
   private Tile            barChartTile;
//    private Tile            customTile;
//    private Tile            leaderBoardTile;
//    private Tile            mapTile;
//    private Tile            radialChartTile;
//    private Tile            donutChartTile;
//    private Tile            circularProgressTile;
//    private Tile            stockTile;
//    private Tile            gaugeSparkLineTile;
//    private Tile            radarChartTile1;
//    private Tile            radarChartTile2;
//    private Tile            smoothAreaChartTile;
//    private Tile            countryTile;
//    private Tile            characterTile;
//    private Tile            flipTile;
//    private Tile            switchSliderTile;
//    private Tile            dateTile;
//    private Tile            calendarTile;
//    private Tile            sunburstTile;
//    private Tile            matrixTile;
//    private Tile            radialPercentageTile;
//    private Tile            statusTile;
//    private Tile            barGaugeTile;
//    private Tile            imageTile;
//    private Tile            timelineTile;
//    private Tile            imageCounterTile;
//    private Tile            ledTile;
//    private Tile            countdownTile;
//    private Tile            matrixIconTile;
//    private Tile            cycleStepTile;
//    private Tile            customFlagChartTile;
//    private Tile            colorTile;
//    private Tile            turnoverTile;
//    private Tile            fluidTile;
//    private Tile            fireSmokeTile;
//    private Tile            gauge2Tile;
//    private Tile            happinessTile;
//    private Tile            radialDistributionTile;


    private long            lastTimerCall;
    private AnimationTimer  timer;
    private DoubleProperty  value;


    @Override public void init() {
        long start = System.currentTimeMillis();


        value = new SimpleDoubleProperty(0);

        // AreaChart Data
        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.setName("Whatever");
        series1.getData().add(new XYChart.Data("MO", 23));
        series1.getData().add(new XYChart.Data("TU", 21));
        series1.getData().add(new XYChart.Data("WE", 20));
        series1.getData().add(new XYChart.Data("TH", 22));
        series1.getData().add(new XYChart.Data("FR", 24));
        series1.getData().add(new XYChart.Data("SA", 22));
        series1.getData().add(new XYChart.Data("SU", 20));

        // LineChart Data
        XYChart.Series<String, Number> series2 = new XYChart.Series();
        series2.setName("Inside");
        series2.getData().add(new XYChart.Data("MO", 8));
        series2.getData().add(new XYChart.Data("TU", 5));
        series2.getData().add(new XYChart.Data("WE", 0));
        series2.getData().add(new XYChart.Data("TH", 2));
        series2.getData().add(new XYChart.Data("FR", 4));
        series2.getData().add(new XYChart.Data("SA", 3));
        series2.getData().add(new XYChart.Data("SU", 5));

        XYChart.Series<String, Number> series3 = new XYChart.Series();
        series3.setName("Outside");
        series3.getData().add(new XYChart.Data("MO", 8));
        series3.getData().add(new XYChart.Data("TU", 5));
        series3.getData().add(new XYChart.Data("WE", 0));
        series3.getData().add(new XYChart.Data("TH", 2));
        series3.getData().add(new XYChart.Data("FR", 4));
        series3.getData().add(new XYChart.Data("SA", 3));
        series3.getData().add(new XYChart.Data("SU", 5));

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

        // BarChart Items
        barChartItem1 = new BarChartItem("Gerrit", 47, Tile.BLUE);
        barChartItem2 = new BarChartItem("Sandra", 43, Tile.RED);
        barChartItem3 = new BarChartItem("Lilli", 12, Tile.GREEN);
        barChartItem4 = new BarChartItem("Anton", 8, Tile.ORANGE);

        barChartItem1.setFormatString("%.1f kWh");

   
        barChartTile = TileBuilder.create()
                                  .skinType(SkinType.BAR_CHART)
                                  .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                  .title("BarChart Tile")
                                  .text("Whatever text")
                                  .barChartItems(barChartItem1, barChartItem2, barChartItem3, barChartItem4)
                                  .decimals(0)
                                  .build();

        ZonedDateTime   now          = ZonedDateTime.now();
        List<ChartData> calendarData = new ArrayList<>(10);
        calendarData.add(new ChartData("Item 1", now.minusDays(1).toInstant()));
        calendarData.add(new ChartData("Item 2", now.plusDays(2).toInstant()));
        calendarData.add(new ChartData("Item 3", now.plusDays(10).toInstant()));
        calendarData.add(new ChartData("Item 4", now.plusDays(15).toInstant()));
        calendarData.add(new ChartData("Item 5", now.plusDays(15).toInstant()));
        calendarData.add(new ChartData("Item 6", now.plusDays(20).toInstant()));
        calendarData.add(new ChartData("Item 7", now.plusDays(7).toInstant()));
        calendarData.add(new ChartData("Item 8", now.minusDays(1).toInstant()));
        calendarData.add(new ChartData("Item 9", now.toInstant()));
        calendarData.add(new ChartData("Item 10", now.toInstant()));



        TreeNode tree   = new TreeNode(new ChartData("ROOT"));
        TreeNode first  = new TreeNode(new ChartData("1st", 8.3, Tile.BLUE), tree);
        TreeNode second = new TreeNode(new ChartData("2nd", 2.2, Tile.ORANGE), tree);
        TreeNode third  = new TreeNode(new ChartData("3rd", 1.4, Tile.PINK), tree);
        TreeNode fourth = new TreeNode(new ChartData("4th", 1.2, Tile.LIGHT_GREEN), tree);

        TreeNode jan = new TreeNode(new ChartData("Jan", 3.5), first);
        TreeNode feb = new TreeNode(new ChartData("Feb", 3.1), first);
        TreeNode mar = new TreeNode(new ChartData("Mar", 1.7), first);
        TreeNode apr = new TreeNode(new ChartData("Apr", 1.1), second);
        TreeNode may = new TreeNode(new ChartData("May", 0.8), second);
        TreeNode jun = new TreeNode(new ChartData("Jun", 0.3), second);
        TreeNode jul = new TreeNode(new ChartData("Jul", 0.7), third);
        TreeNode aug = new TreeNode(new ChartData("Aug", 0.6), third);
        TreeNode sep = new TreeNode(new ChartData("Sep", 0.1), third);
        TreeNode oct = new TreeNode(new ChartData("Oct", 0.5), fourth);
        TreeNode nov = new TreeNode(new ChartData("Nov", 0.4), fourth);
        TreeNode dec = new TreeNode(new ChartData("Dec", 0.3), fourth);




        Indicator leftGraphics = new Indicator(Tile.RED);
        leftGraphics.setOn(true);

        Indicator middleGraphics = new Indicator(Tile.YELLOW);
        middleGraphics.setOn(true);

        Indicator rightGraphics = new Indicator(Tile.GREEN);
        rightGraphics.setOn(true);


        MatrixIcon matrixIcon1 = new MatrixIcon();
        matrixIcon1.fillPixels(2, 5, 1, Color.BLACK);
        matrixIcon1.setPixelAt(1, 2, Color.BLACK);
        matrixIcon1.fillPixels(2, 5, 2, Color.WHITE);
        matrixIcon1.setPixelAt(6, 2, Color.BLACK);
        matrixIcon1.setPixelAt(0, 3, Color.BLACK);
        matrixIcon1.fillPixels(1, 2, 3, Color.WHITE);
        matrixIcon1.fillPixels(3, 4, 3, Color.web("#4d79ff"));
        matrixIcon1.fillPixels(5, 6, 3, Color.WHITE);
        matrixIcon1.setPixelAt(7, 3, Color.BLACK);
        matrixIcon1.setPixelAt(0, 4, Color.BLACK);
        matrixIcon1.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon1.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon1.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon1.setPixelAt(7, 4, Color.BLACK);
        matrixIcon1.setPixelAt(1, 5, Color.BLACK);
        matrixIcon1.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon1.setPixelAt(6, 5, Color.BLACK);
        matrixIcon1.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon2 = new MatrixIcon();
        matrixIcon2.fillPixels(1, 6, 2, Color.BLACK);
        matrixIcon2.setPixelAt(0, 3, Color.BLACK);
        matrixIcon2.fillPixels(1, 2, 3, Color.WHITE);
        matrixIcon2.fillPixels(3, 4, 3, Color.web("#4d79ff"));
        matrixIcon2.fillPixels(5, 6, 3, Color.WHITE);
        matrixIcon2.setPixelAt(7, 3, Color.BLACK);
        matrixIcon2.setPixelAt(0, 4, Color.BLACK);
        matrixIcon2.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon2.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon2.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon2.setPixelAt(7, 4, Color.BLACK);
        matrixIcon2.setPixelAt(1, 5, Color.BLACK);
        matrixIcon2.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon2.setPixelAt(6, 5, Color.BLACK);
        matrixIcon2.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon3 = new MatrixIcon();
        matrixIcon3.fillPixels(0, 7, 3, Color.BLACK);
        matrixIcon3.setPixelAt(0, 4, Color.BLACK);
        matrixIcon3.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon3.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon3.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon3.setPixelAt(7, 4, Color.BLACK);
        matrixIcon3.setPixelAt(1, 5, Color.BLACK);
        matrixIcon3.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon3.setPixelAt(6, 5, Color.BLACK);
        matrixIcon3.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon4 = new MatrixIcon();
        matrixIcon4.setPixelAt(0, 3, Color.BLACK);
        matrixIcon4.setPixelAt(7, 3, Color.BLACK);
        matrixIcon4.fillPixels(0, 7, 4, Color.BLACK);
        matrixIcon4.setPixelAt(1, 5, Color.BLACK);
        matrixIcon4.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon4.setPixelAt(6, 5, Color.BLACK);
        matrixIcon4.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon5 = new MatrixIcon();
        matrixIcon5.setPixelAt(0, 3, Color.BLACK);
        matrixIcon5.setPixelAt(7, 3, Color.BLACK);
        matrixIcon5.setPixelAt(0, 4, Color.BLACK);
        matrixIcon5.setPixelAt(7, 4, Color.BLACK);
        matrixIcon5.setPixelAt(1, 5, Color.BLACK);
        matrixIcon5.fillPixels(2, 5, 5, Color.BLACK);
        matrixIcon5.setPixelAt(6, 5, Color.BLACK);
        matrixIcon5.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon6 = new MatrixIcon();
        matrixIcon6.setPixelAt(0, 3, Color.BLACK);
        matrixIcon6.setPixelAt(7, 3, Color.BLACK);
        matrixIcon6.fillPixels(0, 7, 4, Color.BLACK);
        matrixIcon6.setPixelAt(1, 5, Color.BLACK);
        matrixIcon6.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon6.setPixelAt(6, 5, Color.BLACK);
        matrixIcon6.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon7 = new MatrixIcon();
        matrixIcon7.fillPixels(0, 7, 3, Color.BLACK);
        matrixIcon7.setPixelAt(0, 4, Color.BLACK);
        matrixIcon7.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon7.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon7.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon7.setPixelAt(7, 4, Color.BLACK);
        matrixIcon7.setPixelAt(1, 5, Color.BLACK);
        matrixIcon7.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon7.setPixelAt(6, 5, Color.BLACK);
        matrixIcon7.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon8 = new MatrixIcon();
        matrixIcon8.fillPixels(1, 6, 2, Color.BLACK);
        matrixIcon8.setPixelAt(0, 3, Color.BLACK);
        matrixIcon8.fillPixels(1, 2, 3, Color.WHITE);
        matrixIcon8.fillPixels(3, 4, 3, Color.web("#4d79ff"));
        matrixIcon8.fillPixels(5, 6, 3, Color.WHITE);
        matrixIcon8.setPixelAt(7, 3, Color.BLACK);
        matrixIcon8.setPixelAt(0, 4, Color.BLACK);
        matrixIcon8.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon8.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon8.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon8.setPixelAt(7, 4, Color.BLACK);
        matrixIcon8.setPixelAt(1, 5, Color.BLACK);
        matrixIcon8.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon8.setPixelAt(6, 5, Color.BLACK);
        matrixIcon8.fillPixels(2, 5, 6, Color.BLACK);

        MatrixIcon matrixIcon9 = new MatrixIcon();
        matrixIcon9.fillPixels(2, 5, 1, Color.BLACK);
        matrixIcon9.setPixelAt(1, 2, Color.BLACK);
        matrixIcon9.fillPixels(2, 5, 2, Color.WHITE);
        matrixIcon9.setPixelAt(6, 2, Color.BLACK);
        matrixIcon9.setPixelAt(0, 3, Color.BLACK);
        matrixIcon9.fillPixels(1, 2, 3, Color.WHITE);
        matrixIcon9.fillPixels(3, 4, 3, Color.web("#4d79ff"));
        matrixIcon9.fillPixels(5, 6, 3, Color.WHITE);
        matrixIcon9.setPixelAt(7, 3, Color.BLACK);
        matrixIcon9.setPixelAt(0, 4, Color.BLACK);
        matrixIcon9.fillPixels(1, 2, 4, Color.WHITE);
        matrixIcon9.fillPixels(3, 4, 4, Color.web("#4d79ff"));
        matrixIcon9.fillPixels(5, 6, 4, Color.WHITE);
        matrixIcon9.setPixelAt(7, 4, Color.BLACK);
        matrixIcon9.setPixelAt(1, 5, Color.BLACK);
        matrixIcon9.fillPixels(2, 5, 5, Color.WHITE);
        matrixIcon9.setPixelAt(6, 5, Color.BLACK);
        matrixIcon9.fillPixels(2, 5, 6, Color.BLACK);



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

        HBox usa     = getCountryItem(Flag.UNITED_STATES_OF_AMERICA, "USA", "1.618.757 / 96.909");
        HBox brazil  = getCountryItem(Flag.BRAZIL, "Brazil", "363.211 / 22.666");
        HBox uk      = getCountryItem(Flag.UNITED_KINGDOM, "UK", "259.563 / 36.793");
        HBox spain   = getCountryItem(Flag.SPAIN, "Spain", "235.772 / 28.752");
        HBox italy   = getCountryItem(Flag.ITALY, "Italy", "229.585 / 32.785");
        HBox germany = getCountryItem(Flag.GERMANY, "Germany", "178.570 / 8.257");
        HBox france  = getCountryItem(Flag.FRANCE, "France", "142.204 / 28.315");

        VBox dataTable = new VBox(0, header, usa, brazil, uk, spain, italy, germany, france);
        dataTable.setFillWidth(true);


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


                    series1.getData().forEach(data -> data.setYValue(RND.nextInt(100)));
                    series2.getData().forEach(data -> data.setYValue(RND.nextInt(30)));
                    series3.getData().forEach(data -> data.setYValue(RND.nextInt(10)));


//
                   barChartTile.getBarChartItems().get(RND.nextInt(3)).setValue(RND.nextDouble() * 80);
//


                    lastTimerCall = now;
                }
            }
        };

        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override public void start(Stage stage) {
        long start = System.currentTimeMillis();

         FlowGridPane pane = new FlowGridPane(8, 6,
                                             barChartTile);
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

    @Override public void stop() {

        // useful for jpro
        timer.stop();


        System.exit(0);
    }

    private HBox getCountryItem(final Flag flag, final String text, final String data) {
        ImageView imageView = new ImageView(flag.getImage(22));
        HBox.setHgrow(imageView, Priority.NEVER);

        Label name = new Label(text);
        name.setTextFill(Tile.FOREGROUND);
        name.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(name, Priority.NEVER);

        Region spacer = new Region();
        spacer.setPrefSize(5, 5);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label views = new Label(data);
        views.setTextFill(Tile.FOREGROUND);
        views.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(views, Priority.NEVER);

        HBox hBox = new HBox(5, imageView, name, spacer, views);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setFillHeight(true);

        return hBox;
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
