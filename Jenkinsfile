def customImage 
pipeline {
    agent any
    
    stages {
  
        stage('GIT PUll') { 
            steps {
                dir('artifacts'){
                    git credentialsId: 'github' url: 'https://github.com/sahitkumarreddy/crud-operations.git'
                }
                
            }
        }
        
        stage('Build') { 
            steps {
             echo "Building"  
             dir('artifacts'){
                withMaven(maven: 'mymaven') 
                {
                  sh 'mvn clean verify'
                } 
             
              }
            }
       }
        
        stage('Test') { 
            steps {
             echo "Testing"  
             dir('artifacts'){
                withMaven(maven: 'mymaven') 
                {
                  sh 'mvn test'
                } 
             
              }
            }
       }

    stage(' Compile & Package') { 
            steps {
             echo "Static code analysis"  
             dir('artifacts'){
                withMaven(maven: 'mymaven') {
                  sh 'mvn compile' 
                  sh 'mvn package'  
               
             } 
             
            }
        }
    }
    stage('Build image') { 
        agent { label 'docker' }
      steps {
             echo "Build the docker file"  
             script{
                
                 sh 'cp ${JENKINS_HOME}/workspace/${JOB_NAME}/artifacts/target/crud-0.0.1-SNAPSHOT.jar .'
                 customImage = docker.build("sahitkumarreddy/crud:${BUILD_NUMBER}")
                 echo customImage
                
             }
        }
    }
    stage('Deploy Image') { 
       
      steps {
             echo "Build the docker file"  
             script{
                 
                 docker.withRegistry( '', 'DOCKERHUBLOGIN' ) {
                           customImage.push()
                }
             }
        }
    }

}
      
}
