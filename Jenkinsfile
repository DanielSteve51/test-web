pipeline {
    agent any

    environment {
        TOMCAT_IP = "98.130.46.106"
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
        sh '''
        echo "=== DEBUG ==="
        whoami
        echo "HOME=$HOME"
        pwd
        ls -ld $HOME
        ls -l $HOME/.ssh || echo ".ssh not found"
        echo "=== SCP ==="
        scp -v -i $HOME/.ssh/id_rsa \
        -o StrictHostKeyChecking=no \
        target/${WAR_NAME} ubuntu@${TOMCAT_IP}:${DEPLOY_PATH}/
        '''
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
