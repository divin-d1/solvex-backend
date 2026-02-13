package com.solvex.security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity https) throws Exception{
        https
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->auth

                        //Those are open for all users
                        .requestMatchers("/api/auth/**").permitAll()

                        // Problems are also open
                        .requestMatchers("/api/problems/**").permitAll()

                        //View projects which are public
                        .requestMatchers(HttpMethod.GET,"/api/projects/**").permitAll()

                        // protect route of projects
                        .requestMatchers(HttpMethod.POST, "/api/projects/**").hasRole("INNOVATOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasRole("INNOVATOR")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasRole("INNOVATOR")

                        //any other request requires authentication
                        .anyRequest().permitAll()

                )
                .httpBasic(basic-> basic.disable());
        return https.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
