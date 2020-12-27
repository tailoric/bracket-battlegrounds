package com.tailoric.bracketbattlegrounds.unittest;

import com.tailoric.bracketbattlegrounds.controller.BracketsController;
import com.tailoric.bracketbattlegrounds.entity.Bracket;
import com.tailoric.bracketbattlegrounds.service.BracketService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BracketsController.class)
public class BracketControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BracketService service;
    @Mock
    private Bracket returnBracket;

    @Test
    public void createNewBracketShouldReturnRedirectUrl() throws Exception{
        UUID testUUID = UUID.fromString("6467c5fc-cdc6-4683-bcbc-af3536305552");
        when(returnBracket.getId()).thenReturn(testUUID);
        when(service.createNewBracket(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(returnBracket);

        this.mockMvc.perform(post("/brackets/new")
                .param("title", "test Title")
                .param("description", "test Description")
                .param("rules", "test rules")
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        )

                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/brackets/6467c5fc-cdc6-4683-bcbc-af3536305552"));
    }
    @Test
    public void createNewBracketWithEmptyFieldsReturnError() throws Exception{
        this.mockMvc.perform(post("/brackets/new")
                .param("title", "")
                .param("description", "")
                .param("rules", "")
                .header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
        )

                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(model().errorCount(2))
                .andExpect(view().name("brackets/new-bracket-form"));
    }
}
