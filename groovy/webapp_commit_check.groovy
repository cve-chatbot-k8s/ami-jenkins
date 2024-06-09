pipelineJob('conventional-commit-check') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/webapp')
                        credentials('github-credentials')
                    }
                    branches('*/main')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
    triggers {
        githubPullRequest {
            useGitHubHooks()
            permitAll()
        }
    }
}