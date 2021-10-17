package com.epsychiatry.repository;

import com.epsychiatry.model.facebook.AdInsightsAgeGender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdInsightsAgeGenderRepositiry extends JpaRepository<AdInsightsAgeGender, Long> {
}
