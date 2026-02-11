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
                sh 'scp target/*.war ec2-user@18.61.44.158:/opt/tomcat/webapps/'
            }
        }
    }
}
