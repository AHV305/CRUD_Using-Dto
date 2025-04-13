package com.ahv.crud.sb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahv.crud.sb.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
