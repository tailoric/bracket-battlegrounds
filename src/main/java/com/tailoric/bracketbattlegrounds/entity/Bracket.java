package com.tailoric.bracketbattlegrounds.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Bracket {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name="id", updatable = false, nullable = false)
    private UUID id;
    @Column(name="title", nullable = false)
    @Size(min=1)
    private String title;
    @Size(min=1)
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="rules")
    private String rules;

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getRules() {
        return rules;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Bracket() {

    }

    public Bracket(String title, String description, String rules) {
        this.title = title;
        this.description = description;
        this.rules = rules;
    }

    public UUID getId() {
        return this.id;
    }
}