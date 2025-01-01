pipeline {
    agent any

    tools {
        maven 'sonarmaven'
        jdk 'JDK 17'  // Ensure that JDK 17 is configured in Jenkins tools
    }

    environment {
        MAVEN_PATH = '/usr/local/apache-maven-3.9.9/bin'
        JAVA_HOME = '/Library/Java/JavaVirtualMachines/jdk-17.jdk/Contents/Home'  // Set the correct JAVA_HOME path
        SONAR_TOKEN = credentials('sonarqube-token')
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
                export JAVA_HOME=$JAVA_HOME
                export PATH=$MAVEN_PATH:$JAVA_HOME/bin:$PATH
                mvn clean
                '''
            }
        }

        stage('Test') {
            steps {
                echo 'Testing the project...'
                sh '''
                export JAVA_HOME=$JAVA_HOME
                export PATH=$MAVEN_PATH:$JAVA_HOME/bin:$PATH
                mvn test
                '''
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging the compiled code...'
                sh '''
                export JAVA_HOME=$JAVA_HOME
                export PATH=$MAVEN_PATH:$JAVA_HOME/bin:$PATH
                mvn package
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                sh '''
                export JAVA_HOME=$JAVA_HOME
                export PATH=$MAVEN_PATH:$JAVA_HOME/bin:$PATH
                mvn sonar:sonar \
                  -Dsonar.projectKey=SQ-Assessment2 \
                  -Dsonar.projectName='SQ-Assessment2' \
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
