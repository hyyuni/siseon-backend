pipeline {
  agent any

  environment {
    // 호스트에 있는 실제 was 디렉터리 경로
    HOST_WAS_DIR = '/home/ubuntu/siseon/was'
    // 사용할 Compose 컨테이너 이미지 (v1.29.2)
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
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
            -v "${HOST_WAS_DIR}":/app -w /app \\
            ${COMPOSE_IMAGE} \\
            -f docker-compose.was.yml down && \\
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${HOST_WAS_DIR}":/app -w /app \\
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
