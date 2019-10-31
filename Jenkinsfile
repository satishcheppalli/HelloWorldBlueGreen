pipeline {
    agent any
    
    stages {

        stage('Build jar file') { 
            steps {
				withMaven(maven: 'Maven339', jdk: 'JDK8', mavenSettingsConfig: 'bb40ec16-56e1-440a-8fdd-97af1a8b248f', mavenLocalRepo: '${BASE}/maven-repositories/${EXECUTOR_NUMBER}', options: [artifactsPublisher(disabled: true)])
					{
							sh "mvn clean install -DskipTests"
					}
            }
        }        
        stage('Build and Create docker image') { 
            steps {
                script {
                    def scmVars = checkout([
                        $class: 'GitSCM',
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                            url: 'https://github.com/satishcheppalli/HelloWorldBlueGreen.git'
                          ]],
                        branches: [ [name: '*/master'] ]
                      ])
                //sh "docker build -f Dockerfile -t iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:${scmVars.GIT_COMMIT} ."
		sh "docker build -f Dockerfile -t iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:v1 ."
                }
            }
        }
        stage('Push image to OCIR') { 
            steps {
                script {
                    def scmVars = checkout([
                        $class: 'GitSCM',
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                            url: 'https://github.com/satishcheppalli/HelloWorldBlueGreen.git'
                          ]],
                        branches: [ [name: '*/master'] ]
                      ])
                sh "docker login -u 'fedexoraclecloud/oracleidentitycloudservice/2750344' -p 'Ur6G[M>frZ5qMsWp{<QP' iad.ocir.io"
    
                //sh "docker push iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:${scmVars.GIT_COMMIT}"
		sh "docker push iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:v1"	
               // env.GIT_COMMIT = scmVars.GIT_COMMIT
               // sh "export GIT_COMMIT=${env.GIT_COMMIT}"
                }
               }
            }
        
        stage('Deploy Application') {  
			steps {	
				script {
					  def scmVars = checkout([
                        $class: 'GitSCM',
                        doGenerateSubmoduleConfigurations: false,
                        userRemoteConfigs: [[
                            url: 'https://github.com/satishcheppalli/HelloWorldBlueGreen.git'
                          ]],
                        branches: [ [name: '*/master'] ]
                      ])
					
           // sh("kubectl get ns ${namespace} || kubectl create ns ${namespace}")    
           // sh("sed -i 's#iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:latest#iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:${scmVars.GIT_COMMIT}#g' ./k8s/dev/*.yml")	
	   // sh("sed -i 's#iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:latest#iad.ocir.io/fedexoraclecloud/fsc/helloworldbluegreen:v2#g' ./k8s/dev/*.yml")	
            sh("kubectl --namespace=satish-ns apply -f k8s/dev/kube-blue.yml")
           // sh("kubectl --namespace=satish-ns apply -f k8s/dev/kube-green.yml") 
						}
					}
			  }
		}
}
