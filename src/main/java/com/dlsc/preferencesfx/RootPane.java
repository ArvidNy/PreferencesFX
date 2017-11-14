package com.dlsc.preferencesfx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by François Martin on 30.10.17.
 */
public class RootPane extends VBox {
  private static final Logger LOGGER =
      LogManager.getLogger(RootPane.class.getName());

  private PreferencesFX preferencesFX;
  BooleanProperty nachtmodus = new SimpleBooleanProperty(true);
  StringProperty systemName = new SimpleStringProperty("PreferencesFX");
  IntegerProperty helligkeit = new SimpleIntegerProperty(50);

  MenuBar menuBar;
  Menu menu;
  MenuItem preferencesMenuItem;

  RootPane() {
    setupMenuBar();
    setupPreferences();
    setupLabels();
  }

  private void setupMenuBar() {
    menuBar = new MenuBar();
    menu = new Menu("Edit");
    preferencesMenuItem = new MenuItem("Preferences");
    menu.getItems().add(preferencesMenuItem);
    menuBar.getMenus().add(menu);
    getChildren().add(menuBar);
    preferencesMenuItem.setOnAction(e -> new PreferenceDialog(preferencesFX));
  }

  private void setupPreferences() {
    preferencesFX = PreferencesFX.of(
        Category.of("System",
            Group.of("Hello",
                Setting.of("Systemname", Type.BOOLEAN, nachtmodus)
            ),
            Group.of("World",
                Setting.of("Systemname", Type.STRING, systemName)
            ),
            Group.of("Systemname",
                Setting.of("Systemname", Type.INTEGER, helligkeit)
            )
        ),
        Category.of("Bildschirm")
            .subCategories(
                Category.of("Helligkeitseinstellungen",
                    Group.of("Helligkeit",
                        Setting.of("Helligkeit", Type.INTEGER, helligkeit)
                    )
                ),
                Category.of("Tag- und Nachteinstellungen",
                    Group.of("Nachtmodus",
                        Setting.of("Nachtmodus", Type.BOOLEAN, nachtmodus)
                    )
                )
            )
    );
    LOGGER.info("Preferences generated");
  }

  private void setupLabels() {
    Label label = new Label();
    label.textProperty().bind(nachtmodus.asString());

    Label label2 = new Label();
    label2.textProperty().bind(systemName);

    Label label3 = new Label();
    label3.textProperty().bind(helligkeit.asString().concat("%"));

    HBox hBox = new HBox(label, label2, label3);
    hBox.setSpacing(20);
    hBox.setPadding(new Insets(0, 0, 0, 20));
    getChildren().add(hBox);
  }
}
