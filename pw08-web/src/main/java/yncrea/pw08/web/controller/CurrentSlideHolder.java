package yncrea.pw08.web.controller;

import yncrea.pw08.web.data.Slide;

public class CurrentSlideHolder {
    private Slide slide;
    private static CurrentSlideHolder currentSlideHolder;

    public CurrentSlideHolder() {
    }

    public Slide getSlide() {
        return slide;
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
    }

    public static CurrentSlideHolder getInstance(){
        if (currentSlideHolder == null) {
            currentSlideHolder = new CurrentSlideHolder();
        }
        return currentSlideHolder;
    }
}
