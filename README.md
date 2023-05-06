AUTHENTICATION

/api/auth/register [POST]
{
    "username": "thanh",
    "firstname": "thanh",
    "lastname": "thanh",
    "email": "thanh@mail.com",
    "password": "123456"
}

/api/auth/authenticate [POST]
{
    "email": "thanh@mail.com",
    "password": "123456"
}

/api/auth/refresh-token [POST]

-----------------------------
CATEGORY

/api/category/create [POST]
{
    "title": "C#",
    "content": "C#",
    "slug": "c-sharp"
}

/api/category/delete/{id} [DELETE]

/api/category/getAll [GET]

/api/category/update/{id} [PUT]
{
    "id": 2,
    "title": "C#",
    "content": "C#",
    "slug": "c-sharp"
}

-----------------------------

TAG

/api/tag/getAll [GET]

/api/tag/create [POST]
{
    "title": "Spring data Jpa",
    "content": "Spring data Jpa",
    "slug": "spring-data-jpa"
}

/api/tag/update/{id} [PUT]
{
    "id": 1,
    "title": "Spring data Jpa",
    "content": "Spring data Jpa",
    "slug": "spring-data-jpa"
}

/api/tag/delete/{id} [DELETE]

----------------------------

POST

/api/post/create [POST]
{
    "title": "Spring data Jpa",
    "metaTitle": "metaTitle",
    "slug": "spring-data-jpa",
    "summary": "summary",
    "content": "Spring data Jpa",
    "categories":[
        1
    ],
    "tags":[
        1
    ]
}

/api/post/update/{id} [PUT]
{
    "id" : 1,
    "title": "Spring data Jpa",
    "metaTitle": "metaTitle",
    "slug": "spring-data-jpa",
    "summary": "summary",
    "content": "Spring data Jpa",
    "categories":[
        1
    ],
    "tags":[
        1
    ]
}

/api/post/update-published/{id} [PUT]
{
    "id" : 1,
    "currentPublished": true,
    "newPublished": false
}

/api/post/posts?pageSize=5&pageNumber=0&category=java [GET]

/api/post/posts?pageSize=5&pageNumber=0&tags=spring-data-jpa [GET]

/api/post/{postId} [GET]

/api/post/delete/{id} [DELETE]

---------------------
POST COMMENT

/api/comment/create [POST]
{
    "postId": 1,
    "content": "Day la comment"
}

/api/comment/update/{id} [PUT]
{
    "id": 1,
    "postId": 1,
    "content": "Day la comment"
}

/api/comment/{postId} [GET]

/api/comment/delete/{id} [DELETE]

-----------------------

USER

/api/user/{username}/posts [GET]

/api/user/{username}/comments [GET]

