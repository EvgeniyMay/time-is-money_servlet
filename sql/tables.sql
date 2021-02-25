DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `login` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `role` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`login`)
);


DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `description` varchar(255) NOT NULL,
    `is_archived` tinyint(1) NOT NULL DEFAULT '0',
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`name`)
);


DROP TABLE IF EXISTS `mission`;

CREATE TABLE `mission` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `end_time` datetime(6) NOT NULL,
    `start_time` datetime(6) NOT NULL,
    `state` varchar(255) NOT NULL,
    `activity_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY (`activity_id`),
    KEY (`user_id`),
    CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
);

 