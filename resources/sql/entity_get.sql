-- name: get-kingdom--userid
select kingdomid, userid, name, raceid
from kingdom
where userid = :userid::int


-- name: get-kingdom--name
select kingdomid, userid, name, raceid
from kingdom
where name = :name


-- name: get-village--kingdomid
select villageid, name, parcelid, kingdomid
from village
where kingdomid = :kingdomid::int


-- name: get-unit-group--villageid
select groupid, parcelid, villageid
from unit_group
where villageid = :villageid::int


--name: get-unit--unitid
select unitid, groupid, amount, unittypeid
from unit
where unitid = :unitid::int


--name: get-unit--groupid
select unitid, groupid, amount, unittypeid
from unit
where groupid = :groupid::int
