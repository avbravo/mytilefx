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
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private Tile            radialPercentageTile;
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

        // Chart Data
        chartData1 = new ChartData("Item 1", 24.0, Tile.GREEN);
        chartData2 = new ChartData("Item 2", 10.0, Tile.BLUE);
        chartData3 = new ChartData("Item 3", 12.0, Tile.RED);
        chartData4 = new ChartData("Item 4", 13.0, Tile.YELLOW_ORANGE);
        chartData5 = new ChartData("Item 5", 13.0, Tile.BLUE);
        chartData6 = new ChartData("Item 6", 13.0, Tile.BLUE);
        chartData7 = new ChartData("Item 7", 13.0, Tile.BLUE);
        chartData8 = new ChartData("Item 8", 13.0, Tile.BLUE);


        // Creating Tiles




        gaugeTile = TileBuilder.create()
                               .skinType(SkinType.GAUGE)
                               .prefSize(TILE_WIDTH, TILE_HEIGHT)
                               .title("Gauge Tile")
                               .unit("V")
                               .threshold(75)
                               .build();






        timerControlTile = TileBuilder.create()
                                      .skinType(SkinType.TIMER_CONTROL)
                                      .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                      .title("TimerControl Tile")
                                      .text("Whatever text")
                                      .secondsVisible(true)
                                      .dateVisible(true)
                                      .timeSections(timeSection)
                                      .running(true)
                                      .build();

        numberTile = TileBuilder.create()
                                .skinType(SkinType.NUMBER)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Number Tile")
                                .text("Whatever text")
                                .value(13)
                                .unit("mb")
                                .description("Test")
                                .textVisible(true)
                                .build();

        textTile = TileBuilder.create()
                              .skinType(SkinType.TEXT)
                              .prefSize(TILE_WIDTH, TILE_HEIGHT)
                              .title("Text Tile")
                              .text("Whatever text")
                              .description("May the force be with you\n...always")
                              .descriptionAlignment(Pos.TOP_LEFT)
                              .textVisible(true)
                              .build();

        plusMinusTile = TileBuilder.create()
                                   .skinType(SkinType.PLUS_MINUS)
                                   .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                   .maxValue(30)
                                   .minValue(0)
                                   .title("PlusMinus Tile")
                                   .text("Whatever text")
                                   .description("Test")
                                   .unit("\u00B0C")
                                   .build();

        sliderTile = TileBuilder.create()
                                .skinType(SkinType.SLIDER)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Slider Tile")
                                .text("Whatever text")
                                .description("Test")
                                .unit("\u00B0C")
                                .barBackgroundColor(Tile.FOREGROUND)
                                .build();

        switchTile = TileBuilder.create()
                                .skinType(SkinType.SWITCH)
                                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                .title("Switch Tile")
                                .text("Whatever text")
                                //.description("Test")
                                .build();

        switchTile.setOnSwitchPressed(e -> System.out.println("Switch pressed"));
        switchTile.setOnSwitchReleased(e -> System.out.println("Switch released"));



     



        leaderBoardTile = TileBuilder.create()
                                     .skinType(SkinType.LEADER_BOARD)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("LeaderBoard Tile")
                                     .text("Whatever text")
                                     .leaderBoardItems(leaderBoardItem1, leaderBoardItem2, leaderBoardItem3, leaderBoardItem4)
                                     .build();

        mapTile = TileBuilder.create()
                             .skinType(SkinType.MAP)
                             .prefSize(TILE_WIDTH, TILE_HEIGHT)
                             .title("Map Tile")
                             .text("Some text")
                             .description("Description")
                             .currentLocation(new Location(51.91178, 7.63379, "Home", Tile.TileColor.MAGENTA.color))
                             .pointsOfInterest(new Location(51.914405, 7.635732, "POI 1", Tile.TileColor.RED.color),
                                               new Location(51.912529, 7.631752, "POI 2", Tile.TileColor.BLUE.color),
                                               new Location(51.923993, 7.628906, "POI 3", Tile.TileColor.YELLOW_ORANGE.color))
                             .mapProvider(Tile.MapProvider.TOPO)
                             .build();

        radialChartTile = TileBuilder.create()
                                     .skinType(SkinType.RADIAL_CHART)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("RadialChart Tile")
                                     .text("Some text")
                                     .textVisible(false)
                                     .chartData(chartData1, chartData2, chartData3, chartData4)
                                     .build();

        donutChartTile = TileBuilder.create()
                                     .skinType(SkinType.DONUT_CHART)
                                     .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                     .title("DonutChart Tile")
                                     .text("Some text")
                                     .textVisible(false)
                                     .chartData(chartData1, chartData2, chartData3, chartData4)
                                     .build();

        circularProgressTile = TileBuilder.create()
                                          .skinType(SkinType.CIRCULAR_PROGRESS)
                                          .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                          .title("CircularProgress Tile")
                                          .text("Some text")
                                          .unit(Helper.PERCENTAGE)
                                          .build();

        stockTile = TileBuilder.create()
                               .skinType(SkinType.STOCK)
                               .prefSize(TILE_WIDTH, TILE_HEIGHT)
                               .title("Stock Tile")
                               .minValue(0)
                               .maxValue(1000)
                               .averagingPeriod(100)
                               .build();

        gaugeSparkLineTile = TileBuilder.create()
                                        .skinType(SkinType.GAUGE_SPARK_LINE)
                                        .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                        .title("GaugeSparkLine Tile")
                                        .animated(true)
                                        .textVisible(false)
                                        .averagingPeriod(25)
                                        .autoReferenceValue(true)
                                        .barColor(Tile.YELLOW_ORANGE)
                                        .barBackgroundColor(Color.rgb(255, 255, 255, 0.1))
                                        .sections(new eu.hansolo.tilesfx.Section(0, 33, Tile.LIGHT_GREEN),
                                                  new eu.hansolo.tilesfx.Section(33, 67, Tile.YELLOW),
                                                  new eu.hansolo.tilesfx.Section(67, 100, Tile.LIGHT_RED))
                                        .sectionsVisible(true)
                                        .highlightSections(true)
                                        .strokeWithGradient(true)
                                        .fixedYScale(true)
                                        .gradientStops(new Stop(0.0, Tile.LIGHT_GREEN),
                                                       new Stop(0.33, Tile.LIGHT_GREEN),
                                                       new Stop(0.33,Tile.YELLOW),
                                                       new Stop(0.67, Tile.YELLOW),
                                                       new Stop(0.67, Tile.LIGHT_RED),
                                                       new Stop(1.0, Tile.LIGHT_RED))
                                        .build();
        




        firstRank = new Rank(Ranking.FIRST, Tile.YELLOW_ORANGE);





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

        calendarTile = TileBuilder.create()
                                  .skinType(SkinType.CALENDAR)
                                  .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                  .chartData(calendarData)
                                  .build();

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





        radialPercentageTile = TileBuilder.create().skinType(SkinType.RADIAL_PERCENTAGE)
                                          .prefSize(TILE_WIDTH, TILE_HEIGHT)
                                          //.backgroundColor(Color.web("#26262D"))
                                          .maxValue(1000)
                                          .title("RadialPercentage Tile")
                                          .description("Product 1")
                                          .textVisible(false)
                                          .chartData(chartData1, chartData2, chartData3)
                                          .animated(true)
                                          .referenceValue(100)
                                          .value(chartData1.getValue())
                                          .descriptionColor(Tile.GRAY)
                                          //.valueColor(Tile.BLUE)
                                          //.unitColor(Tile.BLUE)
                                          .barColor(Tile.BLUE)
                                          .decimals(0)
                                          .build();

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

        mapTile.addPoiLocation(new Location(51.85, 7.75, "Test"));
        mapTile.removePoiLocation(new Location(51.85, 7.75, "Test"));

        radialPercentageTile.setNotifyRegionTooltipText("tooltip");
        radialPercentageTile.showNotifyRegion(true);
    }

    @Override public void stop() {

        // useful for jpro
        timer.stop();
        clockTile.setRunning(false);
        timerControlTile.setRunning(false);

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
