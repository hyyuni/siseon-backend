pipeline {
  agent any

  environment {
    // Jenkins가 체크아웃한 워크스페이스 안의 was/ 폴더 경로
    HOST_WAS_DIR  = "${env.WORKSPACE}/was"
    // 사용할 Compose 버전 (v1)
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
  }

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy (/was 경로)') {
      steps {
        sh """
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${HOST_WAS_DIR}":/was \\
            -w /was \\
            ${COMPOSE_IMAGE} \\
            -f docker-compose.was.yml down && \\
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${HOST_WAS_DIR}":/was \\
            -w /was \\
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
