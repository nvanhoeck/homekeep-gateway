pipeline {
    agent any
    environment {
        NEW_VERSION = '0.0.3'
        ORG = 'homekeep'
        APP_NAME = 'homekeep-gateway'
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "apache-maven"
    }

    triggers {
        pollSCM('') // Enabling being build on Push
    }

    stages {
        stage('Clean') {
            when {
                expression {
                    BRANCH_NAME == 'develop'
                }
            }
            environment {
                PREVIEW_NAMESPACE = "$APP_NAME-$BRANCH_NAME".toLowerCase()
                HELM_RELEASE = "$PREVIEW_NAMESPACE".toLowerCase()
            }

            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/nvanhoeck/homekeep-gateway.git'
                echo "building version ${NEW_VERSION}"
                echo 'Cleaning...'
                bat "mvn versions:set -DnewVersion=$NEW_VERSION"
                bat "mvn clean"
            }
        }

        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                echo 'Building...'
                bat "mvn package"
            }
        }

        stage('Deploy to Develop') {
            when {
                expression {
                    BRANCH_NAME == 'develop'
                }
            }

            steps {
                // Get some code from a GitHub repository
                bat "git remote add heroku https://git.heroku.com/homekeep-api-config.git"
                bat "git tag -a tagName -m ${NEW_VERSION}"
                bat "git push heroku"
            }
        }

        stage('Release') {
            when {
                expression {
                    BRANCH_NAME == 'develop'
                }
            }

            steps {
                // Get some code from a GitHub repository
                bat "git branch -u origin/develop develop"
                bat "git config user.email \"niko.vanhoeck@gmail.com\""
                bat "git config user.name \"niko\""
                bat "git commit -am \"Released version ${NEW_VERSION}\""
                bat "git checkout master"
                bat "git merge develop"
                bat "git tag -a ${NEW_VERSION} -m ${NEW_VERSION}"
                bat "git push heroku"
            }
        }
    }
}
