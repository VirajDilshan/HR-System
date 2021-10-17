package com.epsychiatry.controller;


import com.epsychiatry.model.facebook.AdInsightsAgeGender;
import com.epsychiatry.pojo.AdInsightErrorOrData;
import com.epsychiatry.pojo.FacebookFetchDataInfo;
import com.epsychiatry.pojo.FacebookSaveDataInfo;
import com.epsychiatry.security.LoggedUser;
import com.epsychiatry.service.AccessTokenService;
import com.epsychiatry.service.AdInsightsAgeGenderService;
import com.epsychiatry.utils.FbInsightsJsonToObject;
import com.epsychiatry.utils.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v0")
public class FbController {
    private static final Logger logger = LogManager.getLogger(FbController.class);

    @Autowired
    private FbInsightsJsonToObject fbInsightsJsonToObject;
    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private AdInsightsAgeGenderService adInsightsAgeGenderService;

    @Autowired
    private FileUtil fileUtil;


    @GetMapping("facebook/insights/all/age-gender")
    public ResponseEntity<FacebookFetchDataInfo> getAllInsightsAgeAndGenderDataInfo() {
        logger.debug("/api/v0/facebook/insights/all/age-gender");
        FacebookFetchDataInfo facebookFetchDataInfo = null;
        AdInsightErrorOrData mapper = null;
        List<AdInsightsAgeGender> adInsightsAgeGenderList = null;

        String token = accessTokenService.getLatestToken().getToken();

        try {
            mapper = fbInsightsJsonToObject.ageAndGender(token, null);
            adInsightsAgeGenderList = mapper.getAdInsightsAgeGenderList();
            logger.debug("initial call");

            // paging through all data
            while (mapper.getPaging().getNext() != null) {
                logger.debug("paging call"+ mapper.getPaging().getNext());
                String nextUrl = mapper.getPaging().getNext();
                mapper = fbInsightsJsonToObject.ageAndGender(token, nextUrl);
                // append to the insight List
                adInsightsAgeGenderList.addAll(mapper.getAdInsightsAgeGenderList());
            }

            logger.info(adInsightsAgeGenderList.size()+" total numbers data gathered");


            if(fbInsightsJsonToObject.hasError()) {

                facebookFetchDataInfo = new FacebookFetchDataInfo.Builder()
                        .totalDataPoint(0)
                        .newDataPoint(0)
                        .error(mapper.getError())
                        .build();
            }else {
                // handle the sira response
                facebookFetchDataInfo = new FacebookFetchDataInfo.Builder()
                        .totalDataPoint(fbInsightsJsonToObject.getTotalOrNewDataPoint(adInsightsAgeGenderList))
                        .startUpdateFrom(fbInsightsJsonToObject.getFirstInsights(adInsightsAgeGenderList).getDate_start())
                        .startUpdateTo(fbInsightsJsonToObject.getFirstInsights(adInsightsAgeGenderList).getDate_stop())
                        .endUpdateFrom(fbInsightsJsonToObject.getLastInsights(adInsightsAgeGenderList).getDate_start())
                        .endUpdateTo(fbInsightsJsonToObject.getLastInsights(adInsightsAgeGenderList).getDate_stop())
                        .build();

                fileUtil.saveToText(adInsightsAgeGenderList, "adInsightsAgeGenderList");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return new ResponseEntity(facebookFetchDataInfo, HttpStatus.OK);
    }

    @GetMapping("facebook/insights/all/age-gender/save")
    public ResponseEntity<FacebookSaveDataInfo> saveAllInsightsAgeAndGenderDataInfo() throws IOException, ClassNotFoundException {
//        if(adInsightsAgeAndGenderList != null && !adInsightsAgeAndGenderList.isEmpty()) {
//            List<AdInsightsAgeGender> list= adInsightsAgeGenderService.saveAllAdInsightsAgeGender(adInsightsAgeAndGenderList);
//            FacebookSaveDataInfo facebookSaveDataInfo = new FacebookSaveDataInfo(list.size());
//
//            return new ResponseEntity(facebookSaveDataInfo, HttpStatus.CREATED);
//
//
//        }

        List<AdInsightsAgeGender> adInsightsAgeGenderList = fileUtil.readFromText("adInsightsAgeGenderList");
        List<AdInsightsAgeGender> list= adInsightsAgeGenderService.saveAllAdInsightsAgeGender(adInsightsAgeGenderList);
        return new ResponseEntity(list, HttpStatus.CREATED);

//        return null;
    }


}















