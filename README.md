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