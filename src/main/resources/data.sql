INSERT INTO recipe (id, name, cuisine, serving_size, description, image) VALUES (1 ,'Arroz con pollo' ,'Latin' ,4 ,'Arroz con pollo is a classic Puertorican meal that combines chicken with rice, and essential flavors from the Puertorican cuisine.', 'https://cdn.pixabay.com/photo/2019/05/30/19/15/rice-dish-4240511_960_720.jpg');
INSERT INTO recipe (id, name, cuisine, serving_size, description, image) VALUES (2 ,'Pizza' ,'Italian' ,4 ,'This pizza recipie will teach bring the italian in you by creating this from scratch.', 'https://cdn.pixabay.com/photo/2016/04/21/22/50/pizza-1344720_960_720.jpg');
INSERT INTO recipe (id, name, cuisine, serving_size, description, image) VALUES (3 ,'Burgers' ,'American' ,3 ,'A home classic. With this recipie, you will take your burgers to grilling competitions.', 'https://cdn.pixabay.com/photo/2016/03/05/19/02/hamburger-1238246_960_720.jpg');

INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (1 ,1 ,'Rice' ,16 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (2 ,1 ,'Chicken' ,1 ,'Whole');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (3 ,1 ,'Adobo' ,10 ,'g');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (4 ,1 ,'Salt' ,5 ,'g');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (5 ,1 ,'Pepper' ,5 ,'g');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (6 ,1 ,'Sofrito' ,4 ,'Tbl');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (7 ,1 ,'Tomatoe Sauce' ,4 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (8 ,2 ,'All Purpose Flour' ,16 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (9 ,2 ,'Yeast' ,4 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (10 ,2 ,'Marinara Sauce' ,4 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (11 ,2 ,'Satl' ,5 ,'g');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (12 ,2 ,'Olive Oil' ,1 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (13, 2 ,'Mozzarella Cheese' ,6 ,'oz');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (14 ,2 ,'Pepperonie' ,10 ,'slices');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (15 ,3 ,'Ground Beef' ,1 ,'lbs');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (16 ,3 ,'Burger Buns' ,4 ,'piece');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (17 ,3 ,'Lettuce' ,1 ,'Whole');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (18 ,3 ,'Tomatoe' ,1 ,'Whole');
INSERT INTO ingredient(id ,recipe_id ,name ,quantity ,measurement) VALUES (19 ,3 ,'Pickles' ,8 ,'Slices');

INSERT INTO process(id ,recipe_id ,step , description) VALUES (1,1,1,'Cut Chicken into pieces, and season with Adobo');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (2,1,2,'Add oil to the caserole, and cook chicken at medium heat until done');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (3,1,3,'Add the seasoning one by one (Sofrito, Salt, Peper, and add the tomatoe sauce) and mix well');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (4,1,4,'Add 16oz of water, and wait until it boils');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (5,1,5,'When the water starts boiling, add the rice and mix well with all the ingredients');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (6,1,6,'Let the rice cook in the boiling water until the water has evaporated');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (7,1,7,'Mix the rice from down up, lower temperature to medium low, and cover for 10 minutes');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (8,1,8,'Remove caserole from heat, and enjoy');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (9,2,1,'Mix your dry ingredients in a bowl');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (10,2,2,'Add 16oz of water, and the oil');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (11,2,3,'Mix and start to need form 15 minutes or until all ingredients are combine');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (12,2,4,'Cover and let it rest on the bowl for 1hour or until it double in size');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (13,2,5,'Split into 4 parts, and stretch in size of a round pizza');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (14,2,6,'Add sause, cheese and peperonie to your liking');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (15,2,7,'Bake in a 475 over for 5-8 mins until crust is golden brown');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (16,3,1,'Split ground beef into 4 equal parts');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (17,3,2,'On a flat iron, put the burger to cook untild the desire temperature is met');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (18,3,3,'Peel lettus for the desire size to add on burger');
INSERT INTO process(id ,recipe_id ,step , description) VALUES (19,3,4,'Put the burger inside the buns, and top it with the tomatoes, letuce, and other condiments wanted.');

INSERT INTO menu (id ,recipe_id , source) VALUES (1,1,'Internal');
INSERT INTO menu (id ,recipe_id , source) VALUES (2,2,'Internal');

INSERT INTO favorite (id ,recipe_id , source) VALUES (1,1,'Internal');