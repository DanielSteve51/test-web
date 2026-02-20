pipeline {
    agent any

    environment {
        TOMCAT_IP = "40.192.6.76"
        DEPLOY_PATH = "/opt/tomcat/webapps"
        SSH_KEY = "C:\\Users\\Daniel Steve\\.ssh\\ec2-tomcat.pem"
    }



    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "Repository cloned"
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
                bat 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                    bat 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                bat """
                scp -i %SSH_KEY% ^
                -o StrictHostKeyChecking=no ^
                target\\%WAR_NAME% ^
                ubuntu@%TOMCAT_IP%:%DEPLOY_PATH%/
                """
            }
        }
    }

    post {
        success {
            echo "Deployment Successful"
        }
        failure {
            echo "Pipeline Failed"
        }
    }
}
