package com.tailoric.bracketbattlegrounds.controller;

import com.tailoric.bracketbattlegrounds.entity.Bracket;
import com.tailoric.bracketbattlegrounds.service.interfaces.IAccountService;
import com.tailoric.bracketbattlegrounds.service.interfaces.IBracketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("brackets")
public class BracketsController {
    final IBracketService bracketService;
    final IAccountService accountService;

    public BracketsController(IBracketService bracketService, IAccountService accountService) {
        this.bracketService = bracketService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/{id}")
    public String bracketPage(@PathVariable("id") UUID id, Model model, @AuthenticationPrincipal OAuth2User principal){
        Bracket bracket = bracketService.findById(id);
        model.addAttribute("bracket", bracket);
        model.addAttribute("username", principal.getAttribute("username"));
        return "brackets/bracket-page";
    }

    @GetMapping(value = "/new")
    public String newBracketForm(Model model, @AuthenticationPrincipal OAuth2User principal){
        Bracket bracket = new Bracket();
        model.addAttribute("bracket", bracket);
        return "brackets/new-bracket-form";
    }
    @PostMapping(
            value = "/new",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public
    ModelAndView createNewBracket(@Validated final Bracket bracket, BindingResult bindingResult, @AuthenticationPrincipal OAuth2User principal){
        var modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("brackets/new-bracket-form");
            modelAndView.setStatus(HttpStatus.BAD_REQUEST);
            modelAndView.addObject("bracket", bracket);
            return modelAndView;
        }
        var discordId = (String) principal.getAttribute("id");
        var creator = Optional.ofNullable(accountService.getUserByDiscordId(discordId));
        if (creator.isEmpty()){
            modelAndView.setViewName("redirect:/oauth2/authorization/discord");
            return modelAndView;
        }
        Optional<Bracket> created = Optional.ofNullable(bracketService.createNewBracket(bracket, creator.get()));
        if(created.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Form input");
        }
        var actualCreated = created.get();
        modelAndView.addObject("bracket", created);
        modelAndView.setViewName("redirect:/brackets/"+actualCreated.getId());

        return modelAndView;
    }
}
