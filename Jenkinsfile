
pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-amazon-corretto.x86_64'
        PATH = "${JAVA_HOME}/bin:${PATH}"
        SONAR_HOST_URL = 'http://sonar.leancloudandevops.online:9000'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Verify Tools') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn -B clean verify'
            }
        }

        stage('Verify JaCoCo Report') {
            steps {
                sh '''
                    echo "=== Check JaCoCo XML report ==="

                    if [ ! -f target/site/jacoco/jacoco.xml ]; then
                        echo "ERROR: JaCoCo XML report was not generated."
                        exit 1
                    fi

                    ls -lh target/site/jacoco/jacoco.xml

                    echo "=== Check CalculatorApp exclusion ==="

                    if grep -q 'name="CalculatorApp"' target/site/jacoco/jacoco.xml; then
                        echo "CalculatorApp is PRESENT in the report"
                    else
                        echo "CalculatorApp is NOT PRESENT in the report"
                    fi
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withCredentials([
                    string(
                        credentialsId: 'sonar-token',
                        variable: 'SONAR_TOKEN'
                    )
                ]) {
                    sh '''
                        mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                          -Dsonar.projectKey=devops-calculator \
                          -Dsonar.projectName="DevOps Calculator" \
                          -Dsonar.host.url="$SONAR_HOST_URL" \
                          -Dsonar.token="$SONAR_TOKEN" \
                          -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
                          -Dsonar.coverage.exclusions=**/CalculatorApp.java
                    '''
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true,
                  testResults: '**/target/surefire-reports/*.xml'
        }

        success {
            echo 'Build, tests, JaCoCo report verification, and SonarQube analysis completed successfully.'
        }

        failure {
            echo 'Pipeline failed. Review the stage logs to identify the issue.'
        }
    }
}
