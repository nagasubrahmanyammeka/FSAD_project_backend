package com.agriconnect.repository;

import com.agriconnect.entity.Guidance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidanceRepository extends JpaRepository<Guidance, Long> {
}
