pipeline {
    agent any
    tools {
        // 위에서 설정한 JDK를 사용하도록 지정
        jdk 'openjdk-17'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                        credentialsId: 'gopang',
                        url: 'https://github.com/ProjectGopang/conn_test.git'
            }
        }

        stage('Prepare Workspace') {
            steps {
                script {
                    // Gradle Wrapper 실행 권한 추가
                    sh 'chmod +x gradlew'
                }
            }
        }

        stage('Gradle Build') {
            steps {
                script {
                    // Gradle 빌드 실행
                    sh './gradlew clean build'
                }
            }
        }
    }
}
