pipeline {
    agent any

    tools {
        maven 'sonarmaven'  // Replace with the actual name of the Maven tool in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Use the SonarQube token stored in Jenkins credentials
        MAVEN_PATH = '/usr/local/apache-maven-3.9.9'  // Set your Maven path here
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'  // Add JAVA_HOME
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
                    export JAVA_HOME=$JAVA_HOME  # Corrected comment
                    mvn clean verify sonar:sonar -X \
                      -Dsonar.projectKey=mavenproject \
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
