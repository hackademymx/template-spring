pipeline {
  agent any
  tools {
    maven 'mavenTool'
  }
  
  environment {
    PROJECT_ROOT = "skel_microservice"
  }
  
  stages {
    stage('build') {
      steps {
        sh "mvn -f ${PROJECT_ROOT} clean install"
      }
    }

  }
}
