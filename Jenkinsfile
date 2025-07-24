pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps { checkout scm }
    }

    stage('Verify Docker') {
      steps { sh 'docker version' }
    }

    stage('Build & Deploy via Compose-Container') {
      steps {
        dir('was') {
          sh '''
            # 이전 컨테이너 내리기 & 재시작을 한 번에
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:latest \
              -f docker-compose.was.yml down && \
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:latest \
              -f docker-compose.was.yml up -d --build
          '''
        }
      }
    }
  }

  post {
    success { echo '✅ 배포 성공!' }
    failure { echo '❌ 배포 실패…' }
  }
}
