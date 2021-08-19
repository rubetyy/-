



# 홍당무 라이브

증고 상품 라이브 커머스 프로젝트입니다.



### 커밋 컨벤션
`feat:[FE/BE] 코드작성한내용`

---



|   Name   | 김용재| 김창규| 이연지  | 최성은|
| :------: | ---  | ---   | ---     | ----  |
| Profile  ||[깃헙](https://github.com/Kim-Chang-Kyoo)|||

- ### 홍당무 라이브를 개발한 서울광주롱디팀을 소개합니다 !

  - 김용재 😤
    - 인간 디버깅이 될때까지 강철 멘탈로 해결해나아가는 
      THE 강한 SSAFY인이 되겠습니다. 화이팅!
  - 김창규😁

    - 온 종일 코딩하면서 말할 일이 없다보니 스크럼 미팅시간이 저의 유일한 탈출구 입니다. 
      이 시간만큼은 팀원들과 즐기고 싶어요!
  - 이연지 🧐
    - 최적화와 완성도를 고려하는 개발자가 되겠습니다 !
  - 최성은 🤩
    - 문제가 무엇인지 정조준하는 개발자가 되겠습니다!
    
    

- ### 서울광주롱디팀은 어떻게 협업하는지 궁금하신가요?! 아래 링크로 오세요!

  - [**서울광주롱디팀의 협업방법 보러가기! 😏**](https://www.notion.so/74deb92179b54a66a10d6cfa45c10766)



---


---

## 😎 [UCC 보러가기](???)

---



## 🚀 배포 플로우

![서비스아키텍처](/image/서비스_아키텍처.png)



## :video_game: 기술 스택

### 💻Back-End

  <details>
      <summary>Back 자세히 살펴보기 🔥</summary>
      <ul>
        <li>기술스택 ⚙</li>
      </ul>
      <ul>
          <li>Spring-Boot : 2.3.9</li>
          <li>Spring Security</li>
          <li>spring-boot-starter-web</li>
          <li>spring-boot-starter-websocket</li>
          <li>spring-boot-starter-freemarker</li>
          <li>spring-boot-starter-data-jpa</li>
          <li>spring-boot-starter-jdbc</li>
          <li>spring-boot-starter-actuator</li>
          <li>modelmapper : 2.3.8</li>
          <li>jjwt : 0.9.1</li>
          <li>lombok</li>
          <li>mysql : 8.0.22</li>
          <li>h2 : 1.4.200</li>
          <li>pusher : 2.2.1</li>
          <li>JUnitParams : 1.1.1</li>
          <li>junit : 4.12</li>
          <li>jersey-multipart : 1.19.4</li>
          <li>spring-restdocs-asciidoctor : 2.0.3.RELEASE</li>
          <li>spring-restdocs-mockmvc : 2.0.3.RELEASE</li>
      </ul>
      <ul>
        <li>라이브러리 📚</li>
      </ul>
  </details>

### ✨Front-End

<details>
    <summary>Front 자세히 살펴보기 🌈</summary>
    <ul>
        <li>기술스택 ⚙</li>
    </ul>   
    <ul>
        <li>JS, HTML, CSS</li>
        <li>Vue.js @2.6.14</li>
    </ul>
    <li>--------------------------------------------------------------------------------------</li>
     <ul>
        <li>라이브러리 📚</li>
    </ul>   
    <ul>
        <li>axios @0.21.1 : Promise 기반 HTTP 클라이언트</li>
        <li>bootstrap @5.0.2 : 전체적인 디자인</li>
        <li>bootstrap-vue @2.21.2</li>
        <li>core-js @3.6.5</li>
        <li>openvidu-browser @2.19.0 : 실시간 라이브 방송</li>
        <li>sockjs-client @1.5.1 : 실시간 채팅</li>
        <li>sweetalert @2.1.2 : alert 커스텀</li>
        <li>swiper @6.8.1 : 캐로우셀 구현</li>
        <li>webstomp-client @1.2.6 : 1대1 채팅</li>
    </ul>
</details>
### 🌏Infra

  <details>
      <summary>개발, CI/CD 자세히 살펴보기 🔥</summary>
      <ul>
          <li>AWS EC2 - Deploy Server</li>
          <li>GitLab</li>
          <li>Docker</li>
      </ul>
  </details>

-------------------------------------------------



## ⚙️ Install and Usage

#### 시스템 환경

홍당무라이브는 아래와 같은 환경에서 실행 중입니다.

- OS : Linux version 5.8.0-1041-aws (buildd@lgw01-amd64-035) (gcc (Ubuntu 9.3.0-17ubuntu1~20.04) 9.3.0, GNU ld (GNU Binutils for Ubuntu) 2.34) #43~20.04.1-Ubuntu SMP Thu Jul 15 11:07:29 UTC 2021
- Cpu 모델 : Intel(R) Xeon(R) CPU E5-2686 v4 @ 2.30GHz
- Total Memory: 16395128 kB
- 물리 cpu 개수 : 4		cpu당 물리 코어 : 4		논리 코어 수 : 32



#### 시스템 구성

- jenkins 2.3
  - blueocean : 지속적 배포와 관리 할 수 있는 UI를 지원합니다.
- docker
  - Backend와 Frontend를 컨테이너화합니다.
- docker-compose - jenkins
- DB 서버 : mysql  Ver 8.0.26-0ubuntu0.20.04.2 for Linux on x86_64 ((Ubuntu))
- 파일 서버 : 프로젝트 images폴더
- WebRTC : openvidu-server:2.19.0 

최종 빌드와 배포를 위해 jenkins 단계는 제외하고 실행합니다.



#### Ubuntu 버전 업 및 기본 설치

```bash
sudo apt-get update
sudo apt-get install nodejs
sudo apt-get install npm
```


#### 도커 명령어

- 도커 컨테이너 보는 법
  - sudo docker ps -a
- 도커 컨테이너 삭제
  - sudo docker rm <container_id>
- 도커 이미지 보는 법
  - sudo docker images
- 도커 이미지 삭제
  - sudo docker rmi <image_id>
- 강제 옵션 -f (삭제가 안될 때,)
  - ex) docker rmi -f <image_id>
- 도커 로그 확인
  - docker logs <image_id> : 도커가 꺼져있어도(컨테이너가) 최종적으로 실행된 기록을 가지고 있습니다(오류 잡을 때, 매우 유용)


#### HTTPS 키 발급

frontend에서는 ssl이 적용되어 있기 때문에 HTTPS 인증을 받아야합니다.

```bash
sudo apt-get install letsencrypt
# 만약 nginx를 사용중이면 중지
sudo systemctl stop nginx
# 인증서 발급
sudo letsencrypt certonly --standalone -d www제외한 도메인 이름
# 이메일 쓰고 Agree
# 뉴스레터 no
# 키가 발급되는데 이 두 가지를 써야합니다. 밑의 경로에 각각 하나씩 있습니다.
 ssl_certificate /etc/letsencrypt/live/도메인이름/fullchain.pem; 
 ssl_certificate_key /etc/letsencrypt/live/도메인이름/privkey.pem; 
```



##   :open_file_folder: 개발 스프린트

|스프린트✌ | 기간📚 | FE🌈   | BE🤖   |
| :----: | :------: | -------- | ---- |
| 프로젝트 구상 및 착수 **(v0.0.1)** | 2021.07.05<br /> ~ <br />2021.07.09 | - 프로젝트 아이디어 도출<br />- 와이어 프레임 작성<br />- vue2로 대체<br/> | - Database ERD 작업 진행<br />- REST API 문서 작성<br />- 개발환경 구축<br /> |
| 프로젝트 기본 기능 개발<br /> **(v1.0.0)** |  2021.07.12<br />~<br />2021.07.16  | **김용재**<br/>- 사용자는 물건을 판매하기 위해 상품 정보를 등록할 수 있습니다 [#S05P12C103-68](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 파일 업로드/다운로드 백엔드 기능 개발 [#S05P12C103-73](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 상품관리 컨트롤러 구현 [#S05P12C103-69](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 제품 등록페이지 기본 양식 완성 [#S05P12C103-21](https://jira.ssafy.com/browse/S05P12C103-21)<br/><br/><br/> **김창규**<br/>- 제품 등록페이지 등록 구현 [#S05P12C103-78](https://jira.ssafy.com/browse/S05P12C103-78)<br/>- 제품 등록페이지 토큰 값 보내 요청 [#S05P12C103-82](https://jira.ssafy.com/browse/S05P12C103-82)<br/>- 마이페이지 response구현 [#S05P12C103-86](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 로그인 프로세스 진행 [#S05P12C103-67](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/><br/><br/> **이연지**<br/>- 메인화면의 상품리스트 화면구성 [#S05P12C103-87](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 방송 만들기 화면 구성 [#S05P12C103-66](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 라이브방송 화면 구성 [#S05P12C103-71](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 서버와 데이터 request&response [#S05P12C103-84](https://meeting.ssafy.com/s05p11c101/channels/13#)<br/>- 프로젝트 공통 폰트/컬러/디자인 적용 [#S05P12C103-85](https://meeting.ssafy.com/s05p11c101/channels/13#) | **최성은** <br/>- 사용자가 서비스를 이용하기 위해 데이터를 저장하는 DB를 설정한다  마이페이지 response구현 #S05P12C103-86 로그인 프로세스 진행<br/> |
| 프로젝트 심화 기능 개발<br />**(v1.1.0)** |  2021.07.19<br />~<br />2021.07.30  | **김용재**<br/>- webRTC 구현하기-kurento(node.js) 튜토리얼 실행하기 [S05P13C103-46](https://jira.ssafy.com/browse/S05P13C103-46)<br/>- webRTC 구현하기-openvidu 튜토리얼 실행하기 [S05P13C103-37](https://jira.ssafy.com/browse/S05P13C103-37)<br/>- 회원가입 유효성 검사 [S05P13C103-34](https://jira.ssafy.com/browse/S05P13C103-34)<br/>- 상품정보 store 관리 [S05P13C103-30](https://jira.ssafy.com/browse/S05P13C103-30)0<br/>- 유저 닉네임 store 관리 [S05P13C103-24](https://jira.ssafy.com/browse/S05P13C103-24)<br/><br/><br/> **김창규**<br/>- 실시간채팅 [S05P13C103-17](https://jira.ssafy.com/browse/S05P13C103-17)<br/>- 라이브방송 판매자와 구매자 화면 구분 [S05P13C103-27](https://jira.ssafy.com/browse/S05P13C103-27)<br/>- 실시간채팅 유저 닉네임 연동 [S05P13C103-25](https://jira.ssafy.com/browse/S05P13C103-25)<br/>- 공통 CSS 디자인 적용 [S05P13C103-9](https://jira.ssafy.com/browse/S05P13C103-9)<br/>- 라이브방송 페이지 디자인 [S05P13C103-43](https://jira.ssafy.com/browse/S05P13C103-43)<br/><br/><br/> **이연지**<br/>- 상품 상세페이지의 상품 이미지를 케로젤로 띄움 [#S05P13C103-53](https://jira.ssafy.com/browse/S05P13C103-53)<br/>- 상품 등록페이지의 사진 업로드 [#S05P13C103-51](https://jira.ssafy.com/browse/S05P13C103-51)<br/>- 상품 등록페이지의 상세 내용 입력 [#S05P13C103-52](https://jira.ssafy.com/browse/S05P13C103-52)<br/>- 메인 페이지의 이미지 리스트 띄우기 [#S05P13C103-47](https://jira.ssafy.com/browse/S05P13C103-47)<br/>- 로그인 폼 디자인 및 기능  [#S05P13C103-40](https://jira.ssafy.com/browse/S05P13C103-40)<br/>- 회원가입 폼 디자인 및 기능  [#S05P13C103-42](https://jira.ssafy.com/browse/S05P13C103-42) | **최성은**<br/>- 마이페이지 상품목록 반환 수정 [#S05P13C103-41] <br/>- 메인페이지 response_image [#S05P13C103-32] <br/>- 백엔드 배포 [#S05P13C103-29] <br/>- 아마존 db연동 [#S05P13C103-26] <br/>- 상품상세보기 response변경 [#S05P13C103-23] <br/>- 로그인시 닉네임도 반환 [#S05P13C103-22] <br/>- 상품정보조회 수정 [#S05P13C103-21] <br/>- 실시간 채팅 웹소켓 백엔드 테스트 [#S05P13C103-19] |
| 프로젝트 핵심 기능 개발<br />**(v1.2.0)** |  2021.08.02<br />~<br />2021.08.20  | **김용재**<br/>- 상품 상세 이미지 캐로우셀 수정 [#S05P13C103-53](https://jira.ssafy.com/browse/S05P13C103-53)<br/>- 카레고리 별 라이브리스트, 상품리스트 구현 완료 [#S05P13C103-54](https://jira.ssafy.com/browse/S05P13C103-54)<br/>- 1대1 채팅 구현 [#S05P13C103-56](https://jira.ssafy.com/browse/S05P13C103-56)<br/>- 웹소켓 세션 정보, 채팅방 매핑 [#S05P13C103-58](https://jira.ssafy.com/browse/S05P13C103-58)<br/>- 실시간 라이브 방송에서 판매자만 화면 보이게 구현 [#S05P13C103-65](https://jira.ssafy.com/browse/S05P13C103-65)<br/>- modal 수정 [#S05P13C103-68](https://jira.ssafy.com/browse/S05P13C103-68)<br/><br/><br/>**이연지**<br/>- 라이브 방송 종료시 알림창 [#S05P13C103-69](https://jira.ssafy.com/browse/S05P13C103-69)<br/>- 라이브 방송 버튼 없이 바로 시작 [#S05P13C103-70](https://jira.ssafy.com/browse/S05P13C103-70)<br/>- 라이브 방송 여부, 웹소켓 subscribe 값 연동 [#S05P13C103-71](https://jira.ssafy.com/browse/S05P13C103-71)<br>- 상품 상세 페이지에서 판매중/판매완료 표기 [#S05P13C103-75](https://jira.ssafy.com/browse/S05P13C103-75)<br>- 메인페이지에서 라이브 방송 리스트 구현 완료 [#S05P13C103-79](https://jira.ssafy.com/browse/S05P13C103-79)<br>- 찜하기 기능 구현 완료 [#S05P13C103-85](https://jira.ssafy.com/browse/S05P13C103-85)<br>- 검색 기능 구현 완료 [#S05P13C103-86](https://jira.ssafy.com/browse/S05P13C103-86)<br>- 라이브 방송 종료 [#S05P13C103-89](https://jira.ssafy.com/browse/S05P13C103-89)<br>- 채팅 자동 스크롤 [#S05P13C103-91](https://jira.ssafy.com/browse/S05P13C103-91)<br>- 404 페이지 구현 완료 [#S05P13C103-93](https://jira.ssafy.com/browse/S05P13C103-93)<br>- nav bar 햄버거 버튼 구현 [#S05P13C103-97](https://jira.ssafy.com/browse/S05P13C103-97) | **최성은**<br/>- DB 초기화: 웹페이지에서 구현된 기능으로 더미데이터 입력 [#S05P13C103-47](https://jira.ssafy.com/browse/S05P13C103-47)<br/>- ERD 테이블에 맞게 코드 수정 [#S05P13C103-79](https://jira.ssafy.com/browse/S05P13C103-79)<br/>- 일대일 채팅 Stopm 구현  [#S05P13C103-55](https://jira.ssafy.com/browse/S05P13C103-55)<br/>- 1대1 채팅에서 판매 완료 구현 [#S05P13C103-63](https://jira.ssafy.com/browse/S05P13C103-63)<br/>- 검색기능 구현 [#S05P13C103-60](https://jira.ssafy.com/browse/S05P13C103-60)<br/>- 메인페이지에 라이브리스트 [#S05P13C103-61](https://jira.ssafy.com/browse/S05P13C103-61)<br/>- 카테고리별 라이브방송, 상품정보 [#S05P13C103-64](https://jira.ssafy.com/browse/S05P13C103-64)<br/>- 1대1 채팅 구현 [#S05P13C103-72](https://jira.ssafy.com/browse/S05P13C103-72)<br/>- 찜하기 구현 [#S05P13C103-73](https://jira.ssafy.com/browse/S05P13C103-73)<br/>- 방송종료 구현 [#S05P13C103-89](https://jira.ssafy.com/browse/S05P13C103-89) |



---

## 📜 Project ProgressStatus

| 구분         | 링크                                                         |
| ------------ | ------------------------------------------------------------ |
| Team Notion👣 | [Team Notion](https://www.notion.so/74deb92179b54a66a10d6cfa45c10766) |
| JIRA🔥        | [SSAFY-JIRA](https://jira.ssafy.com/projects/S05P13C103/issues/S05P13C103-115?filter=allopenissues) |
| GitLab🌟      | [SSAFY-GitLab](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13C103) |
| REST Docs📚   | [Postman_RESTDocs](https://documenter.getpostman.com/view/3490568/TzsWuARM) |

- 일부 링크는 보안문제로 열람이 불가능 할 수 있습니다. 
  요청은 (???)을 통하여 연락주시면 열람권한 드리겠습니다.

