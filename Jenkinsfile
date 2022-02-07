pipeline {
    agent {
        docker {
            image 'maven:3.8.4-openjdk-17'
            args '-v /jenkins/.m2:/jenkins/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker create'){
            steps {
                    sh 'docker build -t micro .'
            }
        }
        stage('Clean docker containers'){
            steps{
                sh 'docker rm --force micro_test'
            }
        }
        stage('Deliver') {
            steps {
                sh 'docker run -d -p 8081:8080 --name micro_test micro'
            }
        }
    }
}
