package com.wahenoor.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wahenoor.Entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
	Optional<Administrator> findByUsername(String username);

	boolean existsByUsername(String username);

	Optional<Administrator> findByEmail(String email);
}