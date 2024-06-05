multibranchPipelineJob('static-site') {
    branchSources {
        github {
            id('csye7125-static-site')
            scanCredentialsId('jenkins')
            repoOwner('csye7125')
            repository('static-site')
        }
    }

    orphanedItemStrategy {
        discardOldItems {
            numToKeep(-1)
            daysToKeep(-1)
        }
    }
}