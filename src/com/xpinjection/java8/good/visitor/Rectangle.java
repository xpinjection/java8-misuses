package com.xpinjection.java8.good.visitor;

/**
 * @author Alimenkou Mikalai
 */
class Rectangle {
    private final double width;
    private final double height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    double getWidth() {
        return width;
    }

    double getHeight() {
        return height;
    }
}