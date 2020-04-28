pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Building..'


            }
        }
        stage('Stage') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Release') {
            steps {
            timeout(time:5, unit: 'DAYS'){
            input message: "Approve Prod?"
            }
                echo 'Deploying....'
            }
        }
    }
}