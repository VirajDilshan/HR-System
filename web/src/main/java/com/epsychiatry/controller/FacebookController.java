package com.epsychiatry.controller;

import com.epsychiatry.model.facebook.AccessToken;
import com.epsychiatry.service.AccessTokenService;
import com.epsychiatry.utils.FbInsightsJsonToObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/admin/facebook")
public class FacebookController {
    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    FbInsightsJsonToObject  fbInsightsJsonToObject;

    @GetMapping("update/token")
    public String getTokenPage(@ModelAttribute("accessToken") AccessToken accessToken, Model model) {
        AccessToken lastestToken = accessTokenService.getLatestToken();
        model.addAttribute("latest_token", lastestToken);
        return "admin/facebook/facebook-token";
    }

    @PostMapping("token")
    public String saveToken(@Valid @ModelAttribute("accessToken") AccessToken accessToken, Model model, BindingResult result) {

        if(result.hasErrors()) {
            return "admin/facebook/facebook-token";
        }

        accessTokenService.saveAccessToken(accessToken);
        return "redirect:/admin/facebook/update/token";
    }

    @GetMapping("update/insights")
    public String getUpdateDatabasePage(Model model) throws IOException, InterruptedException {

        return "admin/facebook/update-database";
    }

    @GetMapping("view/analytics")
    public String getAnalyticsPage(Model model) {
        return "admin/facebook/analytics";
    }
}
