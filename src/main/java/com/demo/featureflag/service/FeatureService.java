package com.demo.featureflag.service;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.domain.User;
import com.demo.featureflag.repository.FeatureRepository;
import com.demo.featureflag.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FeatureService {
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private UserRepository userRepository;

    public Feature addFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Transactional
    public Optional<Feature> enableFeature(Long featureId, Long userId){
        Optional<Feature> feature = featureRepository.findById(featureId);
        Optional<User> user = userRepository.findById(userId);
        if(feature.isEmpty() || user.isEmpty()){
            return Optional.empty();
        }
        feature.get().getUsers().add(user.get());
        Feature result = featureRepository.save(feature.get());
        return Optional.ofNullable(result);
    }
}
