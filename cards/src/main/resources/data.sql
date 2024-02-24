DROP TABLE IF EXISTS card;

CREATE TABLE IF NOT EXISTS `cards` (
    `card_id` int NOT NULL AUTO_INCREMENT,
    `mobile_number` varchar(15) NOT NULL,
    `card_number` varchar(100) NOT NULL,
    `card_type` varchar(100) NOT NULL,
    `total_limit` int NOT NULL,
    `amount_used` int NOT NULL,
    `available_amount` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`card_id`)
);


-- INSERT INTO `card` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
--  VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());
--
-- INSERT INTO `card` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
--  VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());
--
-- INSERT INTO `card` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
--  VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());
