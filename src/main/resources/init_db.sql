CREATE SCHEMA `cinema` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cinema`.`movies` (
                                   `movie_id` INT NOT NULL AUTO_INCREMENT,
                                   `title` VARCHAR(256) NOT NULL,
                                   `description` VARCHAR(4096) NOT NULL,
                                   `deleted` TINYINT NOT NULL DEFAULT '0',
                                   PRIMARY KEY (`movie_id`));
