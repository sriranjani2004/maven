pipeline {
    agent any

    tools {
        maven 'sonarmaven'  // Ensure this is the correct Maven installation name in Jenkins
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')  // Ensure this is the correct credential ID in Jenkins
        MAVEN_PATH = '/usr/local/apache-maven-3.9.9/bin'  // Update with the correct Maven path from the 'where mvn' output
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'  // Set JAVA_HOME here
        PATH = "$JAVA_HOME/bin:$MAVEN_PATH:$PATH"  // Add Maven and JAVA_HOME to the PATH
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
                      -Dsonar.projectKey=new1 \
                      -Dsonar.projectName='new1' \
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
