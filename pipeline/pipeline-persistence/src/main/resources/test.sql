SELECT 
  p.id,
  p.name,
  p.birth_date,
  p.type_id,
  p.owner_id, 
FROM 
  Pets p
WHERE
  p.name = :name
FETCH FIRST 10 ROWS ONLY
