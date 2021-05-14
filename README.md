# Ah-puh

#### codesquad team project week3

### Baseball Game Online
## 팀원
- Freddy ✊
- Goody 🗝
- Seong 🕶
### 협업 과정
- github [discussion](https://github.com/Dae-Hwa/baseball/discussions/6)을 통한 협업 룰 정리 및 백엔드 / 프론트엔드간 소통 진행
- github [issue](https://github.com/Dae-Hwa/baseball/issues)와 [project](https://github.com/Dae-Hwa/baseball/projects/1)를 통한 분업 진행 
- github pull request를 통한 서로의 코드 리뷰
- 애자일 방식을 채택, 빠른 설계와 빠른 구현 반복을 통해 프로젝트 진행
### 협업 방식
- [feature list](https://github.com/Dae-Hwa/baseball/issues/7)를 작성해 전체적인 할 일 정리
- 각자 원하는 feature를 골라 issue를 생성 후 구현, PR로 병합
- PR시 리뷰어를 지정해 서로의 코드를 리뷰, 개선
- project를 통한 프로젝트 진행도 파악
- [기술 정리](https://github.com/Dae-Hwa/baseball/wiki/OAuth-%EA%B5%AC%ED%98%84-with-Firebase---1) 공유를 통한 기술 부채 해결
### 자랑거리
- 좀처럼 자랑하지 않는 겸손함
### API
- [API 구조 설계](https://github.com/Dae-Hwa/baseball/discussions/6#discussioncomment-707182)
- [Swagger](https://baseball-ahpuh.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config)
## 데모

### 메인화면 ( "/" )
메인 화면에서는 팀을 선택하는 페이지("/intro")로 이동하는 버튼과, OAuth 로그인을 하는 버튼이 있습니다. <br/>
현재는 깃허브 혹은 구글 계정으로 단순 로그인만 가능한 상황이지만, <br/>
추후에 OAuth 로그인 계정 정보를 클라이언트에서 백엔드로 보내서, <br/>
PVP 플레이 등 확장성을 높여볼 계획입니다. <br/>
<img width="1024" alt="001" src="https://user-images.githubusercontent.com/71166372/117403474-31511880-af43-11eb-9937-03931fc6c539.png">
<img width="250" alt="006" src="https://user-images.githubusercontent.com/71166372/117403547-4e85e700-af43-11eb-9056-b92e935414d7.png">
<img width="250" alt="007" src="https://user-images.githubusercontent.com/71166372/117403559-5180d780-af43-11eb-9693-ce3b6e0763a7.png">


### 팀 선택 화면 ( "/intro" )
팀 선택 화면에서는 미리 정해둔 6개 팀의 데이터를 백엔드에서 (https://baseball-ahpuh.herokuapp.com/games) 받아옵니다. <br/>
마우스가 팀 목록 구간에 들어올 때만 스크롤바가 나타납니다. <br/>
<img width="1022" alt="002" src="https://user-images.githubusercontent.com/71166372/117403497-3a41ea00-af43-11eb-8819-564d6fe0b345.png">

### 본 게임 화면 ( "/ingame" )
본 게임 화면에서는 아직 백엔드와의 통신 없이, 화면 스타일링만 잡아둔 상태입니다. <br/>
<img width="1022" alt="003" src="https://user-images.githubusercontent.com/71166372/117403511-4332bb80-af43-11eb-8824-1f17859ea4d7.png">

본 게임 화면에서 마우스를 화면 상, 하단으로 가져가면 각각 이닝별 게임 스코어와 양팀 라인업 및 기록을 볼 수 있습니다. <br/>
<img width="1019" alt="004" src="https://user-images.githubusercontent.com/71166372/117403524-475ed900-af43-11eb-9d60-840f8de6a638.png">

<img width="1021" alt="005" src="https://user-images.githubusercontent.com/71166372/117403533-4a59c980-af43-11eb-8148-26a2632fbc30.png">

### 로그인 화면
![001](https://user-images.githubusercontent.com/70461368/118227666-e5165300-b4c3-11eb-84a4-a13ac84767e7.gif)

### 공격 화면
![002](https://user-images.githubusercontent.com/70461368/118228082-96b58400-b4c4-11eb-824a-f50ed120a3fb.gif)

### 수비 화면
![수비](https://user-images.githubusercontent.com/70461368/118228333-f875ee00-b4c4-11eb-8912-029e88da816e.gif)

## 데이터 구조

### ERD

![image](https://user-images.githubusercontent.com/24666330/118240006-6cb88d80-b4d5-11eb-8307-c7d950dccb94.png)
