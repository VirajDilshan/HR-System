package com.epsychiatry.utils;


import com.epsychiatry.model.facebook.AdInsightsAgeGender;
import com.epsychiatry.pojo.AdInsightErrorOrData;
import com.epsychiatry.pojo.ErrorPojo;
import com.epsychiatry.pojo.FBPaging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class FbInsightsJsonToObject {
    private static final Logger logger = LogManager.getLogger(FbInsightsJsonToObject.class);

    private List<AdInsightsAgeGender> list;
    private ErrorPojo error;
    private FBPaging paging;

    public FbInsightsJsonToObject() {
        this.list = null;
        this.error = null;
        this.paging = null;
    }

    public List<AdInsightsAgeGender> getList() {
        return list;
    }

    public void setList(List<AdInsightsAgeGender> list) {
        this.list = list;
    }

    public ErrorPojo getError() {
        return error;
    }

    public void setError(ErrorPojo error) {
        this.error = error;
    }

    public FBPaging getPaging() {
        return paging;
    }

    public void setPaging(FBPaging paging) {
        this.paging = paging;
    }

    public AdInsightErrorOrData ageAndGender(String token, String nextURL) throws IOException, InterruptedException {
        logger.debug("inside fn ageAndGender");
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        if(nextURL == null) {
            logger.debug("initial url");
            request = HttpRequest.newBuilder()
                    .uri(URI.create("https://graph.facebook.com/v8.0/" +
                            "act_766548267106495/insights?" +
                            "fields=campaign_id,campaign_name,adset_id,adset_name,ad_id,ad_name,impressions,reach,clicks,spend,cpc,cpm,cpp,ctr&" +
                            "date_preset=lifetime&" +
                            "time_increment=monthly&" +
                            "level=ad&" +
                            "breakdowns=age,gender&" +
                            "limit=500&" +
                            "access_token="+token))
                    .GET()
                    .build();
        }else{
            logger.debug("next url");
            request = HttpRequest.newBuilder()
                    .uri(URI.create(nextURL))
                    .GET()
                    .build();
        }


        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        JSONObject entireObj = new JSONObject(response.body());
        if(entireObj.has("error")) {
            JSONObject err = entireObj.getJSONObject("error");
            Type listType = new TypeToken<ErrorPojo>() {}.getType();
            this.setError(gson.fromJson(err.toString(), listType));
        } else {
            // paging
            JSONObject page = entireObj.getJSONObject("paging");
            Type listTypePage = new TypeToken<FBPaging>() {}.getType();
            this.setPaging(gson.fromJson(page.toString(), listTypePage));
            // fb insight array
            JSONArray data = entireObj.getJSONArray("data");
            Type listType = new TypeToken<List<AdInsightsAgeGender>>() {}.getType();
            this.setList(gson.fromJson(data.toString(), listType));
        }

        AdInsightErrorOrData adInsightErrorOrData = new AdInsightErrorOrData(this.getError(), this.getList(), this.getPaging());

        return adInsightErrorOrData;
    }

    public <T> Integer getTotalOrNewDataPoint(List<T> t) {
        return t.size();
    }

    public <T> T getFirstInsights(List<T> t) {
        return t.get(0);
    }

    public <T> T getLastInsights(List<T> t) {
        return t.get(t.size() - 1);
    }

    public boolean hasError() {
        if(getError() == null)
            return false;
        else
            return true;
    }

}
