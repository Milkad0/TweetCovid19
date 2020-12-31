# TweetCovid19

Par [Vincent ETHEVE](mailto:vincent.etheve@edu.ece.fr) & [Quentin BENOIST](mailto:quentin.benoist@edu.ece.fr)

Ce projet est application web qui permet de streamer en direct les #HashTags et la localisation d'un Tweets.

Le but de ce projet a donc été de construire une architecture distribuée, conteneurisée autour de cette application pour la rendre plus facile d'utilisation pour le développement, le test, le déploiement et la gestion globale d'application.

Une fois l'application developpée nous l'avons dockerisé et nous avons push l'image de cette application sur notre [Docker Hub](https://hub.docker.com/repository/docker/0wens/repository).

## Demo - Live Demo 
[Lien demo](https://tweetcovidheroku.herokuapp.com/)

## Prérequis

1. [Git](https://git-scm.com/book/fr/v2/Démarrage-rapide-Installation-de-Git)
2. [Docker](https://docs.docker.com/get-docker/)

## Installation

#### Créer un dossier pour le projet et s'y placer

```bash
$ mkdir TweetCovid19
$ cd ./TweetCovid19
```

#### Cloner le repository du projet
```bash
$ git clone https://github.com/Milkad0/TweetCovid19.git
```

## Utilisation

#### Se placer dans le dossier du projet
```bash
$ cd ./TweetCovid19
```

#### Récupère l'image depuis le docker hub
```bash
$ docker pull 0wens/tweetcovid-repo:v1
```

#### Lancer le container
```bash
$ docker run -p 8080:8080 0wens/tweetcovid-repo:v1
```

#### Une fois le container lancé, l'interface est accessible depuis l'adresse suivante: http://localhost:8080/


## Deploiement sur Heroku

#### Prérequis

1. Compte gratuit - [Heroku] https://www.heroku.com.
2. Heroku cli - [Heroku CLI] https://devcenter.heroku.com/articles/heroku-cli

#### Générer le package war de l'application (tweet-covid-19-0.1.0.war)
```sh
$ mvn clean package
```

#### Login to Heroku 
```sh
$ heroku login
```

#### Installer Heroku cli deploy plugin
```sh
$ heroku plugins:install heroku-cli-deploy
```

#### Créer votre application sur Heroku
```sh
$ heroku create <"heroku-app-name">
```

#### Déploier l'application sur heroku
```sh
$ heroku war:deploy target/tweet-covid-19-0.1.0.war --app <"heroku-app-name">
```

#### Une fois le déploiement effectué, l'interface est accessible depuis l'adresse https://<"heroku-app-name">.herokuapp.com/


## Validation

#### Vérifier que le container est bien en cours d'execution sur le port 8080
```bash
$ docker container ls
```

#### Acceder à l'ihm local: http://localhost:8080/
Vérifier que les tweets défilent. La vitesse dépend du nombre de tweets en cours.

#### Acceder à l'ihm heroku: https://<"heroku-app-name">.herokuapp.com/
Vérifier que les tweets défilent. La vitesse dépend du nombre de tweets en cours.
