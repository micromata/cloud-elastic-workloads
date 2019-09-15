FROM node:10
WORKDIR /usr/src/app

COPY prime-factor-reference/*.js ./
COPY prime-factor-reference/package.json ./
RUN npm install

EXPOSE 3000
CMD [ "node", "index.js" ]

