pipeline {
    agent any

    environment {
        TOMCAT_IP = "18.60.154.150"
        DEPLOY_PATH = "/opt/tomcat/webapps"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "Cloning Git Repo"
            }
        }

        stage('Read POM') {
            steps {
                script {
                    def pom = readMavenPom file: 'pom.xml'

                    env.WAR_NAME = pom.build?.finalName
                        ? "${pom.build.finalName}.war"
                        : "${pom.artifactId}-${pom.version}.war"

                    echo "WAR detected: ${env.WAR_NAME}"
                }
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
        sshagent(['tomcat-ssh']) {
            sh """
            scp -o StrictHostKeyChecking=no target/${env.WAR_NAME} ubuntu@${TOMCAT_IP}:${DEPLOY_PATH}/
            """
        }
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
