create view v_report_acc_type AS
SELECT _id, name, datetime, from_account_currency_id, SUM(from_amount)as from_amount, to_account_currency_id, SUM(to_amount) as to_amount, original_currency_id, SUM(original_from_amount) as original_from_amount, is_transfer
from(
select 
	   case when p.type ='CNB' then 1
		when p.type ='CREDIT_CARD' then 2
		when p.type ='IOU' then 3
		when p.type ='LTI' then 4
		when p.type ='MMF' then 5
		when p.type ='SPF' then 6 else 10 end as _id,
       p.type as name,    
       t.datetime as datetime,
       t.from_account_currency_id as from_account_currency_id,
       t.from_amount as from_amount,
       t.to_account_currency_id as to_account_currency_id,
       t.to_amount as to_amount,
       1 as is_transfer,
	   t.original_currency_id as original_currency_id,
	   t.original_from_amount as original_from_amount,
       t.from_account_id as from_account_id,
       t.to_account_id as to_account_id,
       t.category_id as category_id,
       t.category_left as category_left,
       t.category_right as category_right,
       t.project_id as project_id,
       t.location_id as location_id,
       t.payee_id as payee_id,
       t.status as status
from account p
inner join v_blotter_for_account t on t.from_account_id=p._id
where p._id != 0 and t.from_account_is_include_into_totals=1)
GROUP BY _id, name, datetime, from_account_currency_id, to_account_currency_id, original_currency_id, is_transfer 