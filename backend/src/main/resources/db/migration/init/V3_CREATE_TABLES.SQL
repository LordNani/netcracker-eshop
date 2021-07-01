
ALTER TABLE productModel ADD CONSTRAINT productfk1 FOREIGN KEY (genre) REFERENCES genre(id);
ALTER TABLE productModel ADD CONSTRAINT productfk2 FOREIGN KEY (coverType) REFERENCES coverType(id);
ALTER TABLE productModel ADD CONSTRAINT productfk3 FOREIGN KEY (author) REFERENCES author(id);
ALTER TABLE productModel ADD CONSTRAINT productfk4 FOREIGN KEY (language) REFERENCES language(id);
ALTER TABLE productModel ADD CONSTRAINT productfk5 FOREIGN KEY (publisher) REFERENCES publisher(id);

ALTER TABLE orderProduct ADD CONSTRAINT orderProductfk1 FOREIGN KEY (productId) REFERENCES productModel(id);
ALTER TABLE orderProduct ADD CONSTRAINT orderProductfk2 FOREIGN KEY (orderId)  REFERENCES orderCart(id);


ALTER TABLE verificationToken ADD CONSTRAINT verificationTokenfk2 FOREIGN KEY (authorizedUserid)  REFERENCES authorizedUserModel(id);


ALTER TABLE orderCart ADD CONSTRAINT orderCartfk1 FOREIGN KEY (userId)  REFERENCES authorizedUserModel(id);
ALTER TABLE orderCart ADD CONSTRAINT orderCartfk2 FOREIGN KEY (courierId)  REFERENCES authorizedUserModel(id);

