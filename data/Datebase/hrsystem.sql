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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_requests`
--

LOCK TABLES `applicant_requests` WRITE;
/*!40000 ALTER TABLE `applicant_requests` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'MINSK'),(2,'MOGILEV'),(3,'DRESDEN'),(4,'DWAD'),(5,'FWAFWAF'),(6,'HOMEL');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'new_avatar_photo_name','Ivan','Ivanov','1990-06-25','+375-29-768-32-10','ivan_625@yandex.ru','$2a$10$xiRJbdkle1ysFmyUVCPLO.RwViIrwE7D5tDP9GRx5O.qr6ZqW0k5K',_binary '',3),(2,'avatar_photo_name','Aleksey','Klevitov','2000-11-11','+375-29-333-00-13','klevolex@gmail.com','$2a$10$dPxCGOwqpOnWYggzjkWwQ.W54aleqTsungB88gXZRfGFds3Hi2pZK',_binary '',1),(3,'avatar_photo_name','Peter','Petrov','2005-04-02','+375-33-196-98-53','petrov-2005@tut.by','$2a$10$h02C6H1JaSJIrsIfvw4hkeLx.uLdsPQk1Vbs8B/LYBkIlKAcyeCfe',_binary '',2),(4,'avatar_photo_name','Nick','Cool','2016-06-15','+375-29-111-22-13','nick_cool2016@gmail.com','$2a$10$//70bxbSctJWFm3kSvMSsep/d9tt8u6zYhLYEHs4fH7/dBNRzoBF6',_binary '',2),(5,'avatar_photo_name','Nick','Cool','2016-06-15','+375-29-111-22-13','nick_cool2016@gmail.com','$2a$10$5Hy5mfIG/T9UNRB15sewCexVyqfcvnDBnaRo6i/7O1KwdRTjHwiSy',_binary '',2),(6,'avatar_photo_name','Евгений','Дубов','2004-06-23','+375-29-101-32-16','dubov@gmail.com','$2a$10$Amg9kVlKqNtFhmKO70CWze4Q9u//o4edbgXi7Cvj1TNA2P5FiKUIy',_binary '',2),(7,'avatar_photo_name','Евгений','Дубов','2004-06-23','+375-29-101-32-16','dubov@gmail.com','$2a$10$ftLxUjrpO9iW538ih5cF8e6pwauq3d..DKRpFPGXeh61a8Sd0FeiW',_binary '',2),(8,'avatar_photo_name','Иван','Петров','2014-07-11','+375-44-297-23-12','ivan@gmail.com','$2a$10$mLp2kW4R3o2fMlBKjA2O5ugCZeBF7/2pRUKGK6p1pjGrcEAQ0J4jC',_binary '',2),(9,'avatar_photo_name','Иван','Петров','2014-07-11','+375-44-297-23-12','ivan@gmail.com','$2a$10$HtgMdZFEgVX1j5E3g17UUuoRlVF53I1SCZheE5LZFuDQw41XpTfry',_binary '',2),(10,'avatar_photo_name','Jack','Black','1999-04-04','80291234566','jack@gmail.com','$2a$10$xTxalrVMsuiDcSuVjAeFFeNzMGXQDb517aOZz7eHqA/xBw3x6hJEy',_binary '',2),(11,'avatar_photo_name','Nina','Martin','2004-04-24','+375291112233','martin@gmail.com','$2a$10$/o09bzQcidB8I/S0j6fnJ.zmsJwzO2lGaOnoGQvw6moL66.v3STam',_binary '',3),(12,'avatar_photo_name','David','Winter','1888-02-14','3753912345215','david@gmail.com','$2a$10$dpnjl2yK47UGx5m.2YlgcuZgfdILkUBNisJeT72UHdbM1ZWL61prS',_binary '',2),(13,'avatar_photo_name','John','Brown','2013-03-07','+3752998465321','brown@gmail.com','$2a$10$24KNER/Z1iYwlfdu5uteeOROUCpUy7pWAnm446EuFUBg5WhDDU1ly',_binary '',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancies`
--

LOCK TABLES `vacancies` WRITE;
/*!40000 ALTER TABLE `vacancies` DISABLE KEYS */;
INSERT INTO `vacancies` VALUES (1,_binary '\0','Senior Java Developer','MyCompany — продуктовая компания основанная в США. Мы разрабатываем приложения с момента основания App Store, наши приложения неоднократно были профичерены компаниями Apple & Google. Наши приоритеты это создание высококачественных продуктов на основе многолетнего опыта с использованием новейших технологий iOS & Android. Наши мобильные приложения полагаются на мощный, масштабируемый и надежный backend и мы расширяем нашу серверную команду.','2021-02-09',1,1,1),(2,_binary '','Senior Android Engineer','We\'re (GoodCompany) an end-to-end provider of premium products and services for global sport and media operators. We push boundaries every day to offer the most reliable, scalable, engaging end-to-end offerings that continually meet and exceed fan expectations and maximise client value. Underpinned by data we\'re able to determine the best experiences to drive business growth. Deltatre provides strategy, planning, consultancy across all stages of our client\'s lifecycle.','2021-02-09',1,1,1),(3,_binary '','Middle Java Developer','We\'re (GoodCompany) an end-to-end provider of premium products and services for global sport and media operators. We push boundaries every day to offer the most reliable, scalable, engaging end-to-end offerings that continually meet and exceed fan expectations and maximise client value. Underpinned by data we\'re able to determine the best experiences to drive business growth. Deltatre provides strategy, planning, consultancy across all stages of our client\'s lifecycle.','2021-02-09',1,2,1),(4,_binary '','Junior Recruiting/HR','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-09',2,3,1),(5,_binary '','Test','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-14',2,3,2),(6,_binary '','Test','Join one of Inc 5000’s fastest-growing companies as an IT Recruiter. A top-rated Google Marketing Platform Partner, DELVE is a strategic partner for site-side analytics, campaign management, and advanced marketing science.\n\nJoin and expand our team of analysts, data engineers, data scientists, and media traders that drives client growth through a data-driven mindset that converts digital inefficiency into hard ROI.\n\nIn this role you will collaborate directly with hiring managers to understand who they need, when, why, etc. and prioritize a list of hiring needs, per department, per optimal start date, based on known forecasts of inflowing biz.','2021-02-14',1,1,2),(7,_binary '','Test Position #1','Test Position #1','2021-02-14',3,1,2),(8,_binary '','Test Position #2','Test Position #2','2021-02-14',1,1,2),(9,_binary '','Vacancy #15022021','DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription','2021-02-14',1,1,2),(10,_binary '\0','NEW VACANCY','Something interesting','2021-02-14',1,1,2),(11,_binary '','Специалист','dawdwadwad','2021-02-14',4,4,2),(12,_binary '','dawdawfaw','webjavadeveloper','2021-02-14',5,5,2),(13,_binary '','Martin Vacancy','Something','2021-02-16',1,6,11);
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

-- Dump completed on 2021-02-17 14:18:54
