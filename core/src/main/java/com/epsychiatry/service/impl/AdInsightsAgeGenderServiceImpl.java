package com.epsychiatry.service.impl;

import com.epsychiatry.model.facebook.AdInsightsAgeGender;
import com.epsychiatry.repository.AdInsightsAgeGenderRepositiry;
import com.epsychiatry.service.AdInsightsAgeGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdInsightsAgeGenderServiceImpl implements AdInsightsAgeGenderService {

    @Autowired
    private AdInsightsAgeGenderRepositiry adInsightsAgeGenderRepositiry;
    @Override
    public List<AdInsightsAgeGender> saveAllAdInsightsAgeGender(List<AdInsightsAgeGender> list) {
        return (List<AdInsightsAgeGender>)adInsightsAgeGenderRepositiry.saveAll(list);
    }
}
