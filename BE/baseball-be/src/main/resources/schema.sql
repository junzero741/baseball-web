-- -----------------------------------------------------
-- Table `baseball`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`user` ;

CREATE TABLE IF NOT EXISTS `baseball`.`user` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`team` ;

CREATE TABLE IF NOT EXISTS `baseball`.`team` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`game`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`game` ;

CREATE TABLE IF NOT EXISTS `baseball`.`game` (
                                                 `id` INT NOT NULL AUTO_INCREMENT,
                                                 `game_status` VARCHAR(45) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`player` ;

CREATE TABLE IF NOT EXISTS `baseball`.`player` (
                                                   `id` INT NOT NULL AUTO_INCREMENT,
                                                   `name` VARCHAR(45) NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`game_inning`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`game_inning` ;

CREATE TABLE IF NOT EXISTS `baseball`.`game_inning` (
                                                        `game_id` INT NOT NULL,
                                                        `inning` INT NOT NULL,
                                                        `team_id` INT NOT NULL,
                                                        `score` INT NULL,
                                                        PRIMARY KEY (`game_id`, `inning`, `team_id`),
    INDEX `fk_game_has_team_game_idx` (`game_id` ASC),
    INDEX `fk_game_inning_team1_idx` (`team_id` ASC),
    CONSTRAINT `fk_game_has_team_game`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_game_inning_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`base_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`base_info` ;

CREATE TABLE IF NOT EXISTS `baseball`.`base_info` (
                                                      `game_id` INT NOT NULL,
                                                      `first_base` TINYINT NULL,
                                                      `second_base` TINYINT NULL,
                                                      `third_base` TINYINT NULL,
                                                      PRIMARY KEY (`game_id`),
    CONSTRAINT `fk_base_info_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`plate_appearance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`plate_appearance` ;

CREATE TABLE IF NOT EXISTS `baseball`.`plate_appearance` (
                                                             `plate_appearance_number` INT NOT NULL,
                                                             `hitter_id` INT NOT NULL,
                                                             `is_out` TINYINT NULL,
                                                             `game_id` INT NOT NULL,
                                                             `inning` INT NOT NULL,
                                                             `team_id` INT NOT NULL,
                                                             PRIMARY KEY (`plate_appearance_number`, `game_id`, `inning`, `team_id`),
    INDEX `fk_plate_appearance_player1_idx` (`hitter_id` ASC),
    INDEX `fk_plate_appearance_game_inning1_idx` (`game_id` ASC, `inning` ASC, `team_id` ASC),
    CONSTRAINT `fk_plate_appearance_player1`
    FOREIGN KEY (`hitter_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_plate_appearance_game_inning1`
    FOREIGN KEY (`game_id` , `inning` , `team_id`)
    REFERENCES `baseball`.`game_inning` (`game_id` , `inning` , `team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`pitch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`pitch` ;

CREATE TABLE IF NOT EXISTS `baseball`.`pitch` (
                                                  `pitch_number` INT NOT NULL,
                                                  `pitcher_id` INT NOT NULL,
                                                  `result` VARCHAR(45) NULL,
    `plate_appearance_number` INT NOT NULL,
    `game_id` INT NOT NULL,
    `inning` INT NOT NULL,
    `team_id` INT NOT NULL,
    PRIMARY KEY (`pitch_number`, `plate_appearance_number`, `game_id`, `inning`, `team_id`),
    INDEX `fk_pitch_player1_idx` (`pitcher_id` ASC),
    INDEX `fk_pitch_plate_appearance1_idx` (`plate_appearance_number` ASC, `game_id` ASC, `inning` ASC, `team_id` ASC),
    CONSTRAINT `fk_pitch_player1`
    FOREIGN KEY (`pitcher_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_pitch_plate_appearance1`
    FOREIGN KEY (`plate_appearance_number` , `game_id` , `inning` , `team_id`)
    REFERENCES `baseball`.`plate_appearance` (`plate_appearance_number` , `game_id` , `inning` , `team_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`user_control_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`user_control_team` ;

CREATE TABLE IF NOT EXISTS `baseball`.`user_control_team` (
                                                              `user_id` INT NOT NULL,
                                                              `team_id` INT NOT NULL,
                                                              PRIMARY KEY (`user_id`, `team_id`),
    INDEX `fk_user_has_team_team1_idx` (`team_id` ASC),
    INDEX `fk_user_has_team_user1_idx` (`user_id` ASC),
    CONSTRAINT `fk_user_has_team_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `baseball`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_team_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`game_lineup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`game_lineup` ;

CREATE TABLE IF NOT EXISTS `baseball`.`game_lineup` (
                                                        `game_id` INT NOT NULL,
                                                        `player_id` INT NOT NULL,
                                                        `team_id` INT NOT NULL,
                                                        PRIMARY KEY (`game_id`, `player_id`, `team_id`),
    INDEX `fk_game_has_player_player1_idx` (`player_id` ASC),
    INDEX `fk_game_has_player_game1_idx` (`game_id` ASC),
    INDEX `fk_game_has_player_team1_idx` (`team_id` ASC),
    CONSTRAINT `fk_game_has_player_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_game_has_player_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_game_has_player_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`game_has_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`game_has_team` ;

CREATE TABLE IF NOT EXISTS `baseball`.`game_has_team` (
                                                          `game_id` INT NOT NULL,
                                                          `home_team_id` INT NOT NULL,
                                                          `away_team_id` INT NOT NULL,
                                                          PRIMARY KEY (`game_id`),
    INDEX `fk_game_has_team_game1_idx` (`game_id` ASC),
    INDEX `fk_game_has_team_team1_idx` (`home_team_id` ASC),
    INDEX `fk_game_has_team_team2_idx` (`away_team_id` ASC),
    CONSTRAINT `fk_game_has_team_game1`
    FOREIGN KEY (`game_id`)
    REFERENCES `baseball`.`game` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_game_has_team_team1`
    FOREIGN KEY (`home_team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_game_has_team_team2`
    FOREIGN KEY (`away_team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `baseball`.`team_has_player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `baseball`.`team_has_player` ;

CREATE TABLE IF NOT EXISTS `baseball`.`team_has_player` (
                                                            `team_id` INT NOT NULL,
                                                            `player_id` INT NOT NULL,
                                                            PRIMARY KEY (`team_id`, `player_id`),
    INDEX `fk_team_has_player_player1_idx` (`player_id` ASC),
    INDEX `fk_team_has_player_team1_idx` (`team_id` ASC),
    CONSTRAINT `fk_team_has_player_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `baseball`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_team_has_player_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `baseball`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;
