pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // GitHub 저장소 전체를 워크스페이스에 내려받습니다
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        script {
          // Compose 파일과 Dockerfile, 소스가 들어 있는 폴더
          def svc = "${env.WORKSPACE}/siseon-backend"
          sh """
            docker run --rm \\
              -v /var/run/docker.sock:/var/run/docker.sock \\
              -v "${svc}":/app \\
              -w /app \\
              docker/compose:1.29.2 \\
              -f docker-compose.was.yml down && \\
            docker run --rm \\
              -v /var/run/docker.sock:/var/run/docker.sock \\
              -v "${svc}":/app \\
              -w /app \\
              docker/compose:1.29.2 \\
              -f docker-compose.was.yml up -d --build
          """
        }
      }
    }
  }

  post {
    success { echo '✅ 배포 완료!' }
    failure { echo '❌ 배포 실패…' }
  }
}
