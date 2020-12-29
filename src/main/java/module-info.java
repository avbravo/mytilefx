module com.avbravo.mytitlefx {
    requires javafx.controls;
   
    exports com.avbravo.mytitlefx;

    requires eu.hansolo.tilesfx;
    
  opens eu.hansolo.tilesfx  to eu.hansolo.tilesfx;
}