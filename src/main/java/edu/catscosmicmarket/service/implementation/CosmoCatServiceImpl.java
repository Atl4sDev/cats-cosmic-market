package edu.catscosmicmarket.service.implementation;

import edu.catscosmicmarket.featuretoggle.FeatureToggles;
import edu.catscosmicmarket.featuretoggle.annotation.FeatureToggle;
import edu.catscosmicmarket.service.CosmoCatService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CosmoCatServiceImpl implements CosmoCatService {
    @Override
    @FeatureToggle(FeatureToggles.COSMO_CATS)
    public List<String> getCosmoCats() {
        return List.of("tisha", "charlik");
    }
}
