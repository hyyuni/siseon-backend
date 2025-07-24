pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        // GitHub 리포지터리 전체를 $WORKSPACE에 체크아웃
        checkout scm
      }
    }

    stage('Deploy with Compose') {
      steps {
        sh '''
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}":/home/ubuntu/siseon/was \
            -w /home/ubuntu/siseon/was \
            docker/compose:1.29.2 \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}":/home/ubuntu/siseon/was \
            -w /home/ubuntu/siseon/was \
            docker/compose:1.29.2 \
            -f docker-compose.was.yml up -d --build
        '''
      }
    }
  }

  post {
    success { echo '✅ 배포 완료!' }
    failure { echo '❌ 배포 실패...' }
  }
}