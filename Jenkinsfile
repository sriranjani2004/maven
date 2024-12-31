pipeline {
    agent any

    tools {
        maven 'sonarmaven'  // Replace with the actual name of the Maven tool in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Use the SonarQube token stored in Jenkins credentials
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // Checkout the source code from your repository
            }
        }

        stage('Clean, Verify, and SonarQube Analysis') {
            steps {
                echo 'Cleaning, verifying, and running SonarQube analysis...'
                sh '''
                mvn clean verify sonar:sonar \
                  -Dsonar.projectKey=newprojectbackend \
                  -Dsonar.projectName='newprojectbackend' \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.token=$SONAR_TOKEN
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
    }
}
