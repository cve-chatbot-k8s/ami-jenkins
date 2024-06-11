pipelineJob('conventional-commit-check') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/webapp')
                        credentials('github-credentials')
                        refspec('+refs/pull/*:refs/remotes/origin/pr/*')
                    }
                    branch('${sha1}')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
    triggers {
        githubPullRequest {
            useGitHubHooks()
            orgWhitelist('csye7125-su24-team7')
            allowMembersOfWhitelistedOrgsAsAdmin()
            userWhitelist('girish332', 'Raj-Adarsh')
        }
    }

    properties {
        githubProjectUrl('https://github.com/csye7125-su24-team7/webapp')
    }
}