package com.tailoric.bracketbattlegrounds;

import com.tailoric.bracketbattlegrounds.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@SpringBootApplication
public class BracketBattlegroundsApplication extends WebSecurityConfigurerAdapter {

    @Autowired
	AccountService accountService;
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BracketBattlegroundsApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
				.authorizeRequests(a -> a
					.antMatchers(
							"/",
							"/error",
							"/login**",
							"/script/**",
							"/style/**",
							"/media/**"
					).permitAll()
					.anyRequest().authenticated()
				)
				.exceptionHandling(e -> e
				.authenticationEntryPoint(new Http403ForbiddenEntryPoint())
				)
				.oauth2Login( o -> o
						.failureUrl("/error")
                        .userInfoEndpoint(userInfo -> userInfo
								.userService(this.oAuth2UserService())
						)
				);
	}
	private OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(){
		final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
		return (userRequest) ->{
			OAuth2User user = delegate.loadUser(userRequest);
			OAuth2AccessToken token = userRequest.getAccessToken();
			accountService.createAccountIfNotExists(user, token);
			return user;
		};
	}

}