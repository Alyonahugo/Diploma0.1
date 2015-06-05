	3	Alyona_Polishchuk		ROLE_ADMIN



INSERT INTO `schulze`.`employee` (`empp_id`, `name`, `password`, `role`) VALUES ('5', 'Kateryna_Madzhara', '1', 'ROLE_USER');
INSERT INTO `schulze`.`employee` (`empp_id`, `name`, `password`, `role`) VALUES ('6', 'Olga_Petrenchuk', '1', 'ROLE_USER');
INSERT INTO `schulze`.`employee` (`empp_id`, `name`, `password`, `role`) VALUES ('7', 'Nazar_Sheremeta', '1', 'ROLE_USER');
INSERT INTO `schulze`.`employee` (`empp_id`, `name`, `password`, `role`) VALUES ('8', 'Iryna_Bartnytska', '1', 'ROLE_USER');
INSERT INTO `schulze`.`employee` (`empp_id`, `name`, `password`, `role`) VALUES ('9', 'Yaroslav_Mazay', '1', 'ROLE_USER');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('1', 'The main goal is create a rest room for all employee with board games and bags chair', 'Kateruna_Udod', 'test for employee', 'Rest room', '0', '0', 'Roman_Bartnytskiy, Tatsiana_Tychuna', '4');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('2', 'This application is for all employee. For drive it help to save money. For passenger - cheap trip', 'Olga_Kovalenko', 'Cheap comunity', 'Podorozhnyk', '3', '1', 'Volodymor_Pashunskiy, Mychailo_Lysevich', '5');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('3', 'English portal - for interactive self-development in English field', 'Nazar_Sheremeta', 'Self development', 'English portal', '5', '1', 'Inna Cherevichna', '6');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('4', 'Electronic application for poker planning', 'Kateryna_Madzhara', 'Cooparet team work', 'Pocker plann', '1', '0', 'Kateryna_Madzhara, Alex Ivanov', '6');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('5', 'Retrospective help to analyze process between team and customer', 'Iryna Bestuzheva', 'Improve agile process', 'Retro-Shtorm', '2', '0', 'Iryna Bestuzheva, Oleks Mckenzie', '7');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('6', 'Collection of plastic, glass, battery help to save our environment', 'Eugen Shevchenko', 'Save enviroment', 'Eko-company', '4', '0', 'Eugen Shevchenko', '9');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('7', 'Bicycle is excellent transport in  the city. This help to arrive between offices.', 'Yaroslav Mazay', 'Easy arriving', 'Company Bycicle', '0', '1', 'Yaroslav Mazay, Sergiy Polishchuk', '8');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('8', 'Training portal collect all vodeo records of lessons', 'Inna Bondarenko', 'Study by Internet', 'Training portal', '2', '1', 'Alyona_Polishchuk, Nazar Sheremeta, Olga Kovalenko', '3');
INSERT INTO `schulze`.`project` (`proj_id`, `description`, `manager`, `meta`, `name`, `sphere`, `status`, `team`, `empp_id`) VALUES ('9', 'Showers will help to feel comfortable during the day', 'Iryna Bartnytska', 'Comfortable offiice', 'Fresh at office', '0', '1', 'Iryna Bartnytska', '3');


INSERT INTO `schulze`.`section` (`sec_id`, `name`) VALUES ('1', 'General questions');
INSERT INTO `schulze`.`section` (`sec_id`, `name`) VALUES ('2', 'Discussion projects');
INSERT INTO `schulze`.`section` (`sec_id`, `name`) VALUES ('3', 'Other initiatives');

INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('1', 'Why the project \"fresh at office\" should win', '2');
INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('2', 'The future begins with us. Or the benefits of the project \"Eko-company\" ', '2');
INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('3', 'How to improve the development process by the project \"Retro-Shtorm\"', '2');
INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('4', 'Do you like play poker? or Pocker-planning/ Lets vote for \"Pocker plann\"', '2');
INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('5', 'Self Improvement through \"English portal\"', '2');
INSERT INTO `schulze`.`topic` (`topic_id`, `name`, `sec_id`) VALUES ('6', 'Are you tired at work - unwind in \"Rest room\"', '2');


INSERT INTO `schulze`.`comment` (`comm_id`, `message`, `empp_id`, `topic_id`) VALUES ('1', 'We all work in a large company. The amount of workers is very high. Every second dinner and uses plastic plates, knives, forks. There are also other stationery, which are made of plastic. If this project is implemented, it can greatly help the environment.', '4', '2');
INSERT INTO `schulze`.`comment` (`comm_id`, `message`, `empp_id`, `topic_id`) VALUES ('2', 'Sounds good. And on the other hand why the company does not hold own initiatives of this kind', '5', '2');
INSERT INTO `schulze`.`comment` (`comm_id`, `message`, `empp_id`, `topic_id`) VALUES ('3', 'And for me, this project should be realized - we throw away plastic kilograms during the day.', '6', '2');

INSERT INTO `schulze`.`comment` (`comm_id`, `message`, `empp_id`, `topic_id`) VALUES ('4', 'Hi. The idea is wonderful, but not new. The winner must be a project with a new idea, which no analogues.', '7', '2');
