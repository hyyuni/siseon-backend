pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build & Deploy') {
      steps {
        script {
          // 서비스 코드가 있는 디렉터리
          def svc = "${env.WORKSPACE}/siseon-backend"
          sh """
            # 기본 파일명(docker-compose.yml) 사용 → -f 생략
            docker run --rm \\
              -v /var/run/docker.sock:/var/run/docker.sock \\
              -v "${svc}":/app \\
              -w /app \\
              docker/compose:1.29.2 \\
              down && \\
            docker run --rm \\
              -v /var/run/docker.sock:/var/run/docker.sock \\
              -v "${svc}":/app \\
              -w /app \\
              docker/compose:1.29.2 \\
              up -d --build
          """
        }
      }
    }
  }

  post {
    success { echo '✅ 배포 완료!' }
    failure { echo '❌ 배포 실패…' }
  }
}
