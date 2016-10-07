-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.5.50 - MySQL Community Server (GPL)
-- 서버 OS:                        Win32
-- HeidiSQL 버전:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- firework 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `firework` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `firework`;


-- 테이블 firework.reb_board 구조 내보내기
CREATE TABLE IF NOT EXISTS `reb_board` (
  `reb_no` int(11) NOT NULL AUTO_INCREMENT,
  `reb_subject` varchar(50) NOT NULL,
  `reb_pass` varchar(50) NOT NULL,
  `m_id` varchar(20) NOT NULL,
  `reb_content` varchar(1000) NOT NULL,
  `reb_file` varchar(1000) NOT NULL,
  `reb_re_ref` int(100) NOT NULL,
  `reb_re_lev` int(100) NOT NULL,
  `reb_re_seq` int(100) NOT NULL,
  `reb_readcount` int(11) NOT NULL,
  `reb_date` date NOT NULL,
  PRIMARY KEY (`reb_no`),
  KEY `FK__member2` (`m_id`),
  CONSTRAINT `FK__member2` FOREIGN KEY (`m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 firework.reb_board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `reb_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `reb_board` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
