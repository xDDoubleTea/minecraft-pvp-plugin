package me.practice.practice_server.model;

import org.bukkit.Color;

public class Team {
  private Color color;
  private String name;

  public Team(Color color, String name) {
    this.color = color;
    this.name = name;
  }

  public Color getColor() {
    return color;
  }

  public String getName() {
    return name;
  }
}
