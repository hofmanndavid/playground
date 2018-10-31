select t0.id, t0.name, t0.doc_number, t0.status, t0.registered, t0.billing_address_id
from customer t0
left join address t1 on t1.id = t0.billing_address_id
where exists (select 1 from message x where x.customer_id = t0.id)
and (
  t0.id > ?
    or (
      lower(t0.name) like ? escape'|'  and lower(t1.city) like ? escape'|'
    )
)