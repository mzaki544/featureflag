package com.demo.featureflag.repository;

import com.demo.featureflag.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature,Long> {
    @Query(value = "SELECT FEATURE.ID, FEATURE.GLOBAL, FEATURE.TITLE FROM FEATURE  INNER JOIN FEATURE_USERS ON FEATURE_USERS.FEATURE_ID = FEATURE.ID WHERE FEATURE_USERS.USERS_ID = :userId UNION SELECT * FROM FEATURE WHERE FEATURE.GLOBAL = true",nativeQuery = true)
    public List<Feature> findAllByUserId(@Param("userId") Long UserId);
}
