# MCP Server using Spring Boot Java

Spring Boot와 Java 21로 구축된 Model Context Protocol(MCP) 서버 구현입니다.   
이 프로젝트는 AI 어시스턴트를 위한 사용자 관리 도구를 제공하는 MCP 서버를 만드는 방법을 보여줍니다.

## Features

- Spring AI를 사용하여 MCP 서버 기능을 구현합니다.
- MCP를 통해 사용자 관리 도구를 노출합니다.
- 동기식 통신 모드를 사용합니다.
- 표준 입출력(Standard I/O) 및 서버 전송 이벤트(Server-Sent Events, SSE) 전송 방식을 지원합니다.
- MCP Server 데이터 통신을 위해 Homework API와 통합됩니다.

## Technologies

- Java 20
- Spring Boot 3.4.4
- Spring AI (Model Context Protocol)
- Project Lombok
- Gradle

## Getting Started

### Prerequisites

- Java 20
- Gradle

### Installation

1. Clone the repository

```bash
git clone https://github.com/yourusername/demo-mcp.git
cd demo-mcp
```

2. Build the project

```bash
mvn clean package
```

3. Run the application

```bash
java -jar target/mcp-spring-java-0.0.1-SNAPSHOT.jar
```

## Configuration

MCP 서버는 `application.yml` 파일에서 설정됩니다:

- 서버는 8090 포트에서 실행됩니다.
- 서버 이름: `homework-server`
- 동기식 통신 모드를 사용합니다.
- 터미널 기반 통신을 위한 STDIO 전송 방식을 지원합니다.
- SSE(Server-Sent Events) 통신을 위한 `/mcp/message` 엔드포인트를 제공합니다.

## Available Tools

서버는 다음과 같은 사용자 관리 도구들을 제공합니다:

- `saveHomework` - 숙제 저장
- `findAllHomework` - 숙제 일괄 조회
- `updateHomework` - 숙제 정보 수정
- `deleteHomework` - 숙제 삭제

## Testing with Postman

Postman을 사용하여 MCP 서버의 엔드포인트를 테스트할 수 있습니다:

1. MCP 서버를 시작합니다.
2. Postman을 열고 새 요청을 생성합니다:

    - **Method(메서드)**: POST
    - **URL**: http://localhost:8090/mcp/message
    - **Headers(헤더)**:
        - Content-Type: application/json
        - Accept: application/json

3. 요청 본문(Request Body)에는 다음 JSON 형식을 사용하여 사용 가능한 도구들을 호출할 수 있습니다:

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-123",
        "name": "[TOOL_NAME]",
        "parameters": {
          "[PARAMETER_NAME]": "[PARAMETER_VALUE]"
        }
      }
    ]
  }
}
```

### Example Requests

#### 전체 숙제 조회

```json
{
  "message": {
    "toolCalls": [
      {
        "id": "call-1234",
        "name": "findAllHomework",
        "parameters": {}
      }
    ]
  }
}
```

응답은 도구 실행 결과를 포함하는 JSON 객체를 반환합니다.

## Usage with MCP Clients

다음 구성 정보를 사용하여 MCP 클라이언트 애플리케이션에서 이 서버를 설정할 수 있습니다:

```json
{
  "mcpServers": {
    "homework-server": {
      "command": "java",
      "args": [
        "-Dspring.ai.mcp.server.stdio=true",
        "-Dspring.main.web-application-type=none",
        "-Dlogging.pattern.console=",
        "-jar",
        "<저장 경로>/module-server-0.0.1-SNAPSHOT.jar
      ]
    }
  }
}
```

## 참고사이트

- [MCP 공식 사이트](https://modelcontextprotocol.io)
- [MCP 서버 Quick Start](https://modelcontextprotocol.io/quickstart/server)
- [MCP 서버 Quick Start(Java)](https://modelcontextprotocol.io/sdk/java/mcp-server)
