package com.tailoric.bracketbattlegrounds.service;

import com.tailoric.bracketbattlegrounds.entity.Bracket;
import com.tailoric.bracketbattlegrounds.repository.BracketRepository;
import com.tailoric.bracketbattlegrounds.service.interfaces.IBracketService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BracketService implements IBracketService {
    BracketRepository repository;
    public BracketService(BracketRepository repository){
        this.repository = repository;
    }

    @Override
    public Bracket createNewBracket(Bracket bracket) {
        return repository.save(bracket);
    }

    @Override
    public Bracket findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Bracket createNewBracket(String title, String description, String rules) {
        Bracket newBracket = new Bracket(title, description, rules);
        repository.save(newBracket);
        return newBracket;
    }
}
