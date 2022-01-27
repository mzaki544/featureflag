package com.demo.featureflag;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.domain.User;
import com.demo.featureflag.repository.UserRepository;
import com.demo.featureflag.service.FeatureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
