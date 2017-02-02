USE NC_LABORATORY;

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM T_CATEGORY;
DELETE FROM T_OFFER;
DELETE FROM T_COMPONENT;
DELETE FROM T_CHARACTERISTIC_CONTAINER;
DELETE FROM T_CHARACTERISTIC_COMPONENT;
DELETE FROM T_SALES_ORDER;
DELETE FROM T_ORDER;
DELETE FROM T_ORDER_COMPONENT;
DELETE FROM T_CHARACTERISTIC_ITEM;

SET FOREIGN_KEY_CHECKS=1;

/* CATEGORIES */
INSERT INTO T_CATEGORY 

(F_CATEGORY_ID, F_CATEGORY_NAME)

VALUES

(1,'Internet'),
(2,'TV'),
(3,'Telefony'),
(4,'Smartphones');



/* OFFERS FOR INTERNET CATEGORY */
INSERT INTO T_OFFER 

(F_OFFER_ID, F_CATEGORY_ID, F_OFFER_NAME)

VALUES

(1, 1, 'Meganet Ultimate'),
(2, 1, 'Meganet 100'),
(3, 1, 'Meganet 50');



/* OFFERS FOR TV CATEGORY */
INSERT INTO T_OFFER 

(F_OFFER_ID, F_CATEGORY_ID, F_OFFER_NAME)

VALUES

(4, 2, 'Samsung UN19F4000 19-Inch 720p 60Hz LED TV'),
(5, 2, 'Sony KDL48W650D 48-Inch 1080p Smart LED TV'),
(6, 2, 'LG Electronics 32LH500B 32-Inch 720p LED TV');



/*OFFERS FOR TELEFONY CATEGORY */
INSERT INTO T_OFFER 

(F_OFFER_ID, F_CATEGORY_ID, F_OFFER_NAME)

VALUES

(7, 3, 'Smart'),
(8, 3, 'Smart mini'),
(9, 3, 'Smart +');



/* OFFERS FOR SMARTPHONES CATEGORY */
INSERT INTO T_OFFER 

(F_OFFER_ID, F_CATEGORY_ID, F_OFFER_NAME)

VALUES

(10, 4, 'Iphone 7'),
(11, 4, 'Samsung Galaxy S7'),
(12, 4, 'Lenovo Vibe K5 Note');



/* COMPONENTS FOR INTERNET OFFERS */
INSERT INTO T_COMPONENT 

(F_COMPONENT_ID, F_OFFER_ID, F_COMPONENT_NAME)

VALUES

(1, 1, 'Kaspersky Antivirus'),
(2, 1, 'Parent control'),
(3, 2, 'Kaspersky Antivirus'),
(4, 2, 'Parent control'),
(5, 3, 'Avast Antivirus'),
(6, 3, 'Parent control');



/* COPMONENTS FOR TV OFFERS */
INSERT INTO T_COMPONENT 

(F_COMPONENT_ID, F_OFFER_ID, F_COMPONENT_NAME)

VALUES

(7, 4, 'Console'),
(8, 4, 'Parent control'),
(9, 5, 'Console'),
(10, 5, 'Parent control'),
(11, 6, 'Console'),
(12, 6, 'Parent control');



/* COMPONENTS FOR SMARTPHONES OFFERS */
INSERT INTO T_COMPONENT 

(F_COMPONENT_ID, F_OFFER_ID, F_COMPONENT_NAME)

VALUES

(17, 10, 'Cover'),
(18, 10, 'Headphones'),
(19, 11, 'Cover'),
(20, 11, 'Headphones'),
(21, 12, 'Cover'),
(22, 12, 'Headphones');



/* CHARACTERISTICS */
INSERT INTO T_CHARACTERISTIC_CONTAINER 

(F_CH_CONTAINER_ID, F_CH_CONTAINER_NAME) 

VALUES

(1, 'Version'),
(2, 'Color'),
(3, 'Free minutes'),
(4, 'Free megabytes'),
(5, 'RAM'),
(6, 'Storage'),
(7, 'Speed'),
(8, 'Traffic');



/* CHARACTERISTIC VALUES */
INSERT INTO T_CHARACTERISTIC_COMPONENT 

(F_CH_COMPONENT_ID, F_CH_CONTAINER_ID, F_CH_COMPONENT_VALUE)

VALUES

(1, 1, '2014'),
(2, 1, '2015'),
(3, 1, '2016'),
(4, 2, 'Black'),
(5, 2, 'White'),
(6, 2, 'Gold'),
(7, 3, '100'),
(8, 3, '150'),
(9, 3, '250'),
(10, 4, '400'),
(11, 4, '600'),
(12, 4, '800'),
(13, 5, '2Gb'),
(14, 5, '4Gb'),
(15, 5, '8Gb'),
(16, 6, '32Gb'),
(17, 6, '64Gb'),
(18, 6, '128Gb'),
(19, 7, 'Unlim'),
(20, 7, '100 Mbit/s'),
(21, 7, '50 Mbit/s'),
(22, 7, '20 Mbit/s'),
(23, 8, '200 Gb'),
(24, 8, '300 Gb'),
(25, 8, '400 Gb');

INSERT INTO T_OFFER_COMP_M2M_CH_CONT 

(F_OFFER_ID, F_COMPONENT_ID, F_CH_CONTAINER_ID)

VALUES

(1, NULL, 7),
(1, NULL, 8),
(2, NULL, 7),
(2, NULL, 8),
(3, NULL, 7),
(3, NULL, 8),
(4, NULL, 2),
(5, NULL, 2),
(6, NULL, 2),
(7, NULL, 3),
(7, NULL, 4),
(8, NULL, 3),
(8, NULL, 4),
(9, NULL, 3),
(9, NULL, 4),
(10, NULL, 2),
(10, NULL, 5),
(10, NULL, 6),
(11, NULL, 2),
(11, NULL, 5),
(11, NULL, 6),
(12, NULL, 2),
(12, NULL, 5),
(12, NULL, 6),
(NULL, 1, 1),
(NULL, 3, 1),
(NULL, 5, 1),
(NULL, 17, 2),
(NULL, 18, 2),
(NULL, 19, 2),
(NULL, 20, 2),
(NULL, 21, 2),
(NULL, 22, 2);












