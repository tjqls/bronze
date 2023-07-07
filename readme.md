## 1차 요구사항 구현
- [ o ] 유저가 루트 url로 접속시에 게시글 리스트 페이지가 나온다.
- [ o ] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [ o ] 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [ o ] 등록이 되면 해당 게시글 상세 페이지로 리다이렉트 된다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [ o ] 게시글 상세 페이지에는 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

## 미비사항 or 막힌 부분
- 게시글 등록에서 html안에 폼태그구현후 타임리프 문법이 아직 익숙치않아서 좀 헤맸습니다.

## MVC 패턴
- MVC 패턴이란 model view controller로 이루어져 있고 사용자 인터페이스, 데이터 및 논리 제어를 구현하는데 널리 사용되는 소프트웨어 디자인 패턴입니다. 소프트웨어의 비즈니스 로직과 화면을 구분하는데 중점을 두고 있습니다. MVC 패턴은 더나은 업무의 분리와 향상된 관리를 제공합니다.

## 스프링에서 의존성 주입(DI) 방법
- 첫번째는 @Autowired 두번째는 @RequiredArgsConstructor 세번째는 @Setter 입니다.
가장 추천하는 방식은 @RequiredArgsConstructor이며 @Autowired는 편리하지만 남용하면 안됩니다.