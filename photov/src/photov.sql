-- MySQL dump 9.11
--
-- Host: localhost    Database: netwgbv_wgbv
-- ------------------------------------------------------
-- Server version	4.0.22-standard

--
-- Table structure for table `audit_queue`
--

DROP TABLE IF EXISTS audit_queue;
CREATE TABLE audit_queue (
  audit_id int(32) NOT NULL auto_increment,
  user_id int(32) NOT NULL default '0',
  photo_id int(32) NOT NULL default '0',
  completed enum('No','Yes') default 'No',
  reviewed enum('No','Yes') default 'No',
  approved enum('No','Yes') default 'No',
  reason varchar(255) default NULL,
  PRIMARY KEY  (audit_id)
) TYPE=MyISAM;

--
-- Dumping data for table `audit_queue`
--

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS camera;
CREATE TABLE camera (
  camera_id int(32) NOT NULL auto_increment,
  camera_name varchar(64) NOT NULL default '',
  PRIMARY KEY  (camera_id),
  KEY cam_name (camera_name)
) TYPE=MyISAM;

--
-- Dumping data for table `camera`
--

INSERT INTO camera VALUES (1,'Canon ELPH');
INSERT INTO camera VALUES (2,'Canon EOS 5');
INSERT INTO camera VALUES (3,'Canon Rebel X');
INSERT INTO camera VALUES (4,'Rolliflex TLR');
INSERT INTO camera VALUES (5,'Unknown');
INSERT INTO camera VALUES (6,'Epson Sure Shot Digital');
INSERT INTO camera VALUES (7,'Disposable');
INSERT INTO camera VALUES (8,'Canon Digital Elph');
INSERT INTO camera VALUES (9,'Olympus D-450');
INSERT INTO camera VALUES (10,'Canon T-90');
INSERT INTO camera VALUES (11,'Unknown Digital');
INSERT INTO camera VALUES (12,'Canon S400 Digital');
INSERT INTO camera VALUES (13,'Canon 10D');
INSERT INTO camera VALUES (14,'Other Digital');

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  category_id int(32) NOT NULL auto_increment,
  topic_id int(32) NOT NULL default '0',
  category_name varchar(64) NOT NULL default '',
  category_url varchar(255) NOT NULL default '',
  category_date date default NULL,
  PRIMARY KEY  (category_id),
  KEY cat_name (category_name),
  KEY cat_url (category_url),
  KEY top_id (topic_id)
) TYPE=MyISAM;

--
-- Dumping data for table `category`
--

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS country;
CREATE TABLE country (
  country_id int(32) NOT NULL auto_increment,
  country_name varchar(64) NOT NULL default '',
  country_abbr varchar(8) NOT NULL default '',
  PRIMARY KEY  (country_id),
  KEY con_name (country_name)
) TYPE=MyISAM;

--
-- Dumping data for table `country`
--

INSERT INTO country VALUES (1,'United States of America','USA');
INSERT INTO country VALUES (2,'Italy','IT');
INSERT INTO country VALUES (3,'United Kingdom','UK');
INSERT INTO country VALUES (4,'New Zealand','NZ');
INSERT INTO country VALUES (5,'Canada','CA');
INSERT INTO country VALUES (6,'None','XX');
INSERT INTO country VALUES (7,'Mexico','MX');
INSERT INTO country VALUES (8,'Germany','GM');

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS film;
CREATE TABLE film (
  film_id int(32) NOT NULL auto_increment,
  film_name varchar(64) NOT NULL default '',
  PRIMARY KEY  (film_id),
  KEY flm_name (film_name)
) TYPE=MyISAM;

--
-- Dumping data for table `film`
--

--
-- Table structure for table `grp`
--

DROP TABLE IF EXISTS grp;
CREATE TABLE grp (
  grp_id int(32) NOT NULL auto_increment,
  grp_name varchar(64) default '',
  grp_desc varchar(255) default '',
  PRIMARY KEY  (grp_id),
  KEY grp_n (grp_name)
) TYPE=MyISAM;

--
-- Dumping data for table `grp`
--

INSERT INTO grp VALUES (1,'All','All');
INSERT INTO grp VALUES (2,'Private','Private');
INSERT INTO grp VALUES (3,'Public','Public');

--
-- Table structure for table `heading`
--

DROP TABLE IF EXISTS heading;
CREATE TABLE heading (
  heading_id int(32) NOT NULL auto_increment,
  category_id int(32) NOT NULL default '0',
  heading_name varchar(64) NOT NULL default '',
  heading_url varchar(255) NOT NULL default '',
  heading_date date default NULL,
  PRIMARY KEY  (heading_id),
  KEY head_name (heading_name),
  KEY head_url (heading_url),
  KEY cat_id (category_id)
) TYPE=MyISAM;

--
-- Dumping data for table `heading`
--

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS message;
CREATE TABLE message (
  message_id int(32) NOT NULL auto_increment,
  message_name varchar(64) NOT NULL default '',
  message_subject varchar(64) NOT NULL default '',
  message_text text,
  PRIMARY KEY  (message_id)
) TYPE=MyISAM;

--
-- Dumping data for table `message`
--

INSERT INTO message VALUES (1,'Registration Email','Registration','Insert your message here.');
INSERT INTO message VALUES (2,'Forgot Password','Password Reset','Insert your message here.');
INSERT INTO message VALUES (3,'Activate Account','Account Activated','Insert your message here.');
INSERT INTO message VALUES (4,'Audit Queue','Audit Queue Update','Insert your message here.');

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS person;
CREATE TABLE person (
  person_id int(32) NOT NULL auto_increment,
  FName varchar(32) default NULL,
  MName varchar(32) default NULL,
  LName varchar(32) default NULL,
  email varchar(128) default NULL,
  class_year date default NULL,
  PRIMARY KEY  (person_id),
  KEY idxLname (LName,FName),
  KEY idxFname (FName),
  KEY idxemail (email)
) TYPE=MyISAM;

--
-- Dumping data for table `person`
--

--
-- Table structure for table `person_category`
--

DROP TABLE IF EXISTS person_category;
CREATE TABLE person_category (
  person_id int(32) NOT NULL default '0',
  category_id int(32) NOT NULL default '0',
  KEY per_cat (person_id,category_id),
  KEY cat_per (category_id,person_id)
) TYPE=MyISAM;

--
-- Dumping data for table `person_category`
--

--
-- Table structure for table `person_heading`
--

DROP TABLE IF EXISTS person_heading;
CREATE TABLE person_heading (
  person_id int(32) NOT NULL default '0',
  heading_id int(32) NOT NULL default '0',
  KEY per_head (person_id,heading_id),
  KEY head_per (heading_id,person_id)
) TYPE=MyISAM;

--
-- Dumping data for table `person_heading`
--

--
-- Table structure for table `person_photo`
--

DROP TABLE IF EXISTS person_photo;
CREATE TABLE person_photo (
  person_id int(32) NOT NULL default '0',
  photo_id int(32) NOT NULL default '0',
  KEY per_photo (person_id,photo_id),
  KEY photo_per (photo_id,person_id)
) TYPE=MyISAM;

--
-- Dumping data for table `person_photo`
--

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS photo;
CREATE TABLE photo (
  photo_id int(32) NOT NULL auto_increment,
  photographer_id int(32) default '1',
  place_id int(32) default '1',
  camera_id int(32) default '1',
  topic_id int(32) default '1',
  category_id int(32) default '1',
  heading_id int(32) default '1',
  film_id int(32) default '1',
  title varchar(255) default '',
  url varchar(255) default '',
  thumb_url varchar(255) default '',
  site_id int(32) default '1',
  vertical enum('No','Yes') default 'No',
  private enum('No','Yes') default 'No',
  all_people enum('No','Yes') default 'No',
  date date default '0000-00-00',
  tstamp timestamp(14) NOT NULL,
  notes text,
  PRIMARY KEY  (photo_id)
) TYPE=MyISAM;

--
-- Dumping data for table `photo`
--

--
-- Table structure for table `photo_grp`
--

DROP TABLE IF EXISTS photo_grp;
CREATE TABLE photo_grp (
  photo_id int(32) NOT NULL default '0',
  grp_id int(32) NOT NULL default '0',
  KEY photo_grp (photo_id,grp_id),
  KEY grp_photo (grp_id,photo_id)
) TYPE=MyISAM;

--
-- Dumping data for table `photo_grp`
--

--
-- Table structure for table `photo_new_queue`
--

DROP TABLE IF EXISTS photo_new_queue;
CREATE TABLE photo_new_queue (
  new_queue_id int(32) NOT NULL auto_increment,
  photo_id int(32) NOT NULL default '0',
  PRIMARY KEY  (new_queue_id)
) TYPE=MyISAM;

--
-- Dumping data for table `photo_new_queue`
--

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS place;
CREATE TABLE place (
  place_id int(32) NOT NULL auto_increment,
  place_name varchar(64) NOT NULL default '',
  city varchar(32) default '',
  state_id int(32) default '0',
  PRIMARY KEY  (place_id),
  KEY pl_name (place_name),
  KEY st_id (state_id)
) TYPE=MyISAM;

--
-- Dumping data for table `place`
--

--
-- Table structure for table `site`
--

DROP TABLE IF EXISTS site;
CREATE TABLE site (
  site_id int(32) NOT NULL auto_increment,
  site_name varchar(255) default '',
  site_url varchar(255) NOT NULL default '',
  PRIMARY KEY  (site_id),
  KEY st_name (site_name),
  KEY st_url (site_url)
) TYPE=MyISAM;

--
-- Dumping data for table `site`
--

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS state;
CREATE TABLE state (
  state_id int(32) NOT NULL auto_increment,
  state_name varchar(64) NOT NULL default '',
  state_abbr varchar(8) NOT NULL default '',
  country_id int(32) NOT NULL default '0',
  PRIMARY KEY  (state_id),
  KEY st_name (state_name),
  KEY st_abbr (state_abbr)
) TYPE=MyISAM;

--
-- Dumping data for table `state`
--

INSERT INTO state VALUES (1,'Alabama','AL',1);
INSERT INTO state VALUES (2,'Alaska','AK',1);
INSERT INTO state VALUES (3,'Arizona','AZ',1);
INSERT INTO state VALUES (4,'Arkansas','AR',1);
INSERT INTO state VALUES (5,'California','CA',1);
INSERT INTO state VALUES (6,'Colorado','CO',1);
INSERT INTO state VALUES (7,'Connecticut','CT',1);
INSERT INTO state VALUES (8,'Delaware','DE',1);
INSERT INTO state VALUES (9,'District of Columbia','DC',1);
INSERT INTO state VALUES (10,'Florida','FL',1);
INSERT INTO state VALUES (11,'Georgia','GA',1);
INSERT INTO state VALUES (12,'Hawaii','HI',1);
INSERT INTO state VALUES (13,'Idaho','ID',1);
INSERT INTO state VALUES (14,'Illinois','IL',1);
INSERT INTO state VALUES (15,'Indiana','IN',1);
INSERT INTO state VALUES (16,'Iowa','IA',1);
INSERT INTO state VALUES (17,'Kansas','KS',1);
INSERT INTO state VALUES (18,'Kentucky','KY',1);
INSERT INTO state VALUES (19,'Louisiana','LA',1);
INSERT INTO state VALUES (20,'Maine','ME',1);
INSERT INTO state VALUES (21,'Maryland','MD',1);
INSERT INTO state VALUES (22,'Massachusetts','MA',1);
INSERT INTO state VALUES (23,'Michigan','MI',1);
INSERT INTO state VALUES (24,'Minnesota','MN',1);
INSERT INTO state VALUES (25,'Mississippi','MS',1);
INSERT INTO state VALUES (26,'Missouri','MO',1);
INSERT INTO state VALUES (27,'Montana','MT',1);
INSERT INTO state VALUES (28,'Nebraska','NE',1);
INSERT INTO state VALUES (29,'Nevada','NV',1);
INSERT INTO state VALUES (30,'New Hampshire','NH',1);
INSERT INTO state VALUES (31,'New Jersey','NJ',1);
INSERT INTO state VALUES (32,'New Mexico','NM',1);
INSERT INTO state VALUES (33,'New York','NY',1);
INSERT INTO state VALUES (34,'North Carolina','NC',1);
INSERT INTO state VALUES (35,'North Dakota','ND',1);
INSERT INTO state VALUES (36,'Ohio','OH',1);
INSERT INTO state VALUES (37,'Oklahoma','OK',1);
INSERT INTO state VALUES (38,'Oregon','OR',1);
INSERT INTO state VALUES (39,'Pennsylvania','PA',1);
INSERT INTO state VALUES (40,'Rhode Island','RI',1);
INSERT INTO state VALUES (41,'South Carolina','SC',1);
INSERT INTO state VALUES (42,'South Dakota','SD',1);
INSERT INTO state VALUES (43,'Tennessee','TN',1);
INSERT INTO state VALUES (44,'Texas','TX',1);
INSERT INTO state VALUES (45,'Utah','UT',1);
INSERT INTO state VALUES (46,'Vermont','VT',1);
INSERT INTO state VALUES (47,'Virginia','VA',1);
INSERT INTO state VALUES (48,'Washington','WA',1);
INSERT INTO state VALUES (49,'West Virginia','WV',1);
INSERT INTO state VALUES (50,'Wisconsin','WI',1);
INSERT INTO state VALUES (51,'Wyoming','WY',1);

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS topic;
CREATE TABLE topic (
  topic_id int(32) NOT NULL auto_increment,
  topic_name varchar(64) NOT NULL default '',
  topic_url varchar(255) NOT NULL default '',
  topic_date date default '0000-00-00',
  PRIMARY KEY  (topic_id),
  KEY tp_name (topic_name),
  KEY tp_url (topic_url)
) TYPE=MyISAM;

--
-- Dumping data for table `topic`
--

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id int(32) NOT NULL auto_increment,
  person_id int(32) default NULL,
  grp_id int(32) NOT NULL default '0',
  username varchar(32) NOT NULL default '',
  password varchar(32) NOT NULL default '',
  active enum('No','Yes') default NULL,
  can_update enum('Yes','No') default 'No',
  PRIMARY KEY  (user_id),
  KEY usr_grp (user_id,grp_id),
  KEY usr_name (username)
) TYPE=MyISAM;

--
-- Dumping data for table `user`
--

--
-- Table structure for table `user_grp`
--

DROP TABLE IF EXISTS user_grp;
CREATE TABLE user_grp (
  user_id int(32) NOT NULL default '0',
  grp_id int(32) NOT NULL default '0',
  KEY user_grp (user_id,grp_id),
  KEY grp_user (grp_id,user_id)
) TYPE=MyISAM;

--
-- Dumping data for table `user_grp`
--

