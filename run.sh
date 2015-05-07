#!/usr/bin/env bash
#
# Usage:
#        run.sh cardserver
#   -or-
#        run.sh chatserver
#   -or- 
#        run.sh reader
# 
#

runcardserver()
{
	echo running jAccess Card-Server
	java -jar bin:libs/CardServer.jar jAccessCardServer
}

runchatserver()
{
  echo running jAccess Chat-Server
  java -jar bin:libs/ChatServer.jar jAccessChatServer
}

runreader()
{
	echo running jAccess Reader
	java -jar bin:libs/Reader.jar jAccessReader
}


if [[ $1 == reader ]]; then
	RUN=runreader
elif [[ $1 == cardserver ]]; then
	RUN=runcardserver
elif [[ $1 == chatserver ]]; then
  RUN=runchatserver
else
	echo "jAccess usage: $0 [reader|cardserver|chatserver]"
	exit 1
fi

build
$RUN
