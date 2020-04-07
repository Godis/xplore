FROM node:13.12.0
COPY . /app
WORKDIR /app
#RUN npm rebuild node-sass
RUN yarn
RUN yarn build
ENTRYPOINT ["node", "server.js"]
EXPOSE 8000

