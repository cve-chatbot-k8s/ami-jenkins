// Job for Conventional Commit Check for Helm-Webapp
multibranchPipelineJob('helm-conventional-commit-check') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp-cve-processor')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-processor')
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

// Multibranch Pipeline Job for Helm-Webapp Pull Request Checks
multibranchPipelineJob('helm-ci-checks') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-ci')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp-cve-processor')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-processor')
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
            scriptPath('Jenkinsfile') // This is the Jenkinsfile for CI checks
        }
    }
}

// Separate Pipeline Job for Semantic Versioning
multibranchPipelineJob('helm-chart-semantic-versioning') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp-cve-processor')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-processor')
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
            scriptPath('Jenkinsfile_releaserc') // This is the Jenkinsfile for CI checks
        }
    }
}