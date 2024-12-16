package com.wahenoor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wahenoor.Entity.Affiliate;

public interface AffiliateRepository extends JpaRepository<Affiliate, Long> {

	long count(); // Count the number of advertisers

	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Affiliate a WHERE LOWER(a.email) = LOWER(:email)")
	boolean existsByEmail(@Param("email") String email);

}
