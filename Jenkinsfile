pipeline {
  agent any
  tools {
    maven 'mavenTool'
  }
  
  stages {
    stage('build') {
      steps {
        sh "mvn -f ${PROJECT_ROOT} clean install"
      }
    }

  }
}
