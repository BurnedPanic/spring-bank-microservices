DROP TABLE IF EXISTS loan;

CREATE TABLE IF NOT EXISTS `loan` (
    `loan_id` int NOT NULL AUTO_INCREMENT,
    `mobile_number` varchar(15) NOT NULL,
    `loan_number` varchar(100) NOT NULL,
    `loan_type` varchar(100) NOT NULL,
    `total_loan` int NOT NULL,
    `amount_paid` int NOT NULL,
    `outstanding_amount` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`loan_id`)
    );

-- INSERT INTO `loan` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
--  VALUES ( 1, CURDATE()-250, 'Home', 200000, 50000, 150000, CURDATE()-250);
--
-- INSERT INTO `loan` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
--  VALUES ( 1, CURDATE()-376, 'Vehicle', 40000, 10000, 30000, CURDATE()-376);
--
-- INSERT INTO `loan` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
--  VALUES ( 1, CURDATE()-549, 'Home', 50000, 10000, 40000, CURDATE()-549);
--
-- INSERT INTO `loan` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
--  VALUES ( 1, CURDATE()-122, 'Personal', 10000, 3500, 6500, CURDATE()-122);
