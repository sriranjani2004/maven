pipeline {
    agent any

    tools {
        maven 'sonarmaven'
    }

    environment {
        MAVEN_PATH = '/usr/local/apache-maven-3.9.9/bin/mvn'
        SONAR_TOKEN = credentials('sonar-token')
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
                export PATH=$MAVEN_PATH:$PATH
                mvn clean
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project...'
                sh '''
                export PATH=$MAVEN_PATH:$PATH
                mvn test
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                sh '''
                export PATH=$MAVEN_PATH:$PATH
                mvn package
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                sh '''
                export PATH=$MAVEN_PATH:$PATH
                mvn sonar:sonar \
                  -Dsonar.projectKey=SQ-Assessment2 \
                  -Dsonar.sources=src/main/java \
                  -Dsonar.tests=src/test/java \
                  -Dsonar.java.binaries=target/classes \
                  -Dsonar.host.url=http://localhost:9000 \
                  -Dsonar.token=$SONAR_TOKEN \
                  -Dsonar.duplications.hashtable=200000 \
                  -Dsonar.duplications=always
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
