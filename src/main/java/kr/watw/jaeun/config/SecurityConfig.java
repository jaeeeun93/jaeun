package kr.watw.jaeun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("adminPw")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("userPw")).roles("USER")
                .and()
                .withUser("csMonitorAdmin").password(passwordEncoder().encode("csPw")).roles("CSMONITORADMIN")
                .and()
                .withUser("sellerAdmin").password(passwordEncoder().encode("sellerPw")).roles("SELLERADMIN")
                .and()
                .withUser("userManageAdmin").password(passwordEncoder().encode("userManagePw")).roles("USERMANAGEADMIN")
                .and()
                .withUser("refundAdmin").password(passwordEncoder().encode("refundPw")).roles("REFUNDADMIN");
    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe().tokenValiditySeconds(2592000).key("mySecretKey");
   }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
