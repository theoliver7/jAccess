-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3307
-- Erstellungszeit: 17. Apr 2015 um 08:52
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `jaccess`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `abteilung`
--

CREATE TABLE IF NOT EXISTS `abteilung` (
`idAbteilung` int(11) NOT NULL,
  `Abteilungsname` varchar(45) COLLATE utf8_bin NOT NULL,
  `Gebaeude` varchar(45) COLLATE utf8_bin NOT NULL,
  `Leiter` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `abteilung`
--

INSERT INTO `abteilung` (`idAbteilung`, `Abteilungsname`, `Gebaeude`, `Leiter`) VALUES
(1, 'Informatik', 'ZH1', 'Peter');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `arbeiter`
--

CREATE TABLE IF NOT EXISTS `arbeiter` (
  `idArbeiter` varchar(45) COLLATE utf8_bin NOT NULL,
  `Name` varchar(45) COLLATE utf8_bin NOT NULL,
  `Nachname` varchar(45) COLLATE utf8_bin NOT NULL,
  `Wohnort` varchar(45) COLLATE utf8_bin NOT NULL,
  `Funktion` varchar(45) COLLATE utf8_bin NOT NULL,
  `AbteilungID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `arbeiter`
--

INSERT INTO `arbeiter` (`idArbeiter`, `Name`, `Nachname`, `Wohnort`, `Funktion`, `AbteilungID`) VALUES
('45-459-5415', 'Oliver', 'Aschwanden', 'Hirzel', 'Lernender', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zeit`
--

CREATE TABLE IF NOT EXISTS `zeit` (
`idZeit` int(11) NOT NULL,
  `BeginnMorgen` varchar(45) COLLATE utf8_bin NOT NULL,
  `EndeMorgen` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `BeginnNachmittag` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `EndeNachmittag` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `Datum` varchar(45) COLLATE utf8_bin NOT NULL,
  `Total` double DEFAULT NULL,
  `ArbeiterID` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `zeit`
--

INSERT INTO `zeit` (`idZeit`, `BeginnMorgen`, `EndeMorgen`, `BeginnNachmittag`, `EndeNachmittag`, `Datum`, `Total`, `ArbeiterID`) VALUES
(2, '7:30', '11:30', '12:00', '16:00', '02.02.1983', 8.4, '45-459-5415');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `abteilung`
--
ALTER TABLE `abteilung`
 ADD PRIMARY KEY (`idAbteilung`);

--
-- Indizes für die Tabelle `arbeiter`
--
ALTER TABLE `arbeiter`
 ADD PRIMARY KEY (`idArbeiter`);

--
-- Indizes für die Tabelle `zeit`
--
ALTER TABLE `zeit`
 ADD PRIMARY KEY (`idZeit`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `abteilung`
--
ALTER TABLE `abteilung`
MODIFY `idAbteilung` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT für Tabelle `zeit`
--
ALTER TABLE `zeit`
MODIFY `idZeit` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
