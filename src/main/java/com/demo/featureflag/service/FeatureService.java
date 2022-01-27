package com.demo.featureflag.service;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;

    public Feature addFeature(Feature feature) {
        return featureRepository.save(feature);
    }
}
