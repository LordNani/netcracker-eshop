create table authorizedUser(
                               id BIGSERIAL NOT NULL,
                               userLogin varchar(100) NOT NULL,
                               userPassword varchar(100) NOT NULL,
                               userRole varchar(100) NOT NULL,
                               userName varchar(100) NOT NULL,
                               userSurname varchar(100) NOT NULL,
                               userRegistrationDate DATE NOT NULL,
                               userStatus VARCHAR(100) NOT NULL,
                               userAddress VARCHAR(100) NOT NULL,
                               userNumber VARCHAR(100) NOT NULL,
                               CONSTRAINT id_pk PRIMARY KEY(id)
);


create table productCategory(
                                id BIGSERIAL PRIMARY KEY,
                                productCategoryName VARCHAR(100) NOT NULL,
                                productSuperCategoryId integer
);

create table genre(
                      id bigserial PRIMARY KEY,
                      genreName varchar(100) NOT NULL
);

create table coverType(
                          id bigserial PRIMARY KEY,
                          coverTypeName varchar(100) NOT NULL
);

create table author(
                       id bigserial PRIMARY KEY,
                       authorName varchar(100) NOT NULL
);

create table language(
                         id bigserial PRIMARY KEY,
                         languageName varchar(100) NOT NULL
);

create table publisher(
                          id bigserial PRIMARY KEY,
                          publisherName varchar(100) NOT NULL
);

create table product (
                         id bigserial PRIMARY KEY,
                         productName varchar(255) NOT NULL,
                         productAmount bigserial NOT NULL ,
                         productPrice int NOT NULL,
                         productDiscount int NOT NULL,
                         productDate DATE NOT NULL,
                         productPict VARCHAR(100),
                         productDescription VARCHAR(100) NOT NULL,
                         productStatus VARCHAR(100) NOT NULL,
                         genre bigserial NOT NULL,
                         coverType bigserial NOT NULL,
                         author bigserial NOT NULL,
                         language bigserial NOT NULL,
                         publisher bigserial NOT NULL
);

create table orderCart(
                          id BIGSERIAL NOT NULL PRIMARY KEY,
                          userID BIGSERIAL NOT NULL,
                          courierId BIGSERIAL NOT NULL,
                          packageDescription varchar(100) NOT NULL,
                          orderStatus varchar(100) NOT NULL,
                          totalPrice BIGSERIAL NOT NULL,
                          userName varchar(100) NOT NULL,
                          deliveryTime DATE NOT NULL,
                          fullAddress VARCHAR(100) NOT NULL,
                          dontDisturb VARCHAR(1)
);


CREATE table statistic(
                          id BIGSERIAL PRIMARY KEY,
                          productCategory BIGSERIAL NOT NULL,
                          productId BIGSERIAL NOT NULL,
                          productBought BIGSERIAL NOT NULL,
                          productSold BIGSERIAL NOT NULL,
                          productRating BIGSERIAL
);

CREATE table courierCalendar(
                                courierId BIGSERIAL ,
                                F9T10 BIGSERIAL ,
                                F10T11 BIGSERIAL ,
                                F11T12 BIGSERIAL ,
                                F12T13 BIGSERIAL ,
                                F13T14 BIGSERIAL ,
                                F14T15 BIGSERIAL ,
                                F15T16 BIGSERIAL ,
                                F16T17 BIGSERIAL ,
                                F17T18 BIGSERIAL ,
                                F18T19 BIGSERIAL ,
                                F19T20 BIGSERIAL ,
                                F20T21 BIGSERIAL ,
                                userRegistrationDate DATE NOT NULL
);

create table verificationToken(
                                  id BIGSERIAL PRIMARY KEY,
                                  tokenName VARCHAR(100) NOT NULL,
                                  tokenValue VARCHAR(100) NOT NULL,
                                  tokenExpiryDate DATE NOT NULL,
                                  authorizedUserid integer NOT NULL
);

create table orderProduct (
                              id BIGSERIAL PRIMARY KEY,
                              productId BIGSERIAL NOT NULL,
                              orderCardId BIGSERIAL NOT NULL,
                              inCardProductAmount BIGSERIAL
);
