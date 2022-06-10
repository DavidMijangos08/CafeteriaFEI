pipeline {
    agent any
    stages {
        stage('git repo & clean') {
            steps {
               bat "rmdir  /s /q Software Solutions - Cliente Pipeline"
                bat "git clone https://github.com/DavidMijangos08/CafeteriaFEI.git"
                bat "mvn clean -f Software Solutions - Cliente Pipeline"
            }
        }
        stage('install') {
            steps {
                bat "mvn install -f CafeteriaFEI"
            }
        }
        stage('test') {
            steps {
                bat "mvn test -f CafeteriaFEI"
            }
        }
        stage('package') {
            steps {
                bat "mvn package -f CafeteriaFEI"
            }
        }
    }
}
