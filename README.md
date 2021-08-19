# 홍당무 라이브
증고 상품 라이브 커머스 프로젝트입니다.

### 커밋 컨벤션
`feat:[FE/BE] 코드작성한내용`

---



|   Name   | 김용재| 김창규| 이연지  | 최성은|
| :------: | ---  | ---   | ---     | ----  |
| Profile  | 

- ### THXSTORE Project를 함께하는 강철코딩팀을 소개합니다 !

  - **[에러와 매일 밤 싸운다.]** **최동식** 😤
    - 인간 디버깅이 될때까지 강철 멘탈로 해결해나아가는 
      THE 강한 SSAFY인이 되겠습니다. 화이팅!
  - **[항상 웃음으로 인사하는 친절한]** **성정욱** 😁
    - 온 종일 코딩하면서 말할 일이 없다보니 스크럼 미팅시간이 저의 유일한 탈출구 입니다. 
      이 시간만큼은 팀원들과 즐기고 싶어요!
  - **[프로젝트의 최전방에서 로직을 설계한다.]** **황영준** 🧐
    - 최적화와 완성도를 고려하는 개발자가 되겠습니다 !
  - **[솔루션은 나에게 맡겨라 프론트 저격수]** **박대현** 🤩
    - 문제가 무엇인지 정조준하는 개발자가 되겠습니다!
  - **[미친 텐션은 나를 이끌어]** **박상우** 😋
    - 이번 자율프로즉테로 가장 크게 성장하고 있습니다.
      취업과 프로젝트 모두를 성공하는 SSAFY인이 되보겠습니다.

- ### 강철코딩팀은 어떻게 협업하는지 궁금하신가요?! 아래 링크로 오세요!

  - [**강철코딩팀의 협업방법 보러가기! 😏**](https://upsw-p.tistory.com/55)



---


---

## 😎 [UCC 보러가기](https://youtu.be/zS6m7XjCbEo)

---

## 🚀 배포 플로우

![배포플로우]()

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
| 프로젝트 구상 및 착수 **(v0.0.1)** | 2021.04.13<br /> ~ <br />2021.04.18 | - 반응형 웹 논의 진행<br />- JEST, Storybook 학습 진행 및 논의<br /> | - Database ERD 작업 진행<br />- REST API 문서 작성<br />- 개발환경 구축<br />- 패키지 구조 설정 |
| 프로젝트 기본 기능 개발<br /> **(v0.1.0)** |  2021.04.19<br />~<br />2021.05.07  | - **김용재**<br/>- **김창규**<br/>  - **이연지**<br/> 사용자는 물건을 판매하기 위해 상품 정보를 등록할 수 있습니다 #S05P12C103-68 제품 등록페이지 기본 양식 완성 #S05P12C103-21#S05P12C103-55 파일 업로드/다운로드 백엔드 기능 개발 #S05P12C103-73 상품관리 컨트롤러 구현 #S05P12C103-69 제품 등록페이지 등록 구현 #S05P12C103-78  제품 등록페이지 토큰 값 보내 요청 #S05P12C103-82 #S05P12C103-67 메인화면의 상품리스트 화면구성  #S05P12C103-87 방송 만들기 화면 구성 #S05P12C103-66 라이브방송 화면 구성 #S05P12C103-71 서버와 데이터 request&response #S05P12C103-84 프로젝트 공통 폰트/컬러/디자인 적용 #S05P12C103-85| - **최성은** 사용자가 서비스를 이용하기 위해 데이터를 저장하는 DB를 설정한다  마이페이지 response구현 #S05P12C103-86 로그인 프로세스 진행       <br/>  
| 프로젝트 심화 기능 개발<br />**(v0.2.0)**  |  2021.05.08<br />~<br />2021.05.16  | - **김용재**<br/>- **김창규**<br/>  - **이연지**<br/>  | - **최성은**<br/> 
| 프로젝트 핵심 기능 개발<br />**(v0.3.0)**  |  2021.05.17<br />~<br />2021.05.23  | - **김용재**<br/>- **김창규**<br/>  - **이연지**<br/>| - **최성은**<br/>  |



---

## 📜 Project ProgressStatus

| 구분         | 링크                                                         |
| ------------ | ------------------------------------------------------------ |
| Team Notion👣 | [Team Notion](https://jazzy-partner-3a0.notion.site/74deb92179b54a66a10d6cfa45c10766) |
| JIRA🔥        | [SSAFY-JIRA](https://jira.ssafy.com/projects/S05P13C103/summary) |
| GitLab🌟      | [SSAFY-GitLab](https://lab.ssafy.com/s05-webmobile1-sub3/S05P13C103)   |         |
| REST Docs📚   | [Postman_RESTDocs](https://documenter.getpostman.com/view/3490568/TzsWuARM) |

- 일부 링크는 보안문제로 열람이 불가능 할 수 있습니다. 
  요청은 (tkddnsos@gmail.com)을 통하여 연락주시면 열람권한 드리겠습니다.
