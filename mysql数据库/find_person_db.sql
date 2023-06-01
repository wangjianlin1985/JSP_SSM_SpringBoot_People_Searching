/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : find_person_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-07-26 15:31:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `t_findcomment`
-- ----------------------------
DROP TABLE IF EXISTS `t_findcomment`;
CREATE TABLE `t_findcomment` (
  `commentId` int(11) NOT NULL auto_increment COMMENT '评论id',
  `findPersonObj` int(11) NOT NULL COMMENT '被评寻人信息',
  `content` varchar(1000) NOT NULL COMMENT '评论内容',
  `userObj` varchar(30) NOT NULL COMMENT '评论用户',
  `commentTime` varchar(20) default NULL COMMENT '评论时间',
  PRIMARY KEY  (`commentId`),
  KEY `findPersonObj` (`findPersonObj`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_findcomment_ibfk_1` FOREIGN KEY (`findPersonObj`) REFERENCES `t_findperson` (`findPersonId`),
  CONSTRAINT `t_findcomment_ibfk_2` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_findcomment
-- ----------------------------
INSERT INTO `t_findcomment` VALUES ('1', '1', '谁有线索请马上联系我', 'user1', '2018-05-24 12:11:12');
INSERT INTO `t_findcomment` VALUES ('2', '1', '我好像看到过！', 'user2', '2018-05-25 21:25:04');
INSERT INTO `t_findcomment` VALUES ('3', '2', '人贩子最可恨了！', 'user1', '2018-05-25 21:59:50');
INSERT INTO `t_findcomment` VALUES ('4', '2', '是的，希望所有的人贩子都死光！', 'user2', '2018-05-25 22:00:16');

-- ----------------------------
-- Table structure for `t_findperson`
-- ----------------------------
DROP TABLE IF EXISTS `t_findperson`;
CREATE TABLE `t_findperson` (
  `findPersonId` int(11) NOT NULL auto_increment COMMENT '寻人id',
  `personName` varchar(20) NOT NULL COMMENT '被寻人姓名',
  `sex` varchar(4) NOT NULL COMMENT '性别',
  `personPhoto` varchar(60) NOT NULL COMMENT '被寻人照片',
  `bloodType` varchar(20) NOT NULL COMMENT '血型',
  `nativePlaceObj` int(11) NOT NULL COMMENT '籍贯',
  `findTypeObj` int(11) NOT NULL COMMENT '寻找类型',
  `lostTypeObj` int(11) NOT NULL COMMENT '失踪类型',
  `bornDate` varchar(20) default NULL COMMENT '出生日期',
  `lostDate` varchar(20) default NULL COMMENT '失踪日期',
  `lostHeight` varchar(20) NOT NULL COMMENT '失踪时身高',
  `lostPlace` varchar(50) NOT NULL COMMENT '失踪地点',
  `personDesc` varchar(8000) NOT NULL COMMENT '相貌特征描述',
  `connectPerson` varchar(20) NOT NULL COMMENT '联系人',
  `connectPhone` varchar(20) NOT NULL COMMENT '联系电话',
  `email` varchar(50) default NULL COMMENT '电子邮箱',
  `connectAddress` varchar(60) default NULL COMMENT '联系地址',
  `postcode` varchar(20) default NULL COMMENT '邮政编码',
  `otherWay` varchar(30) default NULL COMMENT '其他联系方式',
  `memo` varchar(500) default NULL COMMENT '备注',
  `userObj` varchar(30) NOT NULL COMMENT '发布用户',
  `addTime` varchar(20) NOT NULL COMMENT '发布时间',
  PRIMARY KEY  (`findPersonId`),
  KEY `nativePlaceObj` (`nativePlaceObj`),
  KEY `findTypeObj` (`findTypeObj`),
  KEY `lostTypeObj` (`lostTypeObj`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_findperson_ibfk_1` FOREIGN KEY (`nativePlaceObj`) REFERENCES `t_nativeplace` (`placeId`),
  CONSTRAINT `t_findperson_ibfk_2` FOREIGN KEY (`findTypeObj`) REFERENCES `t_findtype` (`findTypeId`),
  CONSTRAINT `t_findperson_ibfk_3` FOREIGN KEY (`lostTypeObj`) REFERENCES `t_losttype` (`lostTypeId`),
  CONSTRAINT `t_findperson_ibfk_4` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_findperson
-- ----------------------------
INSERT INTO `t_findperson` VALUES ('1', '赵玉婷', '女', 'upload/b588918d-3e93-4ba8-8925-6d7d111c5fd8.jpg', '不详', '1', '8', '1', '1994-02-09', '2018-05-01', '1.55米', '广州市番禺区市桥街大南路', '<p><span style=\"font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 30px;\">白色无袖连衣裙，长发扎马尾，黑色人字拖</span></p>', '赵小姐', '15625129043', '--', '--', '--', '--', '--', 'user1', '2018-05-24 11:52:38');
INSERT INTO `t_findperson` VALUES ('2', '李晓梅', '女', 'upload/e2c925a7-56e3-4812-b58a-241bb63d0dfa.jpg', 'O型', '5', '4', '2', '1998-05-04', '2018-05-17', '1.54米', '湖北', '<p><span style=\"font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 30px;\">大眼睛双眼皮，头发比较多，腰身有褐色胎记&nbsp;</span></p>', '滕小歌', '13733996210', '446714756@qq.com', '河南省漯河市临颖县', '462300', '17339572096', '是1991年农历11月份被拐卖过来的', 'user1', '2018-05-25 20:59:41');

-- ----------------------------
-- Table structure for `t_findtype`
-- ----------------------------
DROP TABLE IF EXISTS `t_findtype`;
CREATE TABLE `t_findtype` (
  `findTypeId` int(11) NOT NULL auto_increment COMMENT '寻找类型id',
  `findTypeName` varchar(20) NOT NULL COMMENT '寻找类型名称',
  PRIMARY KEY  (`findTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_findtype
-- ----------------------------
INSERT INTO `t_findtype` VALUES ('1', '父母寻儿子');
INSERT INTO `t_findtype` VALUES ('2', '父母寻女儿');
INSERT INTO `t_findtype` VALUES ('3', '儿子寻父母');
INSERT INTO `t_findtype` VALUES ('4', '女儿寻父母');
INSERT INTO `t_findtype` VALUES ('5', '兄弟寻姐妹');
INSERT INTO `t_findtype` VALUES ('6', '姐妹寻兄弟');
INSERT INTO `t_findtype` VALUES ('7', '兄弟寻兄弟');
INSERT INTO `t_findtype` VALUES ('8', '姐妹寻姐妹');
INSERT INTO `t_findtype` VALUES ('9', '孙辈寻爷辈');
INSERT INTO `t_findtype` VALUES ('10', '爷辈寻孙辈');
INSERT INTO `t_findtype` VALUES ('11', '侄甥寻姑舅');
INSERT INTO `t_findtype` VALUES ('12', '姑舅寻侄甥');
INSERT INTO `t_findtype` VALUES ('13', '寻战友');
INSERT INTO `t_findtype` VALUES ('14', '寻同事');
INSERT INTO `t_findtype` VALUES ('15', '寻熟人');
INSERT INTO `t_findtype` VALUES ('16', '寻同学');
INSERT INTO `t_findtype` VALUES ('17', '寻邻居');
INSERT INTO `t_findtype` VALUES ('18', '寻亲友');
INSERT INTO `t_findtype` VALUES ('19', '其他');

-- ----------------------------
-- Table structure for `t_leaveword`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaveword`;
CREATE TABLE `t_leaveword` (
  `leaveWordId` int(11) NOT NULL auto_increment COMMENT '留言id',
  `leaveTitle` varchar(80) NOT NULL COMMENT '留言标题',
  `leaveContent` varchar(2000) NOT NULL COMMENT '留言内容',
  `userObj` varchar(30) NOT NULL COMMENT '留言人',
  `leaveTime` varchar(20) default NULL COMMENT '留言时间',
  `replyContent` varchar(1000) default NULL COMMENT '管理回复',
  `replyTime` varchar(20) default NULL COMMENT '回复时间',
  PRIMARY KEY  (`leaveWordId`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_leaveword_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaveword
-- ----------------------------
INSERT INTO `t_leaveword` VALUES ('1', '我想找我姐姐', '我姐姐是在她一岁的时候由我爸妈送给别人抚养的，当时的情况比较特殊。因为是农村，我爸妈那时候生了三个女儿，迫于各方面的压力，爸妈忍痛决定把我三姐送养，只希望能生一个儿子。当时是经过中间人介绍送给了一对外地夫妇，他们没有儿女，双方商量好的是，以后当做亲戚，经常走动，能让我爸妈经常看看我姐，那对夫妇也答应了，我爸妈就把我三姐给了那家人。等我妈怀孕了以后，打算去看看我三姐的时候，那对夫妇已经带着我姐搬走了，至今三十二年了，我爸妈很挂念我三姐，我做为我爸妈盼星星盼月亮盼来的儿子，我也希望能够帮爸妈找到我姐。我知道我能提供的资料很少很少，希望也很渺茫。但是，现在父母已经60多岁了，经常听他们说起这个素未谋面的三姐，我能看见老人眼里的亏欠和懊悔，爸爸经常看的就是中央台的寻亲节目，我能理解爸妈对女儿的思念。希望能够通过这个平台，能够给我一点点帮助吧！谢谢', 'user1', '2018-05-16 22:47:15', '已经发布寻找中', '2018-05-24 22:47:19');
INSERT INTO `t_leaveword` VALUES ('2', '92年冬生的女孩寻生父母', '我是92年冬天腊月的样子，被现在的姑父姑母抱回来给我养父收养的，大概在南通市里，当时的养父看上了我，对方(俩女一老一少)不给，可能也在挑人家，有可能她俩是我亲人，也有可能是人贩子，过了几天我现在姑父姑母去领我她们愿意给了，可能她们看她俩口子形象各方面还可以，当时给了600就把我带回现在家了。我当时应该很小，足月或一俩月的样子，右手手臂上有炭笔或者不知道是什么写的字，小时候大家也都认不出，现在大了痕迹不太明显。现在的家里生活的很好，养父也很疼我，自己大了，想找到自己的来源，不想人生有疑惑，有类似的可以发我邮件！自己发布的，只想自己知道，不想让现在家人知晓，怕伤害到他们，只想客观的寻源而已。', 'user1', '2018-05-25 21:40:57', '你可以发布寻找信息', '2018-05-25 21:41:07');

-- ----------------------------
-- Table structure for `t_losttype`
-- ----------------------------
DROP TABLE IF EXISTS `t_losttype`;
CREATE TABLE `t_losttype` (
  `lostTypeId` int(11) NOT NULL auto_increment COMMENT '丢失类型id',
  `lostTypeName` varchar(20) NOT NULL COMMENT '丢失类型名称',
  PRIMARY KEY  (`lostTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_losttype
-- ----------------------------
INSERT INTO `t_losttype` VALUES ('1', '失散亲友');
INSERT INTO `t_losttype` VALUES ('2', '被拐卖');
INSERT INTO `t_losttype` VALUES ('3', '被骗走');
INSERT INTO `t_losttype` VALUES ('4', '离家出走');
INSERT INTO `t_losttype` VALUES ('5', '迷路走失');
INSERT INTO `t_losttype` VALUES ('6', '不明原因');
INSERT INTO `t_losttype` VALUES ('7', '孤儿');
INSERT INTO `t_losttype` VALUES ('8', '遗弃');
INSERT INTO `t_losttype` VALUES ('9', '送养');
INSERT INTO `t_losttype` VALUES ('10', '收养');
INSERT INTO `t_losttype` VALUES ('11', '死亡');

-- ----------------------------
-- Table structure for `t_nativeplace`
-- ----------------------------
DROP TABLE IF EXISTS `t_nativeplace`;
CREATE TABLE `t_nativeplace` (
  `placeId` int(11) NOT NULL auto_increment COMMENT '籍贯id',
  `placeName` varchar(20) NOT NULL COMMENT '籍贯名称',
  PRIMARY KEY  (`placeId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_nativeplace
-- ----------------------------
INSERT INTO `t_nativeplace` VALUES ('1', '北京');
INSERT INTO `t_nativeplace` VALUES ('2', '天津');
INSERT INTO `t_nativeplace` VALUES ('3', '重庆');
INSERT INTO `t_nativeplace` VALUES ('4', '上海');
INSERT INTO `t_nativeplace` VALUES ('5', '河北');
INSERT INTO `t_nativeplace` VALUES ('6', '山西');
INSERT INTO `t_nativeplace` VALUES ('7', '山西');
INSERT INTO `t_nativeplace` VALUES ('8', '辽宁');
INSERT INTO `t_nativeplace` VALUES ('9', '吉林');
INSERT INTO `t_nativeplace` VALUES ('10', '黑龙江');
INSERT INTO `t_nativeplace` VALUES ('11', '江苏');
INSERT INTO `t_nativeplace` VALUES ('12', '浙江');
INSERT INTO `t_nativeplace` VALUES ('13', '安徽');
INSERT INTO `t_nativeplace` VALUES ('14', '福建');
INSERT INTO `t_nativeplace` VALUES ('15', '江西');
INSERT INTO `t_nativeplace` VALUES ('16', '山东');
INSERT INTO `t_nativeplace` VALUES ('17', '河南');
INSERT INTO `t_nativeplace` VALUES ('18', '湖北');
INSERT INTO `t_nativeplace` VALUES ('19', '湖南');
INSERT INTO `t_nativeplace` VALUES ('20', '广东');
INSERT INTO `t_nativeplace` VALUES ('21', '海南');
INSERT INTO `t_nativeplace` VALUES ('22', '四川');
INSERT INTO `t_nativeplace` VALUES ('23', '贵州');
INSERT INTO `t_nativeplace` VALUES ('24', '云南');
INSERT INTO `t_nativeplace` VALUES ('25', '陕西');
INSERT INTO `t_nativeplace` VALUES ('26', '甘肃');
INSERT INTO `t_nativeplace` VALUES ('27', '青海');
INSERT INTO `t_nativeplace` VALUES ('28', '内蒙古');
INSERT INTO `t_nativeplace` VALUES ('29', '广西');
INSERT INTO `t_nativeplace` VALUES ('30', '宁夏');
INSERT INTO `t_nativeplace` VALUES ('31', '新疆');
INSERT INTO `t_nativeplace` VALUES ('32', '西藏');
INSERT INTO `t_nativeplace` VALUES ('33', '香港');
INSERT INTO `t_nativeplace` VALUES ('34', '澳门');
INSERT INTO `t_nativeplace` VALUES ('35', '台湾');
INSERT INTO `t_nativeplace` VALUES ('36', '国外');
INSERT INTO `t_nativeplace` VALUES ('37', '不详');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeId` int(11) NOT NULL auto_increment COMMENT '公告id',
  `title` varchar(80) NOT NULL COMMENT '标题',
  `content` varchar(5000) NOT NULL COMMENT '公告内容',
  `publishDate` varchar(20) default NULL COMMENT '发布时间',
  PRIMARY KEY  (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '“中国寻亲网”助人找回亲骨肉', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">【本报惠州电】（记者杜建华通讯员袁治平）2002年1月4日，新华社播发了一则感人至深的故事《好心养母网上苦寻亲》，讲述了福建莆田县教师方秀梅为帮助自己收养了两年多的养子寻找生身父母，通过“中国寻亲网”的帮助，终于使苦苦寻子3年之久的贵州农民杨长祥找回亲骨肉的佳话。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　建立“中国寻亲网”的是惠州市金桥网络有限公司。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　2000年1月，贵州警方破获了“全国打拐第一案”，解救出60多名被拐卖儿童，在社会上引起轰动。当这批儿童到达贵阳市时，有300多名家长前往认领，但最后仍有20多个儿童因对不上号而没被认领走。金桥网络公司总经理沈钢从媒体上得知这一消息后，萌发了一个念头：建立一个专业网站，搜集、公布失踪儿童的信息资料，借助国际互联网这一现代通信手段，为群众寻找失散家庭人员助一臂之力。2000年2月26日，网址为http：∥www.china－seek.net的“中国寻亲网”域名申请成功。同年4月20日，“中国寻亲网”正式开通。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　得知“中国寻亲网”将免费登载失踪儿童信息，帮助寻找失踪儿童，家长们非常感激。一位古稀老人为寻找他失踪的女婿，从东莞把女婿龚平的照片送到惠州；一位年近五旬的妇女为寻找失散的儿子冯华驱，专程从贵阳来到惠州送上儿子的资料……到2001年11月，“中国寻亲网”已收集失踪人员资料近300份。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　2001年10月18日，经过“中国寻亲网”的帮助，贵阳的杨长祥终于与失踪3年的儿子小顺顺团聚，这是“中国寻亲网”一年多辛勤耕耘的回报。随着新华社、《中国青年报》等媒体的宣传，“中国寻亲网”的知名度越来越高，要求协助寻亲的人也越来越多，这也给金桥网络有限公司带来更大的压力。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　记者采访当天，“中国寻亲网”上又收到了安徽省芜湖市跃进橡胶厂下岗工人黄秋宝的来信，反映他儿子黄海涛于2000年12月9日被人拐走，要求在“中国寻亲网”上帮助寻找失踪的儿子。金桥网络公司新年计划加大投入，扩大资料来源和寻亲范围，以帮助失散人员与家庭团聚为己任，把“中国寻亲网”建成全国最大的失踪人口资料库。</p><p><br/></p>', '2018-05-24 22:47:28');
INSERT INTO `t_notice` VALUES ('2', '好心人操碎心寻亲网帮了忙9年后哑女终于找到家', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\"><span style=\"font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px;\">　　</span>４月１６日上午，哑女樊学莉流着眼泪跟随父亲离开了焦作市福利院。９年前，因为外出迷了路，她从许昌市襄城县姜庄村乡樊庄村独自流落到了焦作，后被焦作市福利院收留。几年间，一些好心人为了帮助她“寻家”，历经曲折，终于圆了她的回家之梦。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　９年前的夏天，１７岁的哑女樊学莉在焦作市街头被焦作市收容所的工作人员送到了焦作市福利院。收容所为了给她寻家，专门带她到洛阳、平顶山、许昌、漯河等地，结果均徒劳而返。２００１年１０月，时任焦作日报记者的李刚星在一次偶然的采访中得知了这位哑巴姑娘找不到家的信息后，便着手为哑巴女孩找家。然而樊学莉不会说话，又没有上过学，她写画的那些东西价值也不大，最终李刚星选择了利用互联网给这个女孩找家。他首先想到了“中国寻亲网”，于是，李刚星把有关女孩的文字和照片发到了网上。两年多过去了，还是没有给樊学莉找到家，正当大家失望的时候，今年４月２日，哑巴女孩的父亲来焦作找女儿了。原来，樊学莉有个弟弟，叫樊石头，去年考上了湖南一所大学，上大学后，他仍不忘寻找多年前走失的姐姐。今年３月３１日，当他打开“中国寻亲网”时，突然看到了一个女孩的照片和资料，他感觉资料上的许多地方和他的姐姐非常相似。于是，就迅速将电话打到老家。樊学莉的父亲接到电话后，激动得一夜没有合眼。第二天，便按照网上的地址来到了焦作。尽管樊学莉在福利院９年没见过父亲，但父女俩还是相互一眼就认出来了，不由抱头痛哭。４月１６日上午，樊学莉跟着父亲踏上了回家之路。</p><p><br/></p>', '2018-05-25 21:37:44');
INSERT INTO `t_notice` VALUES ('3', '“中国寻亲网”编织在泪水与脚印之间', '<p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\"><span style=\"font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px;\">　　</span>2000年2月，贵州警方破获世纪打拐第一大案，解救出60多个被拐卖儿童，在社会上引起轰动，同时，也引起了惠州金桥网络有限公司的关注。当这批儿童到达贵阳时，有约300个家长前往认领，最后仍有20多个儿童没被认走。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　惠州金桥网络有限公司萌发了一个念头：建立一个专业网站，搜集、公布失踪人口的信息，借助国际互联网这一现代通信手段，为失散家庭的团聚助一份力。网站取名“中国寻亲网”。可是建网站需要大量第一手资料，开始时，惠州金桥网络有限公司从《人民公安报》上收集相关资料。后来他们从《羊城晚报》得知，贵阳市农民王万军在寻找丢失儿子的过程中，将失踪儿童家长联合起来互相帮助，报道中还透露王万军收集了贵阳市200多个失踪儿童的资料。为此，惠州金桥网络有限公司总经理沈钢先生飞赴贵阳，拜访了王万军，看望了贵阳、遵义多个失踪儿童的家庭。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　得知金桥网络公司将建立“中国寻亲网”，并免费登载失踪儿童信息，帮助寻找失踪儿童，家长们非常激动和感谢，王万军当即将其手上所有的资料交给了寻亲网。贵州之行更加坚定了惠州金桥网络有限公司创办“中国寻亲网”的决心，沈钢永远忘不了失踪儿童家庭那一贫如洗的家庭、失子之痛的哭诉和那一双双期待的眼睛，同时也深切感到只有国际互联网这一媒体，才能具有不受地域、时间之限制，最大限度地发挥寻人广告之作用。经过紧张筹备，2000年4月，“中国寻亲网”正式开通。之后，王万军又陆续寄来许多补充资料，同时一些素不相识的人也给予网站寄来了资料。</p><p style=\"margin-top: 0px; margin-bottom: 0px; padding: 0px; list-style-type: none; font-family: Arial, Helvetica, sans-serif, 新宋体; font-size: 14px; line-height: 36px; white-space: normal;\">　　沈钢先生向本报记者表示，他们将加大投入，扩大资料来源和寻亲范围，以离散家庭的团聚为已任，把“中国寻亲网”办得更好。</p><p><br/></p>', '2018-05-25 21:39:49');

-- ----------------------------
-- Table structure for `t_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `user_name` varchar(30) NOT NULL COMMENT 'user_name',
  `password` varchar(30) NOT NULL COMMENT '登录密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `birthDate` varchar(20) default NULL COMMENT '出生日期',
  `userPhoto` varchar(60) NOT NULL COMMENT '用户照片',
  `telephone` varchar(20) NOT NULL COMMENT '联系电话',
  `email` varchar(50) NOT NULL COMMENT '邮箱',
  `address` varchar(80) default NULL COMMENT '家庭地址',
  `regTime` varchar(20) default NULL COMMENT '注册时间',
  PRIMARY KEY  (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------
INSERT INTO `t_userinfo` VALUES ('user1', '123', '张敏佳', '女', '1995-02-15', 'upload/c9cb89d6-2169-4182-ac82-c880ff70187c.jpg', '13599809834', 'minjia|@126.com', '四川成都成华区', '2018-05-24 22:45:42');
INSERT INTO `t_userinfo` VALUES ('user2', '123', '王全', '男', '2018-05-03', 'upload/b78a7cfa-949e-4342-b1bd-ed20716661ff.jpg', '13983134934', 'wangquan@163.com', '四川南充滨江路', '2018-05-25 13:11:58');
