-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: anime_shop
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anime_category`
--

DROP TABLE IF EXISTS `anime_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime_category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `category_name` varchar(30) NOT NULL COMMENT '类别名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`) COMMENT '商品类别表'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime_category`
--

LOCK TABLES `anime_category` WRITE;
/*!40000 ALTER TABLE `anime_category` DISABLE KEYS */;
INSERT INTO `anime_category` VALUES (1,'国漫','2020-08-11 01:11:04','2020-08-11 01:11:04'),(2,'日漫','2020-08-11 01:11:32','2020-08-11 01:12:03'),(3,'美漫','2020-08-11 01:12:03','2020-08-11 01:12:07'),(5,'全部','2020-08-20 12:45:56','2020-08-20 12:45:56');
/*!40000 ALTER TABLE `anime_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anime_info`
--

DROP TABLE IF EXISTS `anime_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime_info` (
  `anime_id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `anime_name` varchar(60) NOT NULL COMMENT '商品名称',
  `category_id` int NOT NULL COMMENT '商品类别',
  `anime_price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `anime_desc` varchar(300) NOT NULL COMMENT '商品描述',
  `anime_stock` int NOT NULL COMMENT '商品库存',
  `anime_icon` varchar(1000) DEFAULT NULL COMMENT '商品图片',
  `anime_tag` varchar(300) NOT NULL COMMENT '商品标签',
  `admin_id` int NOT NULL COMMENT '管理员id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`anime_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime_info`
--

LOCK TABLES `anime_info` WRITE;
/*!40000 ALTER TABLE `anime_info` DISABLE KEYS */;
INSERT INTO `anime_info` VALUES (1,'海贼王_路飞',2,118.00,'现货 Megahouse 手办 POP 海贼 和之国 WA路飞 和服武士',8,'../static/images/anime_img/rm/路飞/lufei01.jpg&../static/images/anime_img/rm/路飞/lufei02.jpg&../static/images/anime_img/rm/路飞/lufei03.jpg&../static/images/anime_img/rm/路飞/lufei04.jpg&../static/images/anime_img/rm/路飞/lufei05.jpg','ACG作品名: 海贼王/onepiece&款式: 静态',1,'2020-08-11 01:24:12','2020-08-30 02:05:29'),(2,'孙悟空_超三 ',2,369.00,'眼镜厂16330 景品 龙珠Z Grandista nero 孙悟空 超三 3 海外限定',8,'../static/images/anime_img/rm/孙悟空3/chao301.jpg&../static/images/anime_img/rm/孙悟空3/chao302.jpg&../static/images/anime_img/rm/孙悟空3/chao303.jpg&../static/images/anime_img/rm/孙悟空3/chao304.jpg&../static/images/anime_img/rm/孙悟空3/chao305.jpg','ACG作品名: 龙珠Z&品牌: Banpresto/眼镜厂',1,'2020-08-11 01:29:25','2020-08-30 07:48:57'),(3,'夜凯',2,89.00,'优质版现货火影忍者鸣人GK迈特凯八门遁甲夜凯可亮灯机箱手办模型',8,'../static/images/anime_img/rm/夜凯/kai01.jpg&../static/images/anime_img/rm/夜凯/kai02.jpg&../static/images/anime_img/rm/夜凯/kai03.jpg&../static/images/anime_img/rm/夜凯/kai04.jpg','尺寸: 现货优质版&颜色分类: 八门夜凯（眼睛可亮）',1,'2020-08-11 01:43:37','2020-09-01 01:42:12'),(4,'石神千空',2,338.00,'GSC Dr.STONE 石纪元 石神千空 粘土人 手办 现货',8,'../static/images/anime_img/rm/石神千空/qiankong01.jpg&../static/images/anime_img/rm/石神千空/qiankong02.jpg&../static/images/anime_img/rm/石神千空/qiankong03.jpg&../static/images/anime_img/rm/石神千空/qiankong04.jpg&../static/images/anime_img/rm/石神千空/qiankong05.jpg','品牌: Good Smile&出售状态: 现货',1,'2020-08-11 01:49:31','2020-09-01 01:42:37'),(5,'索隆',2,168.00,'全套海贼王手办模型索隆战斗三千世界公仔动漫生日礼物摆件玩偶',8,'../static/images/anime_img/rm/索隆/suolong01.jpg&../static/images/anime_img/rm/索隆/suolong02.jpg&../static/images/anime_img/rm/索隆/suolong03.jpg&../static/images/anime_img/rm/索隆/suolong04.jpg&../static/images/anime_img/rm/索隆/suolong05.jpg','ACG角色名: 索隆&颜色分类: 索隆带剑气',1,'2020-08-11 01:56:56','2020-08-30 01:24:24'),(6,'卫庄',1,668.00,'秦时明月手办RinToys玩界 卫庄《秦时明月》系列1/8手办人偶',8,'../static/images/anime_img/gm/卫庄/weizhuang01.jpg&../static/images/anime_img/gm/卫庄/weizhuang02.jpg&../static/images/anime_img/gm/卫庄/weizhuang03.jpg&../static/images/anime_img/gm/卫庄/weizhuang04.jpg&../static/images/anime_img/gm/卫庄/weizhuang05.jpg','ACG作品名: 秦时明月&型号: RT009',1,'2020-08-11 02:25:33','2020-09-01 01:39:31'),(7,'曹焱兵',1,287.00,'正版镇魂街曹焱兵许褚Q版手办模型玩偶摆件公仔守护灵动漫周边',9,'../static/images/anime_img/gm/曹焱兵/cao01.jpg&../static/images/anime_img/gm/曹焱兵/cao02.jpg&../static/images/anime_img/gm/曹焱兵/cao03.jpg&../static/images/anime_img/gm/曹焱兵/cao04.jpg&../static/images/anime_img/gm/曹焱兵/cao05.jpg','ACG作品名: 镇魂街&款式: 静态',1,'2020-08-11 02:25:33','2020-09-01 01:47:14'),(8,'宝儿姐_雨天',1,518.00,'ACTOYS冯宝宝手办一人之下周边1/7宝儿姐雨天撑伞古风',10,'../static/images/anime_img/gm/宝儿姐_冬日/baojied01.jpg&../static/images/anime_img/gm/宝儿姐_冬日/baojied02.jpg&../static/images/anime_img/gm/宝儿姐_冬日/baojied03.jpg&../static/images/anime_img/gm/宝儿姐_冬日/baojied04.jpg&../static/images/anime_img/gm/宝儿姐_冬日/baojied05.jpg','ACG作品名: 一人之下&热门系列: pvc系列',1,'2020-08-11 02:30:47','2020-08-29 07:05:48'),(9,'宝儿姐_冬日',1,238.00,'ACTOYS一人之下冯宝宝手办烤红薯冬日暖薯宝儿姐周边',9,'../static/images/anime_img/gm/宝儿姐_雨天/baojiey01.jpg&../static/images/anime_img/gm/宝儿姐_雨天/baojiey02.jpg&../static/images/anime_img/gm/宝儿姐_雨天/baojiey03.jpg&../static/images/anime_img/gm/宝儿姐_雨天/baojiey04.jpg&../static/images/anime_img/gm/宝儿姐_雨天/baojiey05.jpg','品牌: ACTOYS&适用年龄: 14周岁以上',1,'2020-08-11 02:30:47','2020-09-01 01:39:24'),(10,'不良帅',1,388.00,'不良帅 手办 画江湖之不良人周边 不良帅 树脂粘土手办（硬手办）',9,'../static/images/anime_img/gm/不良帅/buliangshuai01.jpg&../static/images/anime_img/gm/不良帅/buliangshuai02.jpg&../static/images/anime_img/gm/不良帅/buliangshuai03.jpg&../static/images/anime_img/gm/不良帅/buliangshuai04.jpg&../static/images/anime_img/gm/不良帅/buliangshuai05.jpg','ACG作品名: 画江湖之不良人&款式: 静态',1,'2020-08-11 02:47:37','2020-09-01 01:41:44'),(11,'美国队长',3,190.00,'Team of Prototypingm美国队长斯蒂夫罗杰斯12寸1/6可动模型手办',9,'../static/images/anime_img/mm/美国队长/duizhang01.jpg&../static/images/anime_img/mm/美国队长/duizhang02.jpg&../static/images/anime_img/mm/美国队长/duizhang03.jpg&../static/images/anime_img/mm/美国队长/duizhang04.jpg&../static/images/anime_img/mm/美国队长/duizhang05.jpg','款式: 关节可动&作品来源: 影视',1,'2020-08-11 03:05:59','2020-08-30 01:30:57'),(12,'蜘蛛侠',3,145.00,'Empire toys复仇者联盟3无限战争钢铁蜘蛛侠彼得帕克1/6模型手办',10,'../static/images/anime_img/mm/蜘蛛侠/zhizhu01.jpg&../static/images/anime_img/mm/蜘蛛侠/zhizhu02.jpg&../static/images/anime_img/mm/蜘蛛侠/zhizhu03.jpg&../static/images/anime_img/mm/蜘蛛侠/zhizhu04.jpg&../static/images/anime_img/mm/蜘蛛侠/zhizhu05.jpg','ACG作品名: 漫威漫画系列&材质: PVC',1,'2020-08-11 03:05:59','2020-08-29 04:05:02'),(13,'绿巨人',3,128.00,'漫威绿巨人无敌浩克手办模型玩具公仔家居摆件生日礼物',9,'../static/images/anime_img/mm/绿巨人/lujuren01.jpg&../static/images/anime_img/mm/绿巨人/lujuren02.jpg&../static/images/anime_img/mm/绿巨人/lujuren03.jpg&../static/images/anime_img/mm/绿巨人/lujuren04.jpg&../static/images/anime_img/mm/绿巨人/lujuren05.jpg','热门系列: 绿巨人&颜色分类: 静态绿巨人',1,'2020-08-11 03:05:59','2020-08-30 07:51:06'),(14,'忍者神龟',3,306.00,'正版NECA忍者神龟4款7寸可动人偶模型摆件手办盒装',10,'../static/images/anime_img/mm/忍者神龟/shengui01.jpg&../static/images/anime_img/mm/忍者神龟/shengui02.jpg&../static/images/anime_img/mm/忍者神龟/shengui03.jpg&../static/images/anime_img/mm/忍者神龟/shengui04.jpg','材质: PVC&款式: 关节可动',1,'2020-08-11 03:05:59','2020-08-29 04:05:02'),(15,'海绵宝宝',3,788.00,'正版Mighty Jaxx海绵宝宝盲盒 半解刨创意手办公仔摆件',10,'../static/images/anime_img/mm/海绵宝宝/haimian01.jpg&../static/images/anime_img/mm/海绵宝宝/haimian02.jpg&../static/images/anime_img/mm/海绵宝宝/haimian03.jpg&../static/images/anime_img/mm/海绵宝宝/haimian04.jpg&../static/images/anime_img/mm/海绵宝宝/haimian05.jpg','ACG作品名: 海绵宝宝&款式: 静态',1,'2020-08-11 03:05:59','2020-08-29 06:39:10');
/*!40000 ALTER TABLE `anime_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anime_specs`
--

DROP TABLE IF EXISTS `anime_specs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anime_specs` (
  `specs_id` int NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `anime_id` int NOT NULL COMMENT '商品id',
  `specs_name` varchar(100) NOT NULL COMMENT '规格名称',
  `specs_stock` int NOT NULL COMMENT '规格库存',
  `specs_price` decimal(10,2) NOT NULL COMMENT '规格价格',
  `specs_icon` varchar(300) DEFAULT NULL COMMENT '规格图片(大)',
  `specs_preview` varchar(300) DEFAULT NULL COMMENT '规格图片(小)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`specs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品规格';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anime_specs`
--

LOCK TABLES `anime_specs` WRITE;
/*!40000 ALTER TABLE `anime_specs` DISABLE KEYS */;
INSERT INTO `anime_specs` VALUES (1,1,'高25cm',4,118.00,'../static/images/anime_img/rm/路飞/lufei01.jpg','../static/images/anime_img/rm/路飞/lufei01.jpg','2020-08-11 01:26:19','2020-08-30 01:18:00'),(2,1,'高50cm',4,312.00,'../static/images/anime_img/rm/路飞/lufei02.jpg','../static/images/anime_img/rm/路飞/lufei02.jpg','2020-08-11 01:26:54','2020-08-30 02:05:55'),(3,2,'高28cm',4,369.00,'../static/images/anime_img/rm/孙悟空3/chao301.jpg','../static/images/anime_img/rm/孙悟空3/chao301.jpg','2020-08-11 01:32:28','2020-08-30 01:18:04'),(4,2,'高52cm',4,552.00,'../static/images/anime_img/rm/孙悟空3/chao302.jpg','../static/images/anime_img/rm/孙悟空3/chao302.jpg','2020-08-11 01:32:28','2020-08-30 07:48:57'),(5,3,'高31cm',4,89.00,'../static/images/anime_img/rm/夜凯/kai01.jpg','../static/images/anime_img/rm/夜凯/kai01.jpg','2020-08-11 01:45:16','2020-09-01 01:42:12'),(6,3,'高55cm',4,199.00,'../static/images/anime_img/rm/夜凯/kai02.jpg','../static/images/anime_img/rm/夜凯/kai02.jpg','2020-08-11 01:45:16','2020-08-30 01:35:25'),(7,4,'高10cm',4,338.00,'../static/images/anime_img/rm/石神千空/qiankong01.jpg','../static/images/anime_img/rm/石神千空/qiankong01.jpg','2020-08-11 01:51:05','2020-09-01 01:42:37'),(8,4,'高25cm',4,458.00,'../static/images/anime_img/rm/石神千空/qiankong02.jpg','../static/images/anime_img/rm/石神千空/qiankong02.jpg','2020-08-11 01:51:05','2020-09-01 01:42:12'),(9,5,'高21cm',4,168.00,'../static/images/anime_img/rm/索隆/suolong01.jpg','../static/images/anime_img/rm/索隆/suolong01.jpg','2020-08-11 01:58:21','2020-08-30 01:24:24'),(10,5,'高42cm',4,238.00,'../static/images/anime_img/rm/索隆/suolong02.jpg','../static/images/anime_img/rm/索隆/suolong02.jpg','2020-08-11 01:58:21','2020-08-30 01:18:10'),(11,6,'高68cm',4,668.00,'../static/images/anime_img/gm/卫庄/weizhuang01.jpg','../static/images/anime_img/gm/卫庄/weizhuang01.jpg','2020-08-11 02:14:52','2020-08-30 01:35:09'),(12,6,'高93cm',4,998.00,'../static/images/anime_img/gm/卫庄/weizhuang02.jpg','../static/images/anime_img/gm/卫庄/weizhuang02.jpg','2020-08-11 02:14:52','2020-09-01 01:39:31'),(13,7,'高16cm',4,287.00,'../static/images/anime_img/gm/曹焱兵/cao01.jpg','../static/images/anime_img/gm/曹焱兵/cao01.jpg','2020-08-11 02:26:39','2020-08-30 01:35:21'),(14,7,'高28cm',5,396.00,'../static/images/anime_img/gm/曹焱兵/cao02.jpg','../static/images/anime_img/gm/曹焱兵/cao02.jpg','2020-08-11 02:26:39','2020-09-01 01:47:14'),(15,8,'高31cm',5,518.00,'../static/images/anime_img/gm/宝儿姐_冬日/baojied01.jpg','../static/images/anime_img/gm/宝儿姐_冬日/baojied01.jpg','2020-08-11 02:34:03','2020-08-29 07:06:03'),(16,8,'高51cm',5,728.00,'../static/images/anime_img/gm/宝儿姐_冬日/baojied02.jpg','../static/images/anime_img/gm/宝儿姐_冬日/baojied02.jpg','2020-08-11 02:34:03','2020-08-29 03:56:36'),(17,9,'高16cm',4,238.00,'../static/images/anime_img/gm/宝儿姐_雨天/baojiey01.jpg','../static/images/anime_img/gm/宝儿姐_雨天/baojiey01.jpg','2020-08-11 02:34:03','2020-09-01 01:39:24'),(18,9,'高28cm',5,398.00,'../static/images/anime_img/gm/宝儿姐_雨天/baojiey02.jpg','../static/images/anime_img/gm/宝儿姐_雨天/baojiey02.jpg','2020-08-11 02:34:03','2020-08-29 04:05:02'),(19,10,'高12cm',4,388.00,'../static/images/anime_img/gm/不良帅/buliangshuai01.jpg','../static/images/anime_img/gm/不良帅/buliangshuai01.jpg','2020-08-11 02:48:09','2020-09-01 01:41:44'),(20,10,'高25cm',5,528.00,'../static/images/anime_img/gm/不良帅/buliangshuai02.jpg','../static/images/anime_img/gm/不良帅/buliangshuai02.jpg','2020-08-11 02:48:09','2020-08-29 03:54:16'),(21,11,'高30cm',4,190.00,'../static/images/anime_img/mm/美国队长/duizhang01.jpg','../static/images/anime_img/mm/美国队长/duizhang01.jpg','2020-08-11 02:54:15','2020-08-30 01:30:57'),(22,11,'高55cm',5,380.00,'../static/images/anime_img/mm/美国队长/duizhang02.jpg','../static/images/anime_img/mm/美国队长/duizhang02.jpg','2020-08-11 02:54:15','2020-08-24 00:41:24'),(23,12,'高28cm',5,145.00,'../static/images/anime_img/mm/蜘蛛侠/zhizhu01.jpg','../static/images/anime_img/mm/蜘蛛侠/zhizhu01.jpg','2020-08-11 02:57:49','2020-08-29 04:05:02'),(24,12,'高50cm',5,298.00,'../static/images/anime_img/mm/蜘蛛侠/zhizhu02.jpg','../static/images/anime_img/mm/蜘蛛侠/zhizhu02.jpg','2020-08-11 02:57:49','2020-08-21 03:47:12'),(25,13,'高30cm',4,128.00,'../static/images/anime_img/mm/绿巨人/lujuren01.jpg','../static/images/anime_img/mm/绿巨人/lujuren01.jpg','2020-08-11 03:09:45','2020-08-30 01:31:03'),(26,13,'高60cm',5,328.00,'../static/images/anime_img/mm/绿巨人/lujuren02.jpg','../static/images/anime_img/mm/绿巨人/lujuren02.jpg','2020-08-11 03:09:46','2020-08-30 07:51:06'),(27,14,'高18cm',5,306.00,'../static/images/anime_img/mm/忍者神龟/shengui01.jpg','../static/images/anime_img/mm/忍者神龟/shengui01.jpg','2020-08-11 03:09:46','2020-08-29 04:05:02'),(28,14,'高32cm',5,512.00,'../static/images/anime_img/mm/忍者神龟/shengui02.jpg','../static/images/anime_img/mm/忍者神龟/shengui02.jpg','2020-08-11 03:09:46','2020-08-27 22:51:06'),(29,15,'高15cm',5,788.00,'../static/images/anime_img/mm/海绵宝宝/haimian01.jpg','../static/images/anime_img/mm/海绵宝宝/haimian01.jpg','2020-08-11 03:09:46','2020-08-29 06:39:10'),(30,15,'高28cm',5,1288.00,'../static/images/anime_img/mm/海绵宝宝/haimian02.jpg','../static/images/anime_img/mm/海绵宝宝/haimian02.jpg','2020-08-11 03:09:46','2020-08-21 03:47:12');
/*!40000 ALTER TABLE `anime_specs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancel_cause`
--

DROP TABLE IF EXISTS `cancel_cause`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cancel_cause` (
  `cause_id` int NOT NULL AUTO_INCREMENT,
  `cause_text` varchar(100) NOT NULL,
  PRIMARY KEY (`cause_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单取消原因';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancel_cause`
--

LOCK TABLES `cancel_cause` WRITE;
/*!40000 ALTER TABLE `cancel_cause` DISABLE KEYS */;
INSERT INTO `cancel_cause` VALUES (1,'取消订单'),(2,'价格有点贵'),(3,'规格/款式/数量拍错'),(4,'收货地址拍错'),(5,'暂时不需要了'),(6,'其他'),(7,'订单已超时'),(8,'已支付'),(9,'未支付');
/*!40000 ALTER TABLE `cancel_cause` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `order_details_id` varchar(100) NOT NULL,
  `order_id` varchar(100) NOT NULL,
  `user_id` int NOT NULL DEFAULT '0',
  `address_id` int NOT NULL,
  `anime_id` int NOT NULL DEFAULT '0',
  `specs_id` int NOT NULL DEFAULT '0',
  `price` decimal(10,0) NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `total_price` decimal(10,0) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES ('1598750318000432512','1598750317997121082',2,7,1,1,11800,1,118,'2020-08-30 01:18:38','2020-08-30 01:18:38'),('1598750318003817161','1598750317997121082',2,7,2,3,36900,1,369,'2020-08-30 01:18:38','2020-08-30 01:18:38'),('1598750318006589459','1598750317997121082',2,7,5,10,23800,1,238,'2020-08-30 01:18:38','2020-08-30 01:18:38'),('1598750664557332537','1598750664557284073',2,7,5,9,16800,1,168,'2020-08-30 01:24:24','2020-08-30 01:24:24'),('1598750879733251984','1598750879733875505',2,7,9,17,23800,1,238,'2020-08-30 01:27:59','2020-08-30 01:27:59'),('1598751068729580044','1598751068725121890',2,7,11,21,19000,1,190,'2020-08-30 01:31:08','2020-08-30 01:31:08'),('1598751068732798193','1598751068725121890',2,7,13,25,12800,1,128,'2020-08-30 01:31:08','2020-08-30 01:31:08'),('1598751124787661021','1598751124787703000',2,7,1,2,31200,1,312,'2020-08-30 01:32:04','2020-08-30 01:32:04'),('1598751309647280877','1598751309647641327',2,7,6,11,66800,1,668,'2020-08-30 01:35:09','2020-08-30 01:35:09'),('1598751330233789197','1598751330224230902',2,7,3,6,19900,1,199,'2020-08-30 01:35:30','2020-08-30 01:35:30'),('1598752412528879433','1598752412528779816',2,7,1,2,31200,1,312,'2020-08-30 01:53:32','2020-08-30 01:53:32'),('1598753006496658396','1598753006496956935',2,7,4,8,45800,1,458,'2020-08-30 02:03:26','2020-08-30 02:03:26'),('1598773749278493367','1598773749267142201',2,7,2,4,55200,1,552,'2020-08-30 07:49:09','2020-08-30 07:49:09'),('1598773749283388813','1598773749267142201',2,7,4,7,33800,1,338,'2020-08-30 07:49:09','2020-08-30 07:49:09'),('1598773806285318498','1598773806285928609',2,7,3,5,8900,1,89,'2020-08-30 07:50:06','2020-08-30 07:50:06'),('1598773857973353262','1598773857969189146',2,7,4,7,33800,1,338,'2020-08-30 07:50:57','2020-08-30 07:50:57'),('1598773857977232510','1598773857969189146',2,7,13,26,32800,1,328,'2020-08-30 07:50:57','2020-08-30 07:50:57'),('1598924275330225466','1598924275327841843',2,7,7,14,39600,1,396,'2020-09-01 01:37:55','2020-09-01 01:37:55'),('1598924275334717260','1598924275327841843',2,7,9,17,23800,1,238,'2020-09-01 01:37:55','2020-09-01 01:37:55'),('1598924376403167664','1598924376399414817',2,7,9,17,23800,1,238,'2020-09-01 01:39:36','2020-09-01 01:39:36'),('1598924376406720943','1598924376399414817',2,7,6,12,99800,1,998,'2020-09-01 01:39:36','2020-09-01 01:39:36'),('1598924408162285567','1598924408162316382',2,7,4,8,45800,1,458,'2020-09-01 01:40:08','2020-09-01 01:40:08'),('1598924425562523863','1598924425562987576',2,7,10,19,38800,1,388,'2020-09-01 01:40:25','2020-09-01 01:40:25'),('1598924504766453264','1598924504766314717',2,7,10,19,38800,1,388,'2020-09-01 01:41:44','2020-09-01 01:41:44'),('1598924524970130851','1598924524967799264',2,7,3,5,8900,1,89,'2020-09-01 01:42:04','2020-09-01 01:42:04'),('1598924524973470202','1598924524967799264',2,7,4,8,45800,1,458,'2020-09-01 01:42:04','2020-09-01 01:42:04'),('1598924548198538242','1598924548198531954',2,7,4,7,33800,1,338,'2020-09-01 01:42:28','2020-09-01 01:42:28'),('1598924824985751000','1598924824982778914',3,14,7,14,39600,1,396,'2020-09-01 01:47:04','2020-09-01 01:47:04');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` varchar(100) NOT NULL COMMENT '订单编号',
  `order_price` decimal(10,0) NOT NULL,
  `order_state` int NOT NULL DEFAULT '0' COMMENT '订单状态(0未支付,1已支付,2取消订单)',
  `cause_id` int NOT NULL DEFAULT '9' COMMENT '取消订单原因',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('1598750317997121082',745,1,8,'2020-08-30 01:18:38','2020-08-30 01:24:12'),('1598750664557284073',188,1,8,'2020-08-30 01:24:24','2020-08-30 01:28:31'),('1598750879733875505',258,2,3,'2020-08-30 01:27:59','2020-08-30 01:28:15'),('1598751068725121890',338,1,8,'2020-08-30 01:31:08','2020-08-30 01:33:46'),('1598751124787703000',332,1,8,'2020-08-30 01:32:04','2020-08-30 01:33:46'),('1598751309647641327',688,1,8,'2020-08-30 01:35:09','2020-08-30 01:35:10'),('1598751330224230902',506,1,8,'2020-08-30 01:35:30','2020-08-30 01:35:31'),('1598753006496956935',478,2,7,'2020-08-30 02:03:26','2020-08-30 02:04:32'),('1598773749267142201',910,1,8,'2020-08-30 07:49:09','2020-08-30 07:49:45'),('1598773806285928609',109,1,8,'2020-08-30 07:50:06','2020-08-30 07:50:24'),('1598773857969189146',686,2,3,'2020-08-30 07:50:57','2020-08-30 07:51:06'),('1598924275327841843',654,2,7,'2020-09-01 01:37:55','2020-09-01 01:38:54'),('1598924376399414817',1256,1,8,'2020-09-01 01:39:36','2020-09-01 01:39:42'),('1598924408162316382',478,1,8,'2020-09-01 01:40:08','2020-09-01 01:40:13'),('1598924425562987576',408,2,7,'2020-09-01 01:40:25','2020-09-01 01:41:25'),('1598924504766314717',408,1,8,'2020-09-01 01:41:44','2020-09-01 01:41:46'),('1598924524967799264',567,2,5,'2020-09-01 01:42:04','2020-09-01 01:42:12'),('1598924548198531954',358,2,3,'2020-09-01 01:42:28','2020-09-01 01:42:37'),('1598924824982778914',416,2,5,'2020-09-01 01:47:04','2020-09-01 01:47:14');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address` (
  `user_addressid` int NOT NULL AUTO_INCREMENT COMMENT '地址编号',
  `user_id` int NOT NULL,
  `address_name` varchar(100) NOT NULL,
  `user_phone` varchar(11) NOT NULL COMMENT '用户手机',
  `user_address` varchar(100) NOT NULL COMMENT '用户地址',
  `user_area` int NOT NULL COMMENT '地区编号',
  PRIMARY KEY (`user_addressid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` VALUES (7,2,'张三','13256265658','河北省/唐山市/路北区/建设路181号',130203),(14,3,'李四','12312345645','河北省/唐山市/路南区/长安大街滨湖小区18号楼3单元201',130202),(18,2,'张三爸爸','13784146258','河北省/唐山市/路南区/建设路10号',130202);
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_cart`
--

DROP TABLE IF EXISTS `user_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_cart` (
  `cart_id` varchar(100) NOT NULL COMMENT '购物车',
  `user_id` int NOT NULL COMMENT '用户id',
  `anime_id` int NOT NULL COMMENT '商品id\n',
  `specs_id` int NOT NULL COMMENT '商品规格id',
  `quantity` int NOT NULL COMMENT '加入购物车数量',
  `checked` int NOT NULL DEFAULT '0' COMMENT '购物车选中状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_cart`
--

LOCK TABLES `user_cart` WRITE;
/*!40000 ALTER TABLE `user_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(60) NOT NULL COMMENT '用户名称',
  `user_salt` varchar(100) DEFAULT NULL,
  `user_password` varchar(100) NOT NULL COMMENT '用户密码',
  `user_icon` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `user_addressid` int DEFAULT '0' COMMENT '地址编号',
  `state` int NOT NULL DEFAULT '0' COMMENT '登陆状态',
  `user_role` varchar(20) NOT NULL COMMENT '用户角色',
  `user_perm` varchar(2) NOT NULL COMMENT '用户权限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'admin','yPHmDsqUvKfDRJHEzpJ8MQ==','47d5a1fcfcc99e58f6d5a527e9e69577','',0,0,'admin','1','2020-08-11 06:49:31','2020-08-23 08:23:48'),(2,'zhangsan','Wkd2aO8ZUbxIIGSBtqYIeQ==','42ffd160adf9ad7e0b8f631f64d7c8ea','http://127.0.0.1:8040/img/file/T_1598926527100423176.jpg',18,1,'user','0','2020-08-18 07:26:56','2020-09-01 03:03:17'),(3,'lisi','HNhKFiGjkZV2ayicoFJV/A==','ce9f28fa6fe1539a499460aaf174b88b','http://127.0.0.1:8040/img/file/T_1598924620821251305.jpg',14,0,'user','0','2020-08-18 10:26:53','2020-09-01 01:46:58'),(4,'王五','GBeNd8FHkMY4yjsPfZdM6Q==','54679593f656752b7a49f1a3129d7e7b',NULL,0,0,'user','0','2020-08-18 10:33:18','2020-08-18 10:33:18'),(5,'小红','ZPR0weIHe1gT6eo74up3Bw==','b135ad55aae501df3256eb6d1e144a48',NULL,0,0,'user','0','2020-08-18 10:34:13','2020-08-18 10:38:24'),(6,'小明','yPHmDsqUvKfDRJHEzpJ8MQ==','47d5a1fcfcc99e58f6d5a527e9e69577',NULL,0,0,'user','0','2020-08-18 10:37:47','2020-08-18 10:37:47'),(7,'abc','UNRfE6Mgmjr9mhXbshrEKg==','8aa2c8c0e71d9fdaf366c3e9a9c5dcf3',NULL,0,0,'user','0','2020-08-18 13:03:21','2020-08-18 13:03:21'),(10,'测试1','4em40fQE2uEHZ2YLmTWtCA==','cef6c432929760da0f869fec004337b5',NULL,0,0,'user','0','2020-08-18 13:26:24','2020-08-18 13:26:24'),(11,'测试2','y67lCyOl2SleJmQH49Jnww==','927256c844b319764ec31bbf008f138a',NULL,0,0,'user','0','2020-08-18 13:27:55','2020-08-18 13:27:55'),(12,'测试3','y4RSaFpH4X/XAc1rVwJQRw==','8e36c58123451fd5e566c619820fe363',NULL,0,0,'user','0','2020-08-18 13:28:37','2020-08-18 13:28:37'),(13,'测试4','EgeRqaxvjeGJ0RkTdNHgTQ==','d9aaafe39ab7aa698278c237252a036c',NULL,0,0,'user','0','2020-08-18 13:29:11','2020-08-18 13:29:11'),(14,'杂货','pu3mH9wrVwyDuFA1xLrtrw==','e5c46a1b92722213a28f4af4b1fac9c9',NULL,0,0,'user','0','2020-09-01 02:26:12','2020-09-01 02:26:12');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-01 11:19:41
