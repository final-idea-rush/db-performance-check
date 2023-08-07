# MySQL vs PostgreSQL
<hr>
| 구분            | 버전       |
|-------------|--------------|
| OS 환경 | Window 10 Pro |
| RAM | 32GB |
| Mysql | 8 |
| PostgreSQL | 15 |
| Spring | 3.1.2 |
| JDK | 17 |
<hr>
<h3>읽기와 쓰기 단계로 나뉘어서 테스트 코드 작성 및 속도 체크 하였습니다.<br><br>
읽기는 약 2,073,677건의 데이터를 가지고 사용 하였습니다.<br><br>
또한, 기존의 데이터는 null 값이 많은 컬럼들의 데이터들을 삭제하였습니다. <br><br>
데이터를 1차 가공하여 사용하였습니다. <br><br>
</h3>

- 데이터 출처 : https://www.data.go.kr/data/15096283/standard.do
<hr>

## 성능 체크 : Hibernate Statistics
Hibernate는 JPA의 한 구현체로, hibernate.generate_statistics 속성을 true로 설정하면 
SQL 쿼리에 대한 통계를 수집합니다. 이 통계에는 쿼리 수행 시간이 포함됩니다. 
이 방법은 디버깅과 프로토타이핑 단계에서 유용하지만, 
프로덕션 환경에서는 부가적인 오버헤드 때문에 사용하지 않는 것이 좋습니다.
## 성능 테스트 목록
* 전체 조회
* 단건 조회
* like 조회
* index 조회
* 1만, 10만, 100만 데이터 입력

## 읽기 총 결과

|     | MySQL            | PostgreSQL       |
|------------|------------------|------------------|
| `전체 조회`    | 11,688ms (약 11초) | 15,366ms (약 15초) |
| `단건 조회`    | 219ms (약 0.219초) | 2,544ms (약 2초)   |
| `like 조회`  | 236ms (약 0.236초) | 2,816ms (약 2초)   |
| `index 조회` | 133ms (약 0.133초) | 134ms (약 134초)   |

![KakaoTalk_20230807_234539283_01](https://github.com/final-idea-rush/db-performance-check/assets/73750927/a64e25f7-5eb3-4096-b286-41a3682c2fd2)


## 쓰기 총 결과

|            | MySQL                     | PostgreSQL           |
|------------|---------------------------|----------------------|
| `1만건`      | 1,617ms (약 1초)            | 2,085ms (약 2초)       |
| `10만건`     | 17,861ms (약 17초)          | 24,683ms (약 24초)     |
| `100만건`    | 162,229ms (약 2분 42초) | 208,652ms (약 3분 29초) |

![KakaoTalk_20230807_234539283](https://github.com/final-idea-rush/db-performance-check/assets/73750927/87faba4d-58b3-4d80-8418-7d75584c7d44)

<hr>



