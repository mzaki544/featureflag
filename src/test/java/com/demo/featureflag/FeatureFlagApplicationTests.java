package com.demo.featureflag;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.domain.User;
import com.demo.featureflag.repository.UserRepository;
import com.demo.featureflag.service.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeatureFlagApplicationTests {
	@Autowired
	private FeatureService featureService;
	@Autowired
	private UserRepository userRepository;

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

	@Test
	public void enableFeatureTest(){
		User user1 = new User();
		user1.setName("user1");
		user1.setAdmin(true);
		User u1 = userRepository.save(user1);
		assertNotNull(u1);

		Feature feature1 = new Feature();
		feature1.setTitle("feature1");
		Feature f1 = featureService.addFeature(feature1);
		assertNotNull(f1);

		Optional<Feature> result = featureService.enableFeature(f1.getId(),u1.getId());
		assertNotNull(result);
		assertEquals(result.get().getId(),f1.getId());
		assertTrue(result.get().getUsers().contains(u1));

	}
	@Test
	public void getAllEnabledFeaturedByUserId(){
		User user1 = new User();
		user1.setName("user1");
		user1.setAdmin(false);
		User u1 = userRepository.save(user1);
		assertNotNull(u1);

		Feature feature1 = new Feature();
		feature1.setTitle("feature1");
		Feature f1 = featureService.addFeature(feature1);
		assertNotNull(f1);

		Feature feature2 = new Feature();
		feature2.setTitle("feature2");
		Feature f2 = featureService.addFeature(feature2);
		assertNotNull(f2);

		Feature feature3 = new Feature();
		feature3.setTitle("feature3");
		feature3.setGlobal(true);
		Feature f3 = featureService.addFeature(feature3);
		assertNotNull(f3);

		Optional<Feature> result = featureService.enableFeature(f1.getId(),u1.getId());
		assertNotNull(result);
		assertEquals(result.get().getId(),f1.getId());
		assertTrue(result.get().getUsers().contains(u1));

		List<Feature> allEnabledFeatures = featureService.getAllEnabledFeatures(u1.getId());
		assertNotNull(allEnabledFeatures);
		assertTrue(allEnabledFeatures.size() == 2);
	}

}
