pipeline {
  agent any

  environment {
    // 호스트의 was 디렉터리 절대 경로
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
        // 호스트 WAS 디렉터리를 /app으로 마운트
        sh '''
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            docker/compose:latest \
            down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${HOST_WAS_DIR}":/app -w /app \
            docker/compose:latest \
            up -d --build
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
