
USE libros;
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

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `idLibro` int NOT NULL AUTO_INCREMENT,
  `portada` varchar(45) NOT NULL,
  `genero` varchar(45) NOT NULL,
  `anoPublicacion` varchar(45) NOT NULL,
  `disponible` tinyint NOT NULL,
  `autor` varchar(45) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  PRIMARY KEY (`idLibro`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` (`idLibro`, `portada`, `genero`, `anoPublicacion`, `disponible`, `autor`, `titulo`) VALUES
(1,  'C:\\covers\\1984.jpg',              'Distopía',           '1949',         1, 'George Orwell',       '1984'),
(2,  'C:\\covers\\hobbit.jpg',           'Fantasía',           '1937',         1, 'J.R.R. Tolkien',       'El Hobbit'),
(3,  'C:\\covers\\harrypotter1.jpg',     'Fantasía',           '1997',         1, 'J.K. Rowling',         'Harry Potter y la piedra filosofal'),
(4,  'C:\\covers\\dune.jpg',             'Ciencia Ficción',    '1965',         0, 'Frank Herbert',        'Dune'),
(5,  'C:\\covers\\gatsby.jpg',           'Clásico',            '1925',         1, 'F. Scott Fitzgerald',  'El gran Gatsby'),
(6,  'C:\\covers\\mockingbird.jpg',      'Drama',              '1960',         1, 'Harper Lee',           'Matar a un ruiseñor'),
(7,  'C:\\covers\\cienanos.jpg',         'Realismo Mágico',    '1967',         1, 'Gabriel García Márquez','Cien años de soledad'),
(8,  'C:\\covers\\fahrenheit451.jpg',    'Distopía',           '1953',         0, 'Ray Bradbury',         'Fahrenheit 451'),
(9,  'C:\\covers\\lotr1.jpg',            'Fantasía',           '1954',         1, 'J.R.R. Tolkien',       'La Comunidad del Anillo'),
(10, 'C:\\covers\\prideprejudice.jpg',   'Romance',            '1813',         1, 'Jane Austen',          'Orgullo y prejuicio'),
(11, 'C:\\covers\\origen.jpg',           'Thriller',           '2017',         1, 'Dan Brown',            'Origen'),
(12, 'C:\\covers\\dracula.jpg',          'Terror',             '1897',         0, 'Bram Stoker',          'Drácula'),
(13, 'C:\\covers\\elalquimista.jpg',     'Ficción',            '1988',         1, 'Paulo Coelho',         'El alquimista'),
(14, 'C:\\covers\\code.jpg',             'Tecnología',         '2003',         1, 'Charles Petzold',      'Code'),
(15, 'C:\\covers\\iq84.jpg',             'Ficción',            '2009',         0, 'Haruki Murakami',      '1Q84'),
(16, 'C:\\covers\\sapiens.jpg',          'Historia',           '2011',         1, 'Yuval Noah Harari',    'Sapiens'),
(17, 'C:\\covers\\neuromancer.jpg',      'Ciberpunk',          '1984',         1, 'William Gibson',       'Neuromante'),
(18, 'C:\\covers\\it.jpg',               'Terror',             '1986',         0, 'Stephen King',         'It'),
(19, 'C:\\covers\\rebelion.jpg',         'Política',           '1945',         1, 'George Orwell',        'Rebelión en la granja'),
(20, 'C:\\covers\\cronicamuerte.jpg',    'Realismo Mágico',    '1981',         1, 'Gabriel García Márquez','Crónica de una muerte anunciada');
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

