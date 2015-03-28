CREATE SCHEMA `testtask`;

CREATE TABLE `testtask`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(40),
  `lastName` VARCHAR(40),
  `userName` VARCHAR(40),
  `userPassword` VARCHAR(40),
  `email` VARCHAR(40),
  `birthday` DATE,
  `isActive` INT,
  `createTS` TIMESTAMP,
  `lastUpdateTS` TIMESTAMP,
  `group_id` INT,
  `address_id` INT,
  PRIMARY KEY  (`user_id`)
);

CREATE TABLE `testtask`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `zip` VARCHAR(5),
  `country` VARCHAR(40),
  `city` VARCHAR(40),
  `district` VARCHAR(40),
  `street` VARCHAR(40),
  PRIMARY KEY  (`address_id`)
);

CREATE TABLE `testtask`.`groups` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `groupType` VARCHAR(40),
  PRIMARY KEY  (`group_id`)
);

ALTER TABLE `testtask`.`users` ADD CONSTRAINT `users_fk1` FOREIGN KEY (`group_id`) REFERENCES `testtask`.`groups`(`group_id`);
ALTER TABLE `testtask`.`users` ADD CONSTRAINT `users_fk2` FOREIGN KEY (`address_id`) REFERENCES `testtask`.`addresses`(`address_id`);
