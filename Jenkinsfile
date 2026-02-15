pipeline {
    agent any

    environment {
        DEPLOY_PATH = "/opt/tomcat/webapps"
    }

    stages {

        stage('Read POM') {
              steps {
                script {
                      def pom = readMavenPom file: 'pom.xml'
                      env.WAR_NAME = "${pom.build.finalName}.war"
                      echo "WAR file: ${env.WAR_NAME}"
                }
              }
            }
        
        stage('Checkout') {
            steps {
                checkout scm
                echo "Cloning Git Repo"
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
                echo "Building .war"
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh """
                scp $WORKSPACE/target/${WAR_NAME} ec2-user@${TOMCAT_IP}:${DEPLOY_PATH}/
                """
                echo "Deplying to Tomcat"
            }
        }
    }

    post {
        success {
            echo "Success"
        }
        failure {
            echo "Failed"
        }
    }
}
