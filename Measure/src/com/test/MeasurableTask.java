package com.test;

public class MeasurableTask implements Measurable {

    Runnable task;
    double avarage;
    long min = -1;
    long max;
    long counter;

    public MeasurableTask(Runnable task) {
        this.task = task;
    }

    @Override
    public long measureTask() {
        long mills = System.nanoTime();
        task.run();
        mills = System.nanoTime() - mills;
        if (mills < min || min < 0) {
            min = mills;
        }
        if (mills > max) {
            max = mills;
        }
        counter++;
        avarage = ((avarage - mills) / counter) + mills;
        return mills;
    }
    
    @Override
    public long getMinValue() {
        return min;
    }

    @Override
    public long getAvarageValue() {
        return (long) avarage;
    }

    @Override
    public long getMaxValue() {
        return max;
    }
    
    @Override
    public String toString(){
        return String.format("min: %s, max: %s, avrg: %s", min, max, avarage);
    }

    @Override
    public void measure(long times) {
        for(long i = 0; i < times; i++){
            measureTask();
        }
    }
}
