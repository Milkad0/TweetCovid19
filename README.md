# TweetCovid19

Par [Vincent ETHEVE](mailto:vincent.etheve@edu.ece.fr), [Quentin BENOIST](mailto:quentin.benoist@edu.ece.fr)

Ce projet est [...]

Le but de ce projet a donc été de construire une architecture distribuée, conteneurisée autour d'une application de [...]


## Explications

schéma [...]

Pour réaliser ce projet nous avons d'abord [...]

Enfin nous avons developpé un [...]

Une fois ces applications developpées nous les avons dockerisés et avons push les images sur notre [Docker Hub](https://hub.docker.com/repository/docker/0wens/repository).

Puis pour finir nous avons réalisé un Dockerfile réalisant les actions suivantes :
- Mise en place [...]

L'installation a été rendue le plus simple possible avec le Dockerfile, il n'y a rien à initialiser, seulement à clone ce dépôt github et lancer le container docker.

## Prérequis

- [Git](https://git-scm.com/book/fr/v2/Démarrage-rapide-Installation-de-Git)
- [Docker](https://docs.docker.com/get-docker/)

## Installation

Créer un dossier pour le projet et s'y placer

```bash
mkdir TweetCovid19
cd ./TweetCovid19
```

Cloner le repository du projet

```bash
git clone https://github.com/Milkad0/TweetCovid19.git
```

## Utilisation

Se placer dans le dossier du projet
```bash
cd ./TweetCovid19
```

Récupère l'image depuis le docker hub
```bash
docker pull 0wens/repository:1.0
```

Lancer le container
```bash
docker run -p 8080:8080 0wens/repository:1.0
```

Une fois le container lancé, l'interface est accessible depuis l'adresse http://localhost:8080/

## Validation

Vérifier que le container est bien en cours d'execution sur le port 8080
```bash
docker container ls
```

Acceder à l'ihm : http://localhost:8080/

Vérifier que les tweets défile bien. La vitesse dépend du nombre de tweets en cours.
