-- name: get-all-user-data
-- Get all user info for logins
select userid, name, email, password
from users
where email = :email
limit 1;


-- name: enter-new-user!
-- register a new user
insert into users (email, name, password) values (:email, :name, :password);


