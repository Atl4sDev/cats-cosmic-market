package edu.catscosmicmarket.controllers;

import edu.catscosmicmarket.featuretoggle.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("api/management-console")
@RequiredArgsConstructor
public class FeatureToggleController {

    private final FeatureToggleService featureToggleService;

    @PostMapping("/enable")
    public ResponseEntity<String> enableFeatureToggle(@PathVariable String name) {
        featureToggleService.enable(name);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PostMapping("/disable/{name}")
    public ResponseEntity<String> disableFeatureToggle(@PathVariable String name) {
        featureToggleService.disable(name);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ConcurrentHashMap<String, Boolean>> getFeatureToggles() {
       return new ResponseEntity<>(featureToggleService.getFeatureToggles(), HttpStatus.OK);
    }



}
