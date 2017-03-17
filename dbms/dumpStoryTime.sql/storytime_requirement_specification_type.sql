-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: storytime
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `requirement_specification_type`
--

DROP TABLE IF EXISTS `requirement_specification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirement_specification_type` (
  `Requirement_idRequirement` int(11) NOT NULL,
  `Specification_Type_idRequirement_Type` int(11) NOT NULL,
  PRIMARY KEY (`Requirement_idRequirement`,`Specification_Type_idRequirement_Type`),
  KEY `fk_Requirement_has_Specification_Type_Specification_Type1_idx` (`Specification_Type_idRequirement_Type`),
  KEY `fk_Requirement_has_Specification_Type_Requirement1_idx` (`Requirement_idRequirement`),
  CONSTRAINT `fk_Requirement_has_Specification_Type_Requirement1` FOREIGN KEY (`Requirement_idRequirement`) REFERENCES `requirement` (`idRequirement`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requirement_has_Specification_Type_Specification_Type1` FOREIGN KEY (`Specification_Type_idRequirement_Type`) REFERENCES `specification_type` (`idRequirement_Type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement_specification_type`
--

LOCK TABLES `requirement_specification_type` WRITE;
/*!40000 ALTER TABLE `requirement_specification_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement_specification_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-16 18:57:14
