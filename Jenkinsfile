pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy') {
            steps {
                sh 'scp target/*.war ec2-user@TOMCAT-IP:/opt/tomcat/webapps/'
            }
        }
    }
}
