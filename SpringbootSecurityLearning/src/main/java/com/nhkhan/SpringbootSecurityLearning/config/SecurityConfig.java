package com.nhkhan.SpringbootSecurityLearning.config;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// this will disable the default spring security configuration- Only this below code now there will be no security But will add our custom security
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain  SecurityFilterChain (HttpSecurity http) throws Exception {
//
//      return  http.build();
//    }
//}

@Configuration // by this spring security will consider this is the security configuration file
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{

        http.csrf(customizer-> customizer.disable()); // disabling the csrf token
        http.authorizeHttpRequests(authorize->
                    authorize.anyRequest().authenticated()); // enabling security
        // now there is no way to fill the user creds in ui and on Postman we have But here in we are not handling
        // but lets add formLogin to add a form so that user can enter on browser
//        http.formLogin(Customizer.withDefaults()); // now this will provide a form on UI - but still we won't be able to access from postman(because that can send http request - we only get in response as html code) so for that will do below code to allow the login/access from postman as well as brower
        http.httpBasic(Customizer.withDefaults()); // this single one will from postman as well as browser but if we use only above then only from browser.
        http.sessionManagement(session->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // this IS USED  to not let the browser store the sessionid because if user have session id the he/she can use that get the user detail and can be misuse- But in this sessionid is useless because eahc time resource want to access the page will genrate the new id - we have to comment http.formLogin(Customizer.withDefaults());
        return http.build();
    }
}
