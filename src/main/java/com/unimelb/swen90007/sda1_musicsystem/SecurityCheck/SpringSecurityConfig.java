package com.unimelb.swen90007.sda1_musicsystem.SecurityCheck;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf().disable() //uncomment to disable csrf
                .authorizeHttpRequests()
                .requestMatchers("/index*").authenticated()


                .requestMatchers("/triggertestAdd").permitAll()
                .requestMatchers("/triggertestUpdate").permitAll()
                .requestMatchers("/triggertestAddUpdate").permitAll()

                .requestMatchers("/viewuser*").hasRole("Admin")
                .requestMatchers("/createuser*").hasRole("Admin")
                .requestMatchers("/CreateUser*").hasRole("Admin")
                .requestMatchers("/CreateUser*").hasRole("Admin")
                .requestMatchers("/assignevents*").hasRole("Admin")
                .requestMatchers("/CreateVenue*").hasRole("Admin")
                .requestMatchers("/createvenue*").hasRole("Admin")
                .requestMatchers("/viewevents*").hasAnyRole("Admin","User","EventPlanner")
                .requestMatchers("/OrderView*").hasAnyRole("Admin","User")
                .requestMatchers("/eventView*").hasAnyRole("Admin","User")
                .requestMatchers("/Ticket*").hasAnyRole("Admin","User")

                .requestMatchers("/updateEvent*").hasAnyRole("User","EventPlanner")
                .requestMatchers("/EventsView*").hasRole("User")
                .requestMatchers("/BuyEventsView*").hasRole("User")
                .requestMatchers("/TicketView*").hasRole("User")


                .requestMatchers("/eventplanner*").hasRole("EventPlanner")
                .requestMatchers("/eventPlannerCreateEvent*").hasRole("EventPlanner")
                .requestMatchers("/eventPlannerPage*").hasRole("EventPlanner")
                .requestMatchers("/AssignEvent*").hasRole("EventPlanner")
                .requestMatchers("/viewallbooking*").hasRole("EventPlanner")
                .requestMatchers("/viewbookings*").hasRole("EventPlanner")
                .requestMatchers("/viewticket*").hasRole("EventPlanner")

                .requestMatchers("/Navigate*").permitAll()
                .requestMatchers("/SourceFile/*").permitAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/Navigate", true)
                .and()
                .csrf().disable()
                .authenticationManager(http
                        .getSharedObject(AuthenticationManagerBuilder.class)
                        .userDetailsService(new UserWithRoleServiceImpl())
                        .passwordEncoder(new BCryptPasswordEncoder()).and().build())
                .logout();
        return http.build();

    }

  /* @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .parentAuthenticationManager(null)
                .userDetailsService(new UserDetailServiceImpl())
                .passwordEncoder(new BCryptPasswordEncoder()).and().build();
    }*/



}