# BallClock Assignment

Mode 1 (Cycle Days)
The first mode takes a single parameter specifying the number of balls and reports the number of balls given in the input and the number of days (24-hour periods) which elapse before the clock returns to its initial ordering.

An input of 30 yields the following output:

`30 balls cycle after 15 days.`

`Completed in x milliseconds (y.yyy seconds)`

An input of 45 yields the following output:

`45 balls cycle after 378 days.`

`Completed in x milliseconds (y.yyy seconds)`

Mode 2 (Clock State)
The second mode takes two parameters, the number of balls and the number of minutes to run for. If the number of minutes is specified, the clock must run to the number of minutes and report the state of the tracks at that point in a JSON format. An input of 30 325 yields the following output:

`{"Min":[],"FiveMin":[22,13,25,3,7],"Hour":[6,12,17,4,15],"Main":
[11,5,26,18,2,30,19,8,24,10,29,20,16,21,28,1,23,14,27,9]}`

Run with Maven:

`mvn clean compile`

`mvn exec:java -Dexec.mainClass="clockFiles.BallClockRun"`

Output:

`Enter m to count minutes or enter d to count days`

at this step you need to choose what will be counted

if you enter m:

`We will count : minutes`

`Please, enter minutes count`

required to enter minutes-> 5

`Please, enter balls count` -> 27

Output:

`Ok, we will count value for 5 minutes and 27 balls
{"Minutes":[],"FiveMin":[1],"Hours":[],"Main":[2,6,3,9,8,4,14,16,18,20,10,7,5,23,15,27,17,26,19,25,21,22,11,24,12,13]}`

if you enter d:

`We will count : days`

`Please, enter balls count` ->27

`27 balls cycle after 13 days.`

`Completed in 5 milliseconds (0.005 seconds)`