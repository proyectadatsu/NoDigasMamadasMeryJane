package com.pe.Ferreteria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails cliente = User.builder()
                .username("cliente")
                .password(passwordEncoder.encode("cliente123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, cliente);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos públicos
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/h2-console/**").permitAll()
                // Vista y API pública de Login
                .requestMatchers("/login").permitAll()
                // Rutas de administración exclusivas para ROLE_ADMIN
                .requestMatchers("/productos/nuevo", "/productos/editar/**", "/productos/guardar", "/productos/actualizar/**", "/productos/eliminar/**").hasRole("ADMIN")
                // Rutas de lectura permitidas para ROLE_USER y ROLE_ADMIN
                .requestMatchers("/productos", "/ferreteria/productos/**").hasAnyRole("USER", "ADMIN")
                // Cualquier otra solicitud requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/productos", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            // Permitir H2 Console en frames
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/ferreteria/productos/**", "/productos/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}
