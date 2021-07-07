
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME, USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('9990', 'ADMIN@gmail.com', '$2a$10$x.MxMBwMxr8aaPtdEIyd0uTkZ6986p9JmrBT3t1yO5V/cRf9ArJom', 'ADMIN', 'ADMINNAME', 'ADMINSURNAME', TO_DATE('1981-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ACTIVE', 'ADMINSTREET', 'ADMINNUMBER');
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME,USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('9991', 'MANAGER@gmail.com','$2a$10$x.MxMBwMxr8aaPtdEIyd0uTkZ6986p9JmrBT3t1yO5V/cRf9ArJom', 'MANAGER', 'yarr', 'khr', TO_DATE('1982-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'INACTIVE', 'MANAGERSTREET', 'MANAGERNUMBER');
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME,USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('9992', 'COURIER@gmail.com','$2a$10$x.MxMBwMxr8aaPtdEIyd0uTkZ6986p9JmrBT3t1yO5V/cRf9ArJom', 'COURIER', 'mand', 'hor', TO_DATE('1983-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'TERMINATED', 'COURIERSTREET', 'COURIERNUMBER');
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME,USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('9993', 'temp@gmail.com','$2a$10$x.MxMBwMxr8aaPtdEIyd0uTkZ6986p9JmrBT3t1yO5V/cRf9ArJom', 'USER', 'mand', 'hor', TO_DATE('1983-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ACTIVE', 'COURIERSTREET', 'COURIERNUMBER');
--
INSERT INTO GENRE (ID, GENRENAME) VALUES ('0', 'Fantasy');
INSERT INTO GENRE (ID, GENRENAME) VALUES ('1', 'Sci-Fi');
INSERT INTO GENRE (ID, GENRENAME) VALUES ('3', 'Adventure');
--
INSERT INTO COVERTYPE (ID, COVERTYPENAME) VALUES ('0', 'Hard');
INSERT INTO COVERTYPE (ID, COVERTYPENAME) VALUES ('1', 'Soft');
--
INSERT INTO AUTHOR (ID, AUTHORNAME) VALUES ('0', 'Pushkin');
INSERT INTO AUTHOR (ID, AUTHORNAME) VALUES ('1', 'Shevchenko');
INSERT INTO AUTHOR (ID, AUTHORNAME) VALUES ('2', 'George R. R. Martin');
INSERT INTO AUTHOR (ID, AUTHORNAME) VALUES ('3', 'J. K. Rowling');
--
INSERT INTO LANGUAGE (ID, LANGUAGENAME) VALUES ('0', 'English');
INSERT INTO LANGUAGE (ID, LANGUAGENAME) VALUES ('1', 'Ukrainian');
--
INSERT INTO PUBLISHER (ID, PUBLISHERNAME) VALUES ('0', 'A-Ba-Ba-Ga-La-Ma-Ga');
INSERT INTO PUBLISHER (ID, PUBLISHERNAME) VALUES ('1', 'Mahaon');
--
INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
                     VALUES ('0', 'BIGBOOK', '100', '100', '0', TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
                             'TESTURL', 'TESTDESC', 'INACTIVE', '0', '0', '0', '0', '0');
INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9990', 'BIGBOOK2', '100', '100', '0', TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'TESTURL', 'TESTDESC', 'INACTIVE', '1', '1', '1', '1', '1');
INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9992', 'Harry Potter', '20', '200', '0', TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images.penguinrandomhouse.com/cover/9780739360385',
        'Harry Potter is leaving Privet Drive for the last time. But as he climbs into the sidecar of Hagrid’s motorbike and they take to the skies, he knows Lord Voldemort and the Death Eaters will not be far behind.',
        'ACTIVE', '1', '0', '3', '1', '1');

INSERT INTO ORDERCART (ID, USERID, COURIERID, PACKAGEDESCRIPTION, ORDERSTATUS, TOTALPRICE, USERNAME, DELIVERYTIME,  FULLADDRESS, DONTDISTURB) VALUES ('0', '9993', '9992', 'testDescription', 'DELIVERED', '100', 'USER0', TO_DATE('2021-06-02 23:20:43', 'YYYY-MM-DD HH24:MI:SS'), 'USER0STREET', 'T' );

INSERT INTO public.couriercalendar(courierid, cartId, hour, calendardate)
VALUES (9992 , 0, 9, TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'));




INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9993', 'War and Peace', '20', '202', '0', TO_DATE('2021-06-06 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images-na.ssl-images-amazon.com/images/I/A1aDb5U5myL.jpg', 'TESTDESC', 'ACTIVE', '1', '0', '1', '1', '1');

INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9994', 'In Search of Lost Time', '20', '201', '0', TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://m.media-amazon.com/images/I/411fuVxxG4L.jpg',
        'In Search of Lost Time follows the narrator''s recollections of childhood and experiences into adulthood in the late 19th century and early 20th century high society France, while reflecting on the loss of time and lack of meaning in the world. The novel began to take shape in 1909. Proust continued to work on it until his final illness in the autumn of 1922 forced him to break off. Proust established the structure early on, but even after volumes were initially finished, he continued to add new material and edited one volume after another for publication.',
        'ACTIVE', '1', '0', '1', '1', '1');

INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9995', 'The Ambassadors', '20', '220', '0', TO_DATE('2021-06-04 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images-na.ssl-images-amazon.com/images/I/51xBnzWibyL._SX310_BO1,204,203,200_.jpg',
        'In this complex tale of self-discovery, Henry James invokes his favorite theme: the clash of American innocence with European experience. It traces the path of an aging idealist, Lambert Strether, who arrives in Paris intending to persuade his young charge to abandon an obsession with a French woman and return home. Once abroad, however, Strether arrives at unexpected conclusions.',
        'ACTIVE', '1', '0', '1', '1', '1');

INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9997', 'Around the World in 80 Days', '20', '250', '2', TO_DATE('2021-07-07 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images-na.ssl-images-amazon.com/images/I/71RbapQG1LL.jpg',
        'A pioneer of the science-fiction genre, Jules Verne, a French author, is known for his writings about cosmic, atmospheric and underwater travel which runs our imagination way ahead of its time. ',
        'ACTIVE', '1', '1', '3', '0', '1');

INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9998', 'The Three Musketeers', '20', '300', '0', TO_DATE('2021-03-03 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images-na.ssl-images-amazon.com/images/I/41swSYqFcyL._SY344_BO1,204,203,200_.jpg',
        '17th century France: Young DArtagnan leaves his home and travels to Paris with dreams of joining The Musketeers of Guard—the glamourous and gallant group of men who guard Louis XIII, the King of France.',
        'ACTIVE', '0', '0', '0', '0', '0');

INSERT INTO PRODUCT (ID, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE,
                     PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS, GENRE, COVERTYPE, AUTHOR, LANGUAGE, PUBLISHER)
VALUES ('9999', 'The Adventures of Tom Sawyer', '20', '130', '2', TO_DATE('2021-01-01 23:39:34', 'YYYY-MM-DD HH24:MI:SS'),
        'https://images-na.ssl-images-amazon.com/images/I/51uHV4BrMcS._SX460_BO1,204,203,200_.jpg',
        'Satirical and nostalgic, Mark Twain’s epic masterpiece, the Adventures of Tom Sawyer, brings out the inequities of adult reality from beneath the innocence of childhood. ',
        'ACTIVE', '0', '1', '0', '0', '0');

