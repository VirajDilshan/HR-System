package com.epsychiatry.service.impl;

import com.epsychiatry.model.facebook.AccessToken;
import com.epsychiatry.repository.AccessTokenRepository;
import com.epsychiatry.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {
    @Autowired
    private AccessTokenRepository accessTokenRepository;
    @Override
    public AccessToken saveAccessToken(AccessToken accessToken) {
        return accessTokenRepository.save(accessToken);
    }

    @Override
    public AccessToken getLatestToken() {
        AccessToken latestToken = null;
        try {
            latestToken = accessTokenRepository.findFirstByOrderByIdDesc();
            System.out.printf("");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(latestToken == null) {
            latestToken = new AccessToken("no data",
                    new Long(0),
                    "no data .....................................",
                    "no data .............................",
                    "no data",
                    new Long(0));
        }

        return latestToken;

    }
}
