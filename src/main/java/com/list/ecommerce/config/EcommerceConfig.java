package com.list.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EcommerceConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/criar").permitAll()
                        .requestMatchers("/usuario/buscar").permitAll()
                        .requestMatchers("/usuario/buscar/{id}").permitAll()
                        .requestMatchers("/usuario/atualizar/").permitAll()
                        .requestMatchers("/usuario/deletar/").permitAll()
                        .requestMatchers("/produto/buscar").permitAll()
                        .requestMatchers("/produto/buscar/{id}").permitAll()
                        .requestMatchers("/pedido/buscar").permitAll()
                        .requestMatchers("/pedido/criar").permitAll()
                        .requestMatchers("/pagamento/buscar/").permitAll()

                        .requestMatchers("/produto/atualizar/").hasRole("ADMIN")
                        .requestMatchers("/produto/deletar/").hasRole("ADMIN")
                        .requestMatchers("/pedido/deletar/").hasRole("ADMIN")
                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{

        return config.getAuthenticationManager();
    }
}
