pipeline {
agent any
    
    
    
    
    stages {
        

        // main SATGES
        stage ("1st stage : Git checkout PLEAASE"){
            steps{
        git branch: 'main', 
            url: 'https://github.com/hazem-soussi/devops_test.git'
            }
        
        }
    
        
                stage('2nd Stage : Maven Build Project') {
            steps {
                echo "Build our project"
                sh 'mvn clean install '
            }
        }
        
       
             stage ("3rd Stage : unit testing"){
            steps{
                sh "mvn test"
            }
        
        }
        
        stage ("4th Stage : Integration testing"){
            
                steps {
                sh "mvn verify -DskipUnitTests"
                
                }
            }
        
        stage ("5th Stage : SONARQUBE Analysis"){
            steps {
            echo 'Analzying quality code.'
                script {
                    withSonarQubeEnv(credentialsId: 'hazem_sonar_ci', installationName: 'sonarqube_server') {
     
                  sh "mvn clean package sonar:sonar" 

                 }
                }
              }
            
        }
     

          
        
        stage ("7th stage :Docker image build") {
            steps {
                script {
                sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                sh 'docker image tag $JOB_NAME:v1.$BUILD_ID hazem1998/$JOB_NAME:v1.$BUILD_ID '
                sh 'docker image tag $JOB_NAME:v1.$BUILD_ID hazem1998/$JOB_NAME:latest '

                }
        
        
        }
        }
        
        
        stage ("Push image to dockerHUb") {
        
            steps {
                script {
                    withCredentials([string(credentialsId: 'docker_password', variable: 'docker_imagePWD')]) {
                        sh "docker login -u hazem1998 -p ${docker_imagePWD}"
                    sh 'docker image push hazem1998/$JOB_NAME:v1.$BUILD_ID'
                    sh 'docker image push hazem1998/$JOB_NAME:latest '}       
                }
            
            }
        
        
        }
        
        
         
        stage("APP CONTAINERS LAUNCH WITH Docker-Compose") {
          steps {
              sh 'docker-compose up'
             }
     
       } 
    
        
                
                        

    
    
    }
    
    
  
}
