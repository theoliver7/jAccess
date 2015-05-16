
USE jAccess;
CREATE TABLE IF NOT EXISTS `abteilung` (
`idAbteilung` int(11) NOT NULL,
  `Abteilungsname` varchar(45) COLLATE utf8_bin NOT NULL,
  `Gebaeude` varchar(45) COLLATE utf8_bin NOT NULL,
  `Leiter` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `abteilung`
--

INSERT INTO `abteilung` (`idAbteilung`, `Abteilungsname`, `Gebaeude`, `Leiter`) VALUES
(10, 'Informatik', 'ZH1', 'Peter'),
(20, 'KV', 'ZH4', 'Peter Meier');

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
  `AbteilungID` int(11) NOT NULL,
  `kuerzel` varchar(45) COLLATE utf8_bin NOT NULL,
  `Pic` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT 'user.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `arbeiter`
--

INSERT INTO `arbeiter` (`idArbeiter`, `Name`, `Nachname`, `Wohnort`, `Funktion`, `AbteilungID`, `kuerzel`, `Pic`) VALUES
('04224EC2B12D80', 'Nico', 'Fehr', 'Uster', 'Coder;)', 1, 'zfehrn', 'user.png'),
('45-459-5415', 'Oliver', 'Aschwanden', 'Hirzel', 'Lernender', 1, 'zascho', 'user.png'),
('56', 'Hans', 'Nötig', 'Altstetten', 'Stv. Geschäftsführer', 2, 'zhansn', 'user.png');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `zeit`
--

CREATE TABLE IF NOT EXISTS `zeit` (
`idZeit` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ArbeiterID` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Daten für Tabelle `zeit`
--

INSERT INTO `zeit` (`idZeit`, `timestamp`, `ArbeiterID`) VALUES
(3, '2015-04-27 05:00:00', '45-459-5415'),
(5, '2015-04-27 10:01:00', '45-459-5415'),
(6, '2015-04-27 11:02:00', '45-459-5415'),
(7, '2015-04-27 15:30:00', '45-459-5415'),
(18, '2015-04-28 05:00:00', '45-459-5415'),
(19, '2015-04-28 10:00:00', '45-459-5415'),
(20, '2015-04-28 10:30:00', '45-459-5415'),
(24, '2015-04-28 14:40:00', '45-459-5415'),
(26, '2015-04-08 06:36:41', '45-459-5415'),
(27, '2015-04-08 09:36:42', '45-459-5415'),
(28, '2015-04-08 11:00:51', '45-459-5415'),
(31, '2015-04-08 14:00:59', '45-459-5415'),
(34, '2015-05-08 10:53:01', '45-459-5415'),
(35, '2015-05-08 10:53:01', '45-459-5415'),
(36, '2015-05-08 10:53:01', '45-459-5415'),
(38, '2015-05-08 10:53:01', '45-459-5415'),
(39, '2015-05-07 10:53:01', '45-459-5415'),
(40, '2015-05-07 10:53:01', '45-459-5415'),
(41, '2015-05-07 10:53:01', '45-459-5415'),
(42, '2015-05-07 10:53:01', '45-459-5415'),
(43, '2015-05-30 05:00:00', '45-459-5415'),
(44, '2015-05-30 10:00:00', '45-459-5415'),
(45, '2015-05-30 11:00:00', '45-459-5415'),
(46, '2015-05-30 14:00:00', '45-459-5415'),
(47, '2015-05-13 11:02:39', '45-459-5415'),
(48, '2015-05-13 11:02:39', '45-459-5415'),
(49, '2015-05-13 11:02:39', '45-459-5415'),
(50, '2015-05-13 11:02:39', '45-459-5415'),
(51, '2015-05-18 05:00:00', '45-459-5415'),
(52, '2015-05-18 10:00:00', '45-459-5415'),
(53, '2015-05-18 11:00:00', '45-459-5415'),
(54, '2015-05-18 15:00:00', '45-459-5415'),
(56, '2015-05-19 07:00:00', '45-459-5415'),
(57, '2015-05-19 11:00:00', '45-459-5415'),
(58, '2015-05-19 12:00:00', '45-459-5415'),
(59, '2015-05-19 16:00:00', '45-459-5415'),
(61, '2019-05-13 05:00:00', '45-459-5415'),
(62, '2015-05-19 15:00:00', '45-459-5415');

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
MODIFY `idAbteilung` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT für Tabelle `zeit`
--
ALTER TABLE `zeit`
MODIFY `idZeit` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=63;
s
