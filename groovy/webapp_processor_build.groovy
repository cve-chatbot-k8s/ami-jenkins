multibranchPipelineJob('webapp-processor-docker-build-publish') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-webapp-consumer')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('webapp-cve-processor')
                    repositoryUrl('https://github.com/csye7125-su24-team7/webapp-cve-processor')
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

multibranchPipelineJob('webapp-consumer-docker-build-publish') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-webapp-consumer')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('webapp-cve-consumer')
                    repositoryUrl('https://github.com/csye7125-su24-team7/webapp-cve-consumer')
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


multibranchPipelineJob('webapp-consumer-docker-build-publish') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-webapp-consumer')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('webapp-cve-consumer')
                    repositoryUrl('https://github.com/csye7125-su24-team7/webapp-cve-consumer')
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

//cve-operator
multibranchPipelineJob('cve-operator-docker-build-publish') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-cve-operator')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('cve-operator')
                    repositoryUrl('https://github.com/csye7125-su24-team7/cve-operator')
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
