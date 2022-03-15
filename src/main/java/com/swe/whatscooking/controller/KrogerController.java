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
        // Authorization parameters of GET request
        String authURL = "https://api.kroger.com/v1/connect/oauth2/authorize";
        String scope = "cart.basic:write";
        String clientId = "whatscooking-acf72f4b2dbda6aa726382a398050a863025969198037206606";
        String redirectUri = "http://127.0.0.1:8080/callback";
        // Adding headers to the Redirect request for Kroger permission
        redirectAttributes.addAttribute("scope",scope);
        redirectAttributes.addAttribute("response_type","code");
        redirectAttributes.addAttribute("client_id",clientId);
        redirectAttributes.addAttribute("redirect_uri",redirectUri);
        redirectAttributes.addAttribute("state", "Testing");
        // Redirecting to Kroger approval page
        return new RedirectView(authURL);
    }

    // Location where kroger returns the code
    @GetMapping("/callback")
    public String getToken(@RequestParam("code") String code){
        System.out.println("The code is as follows:");
        System.out.println(code);
        // Creating customer token container
        KrogerCustomer krogerCustomer;
        // Service that creates generates the access token with the provided code of authorization from Kroger
        krogerCustomer = krogerService.getAccessToken(code);
        // Sending request to add products into the Kroger Cart
        krogerService.orderProducts(krogerCustomer.getAccess_token(),"0001111041700");
        return "redirect:home";
    }
}
