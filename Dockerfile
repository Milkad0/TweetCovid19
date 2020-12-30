FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD /target/tweet-covid-19-0.1.0.war tweet-covid-19-0.1.0.war
ENTRYPOINT ["java","-jar","tweet-covid-19-0.1.0.war"]