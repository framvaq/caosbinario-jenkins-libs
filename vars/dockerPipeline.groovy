def call(Map pipelineParams) {
    pipeline {
        agent {
            label 'docker-node'
        }

        stages {
            stage('docker build') {
                steps {
                    script {
                        echo "La imagen que se va a construir es: ${pipelineParams.dockerImage}"

                        dockerLib.build(DockerfilePath: pipelineParams.dockerfilePath,
                                        DockerImage: pipelineParams.dockerImage,
                                        DockerContext: pipelineParams.dockerContext)
                    }
                }
            }
            stage('docker push') {
                steps {
                    script {
                        dockerLib.push(DockerImage: pipelineParams.dockerImage)
                    }
                }
            }
        }
    }
}
