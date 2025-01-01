pipeline {
    agent any

    tools {
        maven 'sonarmaven'  // Ensure this is the correct Maven installation name in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Ensure this is the correct credential ID in Jenkins
        MAVEN_PATH = '/Users/ariv/Downloads/apache-maven-3.9.9/bin'  // Ensure this is the correct Maven path
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'  // Set JAVA_HOME here
        PATH = "$JAVA_HOME/bin:$PATH"  // Make sure JAVA_HOME is in the PATH
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
                    # Ensure Maven is in the PATH
                    export PATH=$MAVEN_PATH:$PATH
                    mvn clean verify sonar:sonar -X \
                      -Dsonar.projectKey=newprojectbackend \
                      -Dsonar.projectName=newprojectbackend \
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
