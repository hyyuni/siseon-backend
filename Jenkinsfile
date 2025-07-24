pipeline {
  agent any

  stages {
    stage('Checkout SCM') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        sh '''
          # workspace/was 폴더를 컨테이너 /was로 마운트
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}/was":/was \
            -w /was \
            docker/compose:1.29.2 \
            -f docker-compose.was.yml down && \
          docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v "${WORKSPACE}/was":/was \
            -w /was \
            docker/compose:1.29.2 \
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
