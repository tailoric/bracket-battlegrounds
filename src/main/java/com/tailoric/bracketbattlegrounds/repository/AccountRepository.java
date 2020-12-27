package com.tailoric.bracketbattlegrounds.repository;

import com.tailoric.bracketbattlegrounds.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findById(UUID id);
    Account findByDiscordId(String discordId);
    Account findByUsername(String username);
    boolean existsAccountByDiscordId(String discordId);
}
