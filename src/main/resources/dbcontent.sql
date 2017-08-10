set search_path = camp;

insert into t_salescontenttype(name) values
('Lebensmittel'),('Material'),('Kraftstoff'),('Reisekosten'),('Unterkunft'),('Anzahlung/Vorkasse');

insert into t_location(pk, name, url) values 
(1, 'Olfen', 'http://www.freizeitheim-olfen.de/index.php/startseite.html'),
(2, 'Kiental', 'http://www.lungenliga.ch/de/lungenliga-beider-basel/dienstleistungen/zu-vermieten/ferienhaus-bergfrieden/bildgalerie.html'),
(3, 'Heilbronn', 'http://www.bruedergemeinde-hn.de/'),
(4, 'Hohenhaslach', 'http://www.gemeinde.hohenhaslach.elk-wue.de/cvjm/jugendhaus/'),
(5, 'Rehe', 'http://www.cew-rehe.de/'),
(6, 'Wrist', 'http://www.missionshaus-wrist.de/');

select setval('t_location_pk_seq', max(pk)) from t_location;

insert into t_camp(pk, name, arrive, depart, fk_location, min_age, max_age, price, countries) values
(2, 'Teenagerfreizeit Schweiz', '2016-07-30', '2016-08-13', 2, 12, 16, null, null),
(3, 'Rehe I', '2016-01-22', '2016-01-24', 5, 9, 14, null, null),
(4, 'Rehe II', '2016-03-11', '2016-03-13', 5, 9, 14, null, null),
(5, 'Wrist', '2016-10-15', '2016-10-22', 6, 9, 14, null, null),
(6, 'Olfen I', '2016-03-27', '2016-04-02', 1, 9, 14, null, null),
(7, 'Olfen II', '2016-10-29', '2016-11-05', 1, 9, 14, null, null),
(9, 'Hohenhaslach II', '2016-10-29', '2016-11-05', 4, 9, 14, null, null),
(10, 'Heimfreizeit', '2016-08-29', '2016-09-09', 3, 6, 14, null, null),
(11, 'Hohenhaslach I', '2016-03-27', '2016-04-02', 4, 9, 14, null, null),
(12, 'Rehe I', '2017-01-27', '2017-01-29', 5, 9, 14, null, null),
(13, 'Rehe II', '2017-03-10', '2017-03-12', 5, 9, 14, null, null),
(14, 'Olfen Grow Up', '2017-04-08', '2017-04-15', 1, 13, 18, null, null),
(15, 'Olfen I', '2017-04-16', '2017-04-22', 1, 8, 13, null, null),
(16, 'Teenagerfreizeit (Schweiz)', '2017-07-29', '2017-08-12', 2, 13, 17, 'bei Anmeldung<br />\r\nbis 31.05.: 340 €<br />\r\nab 01.06.: 370 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Hamburg, Hessen, Mecklenburg-Vorpommern, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Schleswig-Holstein'),
(17, 'Heimfreizeit', '2017-08-28', '2017-09-08', 3, 6, 14, 'kostenfrei', 'Heilbronn und Umgebung<br />\r\n&nbsp;'),
(19, 'Herbstfreizeit (Olfen II)', '2017-10-28', '2017-11-04', 1, 8, 12, 'bei Anmeldung<br />\r\nbis 28.09.: 90 €<br />\r\nab 29.09.: 100 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Nordrhein-Westfalen<br />'),
(20, 'Herbstfreizeit', '2017-10-28', '2017-11-04', 4, 12, 15, 'bei Anmeldung<br />\r\nbis 28.09.: 90 €<br />\r\nab 29.09.: 100 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Nordrhein-Westfalen<br />'),
(21, 'Wochenendfreizeit (Rehe I)', '2018-01-26', '2018-01-28', 5, 9, 14, '50 €', 'Hessen, Nordrhein-Westfalen, Rheinland-Pfalz<br />'),
(22, 'Wochenendfreizeit (Rehe II)', '2018-03-09', '2018-03-11', 5, 9, 14, '50 €', 'Hessen, Nordrhein-Westfalen, Rheinland-Pfalz<br />'),
(23, 'Grow Up', '2018-03-24', '2018-03-31', 1, 13, 18, 'bei Anmeldung<br />\r\nbis 28.02.: 100 €,<br />\r\nab 01.03.: 115 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Bremen, Hessen, Mecklenburg-Vorpommern, Niedersachsen, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Sachsen-Anhalt, Thüringen'),
(24, 'Osterfreizeit (Olfen I)', '2018-04-01', '2018-04-07', 1, 8, 13, 'bei Anmeldung<br />\r\nbis 28.02.: 90 €,<br />\r\nab 01.03.: 100 €\r\n', 'Baden-Württemberg, Bayern, Brandenburg, Hessen, Nordrhein-Westfalen, Rheinland-Pfalz, Saarland, Sachsen, Schleswig-Holstein, Thüringen'),
(25, 'Teenagerfreizeit (Schweiz)', '2018-07-28', '2018-08-11', 2, 13, 17, 'bei Anmeldung<br />\r\nbis 31.05.: 340 €<br />\r\nab 01.06.: 370 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg, Hamburg, Mecklenburg-Vorpommern, Nordrhein-Westfalen, Sachsen, Schleswig-Holstein, Thüringen'),
(26, 'Heimfreizeit', '2018-08-20', '2018-08-31', 3, 6, 14, 'kostenfrei', 'Heilbronn und Umgebung'),
(27, 'Herbstfreizeit (Olfen II)', '2018-10-27', '2018-11-03', 1, 8, 12, 'bei Anmeldung<br />\r\nbis 28.09.: 90 €<br />\r\nab 29.09.: 100 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg'),
(28, 'Herbstfreizeit', '2018-10-27', '2018-11-03', 4, 12, 15, 'bei Anmeldung<br />\r\nbis 28.09.: 90 €<br />\r\nab 29.09.: 100 €', 'Baden-Württemberg, Bayern, Berlin, Brandenburg');

select setval('t_camp_pk_seq', max(pk)) from t_camp;
