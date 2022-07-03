# 업비트 API를 활용한 비트코인 백테스터

## 기술 스택

- Java 11
- Springboot 2.7
- MySQL 8.0
- Spring Data JPA
- Gradle
- Upbit Open API, Slack Messenger API

## 주요 기능

- 특정 기간 동안의 비트코인 캔들 크롤링
- 크롤링한 데이터를 기반으로 투자 전략에 따라 백테스트 수행
    - 사용한 투자 전략 : RSI 지표를 사용하여 매도, 매수 타이밍을 결정한다.
    - 백테스트 시작/종료, 매도/매수가 일어날 때마다 슬랙으로 알림 전송
