package com.epsychiatry.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/assets/**", "/plugins/**").permitAll()
                    .antMatchers("/avatar/**").permitAll() // internal resources
                    .antMatchers("/login/**").permitAll()
                    .antMatchers(
                            "/admin/dashboard/**",

                            "/admin/module/**",

                            "/admin/facebook/update/token/**",
                            "/admin/facebook/update/insights/**",

                            "/admin/employee/management/group/add/**",
                            "/admin/employee/management/group/delete/**",
                            "/admin/employee/management/group/edit/**",
                            "/admin/employee/management/group/**",
                            "/admin/employee/management/roles/assignment/**",

                            "/admin/employee/management/module/add/**",
                            "/admin/employee/management/role/delete/**",
                            "/admin/employee/management/role/edit/**",
                            "/admin/employee/management/role/add/**",
                            "/admin/employee/management/roles/**",

                            "/admin/employee/management/emp/add/**",
                            "/admin/employee/management/emp/edit/**",
                            "/admin/employee/management/emp/delete/**",
                            "/admin/employee/management/emp/**",

                            "/admin/employee/management/auth/edit/**",

                            "/admin/hr/management/leave/view/**",
                            "/admin/hr/management/leave/cancel/**",
                            "/admin/hr/management/leave/review/**",
                            "/admin/hr/management/leaves/all/**",
                            "/admin/hr/management/leave/**"

                    )
                        .hasAuthority("ROLE_LOGIN_EPS")

                    .antMatchers("/admin/facebook/update/token/**").hasAuthority("ROLE_FB_GET_TOKEN")
                    .antMatchers("/admin/facebook/update/insights/**").hasAuthority("ROLE_FB_SAVE_INSIGHTS")
                    .antMatchers("/api/v0/facebook/insights/**").hasAuthority("ROLE_FB_SAVE_INSIGHTS")

                    .antMatchers("/admin/employee/management/group/add/**").hasAuthority("ROLE_EMP_MANG_GROUP_ADD")
                    .antMatchers("/admin/employee/management/group/delete/**").hasAuthority("ROLE_EMP_MANG_GROUP_DELETE")
                    .antMatchers("/admin/employee/management/roles/assignment/**").hasAuthority("ROLE_EMP_MANG_ROLE_ASSIGN")
                    .antMatchers("/admin/employee/management/group/edit/**").hasAuthority("ROLE_EMP_MANG_GROUP_EDIT")
                    .antMatchers("/admin/employee/management/group/**").hasAuthority("ROLE_EMP_MANG_USER_GROUP")

                    .antMatchers("/admin/employee/management/module/add/**").hasAuthority("ROLE_EMP_MANG_MODULE_ADD")
                    .antMatchers("/admin/employee/management/role/delete/**").hasAuthority("ROLE_EMP_MANG_ROLE_DELETE")
                    .antMatchers("/admin/employee/management/role/edit/**").hasAuthority("ROLE_EMP_MANG_ROLE_EDIT")
                    .antMatchers("/admin/employee/management/role/add/**").hasAuthority("ROLE_EMP_MANG_ROLE_ADD")
                    .antMatchers("/admin/employee/management/roles/**").hasAuthority("ROLE_EMP_MANG_ROLE")

                    .antMatchers("/admin/employee/management/emp/add/**").hasAuthority("ROLE_EMP_MANG_MODULE_ADD")
                    .antMatchers("/admin/employee/management/emp/edit/**").hasAuthority("ROLE_EMP_MANG_EMPLOYEE_EDIT")
                    .antMatchers("/admin/employee/management/emp/delete/**").hasAuthority("ROLE_EMP_MANG_EMPLOYEE_DELETE")
                    .antMatchers("/admin/employee/management/emp/**").hasAuthority("ROLE_EMP_MANG_EMP_MANAGE")


                    .antMatchers("/admin/employee/management/auth/edit/**").hasAuthority("ROLE_AUTH_EDIT")

                    .antMatchers("/admin/hr/management/leave/view/**").hasAuthority("ROLE_HR_LEAVE_VIEW")
                    .antMatchers("/admin/hr/management/leave/cancel/**").hasAuthority("ROLE_HR_LEAVE_CLOSED")
                    .antMatchers("/admin/hr/management/leave/review/**").hasAuthority("ROLE_HR_LEAVE_REVIEW")
                    .antMatchers("/admin/hr/management/leaves/all/**").hasAuthority("ROLE_HR_VIEW_ALL_LEAVES_VIEW")
                    .antMatchers("/admin/hr/management/leave/**").hasAuthority("ROLE_HR_LEAVE_MANAGE_VIEW")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/perform_login")
                    .failureUrl("/login?error=true")
                    .permitAll()
                    .defaultSuccessUrl("/admin/dashboard", true)

                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .logoutRequestMatcher(new AntPathRequestMatcher("/perform_logout", "GET"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()

                .and()
                .rememberMe()
                .key("test@123")
                .tokenRepository(tokenRepository())

                .and()
                .exceptionHandling().accessDeniedPage("/forbidden");




    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl token = new JdbcTokenRepositoryImpl();
        token.setDataSource(dataSource);
        return token;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new EmpUserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
