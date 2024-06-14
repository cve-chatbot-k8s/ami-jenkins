// Job for Conventional Commit Check for Helm-Webapp
multibranchPipelineJob('terraform-ci-check') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-infra-ci-check')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('infra-jenkins')
                    repositoryUrl('https://github.com/csye7125-su24-team7/infra-jenkins')
                    configuredByUrl(true)
                    traits {
                        gitHubForkDiscovery {
                            strategyId(1)
                            trust {
                                gitHubTrustPermissions()
                            }
                        }
                    }
                }
            }
        }        
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(-1)
            daysToKeep(-1)
        }
    }
    factory {
        workflowBranchProjectFactory {
            scriptPath('Jenkinsfile') // This is the Jenkinsfile for Conventional Commit Check
        }
    }
}
