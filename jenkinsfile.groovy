pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                        credentialsId: 'github_access_token',
                        url: 'https://github.com/ProjectGopang/conn_test.git'
            }
        }

        stage('Gradle Build') {
            steps {
                sh'''
                    ./gradlew clean build
                '''
            }
        }
    }
}
