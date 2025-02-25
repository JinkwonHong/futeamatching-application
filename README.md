# ⚽️ futeamatching ⚽️
풋살을 기반으로 팀 단위 운영 서비스로, 사용자들이 팀 빌딩부터 매치 참가, 지역별 랭킹 시스템 등 다양한 흥미 요소를 경험할 수 있는 플랫폼입니다.
사용자는 직접 팀을 생성하거나 관심 있는 팀에 가입하여 팀 단위로 경기에 참여할 수 있습니다. 매치가 종료되면 상대 팀의 평가가 이루어지며, 해당 **평가 데이터는 매일 새벽 배치 서버를 통해 일괄 처리**되어 업데이트됩니다. 서버 모니터링 시스템을 구축, 실시간으로 서버 모니터링을 진행합니다.
***

## 🚀 주요 기능

- 팀 관리: 팀을 생성하고 팀원을 추가하고, 권한을 부여/변경하는 등의 관리를 할 수 있습니다.
- 팀원 모집 관리: 팀원 모집글을 작성하고, 신청 내역을 확인하며 응답할 수 있습니다.
- 경기 일정 관리: 날짜와 지역별로 경기를 일정에 맞춰 모집, 또는 신청할 수 있습니다.
- 경기 및 팀 검색 : 원하는 날짜, 지역 별로 검색을 할 수 있습니다.
- 팀 랭킹 : 경기 후 평가 점수를 계산하여 팀 별 랭킹을 확인할 수 있습니다.
- 사용자 프로필 관리: 개인 정보를 편집하고, 프로필을 관리할 수 있습니다.

## 🖥️ 서비스 아키텍처
<img width="500" alt="image" src="https://github.com/user-attachments/assets/84daf55d-fc0c-4635-b3ef-655b95860fe1">

#### [ Entity Relationship Diagram ]
![image](https://github.com/user-attachments/assets/a1310af4-06a2-4922-b96f-4689255e3c1e)


## 🛠 사용 기술


- **Java:** 17
- **Spring Boot:** 3.3.1
- **Kotlin:** 1.9.24
- **데이터베이스:** H2, PostgreSQL

  
| 태그           | 이름                        | 상세 설명                                  |
|----------------|---------------------------|-------------------------------------------|
| **Language**   | Kotlin                     | 사용 언어                                  |
| **Version Control** | Git, Github            | 버전 관리                                  |
| **Collaboration** | Slack, Zep, Notion, Figma | 협업 툴                                    |
| **DataBase**   | PostgreSQL                 | 주 DB                                      |
| **DataBase**   | Redis                      | 인증번호 저장, 로그아웃 시 토큰 저장, 매치, 팀 목록 |
| **Back-End**   | SpringBoot                 | 프레임워크                                 |
| **Back-End**   | Spring Batch               | 스프링 프레임워크 기반 배치 라이브러리        |
| **Back-End**   | Spring Validation          | 유효성 검증                                |
| **Back-End**   | Spring Security, JWT       | 인증, 인가                                  |
| **Back-End**   | QueryDSL                   | 쿼리 생성 프레임워크                         |
| **Back-End**   | Logback                    | 로깅 라이브러리                              |
| **Back-End**   | Kotest, Mockk              | 테스트 라이브러리                             |
| **Front-End**  | React, VITE                | 프론트엔드                                   |
| **Infrastructure** | Nginx                 | 웹서버                                       |
| **Infrastructure** | EC2, S3, CodeDeploy    | 배포 도구                                    |
| **Infrastructure** | Github Action          | CI/CD 도구                                   |
| **Infrastructure** | Grafana, Prometheus, Loki, Promtail | 모니터링 |




## 📋 사전 요구 사항

- JDK 17 이상
- Gradle


### **종속성 관리**

프로젝트에서 사용된 주요 라이브러리 및 프레임워크는 다음과 같습니다:

- `spring-boot-starter-web`: 웹 애플리케이션을 구축하기 위한 스타터.
- `jackson-module-kotlin`: Kotlin 지원을 위한 Jackson 모듈.
- `kotlin-reflect`: Kotlin 리플렉션 라이브러리.
- `spring-boot-starter-data-jpa`: Spring Data JPA를 위한 스타터.
- `postgresql`: PostgreSQL JDBC 드라이버.
- `spring-boot-starter-validation`: Hibernate Validator와 함께 Java Bean Validation을 사용하기 위한 스타터.
- `springdoc-openapi-starter-webmvc-ui`: Spring WebMVC와 Springdoc OpenAPI 통합을 위한 스타터.
- `spring-boot-starter-security`: Spring Security를 위한 스타터.
- `jjwt`: JWT를 사용하기 위한 라이브러리.
- `querydsl-jpa`: 복잡한 SQL 쿼리를 타입 안전하게 작성하기 위한 라이브러리.
- `kotest`: 테스트 라이브러리.
- `mockk`: 테스트 라이브러리.



## 📂 프로젝트 구조

```
futeamatching
├── gradle
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── com
│   │   │       └── teamsparta
│   │   │           └── tikitaka
│   │   │               ├── api
│   │   │               │   └── weather
│   │   │               │       ├── controller
│   │   │               │       ├── dto
│   │   │               │       └── service
│   │   │               ├── domain
│   │   │               │   ├── common
│   │   │               │   │   ├── baseentity
│   │   │               │   │   ├── exception
│   │   │               │   │   └── util
│   │   │               │   ├── evaluation
│   │   │               │   │   ├── controller
│   │   │               │   │   ├── dto
│   │   │               │   │   ├── model
│   │   │               │   │   ├── repository
│   │   │               │   │   └── service
│   │   │               │   ├── match
│   │   │               │   │   ├── controller
│   │   │               │   │   ├── dto
│   │   │               │   │   ├── model
│   │   │               │   │   ├── repository
│   │   │               │   │   └── service
│   │   │               │   ├── recruitment
│   │   │               │   │   ├── controller
│   │   │               │   │   ├── dto
│   │   │               │   │   ├── model
│   │   │               │   │   ├── repository
│   │   │               │   │   └── service
│   │   │               │   ├── team
│   │   │               │   │   ├── controller
│   │   │               │   │   ├── dto
│   │   │               │   │   ├── model
│   │   │               │   │   ├── repository
│   │   │               │   │   └── service
│   │   │               │   └── users
│   │   │               ├── infra
│   │   │               │   ├── aop
│   │   │               │   │   ├── StopWatch
│   │   │               │   │   └── StopWatchAspect
│   │   │               │   ├── oauth
│   │   │               │   │   ├── kakao
│   │   │               │   │   ├── naver
│   │   │               │   │   └── oauthlogin
│   │   │               │   ├── querydsl
│   │   │               │   │   └── QueryDslSupport
│   │   │               │   ├── redis
│   │   │               │   │   └── RedisConfig
│   │   │               │   ├── security
│   │   │               │   │   ├── config
│   │   │               │   │   │   ├── EmailConfig
│   │   │               │   │   │   ├── PasswordEncoderConfig
│   │   │               │   │   │   ├── SecurityConfig
│   │   │               │   │   │   └── WebConfig
│   │   │               │   │   ├── jwt
│   │   │               │   │   │   ├── CustomAccessDeniedHandler
│   │   │               │   │   │   ├── CustomAuthenticationEntryPoint
│   │   │               │   │   │   ├── CustomPreAuthorize
│   │   │               │   │   │   └── UserPrincipal
│   │   │               │   │   └── swagger
│   │   │               │   │       └── SwaggerConfig
│   │   │               └── TikiTakaApplication.kt
│   └── resources
│       ├── application.yml
│       └── logback.xml
└── build.gradle.kts

```

  

