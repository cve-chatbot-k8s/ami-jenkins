// Job for Conventional Commit Check for Helm-Webapp
multibranchPipelineJob('helm-conventional-commit-check') {
    branchSources {
        github {
            id('csye7125-heml-conventional-commit')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('helm-webapp-cve-processor')
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
        github {
            id('csye7125-heml-conventional-commit')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('helm-webapp-cve-processor')
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

// multibranchPipelineJob('helm-chart-semantic-versioning') {
//     branchSources {
//         github {
//             id('csye7125-helm-semver')
//             scanCredentialsId('github-credentials')
//             repoOwner('csye7125-su24-team7')
//             repository('helm-webapp-cve-processor')
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
//             scriptPath('Jenkinsfile_releaserc') // This is the Jenkinsfile for CI checks
//         }
//     }
// }

// Separate Pipeline Job for Semantic Versioning
pipelineJob('helm-chart-semantic-versioning') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/helm-webapp-cve-processor')
                        credentials('github-credentials')
                    }
                    branch('main')
                }
            }
            scriptPath('Jenkinsfile_releaserc') // This is the Jenkinsfile for semantic versioning
        }
    }
}
