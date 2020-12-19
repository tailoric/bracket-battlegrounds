package com.tailoric.bracketbattlegrounds.service.interfaces;

import com.tailoric.bracketbattlegrounds.entity.Bracket;

import java.util.UUID;

public interface IBracketService {
    Bracket createNewBracket(String title, String description, String rules);
    Bracket createNewBracket(Bracket bracket);
    Bracket findById(UUID id);
}
