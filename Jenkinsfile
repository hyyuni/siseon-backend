pipeline {
  agent any

  environment {
    // Compose 이미지, 서비스 디렉터리
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
    SERVICE_DIR   = "${WORKSPACE}/siseon-backend"
  }

  stages {
    stage('Checkout') {
      steps {
        // GitHub에서 코드 내려받기
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        // Compose down & up 을 한 번에 실행
        sh """
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${SERVICE_DIR}":/app \\
            -w /app \\
            ${COMPOSE_IMAGE} \\
            down && \\
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${SERVICE_DIR}":/app \\
            -w /app \\
            ${COMPOSE_IMAGE} \\
            up -d --build
        """
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
