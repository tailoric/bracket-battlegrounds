package com.tailoric.bracketbattlegrounds.entity;

import lombok.Getter;
import lombok.Setter;
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
    @Getter
    @Setter
    @Column(unique = true)
    private String discordId;
    @Getter
    @Setter
    private String discordToken;
    @Column(unique = true)
    @Getter
    @Setter
    private String redditId;
    @Getter
    @Setter
    private String redditToken;

    @Getter
    @Setter
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

    protected Account() {

    }
}