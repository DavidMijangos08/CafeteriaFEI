pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat "mvn -Dmaven.test.failure.ignore=true clean package" 
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        
         stage('Test') {
            steps {
                bat 'make check || true' 
                junit '**/target/*.xml' 
            }
        }
        
         stage('Deploy') {
            when {
              expression {
                currentBuild.result == null || currentBuild.result == 'SUCCESS'
              }
            }
            steps {
                sh 'make publish'
            }
        }
    }
}
