pipeline {
    agent any

    tools {
        maven 'sonarmaven'  // Replace with the actual name of the Maven tool in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Use the SonarQube token stored in Jenkins credentials
        MAVEN_PATH = '/Users/ariv/Downloads/apache-maven-3.9.9/bin'  // Set your Maven path here
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
                    # Add Maven to the PATH
                    export PATH=$MAVEN_PATH:$PATH
                    mvn clean verify sonar:sonar -X \
                      -Dsonar.projectKey=mavenproject\
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
