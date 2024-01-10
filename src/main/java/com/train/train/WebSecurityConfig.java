package com.train.train;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/passengers.html").hasRole("ADMIN") // Allow only ADMIN to access admin.html
                    .requestMatchers("/trains.html").hasRole("USER") // Allow only USER to access user.html
                    .requestMatchers("/", "/home").hasAnyRole("USER", "ADMIN") // Allow both USER and ADMIN
                    .requestMatchers("/signup1", "/signup2").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                    .loginPage("/login")
                    .successHandler((request, response, authentication) -> {
        // Mengambil authorities (roles) dari pengguna yang baru login
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Mengecek apakah pengguna memiliki peran ADMIN
        if (authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            // Jika pengguna adalah ADMIN, arahkan ke URL untuk ADMIN
            response.sendRedirect("/passengers");
        } else {
            // Jika pengguna bukan ADMIN, arahkan ke URL default yang lain
            response.sendRedirect("/trains");
        }
    })
    .permitAll()
                )
                .logout((logout) -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll()
                );
    
            return http.build();
        }
    
        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails user = User.builder()
                .username("user")
                .password(this.passwordEncoder().encode("password"))
                .roles("USER")
                .build();
    
            UserDetails admin = User.builder()
                .username("admin")
                .password(this.passwordEncoder().encode("adminPassword"))
                .roles("ADMIN")
                .build();
    
            return new InMemoryUserDetailsManager(user, admin);
        }
    
        @Bean
        @Primary
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
}
