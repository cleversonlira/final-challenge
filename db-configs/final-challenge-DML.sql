USE bank_account;

INSERT INTO type_card(`name`)
VALUES ('DEBIT_CARD'),
	('CREDIT_CARD'),
	('MEAL_CARD'),
	('GIFT_CARD');
    
-- SELECT * FROM type_card;

INSERT INTO `account`(name_owner, agency_code, account_code, digit_verification, register_id)
VALUES
	('Wendell Bezerra', '4321', '12345678', '1', '20558062032'),
	('Patricia Bezerra', '1321', '22345678', '1', '56073739087');

SELECT * FROM `account`;

INSERT INTO card
	(`flag`, `number`, `digit_code`, `limit_balance`, `account_id`, `type_card_id`)
VALUES
	('ELO', '1234.5678.9876.5432', '98765', 258.10, 1, 1),
    ('ELO', '1234.5678.9876.5432', '98765', 1236.80, 1, 2),
    ('MASTERCARD', '4321.5678.9876.1234', '28765', 0, 2, 1),    
    ('MASTERCARD', '4321.5678.9876.1234', '28765', 0, 2, 2);

SELECT * FROM card;

UPDATE card SET flag = 'MASTERCARD' WHERE id BETWEEN 7 AND 8;

SELECT 
	c.`number` as numero_cartao,
    t.`name` as tipo_cartao,
    a.name_owner as proprietario, 
	a.register_id as cpf  
FROM card as c
	JOIN `account` as a
		ON c.account_id = a.id
    JOIN type_card as t
		ON t.id = c.type_card_id;
    