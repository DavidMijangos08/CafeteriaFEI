pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                bat 'make'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        
         stage('Test') {
            steps {
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                */
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
