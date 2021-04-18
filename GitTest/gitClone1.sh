#!/bin/bash
# import multiple remote git repositories to local CODE dir

set -e

# settings / change this to your config
remoteHost="srv.dev.telushealth.com"
remoteUser=t850124
remoteDir="hns"
localRSA="C:/Users/t850124/.ssh/id_rsa.pub"
remoteRepos=$(ssh -i $localRSA $remoteHost "ls $remoteDir")

localCodeDir="C:/escrow/20200312"

# if no output from the remote ssh cmd, bail out
if [ -z "$remoteRepos" ]; then
    echo "No results from remote repo listing (via SSH)"
    exit
fi

# for each repo found remotely, check if it exists locally
# assumption: name repo = repo.git, to be saved to repo (w/o .git)
# if dir exists, skip, if not, clone the remote git repo into it
for gitRepo in $remoteRepos
do
  localRepoDir=$(echo ${localCodeDir}${gitRepo}|cut -d'.' -f1)
  if [ -d $localRepoDir ]; then 	
		echo -e "Directory $localRepoDir already exits, skipping ...\n"
	else
		cloneCmd="git clone ssh://$remoteUser@$remoteHost/$remoteDir"
		cloneCmd=$cloneCmd"$gitRepo $localRepoDir"
		
		cloneCmdRun=$($cloneCmd 2>&1)

		echo -e "Running: \n$ $cloneCmd"
		echo -e "${cloneCmdRun}\n\n"
	fi
done
