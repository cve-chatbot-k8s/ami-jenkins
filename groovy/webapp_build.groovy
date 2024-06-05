pipelineJob('docker-build-publish') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/static-site')
                        credentials('github-credentials')
                    }
                    branches('main')
//                    scriptPath('Jenkinsfile')
                }
            }
        }
    }
}