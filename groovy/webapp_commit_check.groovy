multibranchPipelineJob('commit-check-webapp') {
    branchSources {
        github {
            id('csye7125-webapp-pr')
            scanCredentialsId('github-credentials')
            repoOwner('csye7125-su24-team7')
            repository('webapp')
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(-1)
            daysToKeep(-1)
        }
    }
}