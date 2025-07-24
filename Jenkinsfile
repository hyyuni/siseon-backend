pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // GitHub 저장소 전체를 워크스페이스에 내려받습니다
        checkout scm
      }
    }

    stage('Deploy') {
      steps {
        // siseon-backend 폴더로 이동해서 Compose 명령만 실행
        dir('siseon-backend') {
          sh '''
            docker-compose down
            docker-compose up -d --build
          '''
        }
      }
    }
  }

  post {
    success {
      echo '✅ 배포 완료!'
    }
    failure {
      echo '❌ 배포 실패…'
    }
  }
}