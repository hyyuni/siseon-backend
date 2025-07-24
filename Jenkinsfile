pipeline {
  agent any

  stages {
    stage('Checkout & Inspect') {
      steps {
        checkout scm
        sh 'echo "=== WORKSPACE LISTING ==="'
        sh 'ls -R "${WORKSPACE}"'
      }
    }

    stage('Deploy with Compose') {
      steps {
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
    success { echo '✅ 배포 완료!' }
    failure { echo '❌ 배포 실패…' }
  }
}
