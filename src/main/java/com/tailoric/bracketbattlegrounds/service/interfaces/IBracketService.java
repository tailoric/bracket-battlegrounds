package com.tailoric.bracketbattlegrounds.service.interfaces;

import com.tailoric.bracketbattlegrounds.entity.Account;
import com.tailoric.bracketbattlegrounds.entity.Bracket;

import java.util.UUID;

public interface IBracketService {
    Bracket createNewBracket(String title, String description, String rules, Account creator);
    Bracket createNewBracket(Bracket bracket, Account creator);
    Bracket findById(UUID id);
}
