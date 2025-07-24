pipeline {
  agent any

  environment {
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
    // Jenkins가 체크아웃한 워크스페이스(코드 + Compose 파일 포함)
    WORKSPACE_DIR = "${env.WORKSPACE}"
  }

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy via Compose v1 Container') {
      steps {
        sh """
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${WORKSPACE_DIR}":/app -w /app \\
            ${COMPOSE_IMAGE} \\
            -f docker-compose.was.yml down && \\
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${WORKSPACE_DIR}":/app -w /app \\
            ${COMPOSE_IMAGE} \\
            -f docker-compose.was.yml up -d --build
        """
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
