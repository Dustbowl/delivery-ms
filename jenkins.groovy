pipeline {
    agent any
    stages{
        stage('Github clone repo') {
            steps {
                git branch: 'main', url: 'https://github.com/Dustbowl/delivery-ms.git'
            }
        }
        stage('Maven Compile') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Maven Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Docker Create Image') {
            steps {

            }
        }
        stage('Docker Push Image') {
            steps {

            }
        }
    }
}