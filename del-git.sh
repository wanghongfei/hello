#!/bin/bash

# This script is used to remove ".git" directory resursively in the current directory
# author: Neo Smith(王鸿飞)
# 2014.4.27

del_git() {
    # traverse directories in current path
	for file in `ls -a`; do
		# ignore . and ..
		if [ "." == $file -o ".." == $file ]; then
			continue
		fi

		# delete ".git" directory
		if [ ".git" == "$file" ]; then
			echo "delete `pwd`/$file"
			rm -rf $file
		else
			if [ -d $file ]; then
				cd $file
				del_git
			fi
		fi
	done
	
	cd ..
}

del_git
