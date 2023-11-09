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
            sh'''
                ./gradlew clean build
            '''
        }

        stage("Build & SonarQube") {
            agent any
            steps {
                script {
                    def gradleHome = tool name: 'Gradle', type: 'Tool'
                    sh "${gradleHome}/bin/gradle clean build"
                }
                withSonarQubeEnv('SonarQube_server') {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
}