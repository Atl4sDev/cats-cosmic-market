package edu.catscosmicmarket.controllers;

import edu.catscosmicmarket.featuretoggle.service.FeatureToggleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FeatureToggleControllerTestIT {

    @Mock
    private FeatureToggleService featureToggleService;

    @InjectMocks
    private FeatureToggleController featureToggleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void enableFeatureToggle_ShouldReturnSuccess() {
        String toggleName = "testFeature";

        ResponseEntity<String> response = featureToggleController.enableFeatureToggle(toggleName);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
        verify(featureToggleService).enable(toggleName);
    }

    @Test
    void disableFeatureToggle_ShouldReturnSuccess() {
        String toggleName = "testFeature";

        ResponseEntity<String> response = featureToggleController.disableFeatureToggle(toggleName);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
        verify(featureToggleService).disable(toggleName);
    }

    @Test
    void getFeatureToggles_ShouldReturnToggles() {
        ConcurrentHashMap<String, Boolean> toggles = new ConcurrentHashMap<>();
        toggles.put("testFeature1", true);
        toggles.put("testFeature2", false);

        when(featureToggleService.getFeatureToggles()).thenReturn(toggles);

        ResponseEntity<ConcurrentHashMap<String, Boolean>> response = featureToggleController.getFeatureToggles();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(toggles, response.getBody());
        verify(featureToggleService).getFeatureToggles();
    }
}
