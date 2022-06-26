## 트리플여행자 클럽 마일리지 서비스
- Framework: SpringBoot  
- Database: MySQL

### [멤버 등록]
트리플 여행자 유저를 생성한다.

```
POST /user/new

{
    "name": "유저명"
}
```
이름을 등록하면 유저의 UUID는 랜덤으로 자동 생성된다.

### [장소 등록]
후기를 등록할 장소를 사용자가 선택할 수 있도록 장소를 등록해준다.

```
POST /place/new

{
    "name": "장소명"
}
```
장소명을 등록하면 장소의 UUID는 랜덤으로 자동 생성된다.

### [후기 등록]
```
POST /events

{
    "type": "REVIEW",
    "action": "ADD",
    "content": "내용",
    "userId": "생성한 유저의 UUID",
    "placeId": "생성한 장소의 UUID",
    "attachedPhotoIds": ["등록할 사진의 UUID 1", "등록할 사진의 UUID 2","등록할 사진의 UUID 3"]
}
```
게시물을 등록하면 게시물의 UUID가 자동 생성된다.


### [후기 수정]
```
POST /events

{
    "type": "REVIEW",
    "action": "MOD",
    "content": "내용",
    "userId": "수정할 게시물 유저의 UUID",
    "placeId": "수정할 게시물 장소의 UUID",
    "attachedPhotoIds": ["등록할 사진의 UUID 1", "등록할 사진의 UUID 2","등록할 사진의 UUID 3"]
    "reviewId": "수정할 게시물의 UUID"
}
```
이미 데이터베이스에 있는 사진은 재등록 되지 않고 유지되며 없는 사진은 삭제, 새로운 사진을 등록했을 경우 새로 등록된다.

### [후기 삭제]
```
POST /events

{
    "type": "REVIEW",
    "action": "DEL",
    "userId": "삭제할 게시물 유저의 UUID",
    "reviewId": "삭제할 게시물의 UUID"
}
```
해당 게시물과 사진이 삭제된다.

### [멤버 마일리지 조회]
```
GET /user/mileage

{
    "userUUID": "마일리지를 조회할 유저의 UUID"
}
```
조회한 유저가 가진 총 마일리지를 반환한다.


- 마일리지의 증감은 mileage 테이블에서 사진, 내용, 첫 리뷰 여부를 담아 모든 이력을 기록한다.
- 리뷰 작성, 수정, 삭제시 마일리지가 갱신된다.
- 사용자별로 현재 가진 총점을 나타내는 컬럼이 있어 현재 시점 포인트를 조회할 수 있다.

### DDL
```sql

CREATE TABLE `mileage` (
  `id` bigint(20) NOT NULL,
  `content` bit(1) DEFAULT NULL,
  `first_review` bit(1) NOT NULL,
  `mileage` int(11) NOT NULL,
  `photo` bit(1) DEFAULT NULL,
  `writeruuid` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `postuuid` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `photo` (
  `id` bigint(20) NOT NULL,
  `photouuid` varchar(255) DEFAULT NULL,
  `postuuid` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `del` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `place` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `placeuuid` varchar(255) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `placeuuid` varchar(255) DEFAULT NULL,
  `postuuid` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `useruuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `modified_date` datetime(6) DEFAULT NULL,
  `mileage` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `useruuid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
