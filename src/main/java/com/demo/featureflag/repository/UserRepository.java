package com.demo.featureflag.repository;

import com.demo.featureflag.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
