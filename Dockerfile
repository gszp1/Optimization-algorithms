FROM ubuntu:latest

RUN apt-get update -y
RUN apt-get install -y openjdk-8-jdk
RUN apt-get install -y maven

RUN mkdir -p project
WORKDIR /project
COPY pom.xml /project
COPY src /project/src

RUN mvn install

RUN apt-get install -y python3
RUN apt-get install -y python3-pip
RUN pip3 install matplotlib
RUN pip3 install sympy
RUN pip3 install imageio

COPY scripts /project/scripts

ENTRYPOINT ["/project/scripts/entrypoint.sh"]