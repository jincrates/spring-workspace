# 코딩으로 학습하는 리팩토링(https://inf.run/rgYP)


## 스터디 목표
- 리팩토링(Refactoring)이란 결과의 변경 없이 코드의 구조를 재조정함으로써 코드의 가독성 높이고 유지보수를 편하게 하는 기법을 말합니다.
- 리팩토링 스터디를 통해서 기존 그룹웨어에 사용되는 레거시 코드를 개선하여 유지보수성을 높이기 위함입니다. 나아가 추가개발 및 신규 그룹웨어 개발 시에도 좋은 코드 작성하는 법을 스스로 생각함으로써 조금 더 생산적인 개발을 할 수 있습니다.


## 스터디 방법
강의 및 교재를 통한 이론을 학습하고, 실제 사용 중인 그룹웨어 코드에서 적용점을 찾아서 직접 개선을 하여 **이론과 실습을 동시에 병행**합니다.

- 주 1회(화요일) 스터디원 각자가 정해진 분량의 강의를 학습
    - **[이론]** 강의를 보면서 **기존에 알고 있던 것과 다른 점, 새로 알게된 점**을 정리하여 공유합니다.(노션 적극 활용)
    - **[실습]** 기존 그룹웨어 소스에서 학습한 강의에 해당하는 부분을 찾아서 **직접 개선하여 코드리뷰**를 진행합니다.(기존 그룹웨어 소스에 대한 새로운 Repository 생성하여 자유롭게 테스트할 예정)

## 강의 노트

- [냄새 01. 이해하기 힘든 이름(Mysterius Name)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/note/_%EB%83%84%EC%83%88%2001.%20%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0%20%ED%9E%98%EB%93%A0%20%EC%9D%B4%EB%A6%84(Mysterius%20Name).md)
- [냄새 02. 중복 코드(Duplicated Code)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/note/_%EB%83%84%EC%83%88%2002.%20%EC%A4%91%EB%B3%B5%20%EC%BD%94%EB%93%9C(Duplicated%20Code).md))
- [냄새 03. 긴 함수(Long Function)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/docs/_%EB%83%84%EC%83%88%2003.%20%EA%B8%B4%20%ED%95%A8%EC%88%98(Long%20Function).md)
- [냄새 04. 긴 매개변수 목록(Long Parameter List)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/docs/_%EB%83%84%EC%83%88%2004.%20%EA%B8%B4%20%EB%A7%A4%EA%B0%9C%EB%B3%80%EC%88%98%20%EB%AA%A9%EB%A1%9D(Long%20Parameter%20List).md)
- [냄새 05. 전역 데이터(Global Data)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/docs/_%EB%83%84%EC%83%88%2005.%20%EC%A0%84%EC%97%AD%20%EB%8D%B0%EC%9D%B4%ED%84%B0(Global%20Data).md)
- [냄새 06. 가변 데이터(Mutable Data)](https://github.com/jincrates/spring-workspace/blob/master/refactoring-java/docs/_%EB%83%84%EC%83%88%2006.%20%EA%B0%80%EB%B3%80%20%EB%8D%B0%EC%9D%B4%ED%84%B0(Mutable%20Data).md)
