package com.demo.featureflag.controller;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.service.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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

    /*
     This endpoint allows admin users to link features to users.
     Security is not implemented.
     */
    @PutMapping("/feature/{featureId}/enable/{userId}")
    public Feature enableFeature(@PathVariable Long featureId,
                                 @PathVariable Long userId,
                                 HttpServletResponse response) {
        Optional<Feature> feature = featureService.enableFeature(featureId,userId);
        return feature.orElseThrow(()-> {throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid data");});
    }

    /*
     This endpoint allows users to get all enabled and global features.
     Security is not implemented.
     */
    @GetMapping("/feature/{userId}")
    public List<Feature> getAllEnabledFeatures(@PathVariable Long userId){
        return featureService.getAllEnabledFeatures(userId);
    }
}
