pipeline {
    agent any
    
    // Tools required - make sure these are configured in Global Tool Configuration in Jenkins
    tools {
        maven 'cseMaven' // This name must match the Maven installation name in Jenkins
        jdk 'javacse'     // This name must match the JDK installation name in Jenkins
    }

    environment {
        // --- Variables to substitute with your actual configurations ---
        IMAGE_NAME = "tejaswi2016/online-pharmacy"
        IMAGE_TAG = "latest" // Or use ${env.BUILD_ID} for unique builds
        
        // EC2 Details
        EC2_USER = "ubuntu" // Often ubuntu, ec2-user, or admin
        EC2_IP = "3.91.185.73"
        
        // Credentials IDs set up in Jenkins "Manage Credentials"
        DOCKER_CREDS_ID = "OnlinePharmacyDcokerid"
        EC2_SSH_KEY_ID = "onlinepharmacyec2"
    }

    stages {
        
        // STAGE 1: Clone Code
        stage('1. Clone Code') {
            steps {
                echo 'Checking out source code...'
                // You can configure your SCM here if Jenkins isn't automatically doing it
                checkout scm
            }
        }

        // STAGE 2: Unit Testing
        stage('2. Unit Testing') {
            steps {
                echo 'Running Unit Tests and Generating Jacoco Coverage...'
                // We use clean test to ensure fresh test runs
                sh 'mvn clean test'
            }
        }

        // STAGE 3: SonarQube Analysis
        // Ensure "SonarQube servers" is configured in Jenkins System Configuration 
        // with the name 'sonar-server'. Let the scanner pick up properties automatically.
        stage('3. SonarQube Analysis') {
            steps {
                echo 'Running SonarQube Analysis...'
                withSonarQubeEnv('sonar-server') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=online-pharmacy'
                }
            }
        }

        // Optional: Fail the pipeline if SonarQube Quality Gate fails
        // stage('Quality Gate') {
        //     steps {
        //         timeout(time: 1, unit: 'HOURS') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }

        // STAGE 4: Build Jar
        stage('4. Build Jar') {
            steps {
                echo 'Building Jar Package...'
                // Skip tests here since we already ran them in Step 2
                sh 'mvn clean package -DskipTests'
            }
        }

        // STAGE 5: Docker Image Build
        stage('5. Docker Image') {
            steps {
                echo 'Building Docker Image...'
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
            }
        }

        // STAGE 6: Push to Docker Hub
        stage('6. Push to Docker Hub') {
            steps {
                echo 'Logging into Docker Hub and Pushing Image...'
                withCredentials([usernamePassword(credentialsId: "${DOCKER_CREDS_ID}", passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                    sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }

        // STAGE 7: Deploy to EC2 Instance
        stage('7. Deploy to EC2 Instance') {
            steps {
                echo 'Deploying to AWS EC2...'
                // Utilizing SSH Agent plugin to connect to the target machine securely
                sshagent(credentials: ["${EC2_SSH_KEY_ID}"]) {
                    // Disable strict host key checking for automated pipelines
                    sh """
                    ssh -o StrictHostKeyChecking=no ${EC2_USER}@${EC2_IP} '
                        echo "Logged into EC2..."
                        
                        # Stop and remove any existing container running on port 8099
                        docker ps -q --filter "name=online-pharmacy" | grep -q . && docker stop online-pharmacy && docker rm -fv online-pharmacy || true
                        
                        # Remove the old image so we force a fresh pull
                        docker rmi ${IMAGE_NAME}:${IMAGE_TAG} || true
                        
                        # Pull the latest image
                        docker pull ${IMAGE_NAME}:${IMAGE_TAG}
                        
                        # Run the new container
                        docker run -d --name online-pharmacy -p 8099:8099 ${IMAGE_NAME}:${IMAGE_TAG}
                        
                        echo "Deployment Complete!"
                    '
                    """
                }
            }
        }

    } // end of stages
    
    post {
        always {
            // Clean up workspace after build to save space
            cleanWs()
            // Logout of Docker
            sh 'docker logout || true'
        }
        success {
            echo 'Pipeline successfully completed!'
        }
        failure {
            echo 'Pipeline failed! Please check logs.'
        }
    }
}
