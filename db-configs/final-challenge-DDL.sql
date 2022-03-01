CREATE DATABASE bank_account;
USE bank_account;

CREATE TABLE type_card(
	id INT AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    
    CONSTRAINT pk_type_card PRIMARY KEY(id),
    CONSTRAINT uk_type_card_name UNIQUE(`name`)
);

CREATE TABLE `account`(
	id INT AUTO_INCREMENT,
    name_owner VARCHAR(50) NOT NULL,
    agency_code VARCHAR(4) NOT NULL,
    account_code VARCHAR(8) NOT NULL,
    digit_verification VARCHAR(1) NOT NULL,
    register_id VARCHAR(20) NOT NULL,
    
    CONSTRAINT pk_account PRIMARY KEY(id),
    CONSTRAINT uk_account_register UNIQUE(register_id),
    CONSTRAINT uk_account_code UNIQUE(account_code)
);

CREATE TABLE card(
	id INT AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL DEFAULT 'Card',
    flag VARCHAR(45) NOT NULL,
    `number` VARCHAR(20) NOT NULL,
    digit_code VARCHAR(5) NOT NULL,
    limit_balance DOUBLE(20,2),
    account_id INT,
    type_card_id INT,
    
    CONSTRAINT pk_card PRIMARY KEY(id),
    CONSTRAINT fk_account FOREIGN KEY(account_id)
		REFERENCES `account`(id),
	CONSTRAINT fk_type_card FOREIGN KEY(type_card_id)
		REFERENCES type_card(id)
);

-- ALTER TABLE type_card
-- ADD CONSTRAINT uk_type_card_name UNIQUE(`name`);

-- ALTER TABLE card
-- ALTER `name` SET DEFAULT 'Card';