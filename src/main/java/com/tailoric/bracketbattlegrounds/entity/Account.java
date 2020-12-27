package com.tailoric.bracketbattlegrounds.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Account {
    public Account(String discordId, String redditId, String username) {
        this.discordId = discordId;
        this.redditId = redditId;
        this.username = username;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String discordId;
    private String redditId;

    private String username;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
    private List<Bracket> myBrackets;

    public List<Bracket> getAdminOf() {
        return adminOf;
    }

    public void setAdminOf(List<Bracket> adminOf) {
        this.adminOf = adminOf;
    }

    @ManyToMany(mappedBy = "administrators")
    private List<Bracket> adminOf;

    public String getDiscordId() {
        return discordId;
    }

    public void setDiscordId(String discordId) {
        this.discordId = discordId;
    }

    public String getRedditId() {
        return redditId;
    }

    public void setRedditId(String redditId) {
        this.redditId = redditId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Bracket> getMyBrackets() {
        return myBrackets;
    }

    public void setMyBrackets(List<Bracket> myBrackets) {
        this.myBrackets = myBrackets;
    }


    protected Account() {

    }
}