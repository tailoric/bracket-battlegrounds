package com.tailoric.bracketbattlegrounds.repository;

import com.tailoric.bracketbattlegrounds.entity.Bracket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BracketRepository extends CrudRepository<Bracket, Long> {
    Bracket findById(UUID id);
}
