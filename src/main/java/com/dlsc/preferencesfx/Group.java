package com.dlsc.preferencesfx;

import java.util.Arrays;
import java.util.List;

/**
 * Created by François Martin on 07.11.17.
 */
public class Group {

  private String description;
  private List<Setting> settings;
  private String breadcrumb;

  private Group(String description, Setting... settings) {
    this.description = description;
    this.settings = Arrays.asList(settings);
  }

  public static Group of(String description, Setting... settings) {
    return new Group(description, settings);
  }

  public static Group of(Setting... settings) {
    return new Group(null, settings);
  }

  public Group description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public List<Setting> getSettings() {
    return settings;
  }

  public void addToBreadcrumb(String breadCrumb) {
    this.breadcrumb = breadCrumb + PreferencesFx.BREADCRUMB_DELIMITER + description;
    settings.forEach(setting -> setting.addToBreadcrumb(this.breadcrumb));
  }

  public String getBreadcrumb() {
    return breadcrumb;
  }
}
