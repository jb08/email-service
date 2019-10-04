create table homicide_records (
                                      id serial primary key,
                                      county_or_area TEXT,
                                      year varchar(4),
                                      count int,
                                      rate real,
                                      source text,
                                      source_type text
);

create index country_or_area_idx ON homicide_records(county_or_area);
create index year_idx ON homicide_records(year);