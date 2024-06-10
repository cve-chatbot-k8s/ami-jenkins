// Job for Conventional Commit Check for Helm-Webapp
pipelineJob('helm-conventional-commit-check') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/helm-webapp')
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

// Multibranch Pipeline Job for Helm-Webapp Pull Request Checks
multibranchPipelineJob('helm-pull-request-checks') {
    branchSources {
        branchSource {
            source {
                github {
                    id('helm-webapp-branch-source')
                    scanCredentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp')
                }
            }
            strategy {
                allBranchesSame {
                    buildDiscoveries {
                        buildRegularBranches()
                        buildOriginPRMerge()
                    }
                }
            }
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            daysToKeep(30)
            numToKeep(10)
        }
    }
    triggers {
        githubPush()
    }
}
