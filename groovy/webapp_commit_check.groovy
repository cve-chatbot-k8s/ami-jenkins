pipelineJob('conventional-commit-check') {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/csye7125-su24-team7/webapp')
                        credentials('github-credentials')
                    }
                    branches('*/main')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
    triggers {
        githubPullRequest {
            triggerMode('HEAVY_HOOKS') // Listens for GitHub webhook events
            events {
                pullRequestOpened() // Triggers a build when a PR is opened
            }
        }
    }
}


// groovy.lang.MissingMethodException: No signature of method: javaposse.jobdsl.dsl.helpers.triggers.TriggerContext.github() is applicable for argument types: (script$_run_closure1$_closure3$_closure8) values: [script$_run_closure1$_clo>
//Jun 10 03:53:54 ip-10-0-1-26 jenkins[3889]: Possible solutions: with(groovy.lang.Closure), githubPush(), getAt(java.lang.String)