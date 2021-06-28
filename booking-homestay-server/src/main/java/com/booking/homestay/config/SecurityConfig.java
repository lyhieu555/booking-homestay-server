package com.booking.homestay.config;


import com.booking.homestay.security.component.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**", "/api/booking/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/admin/city/**", "/api/admin/village/**"
                        , "/api/admin/district/**", "/api/admin/place/**", "/api/admin/homestay/**", "/api/admin/detailplace/**"
                        , "/api/employee/house/**", "/api/employee/detailview/**", "/api/employee/detailutility/**", "/api/admin/typeutility/**",
                        "/api/employee/booking/**", "/api/employee/post/**", "/api/admin/typepost/**", "/api/member/feedback/**")
                .permitAll()
                .antMatchers(HttpMethod.POST,
                        "/api/employee/booking/**")
                .permitAll()
                .antMatchers("/api/profile/**").hasAnyAuthority("Admin", "Employee", "Member")
                .antMatchers(HttpMethod.GET, "/api/admin/utility").hasAnyAuthority("Admin", "Employee")
                .antMatchers(HttpMethod.GET, "/api/admin/typepost").hasAnyAuthority("Admin", "Employee")
                .antMatchers("/api/employee/booking/cancellation").hasAnyAuthority("Admin", "Employee")
                .antMatchers("/api/employee/transactioninfo").hasAnyAuthority("Admin", "Employee")
                .antMatchers("/api/employee/member/**", "/api/employee/post/**").hasAnyAuthority("Admin", "Employee")
                .antMatchers("/api/admin/**").hasAnyAuthority("Admin")
                .antMatchers("/api/employee/**").hasAnyAuthority("Employee")
                .antMatchers("/api/member/**").hasAuthority("Member")
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")

                .permitAll()
                .anyRequest()
                .authenticated();
        httpSecurity.addFilterBefore(jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
