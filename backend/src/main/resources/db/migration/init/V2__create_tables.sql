
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME, USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('0', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMINNAME', 'ADMINSURNAME', TO_DATE('1981-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ON', 'ADMINSTREET', 'ADMINNUMBER');
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME,USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('1', 'MANAGER', 'MANAGER', 'MANAGER', 'MANAGERNAME', 'MANAGERSURNAME', TO_DATE('1982-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ON', 'MANAGERSTREET', 'MANAGERNUMBER');
INSERT INTO AUTHORIZEDUSER (ID, USERLOGIN, USERPASSWORD, USERROLE, USERNAME, USERSURNAME,USERREGISTRATIONDATE, USERSTATUS, USERADDRESS, USERNUMBER) VALUES ('2', 'COURIER', 'COURIER', 'COURIER', 'COURIERNAME', 'COURIERSURNAME', TO_DATE('1983-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'ON', 'COURIERSTREET', 'COURIERNUMBER');
--
INSERT INTO PRODUCTCATEGORY (ID, PRODUCTCATEGORYNAME) VALUES ('1', 'BOOK');
INSERT INTO PRODUCTCATEGORY (ID, PRODUCTCATEGORYNAME, PRODUCTSUPERCATEGORYID) VALUES ('2', 'BIGBOOK', '1');
--
INSERT INTO PRODUCT (ID, PRODUCTCATEGORY, PRODUCTNAME, PRODUCTAMOUNT, PRODUCTPRICE, PRODUCTDISCOUNT, PRODUCTDATE, PRODUCTPICT, PRODUCTDESCRIPTION, PRODUCTSTATUS) VALUES ('0', '2', 'BIGBOOK', '100', '100', '0', TO_DATE('2021-06-02 23:39:34', 'YYYY-MM-DD HH24:MI:SS'), 'TESTURL', 'TESTDESC', 'T');
--
INSERT INTO ORDERPRODUCKT (ID, PRODUCTID, ORDERID, INCARTPRODUCTAMOUNT) VALUES ('0', '0', '0', '10');
INSERT INTO ORDERCART (ID, USERID, COURIERID, PACKAGEDESCRIPTION, ORDERSTATUS, TOTALPRICE, USERNAME, DELIVERYTIME, FULLADDRESS, DONTDISTURB) VALUES ('0', '0', '2', 'testDescription', 'on', '100', 'USER0', TO_DATE('2021-06-02 23:20:43', 'YYYY-MM-DD HH24:MI:SS'), 'USER0STREET', 'T');
--

--

