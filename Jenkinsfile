pipeline {
  agent any

  environment {
    HOST_WAS_DIR = '/home/ubuntu/siseon/was'
  }

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy via Compose-Container') {
      steps {
        sh '''
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            docker/compose:latest \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            docker/compose:latest \
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
