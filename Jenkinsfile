pipeline {
  agent any

  environment {
    COMPOSE_IMAGE = 'docker/compose:1.29.2'
    HOST_JENKINS_HOME = '/home/ubuntu/siseon/jenkins'
    HOST_SERVICE_DIR = "${HOST_JENKINS_HOME}/workspace/${env.JOB_NAME}_${env.BUILD_NUMBER}/siseon-backend"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Deploy') {
      steps {
        sh """
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${HOST_SERVICE_DIR}":/app \\
            -w /app \\
            ${COMPOSE_IMAGE} down && \\
          docker run --rm \\
            -v /var/run/docker.sock:/var/run/docker.sock \\
            -v "${HOST_SERVICE_DIR}":/app \\
            -w /app \\
            ${COMPOSE_IMAGE} up -d --build
        """
      }
    }
  }

  post {
    success { echo '✅ 배포 완료!' }
    failure { echo '❌ 배포 실패…' }
  }
}
