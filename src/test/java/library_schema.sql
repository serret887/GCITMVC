DROP table if exists tbl_author;
CREATE TABLE `tbl_author` ( 
`authorId` INT(11) NOT NULL AUTO_INCREMENT, 
`authorName` VARCHAR(45) NOT NULL, 
PRIMARY KEY (`authorId`));
  
DROP table if exists tbl_publisher;
CREATE TABLE `tbl_publisher` ( 
`publisherId` INT(11) NOT NULL AUTO_INCREMENT, 
`publisherName` VARCHAR(45) NOT NULL,
`publisherAddress` VARCHAR(45) NULL DEFAULT NULL, 
`publisherPhone` VARCHAR(45) NULL DEFAULT NULL, 
PRIMARY KEY (`publisherId`));  

DROP table if exists tbl_book;
CREATE TABLE `tbl_book` ( 
`bookId` int(11) NOT NULL AUTO_INCREMENT,
`title` varchar(45) NOT NULL,  
`pubId` int(11) DEFAULT NULL, 
PRIMARY KEY (`bookId`));
--  ,
--  KEY `fk_publisher` (`pubId`),
--  CONSTRAINT `fk_publisher` FOREIGN KEY (`pubId`)
--  REFERENCES `tbl_publisher` (`publisherId`)
--  ON DELETE CASCADE ON UPDATE CASCADE);
DROP table if exists tbl_book_authors;
CREATE TABLE `tbl_book_authors` (
  `bookId` int(11) NOT NULL,
  `authorId` int(11) NOT NULL,
--  PRIMARY KEY (`bookId`,`authorId`)
  );
 
  DROP table if exists tbl_book_copies; 
  CREATE TABLE `tbl_book_copies` (
  `bookId` int(11) NOT NULL,
  `branchId` int(11) NOT NULL,
  `noOfCopies` int(11) DEFAULT NULL,
  PRIMARY KEY (`bookId`,`branchId`));
  
DROP table if exists tbl_book_genres;  
  CREATE TABLE `tbl_book_genres` (
  `genre_id` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  PRIMARY KEY (`genre_id`,`bookId`));

  DROP table if exists tbl_book_loans;  
  CREATE TABLE `tbl_book_loans` (
  `bookId` int(11) NOT NULL,
  `branchId` int(11) NOT NULL,
  `cardNo` int(11) NOT NULL,
  `dateOut` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `dateIn` datetime DEFAULT NULL);

  DROP table if exists tbl_borrower;  
  CREATE TABLE `tbl_borrower` (
  `cardNo` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL);
  
DROP table if exists tbl_genre;  
  CREATE TABLE `tbl_genre` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT,
  `genre_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
);
DROP table if exists tbl_library_branch;
CREATE TABLE `tbl_library_branch` (
  `branchId` int(11) NOT NULL AUTO_INCREMENT,
  `branchName` varchar(45) DEFAULT NULL,
  `branchAddress` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`branchId`)
);



