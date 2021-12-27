pipeline {
  agent any
  tools {
    maven 'mavenTool'
  }
   
  stages {
    stage('build') {
      steps {
        sh "mvn -X clean install"
      }
    }

  }
}
