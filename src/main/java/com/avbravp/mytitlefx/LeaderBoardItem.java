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
import static javafx.application.Application.launch;

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
    private Tile leaderBoardTile;

    private long lastTimerCall;
    private AnimationTimer timer;
    private DoubleProperty value;

    @Override
    public void init() {
        long start = System.currentTimeMillis();

        value = new SimpleDoubleProperty(0);

        // WorldMap Data

        // LeaderBoard Items
        leaderBoardItem1 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Gerrit", 47);
        leaderBoardItem2 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Sandra", 43);
        leaderBoardItem3 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Lilli", 12);
        leaderBoardItem4 = new eu.hansolo.tilesfx.skins.LeaderBoardItem("Anton", 8);

        // Creating Tiles
        leaderBoardTile = TileBuilder.create()
                .skinType(SkinType.LEADER_BOARD)
                .prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("LeaderBoard Tile")
                .text("Whatever text")
                .leaderBoardItems(leaderBoardItem1, leaderBoardItem2, leaderBoardItem3, leaderBoardItem4)
                .build();



        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > lastTimerCall + 3_500_000_000L) {

                    leaderBoardTile.getLeaderBoardItems().get(RND.nextInt(3)).setValue(RND.nextDouble() * 80);

                    lastTimerCall = now;
                }
            }
        };

        System.out.println("Initialization: " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void start(Stage stage) {
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
