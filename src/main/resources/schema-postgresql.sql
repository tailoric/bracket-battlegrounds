CREATE TABLE IF NOT EXISTS account(
    id uuid NOT NULL PRIMARY KEY,
    discord_id VARCHAR(255),
    reddit_id VARCHAR(255),
    username VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS bracket (
    id uuid not null primary key,
    description text NOT NULL,
    rules text not null,
    title text not null,
    creator_id uuid not null,
    constraint fk_creator
        FOREIGN KEY (creator_id)
            references account(id)
)