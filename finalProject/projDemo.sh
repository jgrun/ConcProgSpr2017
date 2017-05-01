#!/bin/bash

echo No Lock Executions:
java test1
java test1
java test1
java test1
java test1
java test1
java test1
java test1
java test1
java test1
echo ---------------------------------------
echo
echo Single Thread Coarse Locking Executions:
java test
java test
java test
java test
java test
java test
java test
java test
java test
java test
echo ---------------------------------------
echo
echo Thirty Thread Coarse Locking Executions:
java test2
java test2
java test2
java test2
java test2
java test2
java test2
java test2
java test2
java test2
