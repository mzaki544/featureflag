package com.demo.featureflag.repository;

import com.demo.featureflag.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Long> {
}
