pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy via Docker') {
      steps {
        dir('was') {
          // 중단된 컨테이너가 있으면 내린 후
          sh 'docker-compose down'
          // Dockerfile 멀티스테이지 빌드 + 컨테이너 기동
          sh 'docker-compose up -d --build'
        }
      }
    }
  }

  post {
    success {
      echo "✅ 배포 완료!"
    }
    failure {
      echo "❌ 배포 실패!"
    }
  }
}
