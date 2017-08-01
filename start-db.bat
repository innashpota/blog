docker run -p 5432:5432 -e POSTGRES_USER=blog -e POSTGRES_PASSWORD=blog -e POSTGRES_DB=blog --name blog_db blog:blog-db

docker run -it --rm -p 8080:8080 --name blog_web --link blog_db  blog:blog-web