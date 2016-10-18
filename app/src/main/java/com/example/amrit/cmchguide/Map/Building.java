package com.example.amrit.cmchguide.Map;

/**
 * Created by Amrit on 17-10-2016.
 */

public class Building {
    public int x, y;
    public String name;

    public Building(String name, int x, int y) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    @Override
    public String toString() {
        return (name + " (" + x + ", " + y + ")");
    }
}
