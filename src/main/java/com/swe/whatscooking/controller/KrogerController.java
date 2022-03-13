package com.swe.whatscooking.controller;

import com.swe.whatscooking.entity.KrogerCustomer;
import com.swe.whatscooking.service.KrogerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class KrogerController {
    private KrogerService krogerService;

    public KrogerController(KrogerService krogerService) {
        this.krogerService = krogerService;
    }

    @GetMapping("/krogerAPI")
    public RedirectView redirectToKroger(RedirectAttributes redirectAttributes){
        String authURL;
        String scope;
        String clientId;
        String redirectUri;
        scope = "cart.basic:write";
        clientId = "whatscooking-acf72f4b2dbda6aa726382a398050a863025969198037206606";
        //redirectUri = "http://localhost:8080/callback";
        redirectUri = "http://127.0.0.1:8080/callback";
        authURL = "https://api.kroger.com/v1/connect/oauth2/authorize";
        redirectAttributes.addAttribute("scope",scope);
        redirectAttributes.addAttribute("response_type","code");
        redirectAttributes.addAttribute("client_id",clientId);
        redirectAttributes.addAttribute("redirect_uri",redirectUri);
        redirectAttributes.addAttribute("state", "Testing");
        return new RedirectView(authURL);
    }

    @GetMapping("/callback")
    public String getToken(@RequestParam("code") String code){
        System.out.println("The code is as follows");
        System.out.println(code);
        KrogerCustomer krogerCustomer;
        krogerCustomer = krogerService.getAccessToken(code);
        krogerService.orderProducts(krogerCustomer.getAccess_token(),"0001111041700");
        return "redirect:home";
    }
}
