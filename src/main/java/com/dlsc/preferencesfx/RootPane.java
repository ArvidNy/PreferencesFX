package com.dlsc.preferencesfx;

import com.dlsc.preferencesfx.pages.Page;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 * Created by François Martin on 30.10.17.
 */
public class RootPane extends StackPane {

  RootPane(){
    Slider slider = new Slider();

    PreferencesFX preferencesFX = PreferencesFX.of(
        Category.of("Bildschirm",
            Setting.of("Nachtmodus", Type.BOOLEAN),
            Setting.of("Skalierung", Type.INTEGER),
            Setting.of("Custom", slider, slider.valueProperty())
        ),
        Category.of("Benachrichtigungen")
            .withSubCategories(
                Category.of("Allgemein"),
                Category.of("Farbe"),
                Category.of("Schrift")
            ),
        Category.of("Netzbetrieb")
            .withSubCategories(
                Category.of("Allgemein")
            )
    );

    getChildren().add(new Page(null));
  }

}
