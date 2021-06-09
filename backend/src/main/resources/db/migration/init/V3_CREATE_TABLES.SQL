ALTER TABLE productCategory ADD CONSTRAINT productCategoryfk1 FOREIGN KEY (productSuperCategoryId) REFERENCES productCategory (id);
ALTER TABLE product ADD CONSTRAINT productfk1 FOREIGN KEY (productCategory)  REFERENCES productCategory(id);
ALTER TABLE orderProduckt ADD CONSTRAINT orderProducktfk1 FOREIGN KEY (productId) REFERENCES product(id);
ALTER TABLE orderProduckt ADD CONSTRAINT orderProducktfk2 FOREIGN KEY (orderId)  REFERENCES orderCart(id);
ALTER TABLE orderCart ADD CONSTRAINT orderCartfk1 FOREIGN KEY (packageId)  REFERENCES orderProduckt(id);
ALTER TABLE orderCart ADD CONSTRAINT orderCartfk2 FOREIGN KEY (courierId)  REFERENCES authorizedUser(id);
ALTER TABLE statistic ADD CONSTRAINT statisticfk1 FOREIGN KEY (productCategory)  REFERENCES productCategory(id);
ALTER TABLE statistic ADD CONSTRAINT statisticfk2 FOREIGN KEY (productId)  REFERENCES product(id);