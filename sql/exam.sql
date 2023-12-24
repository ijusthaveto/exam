/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3308
 Source Schema         : exam

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 24/12/2023 22:37:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `adminId` int NOT NULL AUTO_INCREMENT,
  `secretCode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`adminId`) USING BTREE,
  UNIQUE INDEX `admin_secretCode_uindex`(`secretCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'fcb55c05-e134-4660-b10d-46eaf7ff069f');

-- ----------------------------
-- Table structure for bank
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank`  (
  `bankId` int NOT NULL AUTO_INCREMENT,
  `subjectId` int NULL DEFAULT NULL,
  `bankTitle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bankId`) USING BTREE,
  UNIQUE INDEX `bank_bankId_uindex`(`bankId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bank
-- ----------------------------
INSERT INTO `bank` VALUES (1, 5, 'Computer Science Standard');
INSERT INTO `bank` VALUES (4, 5, 'Computer Science');
INSERT INTO `bank` VALUES (5, 1, 'Geography Quiz');
INSERT INTO `bank` VALUES (6, 8, 'ChemQuiz Master');
INSERT INTO `bank` VALUES (7, 8, 'ChemQuest Bank');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `classId` int NOT NULL AUTO_INCREMENT,
  `classNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classId`) USING BTREE,
  UNIQUE INDEX `class_classId_uindex`(`classId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, 'Z094211');
INSERT INTO `class` VALUES (4, 'Z094213');
INSERT INTO `class` VALUES (5, 'Z094214');
INSERT INTO `class` VALUES (6, 'Z094212');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `examId` int NOT NULL AUTO_INCREMENT,
  `examTitle` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `startTime` datetime(0) NOT NULL,
  `endTime` datetime(0) NOT NULL,
  `bankId` int NULL DEFAULT NULL COMMENT '题库ID',
  `limitTime` int NULL DEFAULT NULL COMMENT '考试限制时间',
  `singleNum` int NULL DEFAULT NULL COMMENT '单选题数量',
  `singleScore` double NULL DEFAULT NULL COMMENT '单选题分数',
  `multipleNum` int NULL DEFAULT NULL COMMENT '多选题数量',
  `multipleScore` double NULL DEFAULT NULL COMMENT '多选题分数',
  `boolNum` int NULL DEFAULT NULL COMMENT '判断题数量',
  `boolScore` double NULL DEFAULT NULL COMMENT '判断题分数',
  PRIMARY KEY (`examId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1516504149 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES (1516504145, 'ChTest', '2023-12-24 00:00:00', '2023-12-24 00:02:00', 7, 2, 12, 2, 12, 2, 12, 2);
INSERT INTO `exam` VALUES (1516504146, 'BrTest', '2023-12-24 21:42:00', '2023-12-24 22:00:00', 4, 18, 12, 2, 12, 2, 12, 2);
INSERT INTO `exam` VALUES (1516504147, 'CSTest', '2023-12-24 21:45:00', '2023-12-24 22:00:00', 4, 15, 12, 2, 12, 2, 12, 2);
INSERT INTO `exam` VALUES (1516504148, 'VyTest', '2023-12-24 22:15:00', '2023-12-24 22:55:00', 4, 40, 12, 2, 12, 2, 12, 2);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `questionId` int NOT NULL AUTO_INCREMENT,
  `questionType` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `difficultyLevel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `questionContent` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `options` json NULL,
  `correctAnswer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `score` int NULL DEFAULT NULL,
  `subjectId` int NULL DEFAULT NULL,
  `bankId` int NULL DEFAULT NULL,
  PRIMARY KEY (`questionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 188 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (2, 'True/False', 'Easy', 'Is the Earth round?', '{\"True\": \"Correct\", \"False\": \"Incorrect\"}', 'TRUE', 2, 2, 1);
INSERT INTO `question` VALUES (4, 'Single Choice', 'Easy', 'Which color is a primary color?', '{\"A\": \"Green\", \"B\": \"Purple\", \"C\": \"Red\", \"D\": \"Orange\"}', 'C', 2, 3, 1);
INSERT INTO `question` VALUES (5, 'True/False', 'Medium', 'The sun rises in the west.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 1);
INSERT INTO `question` VALUES (6, 'Multiple Choice', 'Medium', 'What colors are the French flag?', '{\"A\": \"Blue\", \"B\": \"Green\", \"C\": \"White\", \"D\": \"Red\"}', '[\"C\", \"D\"]', 2, 3, 1);
INSERT INTO `question` VALUES (7, 'Multiple Choice', 'Easy', 'Which of the following are fruits?', '{\"A\": \"Carrot\", \"B\": \"Banana\", \"C\": \"Tomato\", \"D\": \"Potato\"}', '[\"B\", \"C\"]', 2, 4, 1);
INSERT INTO `question` VALUES (8, 'Multiple Choice', 'Hard', 'Which programming languages are statically typed?', '{\"A\": \"Java\", \"B\": \"Python\", \"C\": \"C++\", \"D\": \"JavaScript\"}', '[\"A\", \"C\"]', 2, 5, 1);
INSERT INTO `question` VALUES (9, 'Multiple Choice', 'Medium', 'Which animals are mammals?', '{\"A\": \"Dog\", \"B\": \"Snake\", \"C\": \"Bird\", \"D\": \"Dolphin\"}', '[\"A\", \"D\"]', 2, 4, 1);
INSERT INTO `question` VALUES (11, 'True/False', 'Easy', 'Is the sun a star?', '{\"True\": \"Correct\", \"False\": \"Incorrect\"}', 'TRUE', 2, 6, 1);
INSERT INTO `question` VALUES (13, 'Single Choice', 'Easy', 'Which planet is known as the Red Planet?', '{\"A\": \"Mars\", \"B\": \"Venus\", \"C\": \"Jupiter\", \"D\": \"Saturn\"}', 'A', 2, 6, 1);
INSERT INTO `question` VALUES (14, 'True/False', 'Medium', 'Mount Everest is the highest mountain in the world.', '{\"True\": \"True\", \"False\": \"False\"}', 'TRUE', 2, 1, 1);
INSERT INTO `question` VALUES (15, 'Single Choice', 'Easy', 'Which of the following is not a prime number?', '{\"A\": \"3\", \"B\": \"7\", \"C\": \"9\", \"D\": \"11\"}', 'C', 2, 7, 1);
INSERT INTO `question` VALUES (17, 'True/False', 'Easy', 'Water boils at 100 degrees Celsius.', '{\"True\": \"True\", \"False\": \"False\"}', 'TRUE', 2, 8, 1);
INSERT INTO `question` VALUES (18, 'Single Choice', 'Easy', 'What is the capital of Australia?', '{\"A\": \"Sydney\", \"B\": \"Melbourne\", \"C\": \"Canberra\", \"D\": \"Brisbane\"}', 'C', 2, 1, 1);
INSERT INTO `question` VALUES (20, 'Single Choice', 'Easy', 'What is the capital of China?', '{\"A\": \"Beijing\", \"B\": \"HongKong\", \"C\": \"Shanghai\", \"D\": \"Chongqing\"}', 'A', 2, 1, 1);
INSERT INTO `question` VALUES (21, 'Multiple Choice', 'Medium', 'Which of the following are benefits of using version control systems?', '{\"A\": \"Collaboration\", \"B\": \"Code backup\", \"C\": \"Change tracking\", \"D\": \"Code obfuscation\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 1);
INSERT INTO `question` VALUES (22, 'Single Choice', 'Easy', 'What is the main purpose of an operating system?', '{\"A\": \"Running applications\", \"B\": \"Managing hardware resources\", \"C\": \"Browsing the internet\", \"D\": \"Playing games\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (23, 'True/False', 'Medium', 'Object-oriented programming allows for code reusability.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (24, 'Multiple Choice', 'Medium', 'Which of the following programming languages are statically typed?', '{\"A\": \"Python\", \"B\": \"Java\", \"C\": \"JavaScript\", \"D\": \"C++\"}', '[\"B\", \"D\"]', 2, 5, 1);
INSERT INTO `question` VALUES (25, 'Single Choice', 'Hard', 'What is the time complexity of the quicksort algorithm in the average case?', '{\"A\": \"O(n)\", \"B\": \"O(n log n)\", \"C\": \"O(n^2)\", \"D\": \"O(log n)\"}', 'B', 2, 5, 1);
INSERT INTO `question` VALUES (26, 'True/False', 'Easy', 'A byte is equal to 8 bits.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (27, 'Multiple Choice', 'Medium', 'Which of the following programming languages are statically typed?', '{\"A\": \"Python\", \"B\": \"Java\", \"C\": \"JavaScript\", \"D\": \"C++\"}', '[\"B\", \"D\"]', 2, 5, 1);
INSERT INTO `question` VALUES (28, 'Single Choice', 'Hard', 'What is the time complexity of the quicksort algorithm in the average case?', '{\"A\": \"O(n)\", \"B\": \"O(n log n)\", \"C\": \"O(n^2)\", \"D\": \"O(log n)\"}', 'B', 2, 5, 1);
INSERT INTO `question` VALUES (29, 'True/False', 'Easy', 'A byte is equal to 8 bits.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (30, 'Multiple Choice', 'Medium', 'Which of the following is a relational database management system?', '{\"A\": \"MongoDB\", \"B\": \"MySQL\", \"C\": \"Oracle\", \"D\": \"Redis\"}', '[\"B\", \"C\"]', 2, 5, 1);
INSERT INTO `question` VALUES (31, 'Single Choice', 'Medium', 'What is the purpose of CSS in web development?', '{\"A\": \"Server-side scripting\", \"B\": \"Database management\", \"C\": \"Styling web pages\", \"D\": \"Network security\"}', 'C', 2, 5, 1);
INSERT INTO `question` VALUES (32, 'True/False', 'Hard', 'A linked list can be traversed in both directions.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (33, 'Multiple Choice', 'Easy', 'Which of the following is a high-level programming language?', '{\"A\": \"Assembly\", \"B\": \"C\", \"C\": \"Python\", \"D\": \"Machine code\"}', '[\"B\", \"C\"]', 2, 5, 1);
INSERT INTO `question` VALUES (34, 'Single Choice', 'Medium', 'What is the purpose of the \'finally\' block in a try-catch statement?', '{\"A\": \"To handle exceptions\", \"B\": \"To execute code regardless of an exception\", \"C\": \"To define custom exceptions\", \"D\": \"To declare variables\"}', 'B', 2, 5, 1);
INSERT INTO `question` VALUES (35, 'True/False', 'Easy', 'Java is a purely object-oriented programming language.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (36, 'Multiple Choice', 'Hard', 'Which of the algorithms has the best time complexity in the worst case?', '{\"A\": \"Bubble Sort\", \"B\": \"Insertion Sort\", \"C\": \"Merge Sort\", \"D\": \"Selection Sort\"}', '[\"C\"]', 2, 5, 1);
INSERT INTO `question` VALUES (37, 'Single Choice', 'Medium', 'What is the purpose of the \'this\' keyword in Java?', '{\"A\": \"Refers to current instance of the class\", \"B\": \"Declares a variable\", \"C\": \"Defines a constant\", \"D\": \"Accesses static methods\"}', 'A', 2, 5, 1);
INSERT INTO `question` VALUES (38, 'True/False', 'Easy', 'HTML is a programming language.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 1);
INSERT INTO `question` VALUES (39, 'Multiple Choice', 'Medium', 'Which of the following is an example of a NoSQL database?', '{\"A\": \"MySQL\", \"B\": \"MongoDB\", \"C\": \"SQLite\", \"D\": \"PostgreSQL\"}', '[\"B\"]', 2, 5, 1);
INSERT INTO `question` VALUES (40, 'Single Choice', 'Hard', 'What is the purpose of the \'volatile\' keyword in Java?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent thread interference\", \"C\": \"To create a singleton class\", \"D\": \"To implement method overloading\"}', 'B', 2, 5, 1);
INSERT INTO `question` VALUES (41, 'True/False', 'Medium', 'IPv6 addresses are shorter than IPv4 addresses.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 1);
INSERT INTO `question` VALUES (42, 'Multiple Choice', 'Easy', 'Which of the following is a front-end programming language?', '{\"A\": \"Java\", \"B\": \"JavaScript\", \"C\": \"C#\", \"D\": \"Ruby\"}', '[\"B\"]', 2, 5, 1);
INSERT INTO `question` VALUES (43, 'Single Choice', 'Medium', 'What is the purpose of the \'git clone\' command in Git?', '{\"A\": \"Create a new branch\", \"B\": \"Copy a repository to the local machine\", \"C\": \"Merge branches\", \"D\": \"Delete a branch\"}', 'B', 2, 5, 1);
INSERT INTO `question` VALUES (44, 'True/False', 'Hard', 'Dynamic programming is a technique to solve optimization problems.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 1);
INSERT INTO `question` VALUES (45, 'Single Choice', 'Easy', 'What is the primary function of a CPU in a computer system?', '{\"A\": \"Managing storage\", \"B\": \"Executing instructions\", \"C\": \"Displaying graphics\", \"D\": \"Providing network connectivity\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (46, 'True/False', 'Medium', 'Encapsulation helps in hiding the implementation details.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (47, 'Multiple Choice', 'Medium', 'Which of the following are benefits of using version control systems?', '{\"A\": \"Tracking changes\", \"B\": \"Collaboration\", \"C\": \"Code backup\", \"D\": \"Running applications\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (48, 'Single Choice', 'Hard', 'What is the purpose of the \'malloc\' function in C programming?', '{\"A\": \"Memory allocation\", \"B\": \"Memory deallocation\", \"C\": \"File input/output\", \"D\": \"Pointer arithmetic\"}', 'A', 2, 5, 4);
INSERT INTO `question` VALUES (49, 'True/False', 'Easy', 'Java is platform-independent due to its bytecode compilation.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (50, 'Multiple Choice', 'Medium', 'Which of the following are considered agile software development methodologies?', '{\"A\": \"Scrum\", \"B\": \"Waterfall\", \"C\": \"Kanban\", \"D\": \"Extreme Programming (XP)\"}', '[\"A\", \"C\", \"D\"]', 2, 5, 4);
INSERT INTO `question` VALUES (51, 'Single Choice', 'Hard', 'What is the Big-O notation for the time complexity of a binary search algorithm?', '{\"A\": \"O(n)\", \"B\": \"O(n log n)\", \"C\": \"O(log n)\", \"D\": \"O(n^2)\"}', 'C', 2, 5, 4);
INSERT INTO `question` VALUES (52, 'True/False', 'Easy', 'CSS stands for \'Counter Style Sheets.\'', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (53, 'Multiple Choice', 'Medium', 'Which of the following are valid data types in Python?', '{\"A\": \"String\", \"B\": \"Tuple\", \"C\": \"Dictionary\", \"D\": \"Semaphore\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (54, 'Single Choice', 'Hard', 'What is the purpose of the \'super\' keyword in Python?', '{\"A\": \"To access superclass methods\", \"B\": \"To define a new class\", \"C\": \"To create an instance of a class\", \"D\": \"To handle exceptions\"}', 'A', 2, 5, 4);
INSERT INTO `question` VALUES (55, 'True/False', 'Medium', '\'git pull\' fetches changes from a remote repository and merges them into the current.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (56, 'Multiple Choice', 'Easy', 'Which of the following are commonly used HTTP methods?', '{\"A\": \"GET\", \"B\": \"POST\", \"C\": \"UPDATE\", \"D\": \"DELETE\"}', '[\"A\", \"B\"]', 2, 5, 4);
INSERT INTO `question` VALUES (57, 'Single Choice', 'Medium', 'What is the purpose of the \'volatile\' keyword in C++?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent optimization by the compiler\", \"C\": \"To create a dynamic array\", \"D\": \"To implement method overloading\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (58, 'True/False', 'Hard', 'The time complexity of the bubble sort algorithm is always O(n log n).', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (59, 'Multiple Choice', 'Easy', 'Which of the following are fundamental data types in C#?', '{\"A\": \"int\", \"B\": \"float\", \"C\": \"char\", \"D\": \"class\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (60, 'Single Choice', 'Medium', 'What is the purpose of the \'finally\' block in a try-catch statement?', '{\"A\": \"To handle exceptions\", \"B\": \"To execute code regardless of an exception\", \"C\": \"To define custom exceptions\", \"D\": \"To declare variables\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (61, 'True/False', 'Easy', 'A recursive function calls itself directly or indirectly.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (62, 'Multiple Choice', 'Medium', 'Which of the following are valid ways to declare a variable in JavaScript?', '{\"A\": \"var x;\", \"B\": \"let y;\", \"C\": \"const z;\"}', '[\"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (63, 'Single Choice', 'Hard', 'What is the purpose of the \'const\' keyword in C++?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent optimization by the compiler\", \"C\": \"To create a dynamic array\", \"D\": \"To implement method overloading\"}', 'A', 2, 5, 4);
INSERT INTO `question` VALUES (64, 'True/False', 'Medium', 'The \'npm\' command is used for package management in Python.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (65, 'Multiple Choice', 'Easy', 'Which of the following are valid loop constructs in Java?', '{\"A\": \"for\", \"B\": \"repeat-while\", \"C\": \"do-while\", \"D\": \"if-else\"}', '[\"A\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (66, 'Single Choice', 'Medium', 'What is the purpose of the \'async\' keyword in JavaScript?', '{\"A\": \"To define asynchronous functions\", \"B\": \"To declare a variable\", \"C\": \"To handle exceptions\", \"D\": \"To create a dynamic array\"}', 'A', 2, 5, 4);
INSERT INTO `question` VALUES (67, 'True/False', 'Hard', 'The \'try\' block in a try-catch statement is mandatory.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (68, 'Multiple Choice', 'Easy', 'Which of the following are common relational database management systems?', '{\"A\": \"MySQL\", \"B\": \"SQLite\", \"C\": \"NoSQL\", \"D\": \"Oracle\"}', '[\"A\", \"D\"]', 2, 5, 4);
INSERT INTO `question` VALUES (69, 'Single Choice', 'Hard', 'What is the purpose of the \'sealed\' keyword in C#?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent inheritance\", \"C\": \"To create a dynamic array\", \"D\": \"To implement method overloading\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (70, 'True/False', 'Medium', 'JavaScript is a strictly typed language.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (71, 'Multiple Choice', 'Easy', 'Which of the following are valid ways to define a function in Python?', '{\"A\": \"def func():\", \"B\": \"function func() {}\", \"C\": \"lambda x: x + 1\", \"D\": \"int func() {}\"}', '[\"A\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (72, 'Single Choice', 'Medium', 'What is the purpose of the \'this\' keyword in JavaScript?', '{\"A\": \"Refers to the current instance of the class\", \"B\": \"Declares a variable\", \"C\": \"Defines a constant\", \"D\": \"Accesses static methods\"}', 'A', 2, 5, 4);
INSERT INTO `question` VALUES (73, 'True/False', 'Hard', 'The \'else if\' statement is mandatory after an \'if\' statement in JavaScript.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (74, 'Multiple Choice', 'Easy', 'Which of the following are commonly used design patterns in software development?', '{\"A\": \"Singleton\", \"B\": \"Observer\", \"C\": \"Decorator\", \"D\": \"Router\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (75, 'Single Choice', 'Hard', 'What is the purpose of the \'volatile\' keyword in Java?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent thread interference\", \"C\": \"To create a singleton class\", \"D\": \"To implement method overloading\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (76, 'True/False', 'Medium', '\'await\' keyword can pause the execution of a function until a promise is resolved.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (77, 'Multiple Choice', 'Easy', 'Which of the following are commonly used data structures?', '{\"A\": \"Array\", \"B\": \"LinkedList\", \"C\": \"Tree\", \"D\": \"Router\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (78, 'Single Choice', 'Medium', 'What is the purpose of the \'map\' function in Python?', '{\"A\": \"To create a dictionary\", \"B\": \"To apply a function to all elements of an iterable\", \"C\": \"To sort a list\", \"D\": \"To filter elements of a list\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (79, 'True/False', 'Hard', 'The \'finally\' block in a try-catch statement is executed only if an exception is thrown.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (80, 'Multiple Choice', 'Easy', 'Which of the following are commonly used sorting algorithms?', '{\"A\": \"Bubble Sort\", \"B\": \"Merge Sort\", \"C\": \"Quick Sort\", \"D\": \"Binary Sort\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (81, 'Single Choice', 'Hard', 'What is the purpose of the \'template\' keyword in C++?', '{\"A\": \"To declare a constant variable\", \"B\": \"To create a dynamic array\", \"C\": \"To implement method overloading\", \"D\": \"To define template classes\"}', 'D', 2, 5, 4);
INSERT INTO `question` VALUES (82, 'True/False', 'Medium', 'JSON stands for \'JavaScript Object Notation.\'', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (83, 'Multiple Choice', 'Easy', 'Which of the following are commonly used web development frameworks?', '{\"A\": \"Django\", \"B\": \"React\", \"C\": \"Angular\", \"D\": \"Vue.js\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (84, 'Single Choice', 'Medium', 'What is the purpose of the \'static\' keyword in Java?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent inheritance\", \"C\": \"To create a dynamic array\", \"D\": \"To define class-level variables\"}', 'D', 2, 5, 4);
INSERT INTO `question` VALUES (85, 'True/False', 'Hard', 'The \'await\' keyword can only be used inside an asynchronous function in JavaScript.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (86, 'Multiple Choice', 'Easy', 'Which of the following are common paradigms in programming?', '{\"A\": \"Imperative\", \"B\": \"Declarative\", \"C\": \"Procedural\", \"D\": \"Functional\"}', '[\"A\", \"B\", \"C\", \"D\"]', 2, 5, 4);
INSERT INTO `question` VALUES (87, 'Single Choice', 'Hard', 'What is the purpose of the \'volatile\' keyword in C programming?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent optimization by the compiler\", \"C\": \"To create a dynamic array\", \"D\": \"To implement method overloading\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (88, 'True/False', 'Medium', 'Docker is a virtualization platform that uses hypervisors.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (89, 'Multiple Choice', 'Easy', 'Which of the following are commonly used programming paradigms?', '{\"A\": \"Object-Oriented Programming (OOP)\", \"B\": \"Functional Programming (FP)\", \"C\": \"Procedural Programming\", \"D\": \"Network Programming\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (90, 'Single Choice', 'Medium', 'What is the purpose of the \'strtok\' function in C programming?', '{\"A\": \"To convert a string to uppercase\", \"B\": \"To tokenize a string\", \"C\": \"To concatenate two strings\", \"D\": \"To compare two strings\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (91, 'True/False', 'Hard', 'GraphQL is a query language for querying databases.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 5, 4);
INSERT INTO `question` VALUES (92, 'Multiple Choice', 'Easy', 'Which of are commonly used relational database management systems?', '{\"A\": \"MySQL\", \"B\": \"PostgreSQL\", \"C\": \"MongoDB\", \"D\": \"SQLite\"}', '[\"A\", \"B\"]', 2, 5, 4);
INSERT INTO `question` VALUES (93, 'Single Choice', 'Hard', 'What is the purpose of the \'override\' keyword in C#?', '{\"A\": \"To declare a constant variable\", \"B\": \"To prevent inheritance\", \"C\": \"To create a dynamic array\", \"D\": \"To indicate that a method overrides a base class method\"}', 'D', 2, 5, 4);
INSERT INTO `question` VALUES (94, 'True/False', 'Medium', 'The \'NaN\' value in JavaScript stands for \'Not a Name.\'', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (95, 'Multiple Choice', 'Easy', 'Which of the following are commonly used version control systems?', '{\"A\": \"Git\", \"B\": \"SVN\", \"C\": \"Mercurial\", \"D\": \"Perforce\"}', '[\"A\", \"B\", \"C\"]', 2, 5, 4);
INSERT INTO `question` VALUES (96, 'Single Choice', 'Medium', 'What is the purpose of the \'finally\' block in a try-catch statement?', '{\"A\": \"To handle exceptions\", \"B\": \"To execute code regardless of an exception\", \"C\": \"To define custom exceptions\", \"D\": \"To declare variables\"}', 'B', 2, 5, 4);
INSERT INTO `question` VALUES (97, 'True/False', 'Hard', 'RESTful APIs use the WebSocket protocol for communication.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 5, 4);
INSERT INTO `question` VALUES (98, 'Multiple Choice', 'Easy', 'Which of are commonly used programming languages for web development?', '{\"A\": \"JavaScript\", \"B\": \"Java\", \"C\": \"Python\", \"D\": \"Ruby\"}', '[\"A\", \"C\", \"D\"]', 2, 5, 4);
INSERT INTO `question` VALUES (99, 'Single Choice', 'Easy', 'What is the capital of France?', '{\"A\": \"Berlin\", \"B\": \"Paris\", \"C\": \"Madrid\", \"D\": \"Rome\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (100, 'True/False', 'Medium', 'The Amazon River is the longest river in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (101, 'Multiple Choice', 'Medium', 'Which countries are part of the Scandinavian Peninsula?', '{\"A\": \"Norway\", \"B\": \"Denmark\", \"C\": \"Finland\", \"D\": \"Portugal\"}', '[\"A\", \"B\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (102, 'Single Choice', 'Hard', 'What is the highest mountain peak in the world?', '{\"A\": \"Mount Everest\", \"B\": \"K2\", \"C\": \"Kangchenjunga\", \"D\": \"Lhotse\"}', 'A', 2, 1, 5);
INSERT INTO `question` VALUES (103, 'True/False', 'Easy', 'The Great Barrier Reef is located in Australia.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (104, 'Multiple Choice', 'Medium', 'Which deserts are located in Africa?', '{\"A\": \"Sahara Desert\", \"B\": \"Gobi Desert\", \"C\": \"Karakum Desert\", \"D\": \"Namib Desert\"}', '[\"A\", \"C\", \"D\"]', 2, 1, 5);
INSERT INTO `question` VALUES (105, 'Single Choice', 'Hard', 'What is the capital of Japan?', '{\"A\": \"Seoul\", \"B\": \"Beijing\", \"C\": \"Tokyo\", \"D\": \"Bangkok\"}', 'C', 2, 1, 5);
INSERT INTO `question` VALUES (106, 'True/False', 'Easy', 'The Nile River is the longest river in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (107, 'Multiple Choice', 'Medium', 'Which mountain ranges are located in North America?', '{\"A\": \"Rocky Mountains\", \"B\": \"Andes\", \"C\": \"Appalachian Mountains\", \"D\": \"Himalayas\"}', '[\"A\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (108, 'Single Choice', 'Hard', 'What is the capital of Brazil?', '{\"A\": \"Buenos Aires\", \"B\": \"Lima\", \"C\": \"Brasília\", \"D\": \"Caracas\"}', 'C', 2, 1, 5);
INSERT INTO `question` VALUES (109, 'True/False', 'Medium', 'The Sahara Desert is the largest hot desert in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (110, 'Multiple Choice', 'Easy', 'Which rivers are part of the Seven Wonders of the Ancient World?', '{\"A\": \"Nile\", \"B\": \"Amazon\", \"C\": \"Ganges\", \"D\": \"Tigris-Euphrates\"}', '[\"A\", \"C\", \"D\"]', 2, 1, 5);
INSERT INTO `question` VALUES (111, 'Single Choice', 'Medium', 'What is the capital of Russia?', '{\"A\": \"Istanbul\", \"B\": \"Moscow\", \"C\": \"Berlin\", \"D\": \"Warsaw\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (112, 'True/False', 'Hard', 'The Great Wall of China is visible from space.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (113, 'Multiple Choice', 'Easy', 'Which countries are part of the Iberian Peninsula?', '{\"A\": \"Spain\", \"B\": \"Italy\", \"C\": \"Portugal\", \"D\": \"France\"}', '[\"A\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (114, 'Single Choice', 'Hard', 'What is the capital of South Korea?', '{\"A\": \"Seoul\", \"B\": \"Tokyo\", \"C\": \"Bangkok\", \"D\": \"Beijing\"}', 'A', 2, 1, 5);
INSERT INTO `question` VALUES (115, 'True/False', 'Medium', 'The Dead Sea is the saltiest body of water in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (116, 'Multiple Choice', 'Easy', 'Which countries are part of the British Isles?', '{\"A\": \"Ireland\", \"B\": \"United Kingdom\", \"C\": \"Norway\", \"D\": \"France\"}', '[\"A\", \"B\"]', 2, 1, 5);
INSERT INTO `question` VALUES (117, 'Single Choice', 'Medium', 'What is the capital of India?', '{\"A\": \"Beijing\", \"B\": \"Delhi\", \"C\": \"Seoul\", \"D\": \"Tokyo\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (118, 'True/False', 'Hard', 'The Victoria Falls is located between Zambia and Zimbabwe.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (119, 'Multiple Choice', 'Easy', 'Which countries are part of the Scandinavian Peninsula?', '{\"A\": \"Norway\", \"B\": \"Denmark\", \"C\": \"Finland\", \"D\": \"Portugal\"}', '[\"A\", \"B\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (120, 'Single Choice', 'Hard', 'What is the capital of Australia?', '{\"A\": \"Sydney\", \"B\": \"Canberra\", \"C\": \"Melbourne\", \"D\": \"Auckland\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (121, 'True/False', 'Medium', 'The Sahara Desert is the largest hot desert in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (122, 'Multiple Choice', 'Easy', 'Which rivers are part of the Seven Wonders of the Ancient World?', '{\"A\": \"Nile\", \"B\": \"Amazon\", \"C\": \"Ganges\", \"D\": \"Tigris-Euphrates\"}', '[\"A\", \"C\", \"D\"]', 2, 1, 5);
INSERT INTO `question` VALUES (123, 'Single Choice', 'Medium', 'What is the capital of Canada?', '{\"A\": \"Ottawa\", \"B\": \"Toronto\", \"C\": \"Vancouver\", \"D\": \"Montreal\"}', 'A', 2, 1, 5);
INSERT INTO `question` VALUES (124, 'True/False', 'Hard', 'Mount Kilimanjaro is the highest mountain in Africa.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (125, 'Multiple Choice', 'Easy', 'Which countries are part of the Caribbean Islands?', '{\"A\": \"Jamaica\", \"B\": \"Cuba\", \"C\": \"Haiti\", \"D\": \"Peru\"}', '[\"A\", \"B\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (126, 'Single Choice', 'Hard', 'What is the capital of Argentina?', '{\"A\": \"Buenos Aires\", \"B\": \"Lima\", \"C\": \"Santiago\", \"D\": \"Caracas\"}', 'A', 2, 1, 5);
INSERT INTO `question` VALUES (127, 'True/False', 'Medium', 'The Amazon Rainforest produces 20% of the world\'s oxygen.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (128, 'Multiple Choice', 'Easy', 'Which countries are part of the African Great Lakes region?', '{\"A\": \"Kenya\", \"B\": \"Nigeria\", \"C\": \"Uganda\", \"D\": \"Ethiopia\"}', '[\"A\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (129, 'Single Choice', 'Medium', 'What is the capital of South Africa?', '{\"A\": \"Lagos\", \"B\": \"Johannesburg\", \"C\": \"Cape Town\", \"D\": \"Nairobi\"}', 'C', 2, 1, 5);
INSERT INTO `question` VALUES (130, 'True/False', 'Hard', 'The Great Wall of China is visible from space.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (131, 'Multiple Choice', 'Easy', 'Which countries are part of the Balkan Peninsula?', '{\"A\": \"Greece\", \"B\": \"Italy\", \"C\": \"Serbia\", \"D\": \"Spain\"}', '[\"A\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (132, 'Single Choice', 'Hard', 'What is the capital of Mexico?', '{\"A\": \"Bogotá\", \"B\": \"Mexico City\", \"C\": \"Lima\", \"D\": \"Caracas\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (133, 'True/False', 'Medium', 'The Great Barrier Reef is the largest coral reef system in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (134, 'Multiple Choice', 'Easy', 'Which countries are part of the Scandinavian Peninsula?', '{\"A\": \"Norway\", \"B\": \"Denmark\", \"C\": \"Finland\", \"D\": \"Portugal\"}', '[\"A\", \"B\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (135, 'Single Choice', 'Medium', 'What is the capital of Brazil?', '{\"A\": \"Buenos Aires\", \"B\": \"Lima\", \"C\": \"Brasília\", \"D\": \"Caracas\"}', 'C', 2, 1, 5);
INSERT INTO `question` VALUES (136, 'True/False', 'Hard', 'The Sahara Desert is the largest hot desert in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (137, 'Multiple Choice', 'Easy', 'Which rivers are part of the Seven Wonders of the Ancient World?', '{\"A\": \"Nile\", \"B\": \"Amazon\", \"C\": \"Ganges\", \"D\": \"Tigris-Euphrates\"}', '[\"A\", \"C\", \"D\"]', 2, 1, 5);
INSERT INTO `question` VALUES (138, 'Single Choice', 'Hard', 'What is the capital of Japan?', '{\"A\": \"Istanbul\", \"B\": \"Moscow\", \"C\": \"Tokyo\", \"D\": \"Warsaw\"}', 'C', 2, 1, 5);
INSERT INTO `question` VALUES (139, 'True/False', 'Medium', 'The Dead Sea is the saltiest body of water in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (140, 'Multiple Choice', 'Easy', 'Which mountain ranges are located in North America?', '{\"A\": \"Rocky Mountains\", \"B\": \"Andes\", \"C\": \"Appalachian Mountains\", \"D\": \"Himalayas\"}', '[\"A\", \"C\"]', 2, 1, 5);
INSERT INTO `question` VALUES (141, 'Single Choice', 'Hard', 'What is the highest mountain peak in the world?', '{\"A\": \"Mount Everest\", \"B\": \"K2\", \"C\": \"Kangchenjunga\", \"D\": \"Lhotse\"}', 'A', 2, 1, 5);
INSERT INTO `question` VALUES (142, 'True/False', 'Easy', 'The Great Barrier Reef is located in Australia.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 1, 5);
INSERT INTO `question` VALUES (143, 'Multiple Choice', 'Medium', 'Which deserts are located in Africa?', '{\"A\": \"Sahara Desert\", \"B\": \"Gobi Desert\", \"C\": \"Karakum Desert\", \"D\": \"Namib Desert\"}', '[\"A\", \"C\", \"D\"]', 2, 1, 5);
INSERT INTO `question` VALUES (144, 'Single Choice', 'Hard', 'What is the capital of Russia?', '{\"A\": \"Istanbul\", \"B\": \"Moscow\", \"C\": \"Berlin\", \"D\": \"Warsaw\"}', 'B', 2, 1, 5);
INSERT INTO `question` VALUES (145, 'True/False', 'Easy', 'The Nile River is the longest river in the world.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 1, 5);
INSERT INTO `question` VALUES (146, 'Multiple Choice', 'Medium', 'Which mountain ranges are located in South America?', '{\"A\": \"Andes\", \"B\": \"Rocky Mountains\", \"C\": \"Himalayas\", \"D\": \"Alps\"}', '[\"A\", \"B\"]', 2, 1, 5);
INSERT INTO `question` VALUES (147, 'Single Choice', 'Easy', 'What is the atomic number of carbon?', '{\"A\": \"4\", \"B\": \"6\", \"C\": \"8\", \"D\": \"12\"}', 'B', 2, 8, 6);
INSERT INTO `question` VALUES (148, 'True/False', 'Medium', 'Water consists of two hydrogen atoms and one oxygen atom.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 8, 6);
INSERT INTO `question` VALUES (149, 'Multiple Choice', 'Medium', 'Which elements are noble gases? ', '{\"A\": \"Helium\", \"B\": \"Oxygen\", \"C\": \"Neon\", \"D\": \"Argon\"}', '[\"A\", \"C\", \"D\"]', 2, 8, 6);
INSERT INTO `question` VALUES (150, 'Single Choice', 'Hard', 'What is the chemical symbol for gold?', '{\"A\": \"Au\", \"B\": \"Ag\", \"C\": \"Fe\", \"D\": \"Cu\"}', 'A', 2, 8, 6);
INSERT INTO `question` VALUES (151, 'True/False', 'Easy', 'Acids have a pH greater than 7.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 8, 6);
INSERT INTO `question` VALUES (152, 'Multiple Choice', 'Medium', 'Which compounds are hydrocarbons?', '{\"A\": \"Water\", \"B\": \"Methane\", \"C\": \"Ethanol\", \"D\": \"Propane\"}', '[\"B\", \"D\"]', 2, 8, 6);
INSERT INTO `question` VALUES (153, 'Single Choice', 'Hard', 'What is the molecular formula for glucose?', '{\"A\": \"H2O\", \"B\": \"CO2\", \"C\": \"C6H12O6\", \"D\": \"NH3\"}', 'C', 2, 8, 6);
INSERT INTO `question` VALUES (154, 'True/False', 'Easy', 'Oxygen is a diatomic molecule.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 8, 6);
INSERT INTO `question` VALUES (155, 'Multiple Choice', 'Medium', 'Which subatomic particles are found in the nucleus of an atom?', '{\"A\": \"Protons\", \"B\": \"Electrons\", \"C\": \"Neutrons\", \"D\": \"Positrons\"}', '[\"A\", \"C\"]', 2, 8, 6);
INSERT INTO `question` VALUES (156, 'Single Choice', 'Hard', 'What is the molar mass of water (H2O)?', '{\"A\": \"16 g/mol\", \"B\": \"18 g/mol\", \"C\": \"22 g/mol\", \"D\": \"24 g/mol\"}', 'B', 2, 8, 6);
INSERT INTO `question` VALUES (157, 'True/False', 'Medium', 'Chemical reactions involve the conversion of mass.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 8, 6);
INSERT INTO `question` VALUES (158, 'Multiple Choice', 'Easy', 'Which elements are halogens? (Select all that apply)', '{\"A\": \"Sodium\", \"B\": \"Fluorine\", \"C\": \"Chlorine\", \"D\": \"Potassium\"}', '[\"B\", \"C\"]', 2, 8, 6);
INSERT INTO `question` VALUES (159, 'Single Choice', 'Medium', 'What is the pH of a neutral solution?', '{\"A\": \"0\", \"B\": \"7\", \"C\": \"14\", \"D\": \"1\"}', 'B', 2, 8, 6);
INSERT INTO `question` VALUES (160, 'True/False', 'Hard', 'Ions with a positive charge are called anions.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 8, 6);
INSERT INTO `question` VALUES (161, 'Multiple Choice', 'Easy', 'Which elements are alkali metals? ', '{\"A\": \"Lithium\", \"B\": \"Iron\", \"C\": \"Sodium\", \"D\": \"Calcium\"}', '[\"A\", \"C\"]', 2, 8, 6);
INSERT INTO `question` VALUES (162, 'Single Choice', 'Hard', 'What is the chemical formula for methane?', '{\"A\": \"CH4\", \"B\": \"CO2\", \"C\": \"H2SO4\", \"D\": \"C6H12O6\"}', 'A', 2, 8, 6);
INSERT INTO `question` VALUES (163, 'True/False', 'Medium', 'All organic compounds contain carbon.', '{\"True\": \"False\", \"False\": \"True\"}', 'TRUE', 2, 8, 6);
INSERT INTO `question` VALUES (164, 'Multiple Choice', 'Easy', 'Which elements are transition metals? ', '{\"A\": \"Gold\", \"B\": \"Carbon\", \"C\": \"Iron\", \"D\": \"Helium\"}', '[\"A\", \"C\"]', 2, 8, 6);
INSERT INTO `question` VALUES (165, 'Single Choice', 'Medium', 'What is the boiling point of water at standard atmospheric pressure?', '{\"A\": \"100°C\", \"B\": \"0°C\", \"C\": \"-273°C\", \"D\": \"50°C\"}', 'A', 2, 8, 6);
INSERT INTO `question` VALUES (166, 'True/False', 'Hard', 'Isotopes of an element have the same number of neutrons.', '{\"True\": \"False\", \"False\": \"True\"}', 'FALSE', 2, 8, 6);
INSERT INTO `question` VALUES (167, 'Multiple Choice', 'Easy', 'Which compounds are acids?', '{\"A\": \"HCl\", \"B\": \"NaOH\", \"C\": \"H2O\", \"D\": \"H2SO4\"}', '[\"A\", \"D\"]', 2, 8, 6);
INSERT INTO `question` VALUES (168, 'Single Choice', 'Hard', 'What is the molecular geometry of methane (CH4)?', '{\"A\": \"Linear\", \"B\": \"Tetrahedral\", \"C\": \"Trigonal Planar\", \"D\": \"Octahedral\"}', 'B', 2, 8, 6);
INSERT INTO `question` VALUES (169, 'Single Choice', 'Medium', 'What is the chemical symbol for sodium?', '{\"A\": \"S\", \"B\": \"Na\", \"C\": \"Se\", \"D\": \"Sn\"}', 'B', 2, 8, 7);
INSERT INTO `question` VALUES (170, 'Single Choice', 'Medium', 'What is the molecular formula for sulfuric acid?', '{\"A\": \"H2SO3\", \"B\": \"H2S\", \"C\": \"H2SO4\", \"D\": \"H2CO3\"}', 'C', 2, 8, 7);
INSERT INTO `question` VALUES (171, 'Multiple Choice', 'Hard', 'Which of the following is a noble gas?', '{\"A\": \"Oxygen\", \"B\": \"Neon\", \"C\": \"Sodium\", \"D\": \"Aluminum\"}', '[\"B\"]', 2, 8, 7);
INSERT INTO `question` VALUES (172, 'Single Choice', 'Medium', 'What is the chemical formula for ammonia?', '{\"A\": \"NH3\", \"B\": \"NO2\", \"C\": \"N2O\", \"D\": \"CO2\"}', 'A', 2, 8, 7);
INSERT INTO `question` VALUES (173, 'Multiple Choice', 'Hard', 'Which element is essential for organic life?', '{\"A\": \"Gold\", \"B\": \"Iron\", \"C\": \"Carbon\", \"D\": \"Platinum\"}', '[\"C\"]', 2, 8, 7);
INSERT INTO `question` VALUES (174, 'Single Choice', 'Medium', 'What is the pH of hydrochloric acid (HCl)?', '{\"A\": \"7\", \"B\": \"1\", \"C\": \"14\", \"D\": \"0\"}', 'B', 2, 8, 7);
INSERT INTO `question` VALUES (175, 'Single Choice', 'Medium', 'What is the molecular formula for glucose?', '{\"A\": \"H2O\", \"B\": \"CO2\", \"C\": \"C6H12O6\", \"D\": \"NH3\"}', 'C', 2, 8, 7);
INSERT INTO `question` VALUES (176, 'Single Choice', 'Hard', 'What is the molar mass of water (H2O)?', '{\"A\": \"16 g/mol\", \"B\": \"18 g/mol\", \"C\": \"22 g/mol\", \"D\": \"24 g/mol\"}', 'B', 2, 8, 7);
INSERT INTO `question` VALUES (177, 'Multiple Choice', 'Easy', 'Which elements are halogens? (Select all that apply)', '{\"A\": \"Sodium\", \"B\": \"Fluorine\", \"C\": \"Chlorine\", \"D\": \"Potassium\"}', '[\"B\", \"C\"]', 2, 8, 7);
INSERT INTO `question` VALUES (178, 'Single Choice', 'Medium', 'What is the boiling point of nitrogen at standard atmospheric pressure?', '{\"A\": \"-196°C\", \"B\": \"0°C\", \"C\": \"100°C\", \"D\": \"50°C\"}', 'A', 2, 8, 7);
INSERT INTO `question` VALUES (179, 'Multiple Choice', 'Hard', 'Which of the following is an alkali metal?', '{\"A\": \"Lithium\", \"B\": \"Chlorine\", \"C\": \"Sulfur\", \"D\": \"Iron\"}', '[\"A\"]', 2, 8, 7);
INSERT INTO `question` VALUES (180, 'Single Choice', 'Medium', 'What is the pH of a neutral solution?', '{\"A\": \"0\", \"B\": \"7\", \"C\": \"14\", \"D\": \"1\"}', 'B', 2, 8, 7);
INSERT INTO `question` VALUES (181, 'Single Choice', 'Hard', 'What is the chemical symbol for potassium?', '{\"A\": \"K\", \"B\": \"Ka\", \"C\": \"Ke\", \"D\": \"Ko\"}', 'A', 2, 8, 7);
INSERT INTO `question` VALUES (182, 'Multiple Choice', 'Easy', 'Which elements are alkali metals? (Select all that apply)', '{\"A\": \"Lithium\", \"B\": \"Iron\", \"C\": \"Sodium\", \"D\": \"Calcium\"}', '[\"A\", \"C\"]', 2, 8, 7);
INSERT INTO `question` VALUES (183, 'Single Choice', 'Hard', 'What is the molecular geometry of methane (CH4)?', '{\"A\": \"Linear\", \"B\": \"Tetrahedral\", \"C\": \"Trigonal Planar\", \"D\": \"Octahedral\"}', 'B', 2, 8, 7);
INSERT INTO `question` VALUES (184, 'Single Choice', 'Medium', 'What is the chemical formula for hydrobromic acid?', '{\"A\": \"HBr\", \"B\": \"HCl\", \"C\": \"H2SO4\", \"D\": \"HNO3\"}', 'A', 2, 8, 7);
INSERT INTO `question` VALUES (185, 'Multiple Choice', 'Easy', 'Which compounds are acids? (Select all that apply)', '{\"A\": \"HCl\", \"B\": \"NaOH\", \"C\": \"H2O\", \"D\": \"H2SO4\"}', '[\"A\", \"D\"]', 2, 8, 7);
INSERT INTO `question` VALUES (186, 'Single Choice', 'Hard', 'What is the chemical symbol for gold?', '{\"A\": \"Au\", \"B\": \"Ag\", \"C\": \"Fe\", \"D\": \"Cu\"}', 'A', 2, 8, 7);
INSERT INTO `question` VALUES (187, 'Multiple Choice', 'Easy', 'Which elements are noble gases? (Select all that apply)', '{\"A\": \"Helium\", \"B\": \"Oxygen\", \"C\": \"Neon\", \"D\": \"Argon\"}', '[\"A\", \"C\", \"D\"]', 2, 8, 7);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Student');
INSERT INTO `role` VALUES (2, 'Teacher');

-- ----------------------------
-- Table structure for studentexam
-- ----------------------------
DROP TABLE IF EXISTS `studentexam`;
CREATE TABLE `studentexam`  (
  `studentExamId` int NOT NULL AUTO_INCREMENT,
  `userId` int NULL DEFAULT NULL,
  `examId` int NULL DEFAULT NULL,
  `status` enum('pending','completed') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'pending',
  `score` int NULL DEFAULT 0,
  PRIMARY KEY (`studentExamId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `examId`(`examId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of studentexam
-- ----------------------------
INSERT INTO `studentexam` VALUES (58, 1, 1516504145, 'pending', 0);
INSERT INTO `studentexam` VALUES (59, 1, 1516504146, 'pending', 0);
INSERT INTO `studentexam` VALUES (60, 1, 1516504147, 'pending', 0);
INSERT INTO `studentexam` VALUES (61, 1, 1516504148, 'pending', 0);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subjectId` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`subjectId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, 'Geography');
INSERT INTO `subject` VALUES (2, 'Science');
INSERT INTO `subject` VALUES (3, 'Art');
INSERT INTO `subject` VALUES (4, 'Biology');
INSERT INTO `subject` VALUES (5, 'Computer Science');
INSERT INTO `subject` VALUES (6, 'Astronomy');
INSERT INTO `subject` VALUES (7, 'Mathematics');
INSERT INTO `subject` VALUES (8, 'Chemistry');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `taskId` int NOT NULL AUTO_INCREMENT,
  `userId` int NULL DEFAULT NULL,
  `examId` int NULL DEFAULT NULL,
  `score` double NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `updateTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`taskId`) USING BTREE,
  UNIQUE INDEX `task_taskId_uindex`(`taskId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 221 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES (217, 1, 1516504145, 4, 1, '2023-12-24 21:28:38', '2023-12-24 21:30:38');
INSERT INTO `task` VALUES (218, 1, 1516504147, 0, 1, '2023-12-24 21:46:38', '2023-12-24 21:46:43');
INSERT INTO `task` VALUES (219, 1, 1516504146, 2, 1, '2023-12-24 21:46:46', '2023-12-24 21:46:51');
INSERT INTO `task` VALUES (220, 1, 1516504148, 2, 1, '2023-12-24 22:15:31', '2023-12-24 22:15:37');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `passwordHash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `roleId` int NULL DEFAULT NULL,
  `classId` int NULL DEFAULT NULL,
  `userNo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'pwd123', 1, 6, 'Z09420122');
INSERT INTO `user` VALUES (2, 'mightcell', 'pwd123', 1, 1, 'Z09421202');
INSERT INTO `user` VALUES (3, 'mike', 'pwd123', 1, 1, 'Z09421203');
INSERT INTO `user` VALUES (4, 'kitty', 'pwd123', 1, 1, 'Z09421204');
INSERT INTO `user` VALUES (5, 'cat', 'pwd123', 1, 1, 'Z09421205');
INSERT INTO `user` VALUES (6, 'dog', 'pwd123', 1, 1, 'Z09421206');
INSERT INTO `user` VALUES (7, 'tom', 'pwd123', 1, 1, 'Z09421207');
INSERT INTO `user` VALUES (9, 'bip6asvt', 'pwd123', 1, 1, 'Z09421209');
INSERT INTO `user` VALUES (12, 'john_doe', 'hash123', 1, 1, 'Z09421212');
INSERT INTO `user` VALUES (13, 'alice_smith', 'hash456', 2, 1, 'Z09421213');
INSERT INTO `user` VALUES (14, 'bob_jones', 'hash789', 1, 1, 'Z09421214');
INSERT INTO `user` VALUES (15, 'emma_white', 'hashabc', 2, 1, 'Z09421215');
INSERT INTO `user` VALUES (16, 'sam_green', 'hashdef', 1, 1, 'Z09421216');
INSERT INTO `user` VALUES (17, 'laura_black', 'hashghi', 2, 1, 'Z09421217');
INSERT INTO `user` VALUES (18, 'mike_brown', 'hashjkl', 1, 1, 'Z09421218');
INSERT INTO `user` VALUES (19, 'olivia_davis', 'hashmno', 2, 1, 'Z09421219');
INSERT INTO `user` VALUES (21, 'sophia_wilson', 'hashtuv', 2, 1, 'Z09421221');
INSERT INTO `user` VALUES (22, 'teacher', 'password', 2, 1, NULL);
INSERT INTO `user` VALUES (23, 'jeffery_wilson', '111111', 1, 1, 'Z09421219');
INSERT INTO `user` VALUES (26, 'lisa_davis', '111111', 1, 1, '98765');
INSERT INTO `user` VALUES (27, 'steven_morris', '111111', 1, 1, '43109');
INSERT INTO `user` VALUES (28, 'jennifer_hernandez', '111111', 1, 1, '21098');
INSERT INTO `user` VALUES (29, 'megan_campbell', '111111', 1, 1, '76543');
INSERT INTO `user` VALUES (30, 'kevin_rodriguez', '111111', 1, 1, '80923');
INSERT INTO `user` VALUES (31, 'amanda_miller', '111111', 1, 1, '12345');
INSERT INTO `user` VALUES (32, 'brian_robinson', '111111', 1, 1, '45678');
INSERT INTO `user` VALUES (33, 'serious_elephant', '111111', 1, 4, '23412');
INSERT INTO `user` VALUES (34, 'smart_dog', '111111', 1, 4, '56789');
INSERT INTO `user` VALUES (35, 'funny_monkey', '111111', 1, 4, '87654');
INSERT INTO `user` VALUES (36, 'creative_fish', '111111', 1, 4, '12345');
INSERT INTO `user` VALUES (37, 'happy_elephant', '111111', 1, 4, '67890');
INSERT INTO `user` VALUES (38, 'sad_cat', '111111', 1, 4, '98765');
INSERT INTO `user` VALUES (39, 'serious_bird', '111111', 1, 4, '54321');
INSERT INTO `user` VALUES (40, 'creative_monkey', '111111', 1, 4, '10987');
INSERT INTO `user` VALUES (41, 'happy_dog', '111111', 1, 4, '45678');
INSERT INTO `user` VALUES (42, 'funny_fish', '111111', 1, 4, '21098');
INSERT INTO `user` VALUES (43, 'creative_fish', '111111', 1, 5, '34567');
INSERT INTO `user` VALUES (44, 'serious_elephant', '111111', 1, 5, '89012');
INSERT INTO `user` VALUES (45, 'funny_monkey', '111111', 1, 5, '67890');
INSERT INTO `user` VALUES (46, 'happy_elephant', '111111', 1, 5, '45678');
INSERT INTO `user` VALUES (47, 'sad_cat', '111111', 1, 5, '12345');
INSERT INTO `user` VALUES (48, 'creative_bird', '111111', 1, 5, '56789');
INSERT INTO `user` VALUES (49, 'smart_dog', '111111', 1, 5, '21098');
INSERT INTO `user` VALUES (50, 'funny_fish', '111111', 1, 5, '10987');
INSERT INTO `user` VALUES (51, 'happy_dog', '111111', 1, 5, '87654');
INSERT INTO `user` VALUES (52, 'serious_monkey', '111111', 1, 5, '54321');

SET FOREIGN_KEY_CHECKS = 1;
