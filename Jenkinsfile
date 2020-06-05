pipeline {
   agent any
   environment {
        NEW_VERSION = '0.0.2'
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

         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/nvanhoeck/homekeep-gateway.git'
            echo "building version ${NEW_VERSION}"
            echo 'Cleaning...'
            bat "mvn clean"
      }
     }

     stage('Build') {
       when {
           expression {
           BRANCH_NAME == 'develop'
           }
       }

          steps {
             // Get some code from a GitHub repository
             echo 'Building...'
             bat "mvn package"
       }
     }
   }
}
