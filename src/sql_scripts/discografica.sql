CREATE DATABASE IF NOT EXISTS `discografica`;

USE `discografica`;

DROP TABLE IF EXISTS `cancion`;
DROP TABLE IF EXISTS `album`;

CREATE TABLE `album` (
	`id` INTEGER(3) NOT NULL AUTO_INCREMENT,
	`titulo` VARCHAR(90) NOT NULL,
    `grupo`  VARCHAR(45) NOT NULL,
    `duracion` TIME DEFAULT NULL,
    `productor` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cancion` (
	`id` INTEGER(3) NOT NULL AUTO_INCREMENT,
	`titulo` VARCHAR(90) NOT NULL,
    `duracion` TIME DEFAULT NULL,
    `escritor` VARCHAR(45) DEFAULT NULL,
    `album` INTEGER(3) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`album`) REFERENCES `album` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `album`
VALUES ('1', '1_Sgt. Pepper’s Lonely Heart’s Club Band', 'The Beatles', '00:39:36', 'George Martin'),
		('2', '2_The Dark Side of the Moon', 'Pink Floyd', '00:42:59', 'Pink Floyd'),
        ('3', '3_Thriller', 'Michael Jackson', '00:42:19', 'Quincy Jones');

INSERT INTO `cancion`
VALUES ('1', '1_Sgt. Pepper’s Lonely Heart’s Club Band', '00:01:59', 'Lennon-McCarthey', '1'),
		('2', '1_With a Little Help from My Friends', '00:02:46', 'Lennon-McCartney', '1'),
        ('3', '1_Lucy in the Sky with Diamonds', '00:03:28', 'Lennon-McCartney', '1'),
        ('4', '1_Getting Better', '00:02:47', 'Lennon-McCartney', '1'),
        ('5', '1_Fixing a Hole', '00:02:36', 'Lennon-McCartney', '1'),
        ('6', '1_She\'s Leaving Home', '00:03:26', 'Lennon-McCartney', '1'),
        ('7', '1_Being for the Benefit of Mr. Kite!', '00:02:37', 'Lennon-McCartney', '1'),
        ('8', '1_Within You Without You', '00:05:05', 'Lennon-McCartney', '1'),
        ('9', '1_When I\'m Sixty-Four', '00:02:37', 'Lennon-McCartney', '1'),
        ('10', '1_Lovely Rita', '00:02:36', 'Lennon-McCartney', '1'),
        ('11', '1_Good Morning Good Morning', '00:02:41', 'Lennon-McCartney', '1'),
        ('12', '1_Sgt. Pepper’s Lonely Heart’s Club Band (Reprise)', '00:01:18', 'Lennon-McCarthey', '1'),
        ('13', '1_A Day in the Life', '00:05:35', 'Lennon-McCartney', '1'),
        ('14', '1_Speak to Me', '00:01:07', 'Nick Mason', '2'),
        ('15', '2_Breathe', '00:02:47', 'Roger Waters-Richard Wright-David Gilmour', '2'),
        ('16', '2_On the Run (instrumental)', '00:03:45', 'David Gilmour-Roger Waters', '2'),
        ('17', '2_Time', '00:06:52', 'Pink Floyd', '2'),
        ('18', '2_The Great Gig in the Sky', '00:04:36', 'Richard Wright-Clare Torry', '2'),
        ('19', '2_Money', '00:06:22', 'Roger Waters', '2'),
        ('20', '2_Us and Them', '00:07:51', 'Richard Wright-Roger Waters', '2'),
        ('21', '2_Any Colour You Like', '00:03:25', 'David Gilmour-Nick Mason-Richard Wright', '2'),
        ('22', '2_Brain Damage', '00:03:48', 'Roger Waters', '2'),
        ('23', '2_Eclipse', '00:03:48', 'Roger Waters', '2'),
        ('24', '3_Wanna Be Startin\' Somethin\'', '00:06:03', 'Michael Jackson', '3'),
        ('25', '3_Baby Be Mine', '00:04:20', 'Rod Temperton', '3'),
        ('26', '3_The Girl Is Mine', '00:03:41', 'Michael Jackson', '3'),
        ('27', '3_Vincent Price excerpt from Thriller Voice-Over Session', '00:00:24', 'Temperton', '3'),
        ('28', '3_Thriller', '00:05:57', 'Temperton', '3'),
        ('29', '3_Beat It', '00:04:18', 'Michael Jackson', '3'),
        ('30', '3_Billie Jean', '00:04:54', 'Michael Jackson', '3'),
        ('31', '3_Human Nature', '00:04:07', 'Steve Porcaro-John Bettis', '3'),
        ('32', '3_P.Y.T. (Pretty Young Thing)', '00:03:58', 'James Ingram-Jones', '3'),
        ('33', '3_The Lady in My Life', '00:04:59', 'Temperton', '3');
