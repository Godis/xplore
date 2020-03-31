FROM node:10.16.0
RUN npm install -g serve
COPY dist/ /app/dist
ENTRYPOINT ["serve", "-l", "8000", "/app/dist"]
EXPOSE 8000

