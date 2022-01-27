package com.demo.featureflag;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.service.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FeatureFlagApplicationTests {
	@Autowired
	private FeatureService featureService;

	@Test
	void contextLoads() {
	}

	@Test
	public void addFeatureTest(){
		Feature f1 = new Feature();
		f1.setTitle("feature1");
		Feature result = featureService.addFeature(f1);
		assertEquals("feature1", result.getTitle());
	}

}
