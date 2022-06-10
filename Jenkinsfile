pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat "ant clean compile test package war" 
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
