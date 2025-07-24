pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy via Docker Compose Container') {
      steps {
        // was 디렉터리로 이동한 뒤, Compose 컨테이너를 띄워 down/up 수행
        dir('was') {
          // 기존 컨테이너 종료
          sh '''
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:2.20.2 \
              -f docker-compose.was.yml down
          '''
          // 컨테이너 재빌드 및 재시작
          sh '''
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:2.20.2 \
              -f docker-compose.was.yml up -d --build
          '''
        }
      }
    }
  }

  post {
    success {
      echo '✅ 배포 성공!'
    }
    failure {
      echo '❌ 배포 실패…'
    }
  }
}
