# blogAPI

Welcome to **blogAPI**! This API is built using **Java Spring Boot** and **MySQL** as part of my Java training. Follow the guide below to explore the available endpoints.



## Endpoints and Responses

### GET /posts
**Description:** Retrieve all existing posts.

- **Success:** Returns a **200 OK** status with an array of posts. ✅
- **Error:** Returns a **500 Internal Server Error** status if something goes wrong. ❌

---

### GET /posts?term=searchTerm
**Description:** Search for posts by a term that may appear in the post’s category, content, or title.

- **Success:** Returns a **200 OK** status with an array of matching posts. ✅
- **Error:** Returns a **500 Internal Server Error** status if something goes wrong. ❌

---

### GET /posts/{id}
**Description:** Retrieve a specific post by its unique identifier.

- **Success:** Returns a **200 OK** status with the requested post details. ✅
- **No Content:** Returns a **404 Not Found** status if the post doesn’t exist. 🔍
- **Error:** Returns a **400 Bad Request** status if the request is malformed. ❌

---

### POST /posts
**Description:** Create a new post. The request body must include the following information:

```json
{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```

- **Success:** Returns a **201 Created** status when the post is successfully added. 🎉
- **Error:** Returns a **400 Bad Request** status with an error message if the provided data is invalid. ❌

---

### PUT /posts/{id}
**Description:** Update an existing post. At least one of the following fields must be provided:

```json
{
  "title": "My First Blog Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": ["Tech", "Programming"]
}
```

- **Success:** Returns a **200 OK** status when the post is successfully updated. ✅
- **No Content:** Returns a **404 Not Found** status if the post doesn’t exist. 🔍
- **Error:** Returns a **400 Bad Request** status with an error message if the request is invalid. ❌

---

### DELETE /posts/{id}
**Description:** Delete the post with the given identifier.

- **Success:** Returns a **204 No Content** status if the deletion is successful. ✅
- **No Content:** Returns a **404 Not Found** status if the post doesn’t exist. 🔍
- **Error:** Returns a **500 Internal Server Error** status if something goes wrong. ❌


## Credits

This project was inspired by [Roadmap.sh](https://roadmap.sh/projects/blogging-platform-api). 

Feel free to explore!