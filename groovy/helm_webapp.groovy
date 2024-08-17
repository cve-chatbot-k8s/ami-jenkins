// Job for Conventional Commit Check for Helm-Webapp cve processor
multibranchPipelineJob('helm-conventional-commit-check-processor') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-processor')
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

// // Multibranch Pipeline Job for Helm-Webapp Pull Request Checks
// multibranchPipelineJob('helm-ci-checks') {
//     branchSources {
//         branchSource {
//             source {
//                 github {
//                     id('csye7125-helm-ci')
//                     credentialsId('github-credentials')
//                     repoOwner('csye7125-su24-team7')
//                     repository('helm-webapp-cve-processor')
//                     repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-processor')
//                     configuredByUrl(true)
//                     traits {
//                         gitHubForkDiscovery {
//                             strategyId(1)
//                             trust {
//                                 gitHubTrustPermissions()
//                             }
//                         }
//                     }
//                 }
//             }
//         }
//     }

//     orphanedItemStrategy {
//         discardOldItems {
//             numToKeep(-1)
//             daysToKeep(-1)
//         }
//     }
//     factory {
//         workflowBranchProjectFactory {
//             scriptPath('Jenkinsfile') // This is the Jenkinsfile for CI checks
//         }
//     }
// }

// Separate Pipeline Job for Semantic Versioning for cve processor
multibranchPipelineJob('helm-chart-semantic-versioning-processor') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver-processor')
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


// Job for Conventional Commit Check for Helm-Webapp cve consumer
multibranchPipelineJob('helm-conventional-commit-check-consumer') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-consumer')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp-cve-consumer')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-consumer')
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

// Separate Pipeline Job for Semantic Versioning for Helm-Webapp cve consumer
multibranchPipelineJob('helm-chart-semantic-versioning-consumer') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver-consumer')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-webapp-cve-consumer')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-webapp-cve-consumer')
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



// Job for Conventional Commit Check for Helm-Webapp autoscaler
multibranchPipelineJob('helm-conventional-commit-check-autoscaler') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-autoscaler')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-eks-autoscaler')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-eks-autoscaler')
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

// Separate Pipeline Job for Semantic Versioning for Helm-Webapp cve autoscaler
multibranchPipelineJob('helm-chart-semantic-versioning-autoscaler') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver-autoscaler')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-eks-autoscaler')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-eks-autoscaler')
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

// Job for Conventional Commit Check for Helm-cve-operator
multibranchPipelineJob('helm-conventional-commit-check-cve-operator') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-cve-operator')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-cve-operator')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-cve-operator')
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

// Separate Pipeline Job for Semantic Versioning for cve-operator
multibranchPipelineJob('helm-chart-semantic-versioning-cve-operator') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver-cve-operator')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-cve-operator')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-cve-operator')
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


// Job for Conventional Commit Check for Helm-cve-operator
multibranchPipelineJob('helm-conventional-commit-check-cve-operator') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-cve-operator')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-cve-operator')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-cve-operator')
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


// Job for Conventional Commit Check for Helm-vector
multibranchPipelineJob('helm-conventional-commit-check-vector') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-conventional-commit-vector')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-vector')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-vector')
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


// Separate Pipeline Job for Semantic Versioning for vector
multibranchPipelineJob('helm-chart-semantic-versioning-vector') {
    branchSources {
        branchSource {
            source {
                github {
                    id('csye7125-helm-semver-vector')
                    credentialsId('github-credentials')
                    repoOwner('csye7125-su24-team7')
                    repository('helm-vector')
                    repositoryUrl('https://github.com/csye7125-su24-team7/helm-vector')
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
