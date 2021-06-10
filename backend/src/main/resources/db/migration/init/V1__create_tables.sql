create table authorizedUser(
                               id bigserial NOT NULL,
                               userLogin varchar(100) NOT NULL,
                               userPassword varchar(100) NOT NULL,
                               userRole varchar(100) NOT NULL,
                               userName varchar(100) NOT NULL,
                               userRegistrationDate DATE NOT NULL,
                               userStatus VARCHAR(100) NOT NULL,
                               userAddress VARCHAR(100) NOT NULL,
                               userNumber VARCHAR(100) NOT NULL,
                               CONSTRAINT id_pk PRIMARY KEY(id)
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

create table orderProduct(
                              id bigserial NOT NULL PRIMARY KEY,
                              productId bigserial NOT NULL,
                              orderId bigserial NOT NULL,
                              inCartProductAmount bigserial NOT NULL
);

create table orderCart(
                          id bigserial NOT NULL PRIMARY KEY,
                          packageId integer NOT NULL,
                          courierId integer NOT NULL,
                          packageDescription varchar(100) NOT NULL,
                          orderStatus varchar(100) NOT NULL,
                          totalPrice float NOT NULL,
                          userName varchar(100) NOT NULL,
                          deliveryTime DATE NOT NULL,
                          fullName VARCHAR(100) NOT NULL,
                          fullAddress VARCHAR(100) NOT NULL,
                          dontDisturb VARCHAR(1)
);


CREATE table statistic(
                          id bigserial PRIMARY KEY,
                          productCategory bigserial NOT NULL,
                          productId bigserial NOT NULL,
                          productBought bigserial NOT NULL,
                          productSold bigserial NOT NULL,
                          productRating float
);

