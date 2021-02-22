-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hrsystem
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applicant_requests`
--

DROP TABLE IF EXISTS `applicant_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant_requests` (
  `applicant_request_id` bigint NOT NULL AUTO_INCREMENT,
  `summary` text COLLATE utf8_unicode_ci NOT NULL,
  `applicant_state` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `user_id_fk` bigint NOT NULL,
  `vacancy_id_fk` bigint NOT NULL,
  PRIMARY KEY (`applicant_request_id`),
  KEY `applicant_requests_user_id_idx` (`user_id_fk`),
  KEY `applicant_requests_vacancy_id_idx` (`vacancy_id_fk`),
  CONSTRAINT `applicant_requests_user_id` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `applicant_requests_vacancy_id` FOREIGN KEY (`vacancy_id_fk`) REFERENCES `vacancies` (`vacancy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_requests`
--

LOCK TABLES `applicant_requests` WRITE;
/*!40000 ALTER TABLE `applicant_requests` DISABLE KEYS */;
INSERT INTO `applicant_requests` VALUES (1,'Привет, я Джек.','PASSED',10,2),(2,'Test','FAILED',10,8),(3,'I want to work!','PASSED',10,4),(4,'wdwd','LEFT_REQUEST',10,6),(5,'awfawf','PASSED',10,11),(6,'dwadawdwad','LEFT_REQUEST',12,5),(7,'sadsafas','FAILED',12,2),(8,'dawagfaw','LEFT_REQUEST',12,3),(9,'Hello','LEFT_REQUEST',10,24),(10,'Bad','FAILED',9,24),(11,'Good','PASSED',4,24),(12,'Good','PASSED',6,24),(13,'Good','PASSED',6,25),(14,'Good','PASSED',3,25),(15,'Good','PASSED',3,25);
/*!40000 ALTER TABLE `applicant_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'MINSK'),(2,'MOGILEV'),(3,'DRESDEN'),(4,'DWAD'),(5,'FWAFWAF'),(6,'HOMEL'),(7,'NEW YORK'),(8,'BREST');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `country_id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'BELARUS'),(2,'GERMANY'),(3,'USA'),(4,'WADWADWADWA'),(5,'WAFAWFWA');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_results`
--

DROP TABLE IF EXISTS `interview_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_results` (
  `interview_result_id` bigint NOT NULL AUTO_INCREMENT,
  `rating` tinyint unsigned NOT NULL,
  `comment` text COLLATE utf8_unicode_ci NOT NULL,
  `applicant_request_id_fk` bigint NOT NULL,
  PRIMARY KEY (`interview_result_id`),
  KEY `interview_results_applicant_request_id_idx` (`applicant_request_id_fk`),
  CONSTRAINT `interview_results_applicant_request_id` FOREIGN KEY (`applicant_request_id_fk`) REFERENCES `applicant_requests` (`applicant_request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_results`
--

LOCK TABLES `interview_results` WRITE;
/*!40000 ALTER TABLE `interview_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `interview_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_role_id` bigint NOT NULL AUTO_INCREMENT,
  `role` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'ADMIN'),(2,'APPLICANT'),(3,'EMPLOYEE');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `photo_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone_number` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `is_active` bit(1) NOT NULL,
  `role_id_fk` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `users_role_id_idx` (`role_id_fk`),
  CONSTRAINT `users_role_id` FOREIGN KEY (`role_id_fk`) REFERENCES `user_roles` (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'new_avatar_photo_name','Ivan','Ivanov','1990-06-25','+375-29-768-32-10','ivan_625@yandex.ru','$2a$10$xiRJbdkle1ysFmyUVCPLO.RwViIrwE7D5tDP9GRx5O.qr6ZqW0k5K',_binary '',3),(2,'avatar_photo_name','Aleksey','Klevitov','2000-11-11','+375-29-333-00-13','admin@gmail.com','$2a$10$dPxCGOwqpOnWYggzjkWwQ.W54aleqTsungB88gXZRfGFds3Hi2pZK',_binary '',1),(3,'avatar_photo_name','Peter','Petrov','2005-04-02','+375-33-196-98-53','petrov-2005@tut.by','$2a$10$h02C6H1JaSJIrsIfvw4hkeLx.uLdsPQk1Vbs8B/LYBkIlKAcyeCfe',_binary '',2),(4,'avatar_photo_name','Nick','Cool','2016-06-15','+375-29-111-22-13','nick_cool2016@gmail.com','$2a$10$//70bxbSctJWFm3kSvMSsep/d9tt8u6zYhLYEHs4fH7/dBNRzoBF6',_binary '',2),(5,'avatar_photo_name','Nick','Cool','2016-06-15','+375-29-111-22-13','nick_cool2016@gmail.com','$2a$10$5Hy5mfIG/T9UNRB15sewCexVyqfcvnDBnaRo6i/7O1KwdRTjHwiSy',_binary '',2),(6,'avatar_photo_name','Евгений','Дубов','2004-06-23','+375-29-101-32-16','dubov@gmail.com','$2a$10$Amg9kVlKqNtFhmKO70CWze4Q9u//o4edbgXi7Cvj1TNA2P5FiKUIy',_binary '',2),(7,'avatar_photo_name','Евгений','Дубов','2004-06-23','+375-29-101-32-16','dubov@gmail.com','$2a$10$ftLxUjrpO9iW538ih5cF8e6pwauq3d..DKRpFPGXeh61a8Sd0FeiW',_binary '',2),(8,'avatar_photo_name','Иван','Петров','2014-07-11','+375-44-297-23-12','ivan@gmail.com','$2a$10$mLp2kW4R3o2fMlBKjA2O5ugCZeBF7/2pRUKGK6p1pjGrcEAQ0J4jC',_binary '',2),(9,'avatar_photo_name','Иван','Петров','2014-07-11','+375-44-297-23-12','ivan@gmail.com','$2a$10$HtgMdZFEgVX1j5E3g17UUuoRlVF53I1SCZheE5LZFuDQw41XpTfry',_binary '',2),(10,'avatar_photo_name','Jack','Black','1999-04-04','80291234566','klevolex@gmail.com','$2a$10$xTxalrVMsuiDcSuVjAeFFeNzMGXQDb517aOZz7eHqA/xBw3x6hJEy',_binary '',2),(11,'avatar_photo_name','Nina','Martin','2004-04-24','+375291112233','martin@gmail.com','$2a$10$/o09bzQcidB8I/S0j6fnJ.zmsJwzO2lGaOnoGQvw6moL66.v3STam',_binary '',3),(12,'avatar_photo_name','David','Winter','1888-02-14','3753912345215','david@gmail.com','$2a$10$dpnjl2yK47UGx5m.2YlgcuZgfdILkUBNisJeT72UHdbM1ZWL61prS',_binary '',2),(13,'avatar_photo_name','John','Brown','2013-03-07','+3752998465321','brown@gmail.com','$2a$10$24KNER/Z1iYwlfdu5uteeOROUCpUy7pWAnm446EuFUBg5WhDDU1ly',_binary '',2),(14,'avatar_photo_name','Alice','Green','2020-11-05','80293330013','alice@gmail.com','$2a$10$9WyWRonb7BofjeNj/ibhA.8Vzkv/lPwcrJ6Dl6MVqMllT5XelTfU6',_binary '',3),(15,'avatar_photo_name','Sergey','Pink','2021-02-03','80293330013','sergo@gmail.com','$2a$10$CkEbM6Wpye9zQ71SZuKTlOLqLU4E0xjAenTQq2JjowDxESrt0CTou',_binary '',2),(16,'avatar_photo_name','Ivan','Ivanov','2021-02-17','80293330013','ivanov@gmail.com','$2a$10$lso4BibWLUocoNqxF6HCw.tBHZoWcz7AGh5WD54yYHwdUlAmsfrmC',_binary '',2),(17,'avatar_photo_name','Петр','Петров','2020-10-07','80293330013','petrov@gmail.com','$2a$10$0NypRtVoVEX3uEwnjHaIHejXAndkqP9TBTcRbh5VGXDCL7V9H5Yni',_binary '',2),(18,'avatar_photo_name','Steven','Robin','2021-02-04','80293330013','steven@gmail.com','$2a$10$q48qHkZt/FxJdPgWAIXi2eBjsd3gJi1ZAHQcsJDHzMzAzTNc0iAmC',_binary '',3),(19,'avatar_photo_name','Ivan','Sid','2021-02-03','80293330013','ivsid@mail.ru','$2a$10$XKxmhGSER4l0ce4FgL1lvO/4ZUEwalsaBE3usDsT2sdTMHZhBDJSu',_binary '',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacancies`
--

DROP TABLE IF EXISTS `vacancies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacancies` (
  `vacancy_id` bigint NOT NULL AUTO_INCREMENT,
  `is_available` bit(1) NOT NULL,
  `position` text COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `creation_date` date NOT NULL,
  `country_id_fk` bigint NOT NULL,
  `city_id_fk` bigint NOT NULL,
  `user_id_fk` bigint NOT NULL,
  PRIMARY KEY (`vacancy_id`),
  KEY `vacancies_user_id_idx` (`user_id_fk`),
  KEY `vacancies_country_id_idx` (`country_id_fk`),
  KEY `vacancies_city_id_idx` (`city_id_fk`),
  CONSTRAINT `vacancies_city_id` FOREIGN KEY (`city_id_fk`) REFERENCES `cities` (`city_id`),
  CONSTRAINT `vacancies_country_id` FOREIGN KEY (`country_id_fk`) REFERENCES `countries` (`country_id`),
  CONSTRAINT `vacancies_user_id` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancies`
--

LOCK TABLES `vacancies` WRITE;
/*!40000 ALTER TABLE `vacancies` DISABLE KEYS */;
INSERT INTO `vacancies` VALUES (1,_binary '\0','Senior Java Developer','MyCompany — продуктовая компания основанная в США. Мы разрабатываем приложения с момента основания App Store, наши приложения неоднократно были профичерены компаниями Apple & Google. Наши приоритеты это создание высококачественных продуктов на основе многолетнего опыта с использованием новейших технологий iOS & Android. Наши мобильные приложения полагаются на мощный, масштабируемый и надежный backend и мы расширяем нашу серверную команду.','2021-02-09',1,1,1),(2,_binary '\0','Senior Android Engineer','We\'re (GoodCompany) an end-to-end provider of premium products and services for global sport and media operators. We push boundaries every day to offer the most reliable, scalable, engaging end-to-end offerings that continually meet and exceed fan expectations and maximise client value. Underpinned by data we\'re able to determine the best experiences to drive business growth. Deltatre provides strategy, planning, consultancy across all stages of our client\'s lifecycle.','2021-02-09',1,1,1),(3,_binary '','Middle Java Developer','We\'re (GoodCompany) an end-to-end provider of premium products and services for global sport and media operators. We push boundaries every day to offer the most reliable, scalable, engaging end-to-end offerings that continually meet and exceed fan expectations and maximise client value. Underpinned by data we\'re able to determine the best experiences to drive business growth. Deltatre provides strategy, planning, consultancy across all stages of our client\'s lifecycle.','2021-02-09',1,2,1),(4,_binary '','Junior Recruiting/HR','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-09',2,3,1),(5,_binary '','Test','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-14',2,3,11),(6,_binary '\0','Test','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-14',1,1,11),(7,_binary '\0','Test Position #1','Test Position #1','2021-02-14',3,1,11),(8,_binary '\0','Test Position #2','Test Position #2','2021-02-14',1,1,11),(9,_binary '\0','Vacancy #15022021','DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription','2021-02-14',1,1,11),(10,_binary '\0','NEW VACANCY','Something interesting','2021-02-14',1,1,11),(11,_binary '','Специалист','dawdwadwad','2021-02-14',4,4,11),(12,_binary '','dawdawfaw','webjavadeveloper','2021-02-14',5,5,11),(13,_binary '','Martin Vacancy','Something','2021-02-16',1,6,11),(14,_binary '\0','Мартин джуниор','Привет hello','2021-02-20',1,1,11),(15,_binary '\0','Мартин джуниор #2','Dgaiushf uahsfisajifsa','2021-02-20',1,1,11),(16,_binary '','Test Position #114235','Вцфпфц','2021-02-21',3,7,11),(17,_binary '\0','Test Position #1a','dawfaw','2021-02-21',1,1,11),(18,_binary '','NEW VACANCY #12','sageagawg','2021-02-21',3,7,11),(19,_binary '\0','Специалист #2','aawgawg','2021-02-21',1,1,11),(20,_binary '\0','Test Position #1a24','afawg','2021-02-21',1,1,11),(21,_binary '\0','Test Position #125432','fdasfsa','2021-02-21',1,1,11),(22,_binary '\0','NEW MARTIN VACANCY','fhgaweh','2021-02-21',1,1,11),(23,_binary '\0','AAA','BBB','2021-02-21',1,1,11),(24,_binary '','ALICE VACANCY #1','This is Alice vacancy','2021-02-21',1,1,14),(25,_binary '','ALICE VACANCY #2','Another Alice vacancy','2021-02-21',3,7,14),(26,_binary '\0','ALICE VACANCY #3','Alice new vacancy.','2021-02-22',1,6,14),(27,_binary '','ALICE VACANCY #4','Maybe the last one.','2021-02-22',1,1,14),(28,_binary '','ALICE VACANCY #5','Last one.','2021-02-22',1,1,14),(29,_binary '','ALICE VACANCY #6','The last!','2021-02-22',1,1,14),(30,_binary '\0','ALICE VACANCY #7','Something interesting.','2021-02-22',1,8,14),(31,_binary '','Специалист','dwdwd','2021-02-22',1,1,11),(32,_binary '','ALICE VACANCY #8','LAST OF LAST','2021-02-22',1,1,14),(33,_binary '','Специалист','ddd','2021-02-22',3,1,11);
/*!40000 ALTER TABLE `vacancies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-23  0:19:19
