pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Verify Docker') {
      steps {
        // 에이전트(컨테이너) 안에 docker 바이너리가 살아있는지 확인
        sh 'docker version'
      }
    }

    stage('Build & Deploy via Compose-Container') {
      steps {
        dir('was') {
          // down & up 을 한 번에
          sh '''
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:2.20.2 \
              -f docker-compose.was.yml down && \
            docker run --rm \
              -v /var/run/docker.sock:/var/run/docker.sock \
              -v "$PWD":/app -w /app \
              docker/compose:2.20.2 \
              -f docker-compose.was.yml up -d --build
          '''
        }
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
