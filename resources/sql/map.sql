--name: get-whole-map--
select p.parcelid, p.x, p.y, pt.parceltype
from parcel p
join parcel_data pd on p.parcelid = pd.parcelid
join parcel_types pt on pd.parceltypeid = pt.parceltypeid


