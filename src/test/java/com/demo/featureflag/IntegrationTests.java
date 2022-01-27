package com.demo.featureflag;

import com.demo.featureflag.domain.Feature;
import com.demo.featureflag.domain.User;
import com.demo.featureflag.repository.UserRepository;
import com.demo.featureflag.service.FeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {
    @Autowired
    private FeatureService featureService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addFeatureTest() throws Exception {
        Feature feature1 = new Feature();
        feature1.setTitle("feature1");
        mockMvc.perform( post("/api/v1/feature")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feature1))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("feature1"));
    }

    @Test
    public void enableFeatureTest() throws Exception {
        User user1 = new User();
        user1.setName("user1");
        user1.setAdmin(true);
        User u1 = userRepository.save(user1);
        assertNotNull(u1);

        Feature feature1 = new Feature();
        feature1.setTitle("feature1");
        Feature f1 = featureService.addFeature(feature1);
        assertNotNull(f1);

        mockMvc.perform( put("/api/v1/feature/{featureId}/enable/{userId}",f1.getId(),u1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllEnabledFeaturesTest() throws Exception {
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

        mockMvc.perform( get("/api/v1/feature/{userId}",u1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }


}
