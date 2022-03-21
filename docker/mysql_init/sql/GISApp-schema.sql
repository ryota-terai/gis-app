-- MySQL Script generated by MySQL Workbench
-- Mon Mar 21 23:05:24 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GISApp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GISApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GISApp` DEFAULT CHARACTER SET utf8 ;
USE `GISApp` ;

-- -----------------------------------------------------
-- Table `GISApp`.`POINT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`POINT` (
  `POINT_ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL COMMENT '0:Open\n1:Private',
  `TYPE` VARCHAR(45) NOT NULL,
  `X` DOUBLE NOT NULL,
  `Y` DOUBLE NOT NULL,
  `Z` DOUBLE NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`POINT_ID`))
ENGINE = InnoDB
COMMENT = '地点情報の追加情報を登録するテーブル';


-- -----------------------------------------------------
-- Table `GISApp`.`FILE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`FILE` (
  `POINT_ID` INT NOT NULL,
  `FILE` MEDIUMBLOB NOT NULL COMMENT 'ファイル',
  PRIMARY KEY (`POINT_ID`),
  CONSTRAINT `fk_FILE_POINT1`
    FOREIGN KEY (`POINT_ID`)
    REFERENCES `GISApp`.`POINT` (`POINT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`INFORMATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`INFORMATION` (
  `ID_TYPE` VARCHAR(45) NOT NULL,
  `ID` INT NOT NULL,
  `NAME` VARCHAR(32) NOT NULL COMMENT '情報名',
  `TYPE` VARCHAR(20) NOT NULL COMMENT '情報種別',
  `STRING` LONGTEXT NULL,
  `NUMBER` DOUBLE NULL,
  `BOOLEAN` TINYINT NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`ID_TYPE`, `ID`, `NAME`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`GEOJSON_FILE_QUEUE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`GEOJSON_FILE_QUEUE` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  `EXPAND` TINYINT NOT NULL COMMENT 'GeoJSONの座標情報を展開するか否か',
  `GEOJSON` LONGTEXT NOT NULL COMMENT 'ファイル',
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`SHELTER_INFORMATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`SHELTER_INFORMATION` (
  `POINT_ID` INT NOT NULL,
  `PRIVATE` TINYINT NOT NULL,
  `TYPE` VARCHAR(45) NOT NULL,
  `X` DOUBLE NOT NULL,
  `Y` DOUBLE NOT NULL,
  `Z` DOUBLE NULL,
  `P20_001` LONGTEXT NULL,
  `P20_002` LONGTEXT NULL,
  `P20_003` LONGTEXT NULL,
  `P20_004` LONGTEXT NULL,
  `P20_005` DOUBLE NULL,
  `P20_006` DOUBLE NULL,
  `P20_007` DOUBLE NULL,
  `P20_008` DOUBLE NULL,
  `P20_009` DOUBLE NULL,
  `P20_010` DOUBLE NULL,
  `P20_011` DOUBLE NULL,
  `P20_012` DOUBLE NULL,
  `OPEN` TINYINT NULL,
  `COMMENT` LONGTEXT NULL,
  `UPDATE_TIME` DATETIME(3) NULL,
  PRIMARY KEY (`POINT_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`POLYGON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`POLYGON` (
  `POLYGON_ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL COMMENT '0:Open\n1:Private',
  `TYPE` VARCHAR(45) NOT NULL,
  `POLYGON` JSON NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`POLYGON_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`POLYGON_GEOMETRY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`POLYGON_GEOMETRY` (
  `POLYGON_ID` INT NOT NULL,
  `POLYGON_INDEX` INT NOT NULL,
  `POINT_INDEX` INT NOT NULL,
  `POINT_ID` INT NOT NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`POLYGON_ID`, `POLYGON_INDEX`, `POINT_INDEX`),
  INDEX `fk_POLYGON_GEOMETRY_POINT1_idx` (`POINT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_POLYGON_GEOMETRY_POLYGON1`
    FOREIGN KEY (`POLYGON_ID`)
    REFERENCES `GISApp`.`POLYGON` (`POLYGON_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_POLYGON_GEOMETRY_POINT1`
    FOREIGN KEY (`POINT_ID`)
    REFERENCES `GISApp`.`POINT` (`POINT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`MULTI_POLYGON`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`MULTI_POLYGON` (
  `MULTI_POLYGON_ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL COMMENT '0:Open\n1:Private',
  `TYPE` VARCHAR(45) NOT NULL,
  `MULTI_POLYGON` JSON NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`MULTI_POLYGON_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`MULTI_POLYGON_GEOMETRY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`MULTI_POLYGON_GEOMETRY` (
  `MULTI_POLYGON_ID` INT NOT NULL,
  `MULTI_POLYGON_INDEX` INT NOT NULL,
  `POLYGON_INDEX` INT NOT NULL,
  `POINT_INDEX` INT NOT NULL,
  `POINT_ID` INT NOT NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`MULTI_POLYGON_ID`, `MULTI_POLYGON_INDEX`, `POLYGON_INDEX`, `POINT_INDEX`),
  INDEX `fk_MULTI_POLYGON_GEOMETRY_POINT1_idx` (`POINT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_MULTI_POLYGON_GEOMETRY_MULTI_POLYGON1`
    FOREIGN KEY (`MULTI_POLYGON_ID`)
    REFERENCES `GISApp`.`MULTI_POLYGON` (`MULTI_POLYGON_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MULTI_POLYGON_GEOMETRY_POINT1`
    FOREIGN KEY (`POINT_ID`)
    REFERENCES `GISApp`.`POINT` (`POINT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`LINE_STRING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`LINE_STRING` (
  `LINE_STRING_ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL COMMENT '0:Open\n1:Private',
  `TYPE` VARCHAR(45) NOT NULL,
  `LINE_STRING` JSON NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`LINE_STRING_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`MULTI_LINE_STRING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`MULTI_LINE_STRING` (
  `MULTI_LINE_STRING_ID` INT NOT NULL AUTO_INCREMENT,
  `PRIVATE` TINYINT NOT NULL COMMENT '0:Open\n1:Private',
  `TYPE` VARCHAR(45) NOT NULL,
  `MULTI_LINE_STRING` JSON NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`MULTI_LINE_STRING_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`LINE_STRING_GEOMETRY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`LINE_STRING_GEOMETRY` (
  `LINE_STRING_ID` INT NOT NULL,
  `POINT_INDEX` INT NOT NULL,
  `POINT_ID` INT NOT NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`LINE_STRING_ID`, `POINT_INDEX`),
  INDEX `fk_LINE_STRING_GEOMETRY_POINT1_idx` (`POINT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_LINE_STRING_GEOMETRY_LINE_STRING1`
    FOREIGN KEY (`LINE_STRING_ID`)
    REFERENCES `GISApp`.`LINE_STRING` (`LINE_STRING_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LINE_STRING_GEOMETRY_POINT1`
    FOREIGN KEY (`POINT_ID`)
    REFERENCES `GISApp`.`POINT` (`POINT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GISApp`.`MULTI_LINE_STRING_GEOMETRY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`MULTI_LINE_STRING_GEOMETRY` (
  `MULTI_LINE_STRING_ID` INT NOT NULL,
  `LINE_STRING_INDEX` INT NOT NULL,
  `POINT_INDEX` INT NOT NULL,
  `POINT_ID` INT NOT NULL,
  `UPDATE_TIME` DATETIME(3) NOT NULL,
  PRIMARY KEY (`MULTI_LINE_STRING_ID`, `LINE_STRING_INDEX`, `POINT_INDEX`),
  INDEX `fk_MULTI_LINE_STRING_GEOMETRY_POINT1_idx` (`POINT_ID` ASC) VISIBLE,
  CONSTRAINT `fk_MULTI_LINE_STRING_GEOMETRY_MULTI_LINE_STRING1`
    FOREIGN KEY (`MULTI_LINE_STRING_ID`)
    REFERENCES `GISApp`.`MULTI_LINE_STRING` (`MULTI_LINE_STRING_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MULTI_LINE_STRING_GEOMETRY_POINT1`
    FOREIGN KEY (`POINT_ID`)
    REFERENCES `GISApp`.`POINT` (`POINT_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `GISApp` ;

-- -----------------------------------------------------
-- Placeholder table for view `GISApp`.`SHELTER_INFORMATION_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`SHELTER_INFORMATION_VIEW` (`POINT_ID` INT, `PRIVATE` INT, `TYPE` INT, `X` INT, `Y` INT, `Z` INT, `P20_001` INT, `P20_002` INT, `P20_003` INT, `P20_004` INT, `P20_005` INT, `P20_006` INT, `P20_007` INT, `P20_008` INT, `P20_009` INT, `P20_010` INT, `P20_011` INT, `P20_012` INT, `OPEN` INT, `COMMENT` INT, `UPDATE_TIME` INT);

-- -----------------------------------------------------
-- Placeholder table for view `GISApp`.`POST_INFORMATION_VIEW`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GISApp`.`POST_INFORMATION_VIEW` (`POINT_ID` INT, `PRIVATE` INT, `TYPE` INT, `X` INT, `Y` INT, `Z` INT, `INFORMATION` INT, `APPROVED` INT, `UPDATE_TIME` INT);

-- -----------------------------------------------------
-- View `GISApp`.`SHELTER_INFORMATION_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GISApp`.`SHELTER_INFORMATION_VIEW`;
USE `GISApp`;
CREATE  OR REPLACE VIEW `SHELTER_INFORMATION_VIEW` AS
    SELECT 
        point.POINT_ID,
        point.PRIVATE,
        point.TYPE,
        X,
        Y,
        Z,
        info1.STRING AS P20_001,
        info2.STRING AS P20_002,
        info3.STRING AS P20_003,
        info4.STRING AS P20_004,
        info5.NUMBER AS P20_005,
        info6.NUMBER AS P20_006,
        info7.NUMBER AS P20_007,
        info8.NUMBER AS P20_008,
        info9.NUMBER AS P20_009,
        info10.NUMBER AS P20_010,
        info11.NUMBER AS P20_011,
        info12.NUMBER AS P20_012,
        info13.BOOLEAN AS OPEN,
        info14.STRING AS COMMENT,
        info14.UPDATE_TIME AS UPDATE_TIME
    FROM
        GISApp.POINT AS point
            JOIN
        GISApp.INFORMATION AS info1 ON info1.NAME = 'P20_001'
            AND info1.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info1.ID
            JOIN
        GISApp.INFORMATION AS info2 ON info2.NAME = 'P20_002'
            AND info2.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info2.ID
            JOIN
        GISApp.INFORMATION AS info3 ON info3.NAME = 'P20_003'
            AND info3.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info3.ID
            JOIN
        GISApp.INFORMATION AS info4 ON info4.NAME = 'P20_004'
            AND info4.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info4.ID
            JOIN
        GISApp.INFORMATION AS info5 ON info5.NAME = 'P20_005'
            AND info5.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info5.ID
            JOIN
        GISApp.INFORMATION AS info6 ON info6.NAME = 'P20_006'
            AND info6.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info6.ID
            JOIN
        GISApp.INFORMATION AS info7 ON info7.NAME = 'P20_007'
            AND info7.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info7.ID
            JOIN
        GISApp.INFORMATION AS info8 ON info8.NAME = 'P20_008'
            AND info8.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info8.ID
            JOIN
        GISApp.INFORMATION AS info9 ON info9.NAME = 'P20_009'
            AND info9.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info9.ID
            JOIN
        GISApp.INFORMATION AS info10 ON info10.NAME = 'P20_010'
            AND info10.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info10.ID
            JOIN
        GISApp.INFORMATION AS info11 ON info11.NAME = 'P20_011'
            AND info11.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info11.ID
            JOIN
        GISApp.INFORMATION AS info12 ON info12.NAME = 'P20_012'
            AND info12.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info12.ID
            LEFT JOIN
        GISApp.INFORMATION AS info13 ON info13.NAME = 'OPEN'
            AND info13.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info13.ID
            LEFT JOIN
        GISApp.INFORMATION AS info14 ON info14.NAME = 'COMMENT'
            AND info14.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info14.ID
    WHERE
        point.TYPE = 'shelter';

-- -----------------------------------------------------
-- View `GISApp`.`POST_INFORMATION_VIEW`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `GISApp`.`POST_INFORMATION_VIEW`;
USE `GISApp`;
CREATE  OR REPLACE VIEW `POST_INFORMATION_VIEW` AS
    SELECT 
        point.POINT_ID,
        point.PRIVATE,
        point.TYPE,
        X,
        Y,
        Z,
        info1.STRING AS INFORMATION,
        info2.BOOLEAN AS APPROVED,
        info2.UPDATE_TIME AS UPDATE_TIME
    FROM
        GISApp.POINT AS point
            JOIN
        GISApp.INFORMATION AS info1 ON info1.NAME = 'INFORMATION'
            AND info1.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info1.ID
            JOIN
        GISApp.INFORMATION AS info2 ON info2.NAME = 'APPROVED'
            AND info2.ID_TYPE = 'POINT_ID'
            AND point.POINT_ID = info2.ID
    WHERE
        point.TYPE = 'post_information';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
