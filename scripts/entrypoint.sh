#!/bin/bash
java -jar /project/target/optimization-algorithm-1.0-SNAPSHOT.jar >> data.txt $*
python3 /project/scripts/viz.py $*

mkdir -p /project/results
mv out /project/results
mv *.gif /project/results
mv data.txt /project/results

ls -al /project/results