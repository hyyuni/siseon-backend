pipeline {
  agent any

  environment {
    // compose 컨테이너 이미지
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
    // GitHub에서 내려받은 리포트리 루트 밑에 실제 서비스 폴더
    SERVICE_DIR   = "${env.WORKSPACE}/siseon-backend"
  }

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        sh '''
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${SERVICE_DIR}":/was \
            -w /was \
            ${COMPOSE_IMAGE} \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${SERVICE_DIR}":/was \
            -w /was \
            ${COMPOSE_IMAGE} \
            -f docker-compose.was.yml up -d --build
        '''
      }
    }
  }

  post {
    success { echo '✅ 배포 성공!' }
    failure { echo '❌ 배포 실패…' }
  }
}
