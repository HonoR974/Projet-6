CREATE DATABASE  IF NOT EXISTS `debut2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `debut2`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: debut2
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
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentaire` (
  `id` int NOT NULL,
  `contenu` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `site_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7poewyx0vupiexwj3o3v5r746` (`site_id`),
  KEY `FKnxdc2ad1nq0dstrheq6tmsoo6` (`user_id`),
  CONSTRAINT `FK7poewyx0vupiexwj3o3v5r746` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`),
  CONSTRAINT `FKnxdc2ad1nq0dstrheq6tmsoo6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentaire`
--

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES (119);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `statut_id` int DEFAULT NULL,
  `topo_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK753io2ji7vlsayk72g7tk8eij` (`statut_id`),
  KEY `FKs6rhp8bjbn9p9imq1k5fcpb6a` (`topo_id`),
  KEY `FKm4oimk0l1757o9pwavorj6ljg` (`user_id`),
  CONSTRAINT `FK753io2ji7vlsayk72g7tk8eij` FOREIGN KEY (`statut_id`) REFERENCES `statut` (`id`),
  CONSTRAINT `FKm4oimk0l1757o9pwavorj6ljg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKs6rhp8bjbn9p9imq1k5fcpb6a` FOREIGN KEY (`topo_id`) REFERENCES `topo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`) VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS `site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `site` (
  `id` int NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `tag` bit(1) DEFAULT NULL,
  `topo_id` int DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKowfn7flv042yuklx2avx022iu` (`topo_id`),
  CONSTRAINT `FKowfn7flv042yuklx2avx022iu` FOREIGN KEY (`topo_id`) REFERENCES `topo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `site`
--

LOCK TABLES `site` WRITE;
/*!40000 ALTER TABLE `site` DISABLE KEYS */;
INSERT INTO `site` (`id`, `adresse`, `nom`, `region`, `tag`, `topo_id`, `visible`) VALUES (83,'Massif du Mont Blanc','Mont Blanc','Rhône-Alpes',_binary '\0',82,_binary ''),(87,' entre le canton du Valais et la Vallée d\'Aoste.','Cervin','Suisse',_binary '\0',82,_binary ''),(91,'Suisse Valais Alpes Bernoises','Jungfrau','Suisse',_binary '\0',82,_binary ''),(95,'Massif du Monte Cinto','Cintu','Corse',_binary '\0',94,_binary ''),(99,'Monte Rotondo','Rotondo','Corse',_binary '\0',94,_binary ''),(102,'Massif de la maladeta','Aneto','les Pyrénées',_binary '\0',101,_binary ''),(106,'Espagne, proche de la frontière franco-espagnole','Mont Perdu','les Pyrénées',_binary '\0',101,_binary ''),(109,'Massif du Sancy','Puy de Sancy','Auvergne',_binary '\0',108,_binary ''),(113,'Massif du piton de la Fournaise','Piton de la Fournaise','ile de la Réunion',_binary '\0',112,_binary ''),(116,'Massif du Jura','Crêt de la Neige','L\'Ain',_binary '\0',115,_binary '');
/*!40000 ALTER TABLE `site` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statut`
--

DROP TABLE IF EXISTS `statut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statut` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statut`
--

LOCK TABLES `statut` WRITE;
/*!40000 ALTER TABLE `statut` DISABLE KEYS */;
INSERT INTO `statut` (`id`, `name`) VALUES (1,'EN_ATTENTE'),(3,'REFUSE'),(4,'FINI'),(5,'ACCEPTE');
/*!40000 ALTER TABLE `statut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topo`
--

DROP TABLE IF EXISTS `topo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topo` (
  `id` int NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `disponible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpurggp4l82two9ekgr11hx2qk` (`user_id`),
  CONSTRAINT `FKpurggp4l82two9ekgr11hx2qk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topo`
--

LOCK TABLES `topo` WRITE;
/*!40000 ALTER TABLE `topo` DISABLE KEYS */;
INSERT INTO `topo` (`id`, `date_creation`, `description`, `nom`, `visible`, `user_id`, `disponible`) VALUES (82,'2021-01-15 00:00:00.000000','Les Alpes sont une chaîne de montagnes qui s\'étend en Europe, recouvrant la frontière nord de l\'Italie, le Sud-Est de la France, Monaco, la Suisse, le Liechtenstein, l\'Autriche, le Sud de l\'Allemagne et la Slovénie.','Alpes',_binary '',1,_binary ''),(94,'2021-01-15 00:00:00.000000','La Corse est une montagne dans la mer. Son altitude moyenne de 568 m en fait la plus élevée des îles de Méditerranée occidentale. ','Corse',_binary '',1,_binary ''),(101,'2021-01-16 00:00:00.000000','Les Pyrénées sont une chaîne de montagnes séparant la péninsule Ibérique du reste de l\'Europe.','Pyrénées',_binary '',1,_binary ''),(108,'2021-01-14 00:00:00.000000','Le Massif central est un massif montagneux essentiellement hercynien qui occupe le centre de la moitié sud de la France métropolitaine.','Massif Central',_binary '',3,_binary ''),(112,'2021-01-06 00:00:00.000000','L\'île de la Réunion réputée pour son intérieur volcanique recouvert de forêt tropicale, ses récifs de corail et ses plages.','Réunion',_binary '',3,_binary ''),(115,'2021-01-15 00:00:00.000000','Le Jura est une chaîne de montagnes située en Europe occidentale, principalement le long de la frontière entre la France et la Suisse, au nord-ouest des Alpes.','Jura',_binary '',3,_binary '');
/*!40000 ALTER TABLE `topo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `password`, `username`) VALUES (1,'$2a$10$B/QkQTbKedPEicBimn15xusplLziGTihNM3pUWLlRyYX9eyRCA.4u','personnage1'),(2,'$2a$10$w85gFLMZfY22oEykf65P2uxvz4h6WWcnyODRd14pR7uBp7Mp0lc0K','adminadmin'),(3,'$2a$10$PHSz6EMhRmY5YWGRaVrsU.tYLFuSThf2Me8CHue4zQg5A.cpV98Py','personnage2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `users_id` int NOT NULL,
  `roles_id` int NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`),
  CONSTRAINT `FK7ecyobaa59vxkxckg6t355l86` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`users_id`, `roles_id`) VALUES (1,1),(2,1),(3,1),(2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voie`
--

DROP TABLE IF EXISTS `voie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voie` (
  `id` int NOT NULL,
  `cotation` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `site_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKry0xp2k6722vmxdc95ilxrd9v` (`site_id`),
  CONSTRAINT `FKry0xp2k6722vmxdc95ilxrd9v` FOREIGN KEY (`site_id`) REFERENCES `site` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voie`
--

LOCK TABLES `voie` WRITE;
/*!40000 ALTER TABLE `voie` DISABLE KEYS */;
INSERT INTO `voie` (`id`, `cotation`, `nom`, `site_id`) VALUES (84,'AD','Voie normale',83),(85,'PD+','3 Monts',83),(86,'PD+','Aiguilles Grises',83),(88,'AD ','Arête du Hörnli',87),(89,'AD','Traversée Lion',87),(90,'AD','Arête du Lion',87),(92,'D 6a>4a','Arête NW',91),(93,'D+','Arête NE',91),(96,'  F II T4','Cintu : depuis Lozzi',95),(97,'T4','Cintu : Depuis le refuge Tighjetu',95),(98,'  T4',' Cintu : Par le Cirque de Trimbulacciu depuis le Haut-Asco',95),(100,'  T2',' Monte Rotondo',99),(103,'PD- II P2 E1 PD / S3 ','Par le refuge de la Rencluse',102),(104,'PD+ II E2 AD / S4','Couloir Estasen',102),(105,'D- 2 II P',' Par les lacs et le col de Coronas',102),(107,'PD- II 4.1 / E3 / S4 T4 ','Voie Normale depuis le Col des Tentes',106),(110,'AD I ','Par le Pas de l\'Ane',109),(111,'  PD II 3 ','Couloir des cabines ',109),(114,'T2 ','Par le Pas de Bellecombe',113),(117,'4.1 / AD+ / S4','Versant NW',116),(118,'T2','De Fort l\'Écluse à la Faucille',116);
/*!40000 ALTER TABLE `voie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-25 17:42:44
