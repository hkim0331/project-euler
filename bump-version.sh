#!/bin/sh

if [ -z $1 ]; then
  echo "usage: $0 <version>"
  exit
fi

if [ -e /usr/local/bin/gsed ]; then
  SED='/usr/local/bin/gsed -E'
else
  SED=/usr/bin/sed
fi

${SED} -i "s/uploader .*$/uploader \"$1\"/" project.clj
${SED} -i "s/uploader-.+-stand/uploader-$1-stand/" run.sh