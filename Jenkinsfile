pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build') {
      steps {
        sh './gradlew clean bootJar -x test'
      }
    }

    stage('Deploy') {
      steps {
        dir('was') {
          sh 'docker-compose down'
          sh 'docker-compose up -d --build'
        }
      }
    }
  }

  post {
    success {
      echo "✅ 배포 완료!"
    }
    failure {
      echo "❌ 배포 실패!"
    }
  }
}
