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
                script {
                    dockerImage = docker.build("delivery-ms")
                }
            }
        }
        stage('Docker Push Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}