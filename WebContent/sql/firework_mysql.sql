-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.32 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- firework 의 데이터베이스 구조 덤핑
CREATE DATABASE IF NOT EXISTS `firework` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `firework`;


-- 테이블 firework의 구조를 덤프합니다. goods
CREATE TABLE IF NOT EXISTS `goods` (
  `g_code` varchar(20) NOT NULL,
  `g_name` varchar(20) NOT NULL,
  `g_id` varchar(20) NOT NULL,
  `g_cate` varchar(20) NOT NULL,
  `g_sangse` varchar(80) NOT NULL,
  `g_price` int(11) NOT NULL,
  `g_date` date NOT NULL,
  `g_agree` char(1) NOT NULL DEFAULT 'N',
  `g_img` varchar(500) NOT NULL,
  PRIMARY KEY (`g_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 firework의 구조를 덤프합니다. member
CREATE TABLE IF NOT EXISTS `member` (
  `m_id` varchar(20) NOT NULL,
  `m_pw` varchar(20) NOT NULL,
  `m_level` varchar(20) NOT NULL,
  `m_name` varchar(20) NOT NULL,
  `m_email` varchar(80) NOT NULL,
  `m_date` date NOT NULL,
  `m_addr` varchar(200) NOT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 firework의 구조를 덤프합니다. orders
CREATE TABLE IF NOT EXISTS `orders` (
  `o_no` int(10) NOT NULL AUTO_INCREMENT,
  `m_id` varchar(20) NOT NULL,
  `o_date` date NOT NULL,
  `g_code` varchar(20) NOT NULL,
  `o_count` int(11) NOT NULL,
  `o_total` int(11) NOT NULL,
  `o_state` varchar(80) NOT NULL DEFAULT '입금예정',
  PRIMARY KEY (`o_no`),
  KEY `FK__goods` (`g_code`),
  KEY `FK_orders_member` (`m_id`),
  CONSTRAINT `FK_orders_member` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`),
  CONSTRAINT `FK__goods` FOREIGN KEY (`g_code`) REFERENCES `goods` (`g_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 firework의 구조를 덤프합니다. qna_board
CREATE TABLE IF NOT EXISTS `qna_board` (
  `qna_no` int(11) NOT NULL AUTO_INCREMENT,
  `qna_subject` varchar(50) NOT NULL,
  `m_id` varchar(20) NOT NULL,
  `qna_content` varchar(1000) NOT NULL,
  `qna_secret` varchar(20) NOT NULL,
  `qna_category` varchar(20) NOT NULL,
  `qna_date` date NOT NULL,
  `qna_readcount` int(11) NOT NULL,
  PRIMARY KEY (`qna_no`),
  KEY `FK__member` (`m_id`),
  CONSTRAINT `FK__member` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.


-- 테이블 firework의 구조를 덤프합니다. review_board
CREATE TABLE IF NOT EXISTS `review_board` (
  `review_no` int(11) NOT NULL AUTO_INCREMENT,
  `review_subject` varchar(50) NOT NULL,
  `m_id` varchar(20) NOT NULL,
  `review_content` varchar(1000) NOT NULL,
  `review_secret` varchar(20) NOT NULL,
  `review_category` varchar(20) NOT NULL,
  `review_date` date NOT NULL,
  `review_readcount` int(11) NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `FK__member2` (`m_id`),
  CONSTRAINT `FK__member2` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
