package edu.catscosmicmarket.featuretoggle.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import edu.catscosmicmarket.featuretoggle.FeatureToggles;
import edu.catscosmicmarket.featuretoggle.annotation.FeatureToggle;
import edu.catscosmicmarket.featuretoggle.exception.FeatureNotAvailableException;
import edu.catscosmicmarket.featuretoggle.service.FeatureToggleService;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    @Before(value = "@annotation(featureToggle)")
    public void checkFeatureToggleAnnotation(FeatureToggle featureToggle) {
        FeatureToggles toggle = featureToggle.value();

        if (!featureToggleService.check(toggle.getFeatureName())) {
            log.warn("Feature toggle {} is not enabled!", toggle.getFeatureName());
            throw new FeatureNotAvailableException(toggle.getFeatureName());
        }
    }
}