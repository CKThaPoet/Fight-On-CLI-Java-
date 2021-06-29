-- created a table for the type of role the player or enemy will be will use lateer must have priamary key for role id
CREATE TABLE roles(
role_id serial PRIMARY KEY,
role_type TEXT 
);

--data for the roles
INSERT INTO roles (role_type)
VALUES ('Human'), ('Monster'), ('Animal'), ('Undead');

--the role_type will be use for the foreign key to reference between the player and the enemies
CREATE TABLE player(
player_id serial PRIMARY KEY,
player_name TEXT,
role_type_fk int REFERENCES roles(role_id),
role_type TEXT
);
	
--double check what I should put in the first two sections as placeholders
INSERT INTO player (player_name, role_type_fk, role_type)
VALUES ('The Conqueror', 1, 'Human'), 
			 ('The Savage', 2, 'Monster'),
			 ('The Beast', 3, 'Animal'),
			 ('The Ghoul', 4, 'Undead');

SELECT * FROM player;
SELECT enemy_name, role_type FROM enemies;
TRUNCATE enemies;
DROP TABLE enemies;

--I will comment out the loot for now until I add the loot
CREATE TABLE enemies(
enemy_id serial PRIMARY KEY,
enemy_name TEXT,
role_type_fk int REFERENCES roles(role_id),
role_type TEXT
);

INSERT INTO enemies(enemy_name , role_type_fk, role_type )
VALUES('BodyGuard', 1,'Human'),
			('Brawler', 1,'Human'),
			('Ninja', 1,'Human'),
			('Orc',2, 'Monster'),
			('Troll', 2,'Monster'),
			('Dog', 3,'Animal'),
			('Skeleton', 4,'Undead');
		
--table for my loot just inscae I add it to the game but I will at least make the inserts for it if I have time to add this feature
--CREATE TABLE loot(
--loot_id serial PRIMARY KEY,
--loot_name TEXT
--);
--inserts for the loot that most like wont make it into the game
--INSERT INTO loot (loot_name)
--VALUES ('Gold Coin(s)'), ('Silver Necklace(s)'), ('Platium Bar(s)'), ('Scared Totem(s)');













