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
  `summary` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `technical_interview_date` date DEFAULT NULL,
  `user_id_fk` bigint NOT NULL,
  `vacancy_id_fk` bigint NOT NULL,
  `applicant_state_id_fk` bigint NOT NULL,
  PRIMARY KEY (`applicant_request_id`),
  KEY `applicant_requests_user_id_idx` (`user_id_fk`),
  KEY `applicant_requests_vacancy_id_idx` (`vacancy_id_fk`),
  KEY `state_idx` (`applicant_state_id_fk`),
  CONSTRAINT `applicant_requests_applicant_state_id` FOREIGN KEY (`applicant_state_id_fk`) REFERENCES `applicant_states` (`applicant_state_id`),
  CONSTRAINT `applicant_requests_user_id` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `applicant_requests_vacancy_id` FOREIGN KEY (`vacancy_id_fk`) REFERENCES `vacancies` (`vacancy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_requests`
--

LOCK TABLES `applicant_requests` WRITE;
/*!40000 ALTER TABLE `applicant_requests` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicant_states`
--

DROP TABLE IF EXISTS `applicant_states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant_states` (
  `applicant_state_id` bigint NOT NULL AUTO_INCREMENT,
  `state` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`applicant_state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_states`
--

LOCK TABLES `applicant_states` WRITE;
/*!40000 ALTER TABLE `applicant_states` DISABLE KEYS */;
INSERT INTO `applicant_states` VALUES (1,'LEFT_REQUEST'),(2,'READY_FOR_TECHNICAL_INTERVIEW'),(3,'PASSED'),(4,'FAILED');
/*!40000 ALTER TABLE `applicant_states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `city_id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'MINSK'),(2,'MOGILEV'),(6,'HOMEL'),(8,'BREST'),(9,'GRODNO');
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
  `country` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (6,'BELARUS');
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
  `comment` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `interview_type_id_fk` bigint NOT NULL,
  `applicant_request_id_fk` bigint NOT NULL,
  PRIMARY KEY (`interview_result_id`),
  KEY `interview_results_interview_type_id_idx` (`interview_type_id_fk`),
  KEY `interview_results_applicant_request_id_fk_idx` (`applicant_request_id_fk`),
  CONSTRAINT `interview_results_applicant_request_id_fk` FOREIGN KEY (`applicant_request_id_fk`) REFERENCES `applicant_requests` (`applicant_request_id`),
  CONSTRAINT `interview_results_interview_type_id` FOREIGN KEY (`interview_type_id_fk`) REFERENCES `interview_types` (`interview_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_results`
--

LOCK TABLES `interview_results` WRITE;
/*!40000 ALTER TABLE `interview_results` DISABLE KEYS */;
/*!40000 ALTER TABLE `interview_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview_types`
--

DROP TABLE IF EXISTS `interview_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_types` (
  `interview_type_id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`interview_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_types`
--

LOCK TABLES `interview_types` WRITE;
/*!40000 ALTER TABLE `interview_types` DISABLE KEYS */;
INSERT INTO `interview_types` VALUES (1,'BASIC'),(2,'TECHNICAL');
/*!40000 ALTER TABLE `interview_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reports`
--

DROP TABLE IF EXISTS `user_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_reports` (
  `user_report_id` bigint NOT NULL AUTO_INCREMENT,
  `is_available` bit(1) NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `comment` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `response` text CHARACTER SET utf8 COLLATE utf8_bin,
  `creation_date` date NOT NULL,
  `user_id_fk` bigint NOT NULL,
  PRIMARY KEY (`user_report_id`),
  KEY `user_reports_user_id_idx` (`user_id_fk`),
  CONSTRAINT `user_reports_user_id` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reports`
--

LOCK TABLES `user_reports` WRITE;
/*!40000 ALTER TABLE `user_reports` DISABLE KEYS */;
INSERT INTO `user_reports` VALUES (3,_binary '\0','Опечатка','Вакансия содержит опечатку.','Опечатка была исправлена. Большое спасибо!','2021-04-07',24),(4,_binary '','Change Role','I am an employee of your company, change my role.',NULL,'2021-04-07',22);
/*!40000 ALTER TABLE `user_reports` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
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
  `photo_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(320) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_active` bit(1) NOT NULL,
  `role_id_fk` bigint NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `users_role_id_idx` (`role_id_fk`),
  CONSTRAINT `users_role_id` FOREIGN KEY (`role_id_fk`) REFERENCES `user_roles` (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (21,'a61142e1-278c-4e2a-b38e-150fe75a0d3a.jpg','Aleksey','Klevitov','2000-11-11','+375-29-333-00-13','admin@gmail.com','$2a$10$ETxACUzFSpptry7zDDq/AekRfyPGIiFSvd1McR9zMOYckw2e4uUca',_binary '',1),(22,'bbe0bdd8-2137-4458-aa86-76aa70531da4.png','Alice','Green','1998-06-23','+375-44-297-23-12','alice@gmail.com','$2a$10$MIhonBMDh9q8xxHf1xli/u1ESTSpw8.PpmwIP3lEZjgY9FGApykTa',_binary '',3),(23,'610b72d6-fc55-4c45-b336-f236cc8fe256.png','David','Winter','2003-06-11','+375-29-101-32-16','david@gmail.com','$2a$10$AELWSt1FXeDA.nxD2uuVcuMqiTZaTuyP99f4OoPeiKxfqRc2kQkAy',_binary '',2),(24,'e8162879-65e3-44e2-a3b0-e27d0b0c039f.jfif','Nina','Martin','1998-09-14','+375441113322','martin@gmail.com','$2a$10$IIRKVNGuiwXAKf/mANj/B.erJ7JWRKWkE3BHjD7ENG3bRvbdpuWY.',_binary '',3);
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
  `position` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacancies`
--

LOCK TABLES `vacancies` WRITE;
/*!40000 ALTER TABLE `vacancies` DISABLE KEYS */;
INSERT INTO `vacancies` VALUES (37,_binary '','Senior Android Developer','CoolCompany - продуктовая компания основанная в США. Мы разрабатываем приложения с момента основания App Store, наши приложения неоднократно были профичерены компаниями Apple & Google. Наши приоритеты это создание высококачественных продуктов на основе многолетнего опыта с использованием новейших технологий iOS & Android.\r\n\r\nRequirements\r\n\r\n3+ years of Android development\r\nExpert in development and support of apps starting with Android 6.0\r\nExperience in creating apps with tablet support\r\nDeep knowledge of Material Design & implementation. Android design patterns\r\nStrong reliance on object-oriented analysis and development\r\nSolid knowledge of HTTP client programming\r\nMulti-threading\r\nAutomated testing and test-driven development\r\nAbility to work independently, estimate and deliver on time\r\nWritten English sufficient for communicating with native speakers via email and producing technical documentation.\r\nGood to have\r\n\r\nExperience in the development of B2C products\r\nCompensation\r\n\r\nSalary: from $2,500 with performance-based bonuses\r\nGuaranteed vacation and holidays\r\nStable full-time employment\r\nFlexible work schedule\r\nWorking in a team with friendly people who created winning apps and services, no-nonsense, pure creation atmosphere','2021-02-25',6,1,22),(38,_binary '','Senior/Lead DevOps Engineer','GreatRiver develops custom software solutions for corporate clients in Austria, Germany, Italy, Slovakia and USA. For doing this we have sales and service divisions in the local markets and development centers in Belarus (Minsk and Grodno). \r\n\r\nCurrently we are seeking for a Senior DevOps Engineer to join our fast-growing team located in Grodno. Partner description: Money Bank is a specialist provider of retail financial services and a leader in non-proprietary automotive and consumer finance. \r\n\r\nOur corporate group, Banco Santander, is the largest banking group in the Eurozone and one of the world\'s best banks. The goal is to build a state-of-the-art banking architecture with scalable, flexible and data-driven technology stack. Cooperation with a third-party consulting company with separated responsibilities. You will have a unique opportunity to act as innovator in DevOps space to make sure that build and delivery mechanisms are reliable, transparent, scalable and transportable.','2021-03-26',6,9,22),(39,_binary '','Software Tester','Our team.\r\n\r\nWe are the Quality Assurance team. We are software testing engineers who execute high-quality tests to refine our wide range of products and services. We look after the quality, usability, and stability of our websites and trading platforms to deliver a world-class experience to our end users.\r\n\r\nYour role.\r\n\r\nAs a Software Tester at Vired, you will engage in quality control throughout the product development lifecycle of all our products, services, and systems. You will help come up with test plans and ensure their execution on each stage, from planning until release. We process over a million transactions per day, and we need you to help deliver the best version of every product, fully optimised, free from bugs, and polished for client utilisation. ','2021-04-04',6,8,22),(40,_binary '','Senior System Administrator','Little City - одно из самых больших интернет-издательств в мире. Мы ежедневно создаем и публикуем сотни развлекательных и познавательных статей и видео для 850+ миллионов подписчиков по всему миру. Вы наверняка видели наши проекты в YouTube, Facebook и Instagram: 5-Minute Crafts, Bright Side, AdMe, La La Life, 123 GO!, Slick Slime Sam, Avocado Couple и т.д. Эти и десятки других проектов - результат работы нашей международной креативной команды.\r\n\r\nНаша IT-команда работает над самыми разнообразными проектами:\r\n\r\n- Высоконагруженная система управления производством контента               \r\n- Облачные решения для видео- и анимационного производства\r\n- Внутренний электронный документооборот и система расчетов\r\n- Корпоративная система аналитики\r\n         \r\nПрямо сейчас мы в поиске опытного Senior System Administrator, который обеспечит бесперебойную работу новой видеостудий и будет применять свою сильную техническую экспертизу в решений нестандартных задач.','2021-02-10',6,1,24);
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

-- Dump completed on 2021-04-22 19:20:14
