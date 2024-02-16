package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = Float.MAX_VALUE;
    private float tempSum= 0.0f;
    private int numReadings = 0;
    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;

        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
 
        if (temperature < minTemp) {
            minTemp = temperature;
        }
    }

    @Override
    public String display() {
        return String.format("Weather Stats\nAvg. temp: %f°\nMin. temp: %f°\nMax temp: %f°",
                (tempSum / numReadings), minTemp, maxTemp);
    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics";
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}