CREATE DATABASE  IF NOT EXISTS `dostavljaona` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dostavljaona`;
-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dostavljaona
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `artikl`
--

DROP TABLE IF EXISTS `artikl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artikl` (
  `idArtikl` int(11) NOT NULL AUTO_INCREMENT,
  `idRestoran` int(11) DEFAULT NULL,
  `nazivArtikla` varchar(45) DEFAULT NULL,
  `opis` varchar(45) DEFAULT NULL,
  `cijena` float DEFAULT NULL,
  `vrijemePripremeMin` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArtikl`),
  KEY `idRestoran_idx` (`idRestoran`),
  CONSTRAINT `idRestoran` FOREIGN KEY (`idRestoran`) REFERENCES `restoran` (`idrestoran`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artikl`
--

LOCK TABLES `artikl` WRITE;
/*!40000 ALTER TABLE `artikl` DISABLE KEYS */;
INSERT INTO `artikl` VALUES (1,NULL,'Idi na lokaciju',NULL,NULL,NULL);
/*!40000 ALTER TABLE `artikl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnik`
--

DROP TABLE IF EXISTS `korisnik`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnik` (
  `idKor` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(45) NOT NULL,
  `lozinka` varchar(45) NOT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `prezime` varchar(45) DEFAULT NULL,
  `brMobitela` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `uloga` varchar(45) NOT NULL DEFAULT 'KLIJENT',
  `online` tinyint(4) NOT NULL,
  PRIMARY KEY (`idKor`),
  UNIQUE KEY `korisnickoIme_UNIQUE` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnik`
--

LOCK TABLES `korisnik` WRITE;
/*!40000 ALTER TABLE `korisnik` DISABLE KEYS */;
INSERT INTO `korisnik` VALUES (4,'i','1234','Ivan','Zuglic','098 256','i.z@fer.hr','ADMIN',0),(5,'ramo','1234','Ramo','Netrebaprezime','098 560','ramo@fer.hr','VLASNIK',0),(6,'test','test','Test','ZaTest','092 000','test@test.hr','VLASNIK',0),(7,'neki_klijent','1234','Ivo','Ivic','46478','ivo@fer.hr','KLIJENT',0);
/*!40000 ALTER TABLE `korisnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `narudzba`
--

DROP TABLE IF EXISTS `narudzba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `narudzba` (
  `idNar` int(11) NOT NULL AUTO_INCREMENT,
  `idArtikl` int(11) NOT NULL,
  `idKlijent` int(11) DEFAULT NULL,
  `idDostavljac` int(11) DEFAULT NULL,
  `kolicina` int(11) DEFAULT NULL,
  `geoSirinaPreuzimanja` decimal(8,6) DEFAULT NULL,
  `geoDuzinaPreuzimanja` decimal(9,6) DEFAULT NULL,
  `geoSirinaDostave` decimal(8,6) DEFAULT NULL,
  `geoDuzinaDostave` decimal(9,6) DEFAULT NULL,
  `aktivnostNar` tinyint(4) DEFAULT '0',
  `vrijemeStvaranja` timestamp NULL DEFAULT NULL,
  `vrijemeZavrsetka` timestamp NULL DEFAULT NULL,
  `cijena` float DEFAULT NULL,
  PRIMARY KEY (`idNar`,`idArtikl`),
  KEY `idArtikl_idx` (`idArtikl`),
  KEY `idDostavljac_idx` (`idDostavljac`),
  KEY `idKlijent_idx` (`idKlijent`),
  CONSTRAINT `idArtikl` FOREIGN KEY (`idArtikl`) REFERENCES `artikl` (`idartikl`),
  CONSTRAINT `idDostavljacNar` FOREIGN KEY (`idDostavljac`) REFERENCES `korisnik` (`idkor`),
  CONSTRAINT `idKlijent` FOREIGN KEY (`idKlijent`) REFERENCES `korisnik` (`idkor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `narudzba`
--

LOCK TABLES `narudzba` WRITE;
/*!40000 ALTER TABLE `narudzba` DISABLE KEYS */;
/*!40000 ALTER TABLE `narudzba` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restoran`
--

DROP TABLE IF EXISTS `restoran`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restoran` (
  `idRestoran` int(11) NOT NULL AUTO_INCREMENT,
  `imeRestoran` varchar(45) NOT NULL,
  `opis` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `lokacijaSirina` decimal(8,6) DEFAULT NULL,
  `lokacijaDuzina` decimal(9,6) DEFAULT NULL,
  `kontaktTelefon` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `OIB` varchar(45) NOT NULL,
  `IBAN` varchar(45) DEFAULT NULL,
  `ziroRacun` varchar(45) DEFAULT NULL,
  `slika` varchar(100) DEFAULT NULL,
  `restoranOdobren` tinyint(4) NOT NULL DEFAULT '0',
  `idVlasnik` int(11) NOT NULL,
  PRIMARY KEY (`idRestoran`),
  UNIQUE KEY `OIB_UNIQUE` (`OIB`),
  KEY `idVlasnik_idx` (`idVlasnik`),
  CONSTRAINT `idVlasnik` FOREIGN KEY (`idVlasnik`) REFERENCES `korisnik` (`idkor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restoran`
--

LOCK TABLES `restoran` WRITE;
/*!40000 ALTER TABLE `restoran` DISABLE KEYS */;
INSERT INTO `restoran` VALUES (1,'Maredo','Grill i fancy jela','Hondlova 4',NULL,NULL,'0564 40','4554','89667',NULL,NULL,NULL,0,5),(2,'Samo natjural','Sirovo voce i povrce','Heinzlova 18',NULL,NULL,'40 0584 ','8973','48001',NULL,NULL,NULL,1,6);
/*!40000 ALTER TABLE `restoran` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trenutna_lokacija_dostavljaca`
--

DROP TABLE IF EXISTS `trenutna_lokacija_dostavljaca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trenutna_lokacija_dostavljaca` (
  `idDostavljac` int(11) NOT NULL,
  `geoSirinaTrenutno` decimal(8,6) DEFAULT NULL,
  `geoDuzinaTrenutno` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`idDostavljac`),
  CONSTRAINT `idDostavljac` FOREIGN KEY (`idDostavljac`) REFERENCES `korisnik` (`idkor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trenutna_lokacija_dostavljaca`
--

LOCK TABLES `trenutna_lokacija_dostavljaca` WRITE;
/*!40000 ALTER TABLE `trenutna_lokacija_dostavljaca` DISABLE KEYS */;
/*!40000 ALTER TABLE `trenutna_lokacija_dostavljaca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zadatak`
--

DROP TABLE IF EXISTS `zadatak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zadatak` (
  `idZadatak` int(11) NOT NULL AUTO_INCREMENT,
  `idNar` int(11) NOT NULL,
  `vrstaZad` varchar(45) NOT NULL,
  `zadGotov` tinyint(4) DEFAULT '0',
  `geoSirinaIdiNaLok` decimal(8,6) DEFAULT NULL,
  `geoDuzinaIdiNaLok` decimal(9,6) DEFAULT NULL,
  PRIMARY KEY (`idZadatak`),
  UNIQUE KEY `vrstaZad_UNIQUE` (`vrstaZad`),
  KEY `idNar_idx` (`idNar`),
  CONSTRAINT `idNar` FOREIGN KEY (`idNar`) REFERENCES `narudzba` (`idnar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zadatak`
--

LOCK TABLES `zadatak` WRITE;
/*!40000 ALTER TABLE `zadatak` DISABLE KEYS */;
/*!40000 ALTER TABLE `zadatak` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-16 21:12:33
