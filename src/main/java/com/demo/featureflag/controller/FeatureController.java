package com.demo.featureflag.controller;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FeatureController {
    @Autowired
    private FeatureService featureService;

    /*
     This endpoint allows admin users to add features.
     Security is not implemented.
     */
    @PostMapping("/feature")
    public Feature addFeature(@RequestBody Feature feature) {
        return featureService.addFeature(feature);
    }
}
