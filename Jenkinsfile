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
       
        stage('Deliver') {
            steps {
                sh 'docker build -t test .'
            }
        }
    }
}
