pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Building..'
                 sh "./gradlew clean assembleDebug"

            }
        }
        stage('Stage') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Release') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}