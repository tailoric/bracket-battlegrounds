package com.tailoric.bracketbattlegrounds.service.interfaces;

import com.tailoric.bracketbattlegrounds.entity.Account;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.UUID;

public interface IAccountService {
    Account createAccountIfNotExists(OAuth2User user, OAuth2AccessToken token);
    Account getUserByDiscordId(String discordId);
    Account getUserById(UUID id);

}
