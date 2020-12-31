# TweetCovid19

Par [Vincent ETHEVE](mailto:vincent.etheve@edu.ece.fr), [Quentin BENOIST](mailto:quentin.benoist@edu.ece.fr)

Ce projet est [...]

Le but de ce projet a donc été de construire une architecture distribuée, conteneurisée autour d'une application de [...]


## Explications

Pour réaliser ce projet nous avons d'abord [...]

Enfin nous avons developpé un [...]

Une fois ces applications developpées nous les avons dockerisés et avons push les images sur notre [Docker Hub](https://hub.docker.com/repository/docker/0wens/repository).

Puis pour finir nous avons réalisé un Dockerfile réalisant les actions suivantes :
- Mise en place [...]

L'installation a été rendue le plus simple possible avec le Dockerfile, il n'y a rien à initialiser, seulement à clone ce dépôt github et lancer le container docker.

## Prérequis

1. [Git](https://git-scm.com/book/fr/v2/Démarrage-rapide-Installation-de-Git)
2. [Docker](https://docs.docker.com/get-docker/)

## Installation

####Créer un dossier pour le projet et s'y placer

```bash
$ mkdir TweetCovid19
$ cd ./TweetCovid19
```

####Cloner le repository du projet
```bash
$ git clone https://github.com/Milkad0/TweetCovid19.git
```

## Utilisation

####Se placer dans le dossier du projet
```bash
$ cd ./TweetCovid19
```

####Récupère l'image depuis le docker hub
```bash
$ docker pull 0wens/repository:1.0
```

####Lancer le container
```bash
$ docker run -p 8080:8080 0wens/repository:1.0
```

####Une fois le container lancé, l'interface est accessible depuis l'adresse http://localhost:8080/


## Deploiement sur Heroku

#### Prérequis:

1. Compte gratuit - https://www.heroku.com.
2. heroku cli est installé - https://devcenter.heroku.com/articles/heroku-cli

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

####Une fois le déploiement effectué, l'interface est accessible depuis l'adresse https://<"heroku-app-name">.herokuapp.com/


## Validation

####Vérifier que le container est bien en cours d'execution sur le port 8080
```bash
$ docker container ls
```

####Acceder à l'ihm local: http://localhost:8080/
Vérifier que les tweets défilent. La vitesse dépend du nombre de tweets en cours.

####Acceder à l'ihm heroku: https://<"heroku-app-name">.herokuapp.com/
Vérifier que les tweets défilent. La vitesse dépend du nombre de tweets en cours.
