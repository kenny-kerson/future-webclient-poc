# CompletableFuture와 WebClient를 사용해 Sync Non-blocking( Sync2Async ) 구현하기
이 프로젝트는, 클라이언트로부터 요청을 받은 서버가 응답시간이 오래걸리는 외부API를 호출한 뒤, 그 결과를 리턴하는 상황에서 Thread Blocking을 피하는 것을 목표로 한다.

## Problem
1. 서버에서 호출하는 외부API는 Latency가 10초이다
1. 클라이언트로 리턴은 외부API의 응답이 도착한 뒤에 되어야 한다
1. 서버에서 Controller는 Servlet Stack으로 구현되어야 하며, WebFlux( Mono, Flux )를 통한 리턴은 할 수 없다
1. 이 상황에서 Thread Blocking을 피하면서 서버의 Thread 고갈을 막고, 외부API를 호출 및 연동하고 싶다

---

## Goal
1. Controller가 사용하는 메인 워커 쓰레드풀의 쓰레드는 외부API의 응답이 도착할때까지 Blocking 되지 않는다
2. 외부API 호출로직은 메인 워커 쓰레드풀을 사용하지 않는다
3. 클라이언트로의 응답은 외부API 응답이 왔을때 리턴된다

---

## Requirements
기능 요건
- N/A

비기능 요건
1. (REQ-1) Controller Method 리턴타입은 비동기 반환타입으로 구현한다
2. (REQ-2) 외부API 역할을 하는 Mock API를 구현한다
3. (REQ-3) 외부API 호출은 Async-Nonblocking으로 구현한다

Out of Scope
1. Service Layer의 비지니스 로직은 구현하지 않는다
2. Circuit Breaker를 통한 Failover 및 Fallback은 구현하지 않는다
3. 외부API호출 이외에, DB연동 등 추가적인 IO는 발생하지 않는다고 가정한다


---

## Context

---

## Solution

---

## Material References
1. [Is Mono.toFuture() blocking?](https://stackoverflow.com/questions/58504527/is-mono-tofuture-blocking)
