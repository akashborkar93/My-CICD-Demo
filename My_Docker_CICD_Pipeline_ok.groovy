pipeline {
    agent any
    tools {
	    maven "MAVEN3"
	    jdk "OracleJDK17"
	}

    environment {
        registryCredential = 'ecr:us-east-1:awscreds'
        appRegistry = "309471084520.dkr.ecr.us-east-1.amazonaws.com/mycicdimg"
        vprofileRegistry = "https://309471084520.dkr.ecr.us-east-1.amazonaws.com"
    }
  stages {
    stage('Fetch code'){
      steps {
        git branch: 'main', url: 'https://github.com/akashborkar93/My-CICD-Demo.git'
      }
    }
    stage('Build'){
      steps {
        sh 'mvn install -DskipTests'
      }
    }

    stage('Test'){
      steps {
        sh 'mvn test'
      }
    }

    stage ('CODE ANALYSIS WITH CHECKSTYLE'){
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
            post {
                success {
                    echo 'Generated Analysis Result'
                }
            }
        }
    stage('Build App Image') {
       steps {
       
         script {
                dockerImage = docker.build( appRegistry + ":$BUILD_NUMBER", ".")
             }

     }
    
    }

    stage('Upload App Image') {
          steps{
            script {
              docker.withRegistry( vprofileRegistry, registryCredential ) {
                dockerImage.push("$BUILD_NUMBER")
                dockerImage.push('latest')
              }
            }
          }
     }

  }
}