multibranchPipelineJob('webapp-processor-docker-build-publish') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-webapp')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('webapp')
                    repositoryUrl('https://github.com/csye7125-su24-team7/webapp')
                    configuredByUrl(true)
                    traits {
                        gitHubBranchDiscovery {
                            strategyId(1)
                            headWildcardFilter {
                                includes('main')
                                excludes('')
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
            scriptPath('Jenkinsfile_docker') // This is the Jenkinsfile for building docker image
        }
    }
}