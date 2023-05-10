-- MySQL dump 10.13  Distrib 5.6.43, for Win64 (x86_64)
--
-- Host: localhost    Database: blog_app_api
-- ------------------------------------------------------
-- Server version	5.6.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `caregory_id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`caregory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'This topic is releated to Programming Languages','Programming Language'),(2,'This topic is releated to General Knowledge','General Knowledge'),(4,'This topic is releated to Politics',''),(5,'This topic is releated to Politics','Politics'),(6,'This topic is releated to Technical knowledge','Technical ');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `post_post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKml8v0aac8qo7cbgh37tocvxd4` (`post_post_id`),
  CONSTRAINT `FKml8v0aac8qo7cbgh37tocvxd4` FOREIGN KEY (`post_post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Great learning, good work!!',2),(2,'This is second comment !!',2);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (20);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `post_title` varchar(100) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKjl0ab1c7s7gsd0tp830a7oogx` (`category_id`),
  KEY `FK7ky67sgi7k0ayf22652f7763r` (`user_id`),
  CONSTRAINT `FK7ky67sgi7k0ayf22652f7763r` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKjl0ab1c7s7gsd0tp830a7oogx` FOREIGN KEY (`category_id`) REFERENCES `categories` (`caregory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'programming language is used to communicate with machine ','2022-06-18 13:32:00','default.png','What is programming language',1,1),(2,'Science','2022-06-21 14:45:48','e026e5b9-fbb5-4e7a-ace0-600c524c60b6.png','Science',1,1),(3,'What is programming language','2022-06-27 14:06:15','ae775e80-549c-411f-84ed-840fd89dc349.pdf','What is programming language',1,1),(4,'What is programming language','2022-06-21 15:16:21','4fbecfed-10c6-44f3-9f3f-871c4600a5de.png','What is programming language',1,1),(5,'What is programming language','2022-06-27 14:20:24','0baa7c93-4f45-4cf6-89ee-a1bab2ca4492.txt','What is programming language',1,1),(7,'programming language is used to communicate with machine ','2022-06-18 13:44:13','default.png','What is programming language',1,1),(8,'programming language is used to communicate with machine ','2022-06-18 13:48:33','default.png','What is programming language',1,1),(9,'programming language is used to communicate with machine ','2022-06-18 13:52:44','default.png','What is programming language',1,1),(10,'programming language is used to communicate with machine ','2022-06-18 14:35:38','default.png','What is programming language',1,1),(11,'programming language is used to communicate with machine ','2022-06-18 14:43:30','default.png','What is programming language',1,1),(12,'programming language is used to communicate with machine ','2022-06-18 14:43:34','default.png','What is programming language',1,1),(13,'programming language is used to communicate with machine ','2022-06-18 14:44:11','default.png','What is programming language',1,1),(14,'programming language is used to communicate with machine ','2022-06-18 14:45:45','default.png','What is programming language',1,1),(15,'Java is best programming language  ','2022-06-18 15:10:34','default.png','Which is best programming language?',1,3),(16,'Java is best programming language  ','2022-06-18 15:13:04','default.png','Which is best programming language?',5,2),(17,'Java is best programming language  ','2022-06-18 15:14:11','default.png','',5,2),(18,'Java is best programming language  ','2022-06-18 15:15:55','default.png','',5,2),(19,'Java is best programming language  ','2022-06-18 15:15:58','default.png','',5,2),(20,'Java is best programming language  ','2022-06-18 15:18:22','default.png','',5,2),(21,'In this article we will learn about Political science ','2022-06-20 10:32:28','default.png','Political science',5,2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=503 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (501,'ROLE_ADMIN'),(502,'ROLE_NORMAL');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`user`,`role`),
  KEY `FK26f1qdx6r8j1ggkgras9nrc1d` (`role`),
  CONSTRAINT `FK26f1qdx6r8j1ggkgras9nrc1d` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  CONSTRAINT `FKlduspqw8rg0gbcpludbfadw6l` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,501),(12,502),(13,502),(18,502);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `about` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Hello!!there, Have a nice day','deepak_gupta@nucsoft.in','Deepak','$2a$10$Ebo/9Ns9zzvei8Ml6mSoK.XGPaNpJKoWP1y71FbUCoGaqSZJw7okm'),(2,'Hello!!there, Have a nice day','nishal_gupta@nucsoft.in','Nishal','$2a$10$Ebo/9Ns9zzvei8Ml6mSoK.XGPaNpJKoWP1y71FbUCoGaqSZJw7okm'),(3,'Hello!!there, Have a nice day','ajay_gupta@nucsoft.in','Ajay','nuc1234'),(10,'Hello!!there, I\'m teacher','ankit_gupta@nucsoft.in','Ankit ','$2a$10$i1TN0zf5N6RE31nXcKLVKeJRJ2/zT7SPlzdY5Ubzsvljw.k1d4E46'),(11,'Hello!!there, I\'m teacher','anand_gupta@nucsoft.in','Anand','$2a$10$t.Sxa3d8deXQjpe0gm/Rw.dJMjYkAPl9liTeiujXzlHgf6lrLiz6W'),(12,'Hello!!there, I\'m teacher','mohan_gupta@nucsoft.in','Mohan','$2a$10$97Y2/F8pW/wIo2IUcJHpQuuSUVzFpH5p8UwgbCnp6en7WP0TUiW2q'),(13,'Hello!! This is swagger api','aakanksha@nucsoft.in','Aakanksha','$2a$10$mAcINdgtwaYR80zViJYnbubKNVTj63wnJFSp/uCpnk72md.VR8t6m'),(16,'Hello!!there, I\'m developer','durgesh_gupta@nuc','Durgesg','nuc1234'),(18,'Hello!!there, Have a nice day','deepak_gupta1@nucsoft.com','Deepak','$2a$10$.2OTuyO3Q.MbLZEr.qleAu4hM7nsbajpSabxWO0SaSNcr.bBvLFjO'),(19,'Hello!!there, I\'m developer','durgesh_gupta@nuc','Nandu','nuc1234');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-09 17:55:54
