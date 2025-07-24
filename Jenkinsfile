pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // GitHub 리포지터리 전체를 $WORKSPACE 에 내려받습니다
        checkout scm
      }
    }

    stage('Deploy with Compose') {
      steps {
        // 워크스페이스 전체를 컨테이너의 /was 경로로 마운트
        sh '''
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}":/was \
            -w /was \
            docker/compose:1.29.2 \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}":/was \
            -w /was \
            docker/compose:1.29.2 \
            -f docker-compose.was.yml up -d --build
        '''
      }
    }
  }

  post {
    success {
      echo '✅ 배포 완료!'
    }
    failure {
      echo '❌ 배포 실패...'
    }
  }
}
