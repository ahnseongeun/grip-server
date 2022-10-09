# 그립 앱 클론코딩 프로젝트

## 📝 Description

Grip은 실시간 라이브 영상을 제공하는 App으로 2주라는 단기간 내에 IOS 클라이언트와 협업, DataBase 설계, Restful API 설계 및 개발, 실시간 스트리밍 영상 구현을 목표로한 프로젝트입니다.

## 📷 Preview

![final_translate](https://user-images.githubusercontent.com/47744119/194741345-150e4580-817f-4843-bb58-33647ef5f720.gif)

## 📋 Functions

- Naver, Facebook OAUTH 2.0 로그인 및 회원가입 구현
- JWT를 활용한 자동 로그인 및 인증 시스템
- S3 이미지 업로드 
- m3u8 영상 업로드 및 썸네일 추출 API 구현
- Scheduler를 활용한 실시간 스트리밍 영상 위치를 서버에 저장
    - 클라이언트에서 서버에 저장된 시작 위치부터 시작시켜서 실시간 스트리밍 방식 근접
    
- 간단하고 자주 사용되는 API는 HashMap을 통해서 간단하게 저장 후 일정 시간 이후에 MYSQL 저장
- IOS 실시간 영상 스트리밍을 위해서 FFMPEG를 활용한 m3u8 포맷의 영상 송출
- 카테고리 통한 필터링 제공
- 1분 간격으로 광고 이미지 디렉토리 변경
