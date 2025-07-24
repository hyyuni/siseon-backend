pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps {
        // GitHub 리포지토리 전체를 $WORKSPACE에 가져옵니다
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        // workspace 전체를 /was로 마운트하고, /was에서 compose down/up 실행
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
      echo '✅ 배포 성공!'
    }
    failure {
      echo '❌ 배포 실패…'
    }
  }
}
