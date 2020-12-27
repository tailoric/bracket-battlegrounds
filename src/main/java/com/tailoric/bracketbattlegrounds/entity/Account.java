package com.tailoric.bracketbattlegrounds.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Account {

    public Account(String discordId, String discordToken, String redditId, String redditToken, String username) {
        this.discordId = discordId;
        this.discordToken = discordToken;
        this.redditId = redditId;
        this.redditToken = redditToken;
        this.username = username;
    }

    public static Account buildDiscordAccount(String discordId, String discordToken, String username) {
        return new Account(discordId, discordToken, null, null, username);
    }
    public static Account buildRedditAccount(String redditId, String redditToken, String username) {
        return new Account(null, null, redditId, redditToken, username);
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(unique = true)
    private String discordId;
    private String discordToken;
    @Column(unique = true)
    private String redditId;
    private String redditToken;

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

    public String getDiscordToken() {
        return discordToken;
    }

    public void setDiscordToken(String discordToken) {
        this.discordToken = discordToken;
    }

    public String getRedditToken() {
        return redditToken;
    }

    public void setRedditToken(String redditToken) {
        this.redditToken = redditToken;
    }

    protected Account() {

    }
}