package com.epsychiatry.service;

import com.epsychiatry.model.facebook.AccessToken;

public interface AccessTokenService {
    AccessToken saveAccessToken(AccessToken accessToken);
    AccessToken getLatestToken();
}
