# JayaTechChallenge


This challenge tries to emulate a money conversion between 2 currencies. With this you can:

- Convert 2 currencies (Currently the base currency allowed is only EUR)
- Check all currencies converted.

## How to run

### This api is able to run on docker!

- Build the docker image, for example:  *docker build -t "jayachallenge" .*
- Run with: *docker run -p 8000:8080 jayachallenge*  (this one here might be on any port you want)
- The link to the swagger using the example with the ports above will be on: http://localhost:8000/swagger-ui/

### We are on heroku too!

- You can access the challenge in https://jayatechchallenge.herokuapp.com/swagger-ui/ .


## What is missing:

The base currency base for now only allows to be EUR. The destination can be BRL, USD, EUR, JPY (You can check swagger for more information);

There aren't many unit tests and there are not any integration tests.

- Improving the response and error handling from endpoints
- Refactoring some weird classes
- The swagger could have more information.
