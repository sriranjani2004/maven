pipeline {
    agent any

    tools {
        maven 'sonarmaven'
        jdk 'JDK 17'  // Ensure that JDK 17 is configured in Jenkins tools
    }

    environment {
        MAVEN_PATH = '/usr/local/apache-maven-3.9.9/bin'
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'  // Set the correct JAVA_HOME path
        SONAR_TOKEN = credentials('sonar-token')  // Use Jenkins credentials for the token
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean target folder') {
            steps {
                echo 'Cleaning target directory...'
                sh '''
                mvn clean
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project and generating JaCoCo report...'
                sh '''
                mvn test jacoco:report
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                sh '''
                mvn package
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                sh '''
                mvn sonar:sonar \
                  -Dsonar.projectKey=SQ-Assessment2 \
                  -Dsonar.projectName='SQ-Assessment2' \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.token=$SONAR_TOKEN
                '''
            }
        }
        
        stage('Archive JaCoCo Reports') {
            steps {
                echo 'Archiving JaCoCo report...'
                archiveArtifacts artifacts: 'target/site/jacoco/index.html', allowEmptyArchive: true
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
