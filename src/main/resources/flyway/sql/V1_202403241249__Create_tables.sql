CREATE TABLE `post` (
   `post_id` int unsigned NOT NULL AUTO_INCREMENT,
   `time` bigint unsigned NOT NULL,
   `date` bigint unsigned NOT NULL,
   `post_content` text NOT NULL,
   `image_url` varchar(100) DEFAULT NULL,
   PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `comment` (
   `comment_id` int unsigned NOT NULL AUTO_INCREMENT,
   `comment_content` text NOT NULL,
   `id_for_post` int unsigned NOT NULL,
   PRIMARY KEY (`comment_id`),
   KEY `for_post_idx` (`id_for_post`),
   CONSTRAINT `for_post` FOREIGN KEY (`id_for_post`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;