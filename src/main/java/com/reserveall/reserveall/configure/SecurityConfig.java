package com.reserveall.reserveall.configure;

import com.reserveall.reserveall.security.JWTFilter;
import com.reserveall.reserveall.security.JwtAccessDeniedHandler;
import com.reserveall.reserveall.security.JwtAuthenticationEntryPoint;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JWTFilter jwtFilter;
    private final JwtAccessDeniedHandler accessDeniedHandler;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(JWTFilter jwtFilter, JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.accessDeniedHandler = jwtAccessDeniedHandler;
        this.authenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            @NotNull final AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests( auth->{
                    auth.requestMatchers("/api/v1/project/admin/**",
                            "/api/v1/image/admin/**" ,
                                    "/api/v1/clientMessageController/admin/**",
                                    "/api/v1/user/createUser")
                            .hasAuthority("ADMIN")
                            .anyRequest().authenticated()
                            .requestMatchers("/api/v1/project/getAllProjects", "/api/v1/project/getProjectsByName",
                                    "/api/v1/image/getImagesByProject","/api/v1/clientMessageController/createMessage",
                                    "/api/auth/login")
                            .permitAll();
                })
                .formLogin(fl->fl.disable())
                .httpBasic(httpBasic->httpBasic.disable())
                .exceptionHandling(handling -> handling
                        .accessDeniedHandler(accessDeniedHandler)
                        .authenticationEntryPoint(authenticationEntryPoint) )
                .sessionManagement(sess-> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    //    this bean deactivate securityFilterChain for login
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return(web -> web.ignoring()
//                .requestMatchers("/api/v1/project/getAllProjects", "/api/v1/project/getProjectsByName",
//                        "/api/v1/image/getImagesByProject","/api/v1/clientMessageController/createMessage",
//                        "/api/auth/login"));
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}
