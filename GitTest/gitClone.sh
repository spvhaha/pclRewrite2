#!/bin/bash


# settings / change this to your config
remoteHost=ssh://git@srv.dev.telushealth.com:7999/hns/health-hns-bpcs-esb.git
#https://srv.dev.telushealth.com/bitbucket/projects/HNS/repos/health-hns-bpcs-esb/
#remoteHost/hns/"
remoteUser=t850124
remoteDir="~/health-hns-bpcs-esb/"
remoteRepos=$(ssh -l $remoteUser $remoteHost "ls $remoteDir")
localCodeDir="C:/escrow/20200312"
#ssh://git@srv.dev.telushealth.com:7999/hns/health-hns-batches.git
# if no output from the remote ssh cmd, bail out
#if [ -z "$remoteRepos" ]; then
#    echo "No results from remote repo listing (via SSH)"
#    exit
#fi

# for each repo found remotely, check if it exists locally
# assumption: name repo = repo.git, to be saved to repo (w/o .git)
# if dir exists, skip, if not, clone the remote git repo into it
for gitRepo in $remoteRepos
do
  localRepoDir=$(echo ${localCodeDir}${gitRepo}|cut -d'.' -f1)
  if [ -d $localRepoDir ]; then 	
		echo -e "Directory $localRepoDir already exits, skipping ...\n"
	else
		cloneCmd="git clone ssh -J git@srv.dev.telushealth.com:7999 -l t850124"
		#cloneCmd=$cloneCmd"$gitRepo $localRepoDir"
		cloneCmd=$remoteHost$remoteUser
		
		cloneCmdRun=$($cloneCmd 2>&1)

		echo -e "Running: \n$ $cloneCmd"
		echo -e "${cloneCmdRun}\n\n"
	fi
done
#git clone ssh://git@srv.dev.telushealth.com:7999/hns/health-hns-bpcs-esb.gitgit 

