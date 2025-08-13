pipeline {
    agent any
    
    tools {
        maven 'Maven'    // Must match exactly what you named in Jenkins
        jdk 'JDK-21'      // Must match exactly what you named in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'echo "Building on Unix"'
                    } else {
                        bat 'echo "Building on Windows"'
                    }
                }
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh './mvnw clean compile'
                    } else {
                        bat 'mvnw.cmd clean compile'
                    }
                }
            }
        }
        
        stage('Unit Tests') {
            steps {
                script {
                    if (isUnix()) {
                        sh './mvnw test'
                    } else {
                        bat 'mvnw.cmd test'
                    }
                }
            }
            post {
                always {
                    // Use junit instead of publishTestResults
                    junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
                    // Publish JaCoCo coverage report
                    step([$class: 'JacocoPublisher',
                          execPattern: 'target/jacoco.exec',
                          classPattern: 'target/classes',
                          sourcePattern: 'src/main/java'
                    ])
                }
            }
        }
        
        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        sh './mvnw package -DskipTests'
                    } else {
                        bat 'mvnw.cmd package -DskipTests'
                    }
                }
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
        
        stage('Build Docker Image') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'docker build -t inventory-app:latest .'
                    } else {
                        bat 'docker build -t inventory-app:latest .'
                    }
                }
            }
        }
        
        stage('Push Docker Image') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        if (isUnix()) {
                            sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                            sh 'docker tag inventory-app:latest lastfire1895/inventory-app:latest'
                            sh 'docker push lastfire1895/inventory-app:latest'
                        } else {
                            bat 'echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin'
                            bat 'docker tag inventory-app:latest lastfire1895/inventory-app:latest'
                            bat 'docker push lastfire1895/inventory-app:latest'
                        }
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            # Clean up existing containers
                            docker stop inventory-app postgres-db 2>/dev/null || echo "No containers to stop"
                            docker rm inventory-app postgres-db 2>/dev/null || echo "No containers to remove"
                            
                            # Create network for containers
                            docker network create inventory-network 2>/dev/null || echo "Network exists"
                            
                            # Start PostgreSQL container
                            docker run -d \
                                --name postgres-db \
                                --network inventory-network \
                                -e POSTGRES_DB=inventory_db \
                                -e POSTGRES_USER=postgres \
                                -e POSTGRES_PASSWORD=password123 \
                                postgres:13
                            
                            # Wait for PostgreSQL to start
                            sleep 10
                            
                            # Start your app container
                            docker run -d \
                                --name inventory-app \
                                --network inventory-network \
                                -p 8001:8002 \
                                -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/inventory_db \
                                -e SPRING_DATASOURCE_USERNAME=postgres \
                                -e SPRING_DATASOURCE_PASSWORD=password123 \
                                inventory-app:latest
                            
                            sleep 5
                            docker logs inventory-app
                            echo "✅ Application deployed on http://localhost:8001"
                        '''
                    } else {
                        bat '''
                            REM Similar Windows commands...
                        '''
                    }
                }
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo '🎉 BUILD SUCCESSFUL! All stages completed.'
        }
        failure {
            echo '💥 BUILD FAILED! Check the logs above.'
        }
    }
}
