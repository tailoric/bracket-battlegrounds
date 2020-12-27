package com.tailoric.bracketbattlegrounds.service;

import com.tailoric.bracketbattlegrounds.entity.Account;
import com.tailoric.bracketbattlegrounds.repository.AccountRepository;
import com.tailoric.bracketbattlegrounds.service.interfaces.IAccountService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService implements IAccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccountIfNotExists(OAuth2User user, OAuth2AccessToken token) {
        var discordId = (String) user.getAttribute("id");
        if(repository.existsAccountByDiscordId(discordId)){
            var existingUser =  repository.findByDiscordId(discordId);
            existingUser.setDiscordToken(token.getTokenValue());
            repository.save(existingUser);
            return existingUser;
        }
        var newUser = Account.buildDiscordAccount(discordId, token.getTokenValue(),user.getAttribute("username"));
        repository.save(newUser);
        return newUser;
    }

    @Override
    public Account getUserByDiscordId(String discordId) {
        return repository.findByDiscordId(discordId);
    }

    @Override
    public Account getUserById(UUID id) {
        return repository.findById(id);
    }
}
