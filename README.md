Problem Statement
==================
You are given a folder containing multiple text files.
Your task is to design and implement a Java program to process these files and dynamically find the top nth and bottom nth most frequent word across all the files.

Requirements
=============
*  Read all the files from the specified folder.
*  Process the content of each file efficiently to compute word frequencies.
*  Implement a mechanism to retrieve the nth most frequent word dynamically.

CURL command
=============
curl -X POST -F 'frequency=1' http://localhost:8090/kafka/publish

Docker Commands
================
eval $(minikube docker-env)
docker build -t wordoccurrenceanalyzer:1.0 .

docker images
cd dkube
kubectl apply -f application-deployment.yml
kubectl get deployments
kubectl get pods
kubectl logs {pod name}

kubectl apply -f application-service.yml
kubectl get svc/service

kubectl get nodes -o wide
192.168.49.2:31974

minikube dashboard
