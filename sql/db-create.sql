-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hospital
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `hospital` ;

-- -----------------------------------------------------
-- Schema hospital
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `hospital` ;

-- -----------------------------------------------------
-- Table `hospital`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`role` ;

CREATE TABLE IF NOT EXISTS `hospital`.`role` (
  `id` INT(11) NOT NULL,
  `name` ENUM('admin', 'doctor', 'nurse', 'patient') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`locale`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`locale` ;

CREATE TABLE IF NOT EXISTS `hospital`.`locale` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` ENUM('EN', 'UA') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `hospital`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`account` ;

CREATE TABLE IF NOT EXISTS `hospital`.`account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(16) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `non_locked` TINYINT(1) NOT NULL DEFAULT 1,
  `updated_by` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `locale_id` INT(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  INDEX `fk_account_role_name_idx` (`role_id` ASC),
  INDEX `fk_account_account_id_idx` (`updated_by` ASC),
  INDEX `fk_account_locale_id_idx` (`locale_id` ASC),
  CONSTRAINT `fk_account_account_id`
    FOREIGN KEY (`updated_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `hospital`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_locale_id`
    FOREIGN KEY (`locale_id`)
    REFERENCES `hospital`.`locale` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`account_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`account_details` ;

CREATE TABLE IF NOT EXISTS `hospital`.`account_details` (
  `id` INT(11) NOT NULL,
  `name_EN` VARCHAR(45) NOT NULL,
  `surname_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  `surname_UA` VARCHAR(45) NOT NULL,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_account_details_account_id`
    FOREIGN KEY (`id`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`specialization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`specialization` ;

CREATE TABLE IF NOT EXISTS `hospital`.`specialization` (
  `id` INT(11) NOT NULL,
  `name_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name_EN` ASC),
  UNIQUE INDEX `name_UA_UNIQUE` (`name_UA` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`doctor` ;

CREATE TABLE IF NOT EXISTS `hospital`.`doctor` (
  `id` INT(11) NOT NULL,
  `specialization_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_doctor_specialization_id_idx` (`specialization_id` ASC),
  CONSTRAINT `fk_doctor_account_id`
    FOREIGN KEY (`id`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_specialization_id`
    FOREIGN KEY (`specialization_id`)
    REFERENCES `hospital`.`specialization` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`patient` ;

CREATE TABLE IF NOT EXISTS `hospital`.`patient` (
  `id` INT(11) NOT NULL,
  `birthday` DATE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_patient_account_id`
    FOREIGN KEY (`id`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`medical_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`medical_card` ;

CREATE TABLE IF NOT EXISTS `hospital`.`medical_card` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `is_discharged` TINYINT(1) NULL DEFAULT 0,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` INT(11) NOT NULL,
  `patient_id` INT(11) NOT NULL,
  `doctor_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medical_card_account_id_idx` (`updated_by` ASC),
  INDEX `fk_medical_card_patient_id_idx` (`patient_id` ASC),
  INDEX `fk_medical_card_doctor_id_idx` (`doctor_id` ASC),
  CONSTRAINT `fk_medical_card_account_id`
    FOREIGN KEY (`updated_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_card_doctor_id`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital`.`doctor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medical_card_patient_id`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital`.`patient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`diagnosis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`diagnosis` ;

CREATE TABLE IF NOT EXISTS `hospital`.`diagnosis` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  `description_EN` TEXT NULL,
  `description_UA` TEXT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `medical_card_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_diagnosis_account_id_idx` (`created_by` ASC),
  INDEX `fk_diagnosis_medical_card_id_idx` (`medical_card_id` ASC),
  CONSTRAINT `fk_diagnosis_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_diagnosis_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`medicament`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`medicament` ;

CREATE TABLE IF NOT EXISTS `hospital`.`medicament` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  `description_EN` VARCHAR(1024) NOT NULL,
  `description_UA` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `served_by` INT NULL,
  `medical_card_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_medicament_account_id_idx` (`created_by` ASC),
  INDEX `fk_medicament_medical_card_id_idx` (`medical_card_id` ASC),
  INDEX `fk_medicament_account_id_idx1` (`served_by` ASC),
  CONSTRAINT `fk_medicament_created_by_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicament_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medicament_served_by_account_id`
    FOREIGN KEY (`served_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`procedure`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`procedure` ;

CREATE TABLE IF NOT EXISTS `hospital`.`procedure` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  `description_EN` VARCHAR(1024) NOT NULL,
  `description_UA` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `served_by` INT NULL,
  `medical_card_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_procedure_account_id_idx` (`created_by` ASC),
  INDEX `fk_procedure_medical_card_id_idx` (`medical_card_id` ASC),
  INDEX `fk_procedure_account_id_idx1` (`served_by` ASC),
  CONSTRAINT `fk_procedure_created_by_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procedure_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_procedure_served_byaccount_id`
    FOREIGN KEY (`served_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`sample`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`sample` ;

CREATE TABLE IF NOT EXISTS `hospital`.`sample` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name_EN` VARCHAR(45) NOT NULL,
  `name_UA` VARCHAR(45) NOT NULL,
  `description_EN` VARCHAR(1024) NOT NULL,
  `description_UA` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `medical_card_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sample_account_id_idx` (`created_by` ASC),
  INDEX `fk_sample_medical_card_id_idx` (`medical_card_id` ASC),
  CONSTRAINT `fk_sample_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sample_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`surgery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`surgery` ;

CREATE TABLE IF NOT EXISTS `hospital`.`surgery` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type_EN` VARCHAR(45) NOT NULL,
  `type_UA` VARCHAR(45) NOT NULL,
  `description_EN` VARCHAR(1024) NOT NULL,
  `description_UA` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `served_by` INT NULL,
  `medical_card_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_surgery_account_id_idx` (`created_by` ASC),
  INDEX `fk_surgery_medical_card_id_idx` (`medical_card_id` ASC),
  INDEX `fk_surgery_account_id_idx1` (`served_by` ASC),
  CONSTRAINT `fk_surgery_created_by_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_surgery_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_surgery_served_by_account_id`
    FOREIGN KEY (`served_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital`.`symptom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital`.`symptom` ;

CREATE TABLE IF NOT EXISTS `hospital`.`symptom` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `patient_complaints_EN` VARCHAR(1024) NULL DEFAULT NULL,
  `patient_complaints_UA` VARCHAR(1024) NULL DEFAULT NULL,
  `doctor_observation_EN` VARCHAR(1024) NOT NULL,
  `doctor_observation_UA` VARCHAR(1024) NOT NULL,
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` INT(11) NOT NULL,
  `medical_card_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_symptom_account_id_idx` (`created_by` ASC),
  INDEX `fk_symptom_medical_card_id_idx` (`medical_card_id` ASC),
  CONSTRAINT `fk_symptom_account_id`
    FOREIGN KEY (`created_by`)
    REFERENCES `hospital`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_symptom_medical_card_id`
    FOREIGN KEY (`medical_card_id`)
    REFERENCES `hospital`.`medical_card` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `hospital`.`role`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `hospital`.`role` (`id`, `name`) VALUES (0, 'admin');
INSERT INTO `hospital`.`role` (`id`, `name`) VALUES (1, 'doctor');
INSERT INTO `hospital`.`role` (`id`, `name`) VALUES (2, 'nurse');
INSERT INTO `hospital`.`role` (`id`, `name`) VALUES (3, 'patient');

COMMIT;

-- -----------------------------------------------------
-- Data for table `hospital`.`locale`
-- -----------------------------------------------------
START TRANSACTION;
USE `hospital`;
INSERT INTO `hospital`.`locale` (`id`, `name`) VALUES (DEFAULT, 'EN');
INSERT INTO `hospital`.`locale` (`id`, `name`) VALUES (DEFAULT, 'UA');

COMMIT;

-- -----------------------------------------------------
-- Data for table `hospital`.`account`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `hospital`.`account` (`id`, `login`, `password`, `create_time`, `update_time`, `non_locked`, `updated_by`, `role_id`, `locale_id`) VALUES (DEFAULT, '1', '1', DEFAULT, DEFAULT, 1, 1, 0, DEFAULT);

COMMIT;