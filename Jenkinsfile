pipeline {
    agent any

    environment {
        DEPLOY_PATH = "/opt/tomcat/webapps"
        WAR_NAME = "test_web.war"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "Cloning Git Repo"
                sleep 5
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
                echo "Building .war"
                sleep 5
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh """
                scp $WORKSPACE/target/${WAR_NAME} ec2-user@${TOMCAT_IP}:${DEPLOY_PATH}/
                """
                echo "Deplying to Tomcat"
                sleep 5
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
