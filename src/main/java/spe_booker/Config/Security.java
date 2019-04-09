package spe_booker.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    public static final String REALM_NAME = "SPE";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, role from user where username=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/images/**", "/webjars/**","/templates/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/statistics").access("hasAuthority('admin')")
                    .antMatchers("/users").access("hasAuthority('admin')")
                    .antMatchers("/user/*").access("hasAuthority('admin')")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").defaultSuccessUrl("/home", true).failureUrl("/login-error.html").permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll()
        ;
    }
    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationEntryPoint authEntryPoint;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            System.out.println(auth.jdbcAuthentication());
            System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("select email, password from user where email=?"));
            System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("select email, password from user where email=?").authoritiesByUsernameQuery("select email, year from user where email=?"));
            System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("select email, password from user where email=?").authoritiesByUsernameQuery("select email, year from user where email=?").dataSource(dataSource));
            System.out.println(auth.jdbcAuthentication().usersByUsernameQuery("select email, password from user where email=?").authoritiesByUsernameQuery("select email, year from user where email=?").dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder));

                    auth.
                    jdbcAuthentication()
                    .usersByUsernameQuery("select email, password from user where email=?")
                    .authoritiesByUsernameQuery("select email, year from user where email=?")
                    .dataSource(dataSource)
                    .passwordEncoder(bCryptPasswordEncoder);
                }

            @Autowired
            private BCryptPasswordEncoder bCryptPasswordEncoder;

            @Autowired
            private DataSource dataSource;

            @Override
            protected void configure (HttpSecurity http) throws Exception {


                http.csrf().disable();
                // the ant matcher is what limits the scope of this configuration.
                http.antMatcher("/api/**").authorizeRequests()
                        .antMatchers("/api/**").authenticated()
                        .and().httpBasic().realmName(REALM_NAME)
                        .authenticationEntryPoint(authEntryPoint);
            }
    }

}
