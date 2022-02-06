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
                script{

                    def doc_containers = sh(returnStdout: true, script: 'docker container ps -aq --filter="name=micro_test"').replaceAll("\n", " ") 
                    if (doc_containers) {
                        sh "docker stop ${doc_containers}"
                    }

                }
            }
        }
        stage('Deliver') {
            steps {
                sh 'docker run -p 8081:8080 --name micro_test micro'
            }
        }
    }
}
