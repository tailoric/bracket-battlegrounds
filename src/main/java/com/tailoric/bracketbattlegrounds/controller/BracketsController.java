package com.tailoric.bracketbattlegrounds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("brackets")
public class BracketsController {
    @GetMapping(value = "/new")
    public String newBracketForm(){
        return "brackets/new-bracket-form";
    }
}
