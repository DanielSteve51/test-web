pipeline {
    agent any

    environment {
        TOMCAT_IP = "18.60.107.124"
        DEPLOY_PATH = "/opt/tomcat/webapps"
        WAR_NAME = "test_web.war"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                sh """
                scp $WORKSPACE/target/${WAR_NAME} ec2-user@${TOMCAT_IP}:${DEPLOY_PATH}/
                """
            }
        }
    }

    post {
        success {
            echo "Deployment successful 🚀"
        }
        failure {
            echo "Deployment failed ❌"
        }
    }
}
