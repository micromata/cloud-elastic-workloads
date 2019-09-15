# Build dockerfile

In the root directory, execute

    docker build -t mlesniak/primes -f mlesniak/Dockerfile .

and the run

    docker run --rm -it -p 3000:3000 mlesniak/primes

Test it with

    http :3000 numbers:='[21]'

    

