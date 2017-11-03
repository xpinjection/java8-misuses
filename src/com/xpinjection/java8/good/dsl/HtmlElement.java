package com.xpinjection.java8.good.dsl;

import java.util.stream.Stream;

public interface HtmlElement {
    static void main(String[] args) {
        HtmlElement page = container("HTML",
                container("DIV",
                        block("IMG", 100, 100),
                        block("SPAN", 50, 20)),
                block("IMG", 75, 35)
        );
        System.out.println(page.summary());
    }

    static HtmlElement container(String tagName, HtmlElement... elements) {
        return new Container(tagName, elements);
    }

    static HtmlElement block(String tagName, int width, int height) {
        return new Block(tagName, width, height);
    }

    String getTagName();

    int getWidth();

    int getHeight();

    default String summary() {
        return String.format("<%s>: w=%d, h=%d", getTagName(), getWidth(), getHeight());
    }

    class Container implements HtmlElement {
        private final String tagName;
        private final HtmlElement[] elements;

        private Container(String tagName, HtmlElement[] elements) {
            this.tagName = tagName;
            this.elements = elements;
        }

        @Override
        public String getTagName() {
            return tagName;
        }

        @Override
        public int getWidth() {
            return Stream.of(elements)
                    .mapToInt(HtmlElement::getWidth)
                    .max()
                    .orElse(0);
        }

        @Override
        public int getHeight() {
            return Stream.of(elements)
                    .mapToInt(HtmlElement::getHeight)
                    .sum();
        }
    }

    class Block implements HtmlElement {
        private final String tagName;
        private final int width;
        private final int height;

        private Block(String tagName, int width, int height) {
            this.tagName = tagName;
            this.width = width;
            this.height = height;
        }

        @Override
        public String getTagName() {
            return tagName;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }
    }
}