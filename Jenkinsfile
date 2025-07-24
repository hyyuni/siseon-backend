pipeline {
  agent any

  environment {
    HOST_WAS_DIR = '/home/ubuntu/siseon/was'
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
        sh '''
          # 컨테이너 내 /app 에 호스트의 was 디렉터리 마운트
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            ${COMPOSE_IMAGE} \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            ${COMPOSE_IMAGE} \
            -f docker-compose.was.yml up -d --build
        '''
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
