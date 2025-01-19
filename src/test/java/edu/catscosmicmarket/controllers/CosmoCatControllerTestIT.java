package edu.catscosmicmarket.controllers;

import edu.catscosmicmarket.featuretoggle.FeatureToggles;
import edu.catscosmicmarket.featuretoggle.service.FeatureToggleService;
import edu.catscosmicmarket.service.implementation.CosmoCatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CosmoCatController.class)
class CosmoCatControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CosmoCatServiceImpl cosmoCatServiceImpl;

    @MockBean
    private FeatureToggleService featureToggleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCosmoCats_featureToggleEnabled_shouldReturnCosmoCats() throws Exception {
        when(featureToggleService.isEnabled(FeatureToggles.COSMO_CATS.getFeatureName())).thenReturn(true);

        when(cosmoCatServiceImpl.getCosmoCats()).thenReturn(List.of("Cosmofury", "Galactio", "Lunapaws", "Skyshine"));

        mockMvc.perform(get("/api/cosmo-cats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0]").value("Cosmofury"))
                .andExpect(jsonPath("$[1]").value("Galactio"))
                .andExpect(jsonPath("$[2]").value("Lunapaws"))
                .andExpect(jsonPath("$[3]").value("Skyshine"));
    }

    @Test
    void getCosmoCats_featureToggleDisabled_shouldReturnEmptyList() throws Exception {
        when(featureToggleService.isEnabled(FeatureToggles.COSMO_CATS.getFeatureName())).thenReturn(false);

        mockMvc.perform(get("/api/cosmo-cats")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }
}
