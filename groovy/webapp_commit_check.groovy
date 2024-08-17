multibranchPipelineJob('commit-check-webapp-processor') {
    branchSources {
        github {
            id('csye7125-webapp-processor-pr')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('webapp-cve-processor')
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


multibranchPipelineJob('commit-check-webapp-consumer') {
    branchSources {
        github {
            id('csye7125-webapp-consumer-pr')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('webapp-cve-consumer')
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

multibranchPipelineJob('commit-check-cve-operator') {
    branchSources {
        github {
            id('csye7125-cve-operator')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('cve-operator')
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

multibranchPipelineJob('commit-check-vector') {
    branchSources {
        github {
            id('csye7125-vector')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('vector')
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
