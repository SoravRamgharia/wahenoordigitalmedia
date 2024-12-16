package com.wahenoor.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wahenoor.Entity.Advertiser;
import com.wahenoor.Entity.Affiliate;

public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {

	long count();

	boolean existsByEmail(String email);

	Optional<Advertiser> findByEmail(String email);

}
