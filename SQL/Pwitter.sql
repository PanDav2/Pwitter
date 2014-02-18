SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Images`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Images` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `url` VARCHAR(100) NOT NULL ,
  `tinyUrl` VARCHAR(45) NOT NULL ,
  `created` MEDIUMINT NOT NULL ,
  `desc` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Users`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `firstname` VARCHAR(45) NOT NULL ,
  `lastname` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(70) NOT NULL ,
  `isValid` VARCHAR(45) NOT NULL ,
  `created` MEDIUMINT NOT NULL ,
  `lastUpdate` MEDIUMINT NOT NULL ,
  `lastModify` MEDIUMINT NOT NULL ,
  `image_id` INT NOT NULL ,
  `sessionKey` VARCHAR(255) NOT NULL ,
  `sesssionTime` MEDIUMINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  INDEX `fk_Users_table1_idx` (`image_id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  CONSTRAINT `fk_Users_table1`
    FOREIGN KEY (`image_id` )
    REFERENCES `mydb`.`Images` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Pwitts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Pwitts` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(45) NULL ,
  `content` VARCHAR(255) NOT NULL ,
  `created` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Pwitts_Users1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Pwitts_Users1`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Images_Pwitts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Images_Pwitts` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `image_id` INT NOT NULL ,
  `pwitt_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Images_Pwitts_Images1_idx` (`image_id` ASC) ,
  INDEX `fk_Images_Pwitts_Pwitts1_idx` (`pwitt_id` ASC) ,
  CONSTRAINT `fk_Images_Pwitts_Images1`
    FOREIGN KEY (`image_id` )
    REFERENCES `mydb`.`Images` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Images_Pwitts_Pwitts1`
    FOREIGN KEY (`pwitt_id` )
    REFERENCES `mydb`.`Pwitts` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Hashtags`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Hashtags` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(45) NOT NULL ,
  `created` MEDIUMINT NOT NULL ,
  `created_by` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `UNIQUE` (`title` ASC) ,
  INDEX `fk_Hashtags_Users1_idx` (`created_by` ASC) ,
  CONSTRAINT `fk_Hashtags_Users1`
    FOREIGN KEY (`created_by` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Hashtages_Pwitts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Hashtages_Pwitts` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `hashtag_id` INT NOT NULL ,
  `pwitt_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Hashtages_Pwitts_Pwitts1_idx` (`pwitt_id` ASC) ,
  INDEX `fk_Hashtages_Pwitts_Hashtags1_idx` (`hashtag_id` ASC) ,
  CONSTRAINT `fk_Hashtages_Pwitts_Pwitts1`
    FOREIGN KEY (`pwitt_id` )
    REFERENCES `mydb`.`Pwitts` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hashtages_Pwitts_Hashtags1`
    FOREIGN KEY (`hashtag_id` )
    REFERENCES `mydb`.`Hashtags` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Followers`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Followers` (
  `id` INT NOT NULL ,
  `user_id` INT NOT NULL ,
  `follower_id` INT NOT NULL ,
  `created` MEDIUMINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Followers_Users1_idx` (`user_id` ASC) ,
  INDEX `fk_Followers_Users2_idx` (`follower_id` ASC) ,
  CONSTRAINT `fk_Followers_Users1`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Followers_Users2`
    FOREIGN KEY (`follower_id` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Albums`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Albums` (
  `id` INT NOT NULL ,
  `title` VARCHAR(45) NOT NULL ,
  `desc` TEXT NOT NULL ,
  `Users_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Albums_Users1_idx` (`Users_id` ASC) ,
  CONSTRAINT `fk_Albums_Users1`
    FOREIGN KEY (`Users_id` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Albums_Images`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Albums_Images` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `album_id` INT NOT NULL ,
  `image_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Albums_Images_Albums1_idx` (`album_id` ASC) ,
  INDEX `fk_Albums_Images_Images1_idx` (`image_id` ASC) ,
  CONSTRAINT `fk_Albums_Images_Albums1`
    FOREIGN KEY (`album_id` )
    REFERENCES `mydb`.`Albums` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Albums_Images_Images1`
    FOREIGN KEY (`image_id` )
    REFERENCES `mydb`.`Images` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Notifs`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `mydb`.`Notifs` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `content` VARCHAR(255) NOT NULL ,
  `isViewed` TINYINT NOT NULL ,
  `user_id` INT NOT NULL ,
  `created` MEDIUMINT NOT NULL ,
  `viewed` MEDIUMINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Notifs_Users1_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_Notifs_Users1`
    FOREIGN KEY (`user_id` )
    REFERENCES `mydb`.`Users` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
