# Swarm REST API

Welcome to the Swarm REST API project. This is a Spring Web application that serves as a backend REST API.

## Requirements:

Before you begin, ensure you have met the following requirements:

- Docker is installed on your machine. Docker will handle the Java and Gradle dependencies for you, so there's no need to install them separately. If you don't have Docker installed, please follow the installation guide for [Docker](https://docs.docker.com/get-docker/).

- **Windows Users** make sure you run the following command to set the line endings to match the *nix style to prevent issues with the Docker build:

```bash
git config --global core.autocrlf input
```

## Running The Application

1. **Clone The Repository** Open a terminal and run the following command to clone the source repository:

```bash
git clone https://github.com/LittleTechBookClub/swarm-rest-api.git

```

Navigate to the project directory:
```bash
cd swarm-rest-api
```

2. **Start the Application with Docker Compose** Run the following command to build and start the application along with any necessary services:
```bash
docker compose up
```

**Note for Linux users:** Depending on your Docker setup, you might need to prepend `sudo` to the above command:
```bash
sudo docker compose up
```

3. **Accessing The Application*** You should be able to curl or check this url in browser: http://localhost:8080
and see "Hello Swarm!"

## Shutting Down

To stop and remove the containers, use the following command:
```bash
docker compose down
```

**Linux Users** If you used `sudo` to start the application, make sure to use it here as well.

**For Mac users:** As with starting the application, there is typically no need to use `sudo`.
