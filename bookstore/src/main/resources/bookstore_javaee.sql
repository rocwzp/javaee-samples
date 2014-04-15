
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;


CREATE TABLE categories (
  cat_id int(11) NOT NULL auto_increment,
  name varchar(100) default NULL,
  description longtext,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (cat_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE products (
  product_id int(11) NOT NULL auto_increment,
  cat_id int(11) default NULL,
  name varchar(100) default NULL,
  description longtext,
  price decimal(19,2) default NULL,
  image_url varchar(255) default NULL,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (product_id),
  KEY FKC42BD16474DE085F (cat_id),
  CONSTRAINT FKC42BD16474DE085F FOREIGN KEY (cat_id) REFERENCES categories (cat_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE inventory (
  inv_id int(11) NOT NULL auto_increment,
  product_id int(11) default NULL,
  quantity bigint(20) NOT NULL,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (inv_id),
  KEY FK8790195CA6AA8A7D (product_id),
  CONSTRAINT FK8790195CA6AA8A7D FOREIGN KEY (product_id) REFERENCES products (product_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE addresses (
  addr_id int(11) NOT NULL auto_increment,
  contact_name varchar(255) default NULL,
  addr_line1 varchar(255) default NULL,
  addr_line2 varchar(255) default NULL,
  city varchar(255) default NULL,
  state varchar(255) default NULL,
  zip_code varchar(255) default NULL,
  country varchar(255) default NULL,
  PRIMARY KEY  (addr_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE customers (
  cust_id int(11) NOT NULL auto_increment,
  email varchar(100) default NULL,
  password varchar(50) default NULL,
  firstname varchar(50) default NULL,
  lastname varchar(50) default NULL,
  phone varchar(15) default NULL,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (cust_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE payments (
  payment_id int(11) NOT NULL auto_increment,
  cc_number varchar(255) default NULL,
  cvv varchar(255) default NULL,
  expiry_date datetime default NULL,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (payment_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE orders (
  order_id int(11) NOT NULL auto_increment,
  cust_id int(11) NOT NULL,
  billing_addr_id int(11) NOT NULL,
  shipping_addr_id int(11) NOT NULL,
  payment_id int(11) NOT NULL,
  status int(11) default NULL,
  created_on datetime NOT NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (order_id),
  KEY FKC3DF62E5ED72317C (billing_addr_id),
  KEY FKC3DF62E581DEDF1D (payment_id),
  KEY FKC3DF62E535F009AF (shipping_addr_id),
  KEY FKC3DF62E52ABA97A2 (cust_id),
  CONSTRAINT FKC3DF62E52ABA97A2 FOREIGN KEY (cust_id) REFERENCES customers (cust_id),
  CONSTRAINT FKC3DF62E535F009AF FOREIGN KEY (shipping_addr_id) REFERENCES addresses (addr_id),
  CONSTRAINT FKC3DF62E581DEDF1D FOREIGN KEY (payment_id) REFERENCES payments (payment_id),
  CONSTRAINT FKC3DF62E5ED72317C FOREIGN KEY (billing_addr_id) REFERENCES addresses (addr_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE order_items (
  order_item_id int(11) NOT NULL auto_increment,
  order_id int(11) NOT NULL,
  product_id int(11) default NULL,
  quantity int(11) NOT NULL,
  created_on datetime default NULL,
  updated_on datetime default NULL,
  PRIMARY KEY  (order_item_id),
  KEY FK75109F8FD1BE6DD (order_id),
  KEY FK75109F8FA6AA8A7D (product_id),
  CONSTRAINT FK75109F8FA6AA8A7D FOREIGN KEY (product_id) REFERENCES products (product_id),
  CONSTRAINT FK75109F8FD1BE6DD FOREIGN KEY (order_id) REFERENCES orders (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO categories (cat_id,created_on,description,name,updated_on) VALUES 
 (1,'2013-08-27 00:00:00','Java Programming Books','Java Books',NULL),
 (2,'2013-08-27 00:00:00','MS.NET Programming Books','.NET Books',NULL),
 (3,'2013-08-27 00:00:00','General Books','General Books',NULL);

INSERT INTO products (product_id,created_on,description,image_url,name,price,updated_on,cat_id) VALUES 
 (1,'2013-08-28 00:00:00','Java Persistence with MyBatis 3','MyBatis3.jpg','Java Persistence with MyBatis 3','500.00',NULL,1),
 (2,'2013-08-28 00:00:00','PrimeFaces Beginner\'s Guide: RAW','PFBG_Raw.jpg','PrimeFaces Beginner\'s Guide','680.00',NULL,1),
 (3,'2013-08-28 00:00:00','CleanCode','CleanCode.jpg','CleanCode','4000.00',NULL,1),
 (4,'2013-08-28 00:00:00','FirstLookatC#4.5','FirstLookatCSharp4.5.jpg','FirstLookatC#4.5.','650.00',NULL,2),
 (5,'2013-08-28 00:00:00','HibernateInAction','HibernateInAction.jpg','HibernateInAction','480.00',NULL,1),
 (6,'2013-08-28 00:00:00','JSF2_Cookbook','JSF2_Cookbook.jpg','JSF2_Cookbook','560.00',NULL,1),
 (7,'2013-08-28 00:00:00','PF_Cookbook','PF_Cookbook.jpg','PF_Cookbook','750.00',NULL,1),
 (8,'2013-08-28 00:00:00','PF_Starter','PF_Starter.jpg','PF_Starter','150.00',NULL,1),
 (9,'2013-08-28 00:00:00','ProSpring3','ProSpring3.jpg','ProSpring3','450.00',NULL,1),
 (10,'2013-08-28 00:00:00','Spring Recipies','nocover_book.png','Spring Recipies','2500.00',NULL,1),
 (11,'2013-08-28 00:00:00','Visual Studio 2012 Cookbook','VS2012Cookbook.jpg','Visual Studio 2012 Cookbook','1200.00',NULL,2),
 (12,'2013-08-28 00:00:00','The Monk Who Sold His Ferrari','nocover_book.png','The Monk Who Sold His Ferrari','600.00',NULL,3),
 (13,'2013-08-28 00:00:00','Who Will Cry When You Die?','nocover_book.png','Who Will Cry When You Die?','600.00',NULL,3),
 (14,'2013-08-28 00:00:00','The Alchemist','nocover_book.png','The Alchemist','600.00',NULL,3),
 (15,'2013-08-28 00:00:00','Life is What You Make it','nocover_book.png','Life is What You Make it','600.00',NULL,3);

