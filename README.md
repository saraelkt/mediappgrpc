# MediaApp gRPC

A microservices-based media application built with Spring Boot and gRPC, demonstrating communication between client and server using Protocol Buffers.

## Overview

This project consists of three Maven modules:

- **proto**: Contains Protocol Buffer definitions and generated gRPC stubs
- **mediaserver**: gRPC server that manages videos and creators
- **mediaclient**: Spring Boot client application that communicates with the server

## Features

### Video Service

- Upload new videos with metadata (title, description, URL, duration)
- Retrieve video information by ID
- Associate videos with creators

### Creator Service

- Get creator information by ID
- Retrieve all videos created by a specific creator

## Technologies

- **Java 21**
- **Spring Boot**
- **gRPC** - High-performance RPC framework
- **Protocol Buffers** - Efficient data serialization
- **Maven** - Dependency management and build tool

## Project Structure

```
mediappgrpc/
├── proto/                          # Protocol Buffer definitions
│   └── src/main/resources/
│       └── schema.proto            # gRPC service definitions
├── mediaserver/                    # gRPC Server
│   └── src/main/java/
│       └── com/exemple/mediaserver/
│           ├── dao/                # Data access layer
│           ├── services/           # gRPC service implementations
│           ├── model/              # Domain models
│           └── mappers/            # Entity mappers
├── mediaclient/                    # gRPC Client
│   └── src/main/java/
│       └── com/exemple/mediaclient/
│           ├── Controllers/        # REST controllers
│           ├── Service/            # Client service layer
│           ├── Dto/                # Data transfer objects
│           └── Mapper/             # DTO mappers
└── pom.xml                         # Parent POM
```

## Prerequisites

- Java 21 or higher
- Maven 3.6+
- Git

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/saraelkt/mediappgrpc.git
cd mediappgrpc
```

### 2. Build the project

Build all modules including Protocol Buffer compilation:

```bash
mvn clean install
```

### 3. Run the Media Server

```bash
cd mediaserver
mvn spring-boot:run
```

The gRPC server will start on port `9090`.

### 4. Run the Media Client

In a new terminal:

```bash
cd mediaclient
mvn spring-boot:run
```

The client application will start and connect to the server at `localhost:9090`.

## gRPC Services

### VideoService

```protobuf
service VideoService {
  rpc uploadVideo (UploadVideoRequest) returns (Video);
  rpc getVideo (VideoIdRequest) returns (Video);
}
```

### CreatorService

```protobuf
service CreatorService {
  rpc getCreator (CreatorIdRequest) returns (Creator);
  rpc getCreatorVideos (CreatorIdRequest) returns (VideoStream);
}
```

## Protocol Buffer Messages

### Video

- `id`: Unique identifier
- `title`: Video title
- `description`: Video description
- `url`: Video URL
- `duration_seconds`: Video duration in seconds
- `creator`: Associated Creator object

### Creator

- `id`: Unique identifier
- `name`: Creator name
- `email`: Creator email

## Configuration

### Server Configuration (mediaserver/application.properties)

```properties
spring.application.name=mediaserver
grpc.server.port=9090
```

### Client Configuration (mediaclient/application.properties)

```properties
spring.application.name=mediaclient
grpc.client.mediaserver.address=static://localhost:9090
grpc.client.mediaserver.negotiationType=plaintext
```

## Testing

Run tests for all modules:

```bash
mvn test
```

## Learn More

- [gRPC Documentation](https://grpc.io/docs/)
- [Protocol Buffers](https://developers.google.com/protocol-buffers)
- [Spring Boot](https://spring.io/projects/spring-boot)

## Author

**saraelkt**

## License

This project is open source and available under the [MIT License](LICENSE).

## Contributing

Contributions, issues, and feature requests are welcome!
