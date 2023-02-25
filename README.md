# TaskUzKassa

<h4>Login url is not showing in swagger</h4>
<h6>URL for login: [http://localhost:8183/api/v1/auth/sign-in](http://localhost:8183/api/v1/auth/sign-in)</h6>
```
curl --location --request POST 'http://localhost:8183/api/v1/auth/sign-in' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "*******",
    "password": "*******"
}'
```