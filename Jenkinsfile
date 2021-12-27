pipeline {
  agent any
  tools {
    maven 'mavenTool'
  }
   
  stages {
    stage('build') {
      steps {
        sh "mvn clean install"
      }
    }

  }
}
