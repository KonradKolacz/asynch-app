
# Asynch-app

Simple REST API for asynchronous Tasks Processing.
1. API which allows to create a task, list all tasks, read the status and the results of the task
2. When the task is created the customer receives the unique id of the task
3. The customer can check the status and the results of the task using the received id
4. While one task is executing the next tasks can also be started and processed, API is not blocked.

Task accepts two strings as the parameters: the pattern and the input.
The result of the task is the position of the pattern in the input and the number of the typos
Task find the first best match – position of the pattern in the input with the least number of 
different characters. For example:
• Input: ABCD, pattern: BCD -> position:1, typos:0 (‘BCD’ matches exactly substring on position 1)
• Input: ABCDEFG, pattern: TDD -> position:1, typos:2 (match first - BCD, not CDE)

## Technology stack:
* Java
* Spring Boot
* Maven
* MySQL
* Lombok
* Hibernate

## API Reference

#### Create task

```http
  POST /tasks
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `pattern`      | `string` | **Required**. Task's pattern |
| `input`      | `string` | **Required**. Task's input |

#### Get all tasks

```http
  GET /tasks
```

#### Get task status

```http
  GET /tasks/${id}/status
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Task's id |

#### Get task result

```http
  GET /tasks/${id}/result
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. Task's id |

## System requirments

...
  
## Installation

...
