package com.test;
public interface Measurable {
    void measure(long times);
    long measureTask();
    long getMinValue();
    long getAvarageValue();
    long getMaxValue();
}   