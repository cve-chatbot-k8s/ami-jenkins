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
}