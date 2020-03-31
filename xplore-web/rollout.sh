#!/usr/bin/env bash

#Prebuild
rm -rf dist
yarn build

#Build
version=`node -p "require('./package.json').version"`
image=abimbola/xplore-web:$version
docker build -t $image .

#Auth
docker login

#Push
docker push $image

#Deploy
ssh -i xplore.pem ubuntu@godis.org.uk "docker pull $image"
ssh -i xplore.pem ubuntu@godis.org.uk "docker service update --image $image xplore-web"
