pipeline {
    agent any  
    
    stages { 
    
    	stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        
        stage('compile') {
	      steps {
	        sh 'mvn clean install'
	      }
	    }
	    
	    stage('build docker image') {
	     steps {
	        sh 'docker build -t account:latest .'
	      }
    	}
    	
    	stage('push image') {
	     steps {
	        sh 'docker login -u=admin -p=admin https://mycluster.icp:8500'
	        sh 'docker tag product:latest mycluster.icp:8500/ibmcom/account:latest'
	        sh 'docker push mycluster.icp:8500/ibmcom/account:latest'
	      }
    	}
    }
}