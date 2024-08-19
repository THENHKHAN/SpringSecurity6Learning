package com.nhkhan.SpringbootSecurityLearning.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/security")
public class HomeController {
   @GetMapping("/home")
    public String myHome(){
       return "Hello Noorul, This is home page ";
   }

   // path-Variable : passing value in the url path is mandatory
   @GetMapping("/home/path-variable/{user-name}")
    public String myHomePathVariable(@PathVariable(value = "user-name") String userName){
           return String.format("Hello %s, This is home page " , userName);
   }

//    // Query params -  passing value in the url path is NOT mandatory
    @GetMapping("/home/query-params/")
//    public String myHomeQueryParams(@RequestParam(name = "user-name" , defaultValue = "Kaizen", required = false) Optional<String> userName){ // required = false by default it's true
    public String myHomeQueryParams(@RequestParam(name = "user-name", required = false) Optional<String> userName){ // required = false by default it's true
       if(userName.isPresent()){
           return String.format("Hello %s, This is home page " , userName.get());
       } else {
           return String.format("No data has been passed in query params");
       }
       // can be replaced above two line with one line - ex:
        // return userName.map(s -> String.format("Hello %s, This is home page ", s)).orElseGet(() -> String.format("No data has been passed in query params"));
    }


    @GetMapping("/get-session-id")
    public String getSessionId(HttpServletRequest request){
            String sessionId = request.getSession().getId();
            System.out.println("Your sessionId : " + sessionId);
            return "Your sessionId : " + sessionId;
    }

    @GetMapping("/csrf-token")
    public  Map<String, String> getCsrfToken(HttpServletRequest request){
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        // return csrfToken; - if we return csrfToken then it will be returned as a  CsrfToken object - with key - {parameterName:_csrf, token:<token-alphanumeric>, headerName:X-CSRF-TOKEN}
        Map<String, String> csrfTokenMapCheck = new HashMap<>();
        if (csrfToken==null) {
            System.out.println(" csrf token NULL");
            csrfTokenMapCheck.put("csrfToken", "null");
            return csrfTokenMapCheck;
        } else if(csrfToken.toString().isEmpty()){
            System.out.println(" No csrf token Got");
            csrfTokenMapCheck.put("csrfToken", "Empty csrf token ");
            return csrfTokenMapCheck;
        } else {
            System.out.println("Your csrfToken : " + csrfToken.getToken());
            csrfTokenMapCheck.put("csrfToken", String.valueOf(csrfToken.getToken()));
            return csrfTokenMapCheck;
        }


    }
}
