package pl.borecki.imageUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.borecki.imageUploader.model.AppUser;
import pl.borecki.imageUploader.repo.AppUserRepo;

@Configuration
@PropertySource(value={"classpath:application.properties"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser user = new AppUser("Jan", passwordEncoder().encode("123"), "ROLE_USER");
        AppUser admin = new AppUser("Admin", passwordEncoder().encode("123"), "ROLE_ADMIN");
        appUserRepo.save(user);
        appUserRepo.save(admin);
    }

}
