package edu.iu.habahram.weathermonitoring.controllers;

import edu.iu.habahram.weathermonitoring.model.CurrentConditionDisplay;
import edu.iu.habahram.weathermonitoring.model.HeatIndexDisplay;
import edu.iu.habahram.weathermonitoring.model.Observer;
import edu.iu.habahram.weathermonitoring.model.StatisticsDisplay;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/displays")
public class DisplayController {
    private CurrentConditionDisplay currentConditionDisplay;
    private HeatIndexDisplay heatIndexDisplay;

    public DisplayController(CurrentConditionDisplay currentConditionDisplay, HeatIndexDisplay heatIndexDisplay) {
        this.currentConditionDisplay = currentConditionDisplay;
        this.heatIndexDisplay = heatIndexDisplay;
    }

    @GetMapping
    public ResponseEntity index() {
        String html = "<h1>Available screens:</h1><ul>";
        html += String.format("<li><a href=/displays/%s>%s</a></li>", currentConditionDisplay.id(), currentConditionDisplay.name());
        html += String.format("<li><a href=/displays/%s>%s</a></li>", heatIndexDisplay.id(), heatIndexDisplay.name());
        html += "</ul>";
        return ResponseEntity.status(HttpStatus.FOUND).body(html);
    }

    @GetMapping("/{id}")
    public ResponseEntity display(@PathVariable String id) {
        String html = "";
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            html = currentConditionDisplay.display();
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())) {
            html = heatIndexDisplay.display();
            status = HttpStatus.FOUND;
        }
        return ResponseEntity.status(status).body(html);
    }

    @GetMapping("/{id}/subscribe")
    public ResponseEntity subscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())) {
            heatIndexDisplay.subscribe();
            html = "Subscribed!";
            status = HttpStatus.FOUND;
        } else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(html);
    }

    @GetMapping("/{id}/unsubscribe")
    public ResponseEntity unsubscribe(@PathVariable String id) {
        String html = "";
        HttpStatus status = null;
        if (id.equalsIgnoreCase(currentConditionDisplay.id())) {
            currentConditionDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else if (id.equalsIgnoreCase(heatIndexDisplay.id())) {
            heatIndexDisplay.unsubscribe();
            html = "Unsubscribed!";
            status = HttpStatus.FOUND;
        } else {
            html = "The screen id is invalid.";
            status = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(status).body(html);
    }
}
