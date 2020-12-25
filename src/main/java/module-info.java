module com.avbravp.mytitlefx {
    requires javafx.controls;
    exports com.avbravp.mytitlefx;
    requires eu.hansolo.tilesfx;
    opens eu.hansolo.tilesfx  to eu.hansolo.tilesfx;
}