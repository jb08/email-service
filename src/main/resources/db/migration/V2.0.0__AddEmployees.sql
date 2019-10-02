create table demo.departments
(
    id  serial not null constraint departments_pkey primary key,
    name text not null constraint departments_name_key unique
);

create table demo.employees
(
    id            bigserial not null constraint employee_pkey primary key,
    name          text      not null,
    job_title     text      not null,
    full_time     boolean   not null,
    salaried      boolean   not null,
    typical_hours real,
    annual_salary numeric constraint salary_positive check (annual_salary > (0)::numeric),

    hourly_rate   numeric,
    dept_id       integer   not null constraint employee_dept_id_fkey references demo.departments on delete cascade,

    constraint part_time_has_hours
        check (((NOT salaried) AND (typical_hours IS NOT NULL)) OR (salaried AND (typical_hours IS NULL))),
    constraint salaried_has_salary
        check ((salaried AND (annual_salary IS NOT NULL) AND (hourly_rate IS NULL)) OR
               ((NOT salaried) AND (annual_salary IS NULL) AND (hourly_rate IS NOT NULL)))
);

create index if not exists dept_id_index on demo.employees (dept_id);



