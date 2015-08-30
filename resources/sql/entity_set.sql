-- name: insert-kingdom!
insert into kingdom (name, raceid, userid)
values (:name, :raceid::int, :userid::int);


