create table if not exists `expert`
(
    `number`          VARCHAR(50) NULL DEFAULT '' COMMENT 'number',
    `company`           VARCHAR(50) NULL DEFAULT '' COMMENT 'company',
    `name`            VARCHAR(50) NULL DEFAULT '' COMMENT 'name',
    `id`                VARCHAR(50) NOT NULL COMMENT 'id' PRIMARY KEY ,
    `gender`            VARCHAR(50) NULL DEFAULT '' COMMENT 'gender',
    `job_position`      VARCHAR(50) NULL DEFAULT '' COMMENT 'jobPosition',
    `job_title`         VARCHAR(50) NULL DEFAULT '' COMMENT 'jobTitle',
    `type`            VARCHAR(50) NULL DEFAULT '' COMMENT 'type',
    `area`              VARCHAR(50) NULL DEFAULT '' COMMENT 'area',
    `introduction`      VARCHAR(50) NULL DEFAULT '' COMMENT 'introduction',
    `phone`             VARCHAR(50) NULL DEFAULT '' COMMENT 'phone',
    `secret`            VARCHAR(50) NULL DEFAULT '' COMMENT 'secret',
    `secret_level`      VARCHAR(50) NULL DEFAULT '' COMMENT 'secretLevel',
    `address`           VARCHAR(50) NULL DEFAULT '' COMMENT 'address',
    `postcode`          VARCHAR(50) NULL DEFAULT '' COMMENT 'postcode',
    `recommend_company` VARCHAR(50) NULL DEFAULT '' COMMENT 'recommendCompany',
    `filler`            VARCHAR(50) NULL DEFAULT '' COMMENT 'filler',
    `filler_contact`    VARCHAR(50) NULL DEFAULT '' COMMENT 'fillerContact',
    `is_blocked`        VARCHAR(50) NULL DEFAULT '否' COMMENT 'isBlocked'
);

CREATE TABLE if not exists `program`
(
    id           VARCHAR(50) NOT NULL COMMENT 'id' PRIMARY KEY ,
    area         VARCHAR(50) NULL DEFAULT '' COMMENT 'area',
    secret       VARCHAR(50) NULL DEFAULT '' COMMENT 'secret',
    secret_level VARCHAR(50) NULL DEFAULT '' COMMENT 'secretLevel',
    keyword      VARCHAR(50) NULL DEFAULT '' COMMENT 'keyword',
    start_time   DATETIME    NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'startTime',
    end_time     DATETIME    NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'endTime',
    company      VARCHAR(1024) NULL DEFAULT '' COMMENT 'company',
    state        INT         NULL DEFAULT 0,
    number_tech  INT         NULL DEFAULT 0,
    number_mng   INT         NULL DEFAULT 0,
    number_acc  INT         NULL DEFAULT 0
);

CREATE TABLE if not exists `record`
(
    id           VARCHAR(50) NULL COMMENT 'id' primary key ,
    expert_i_d   VARCHAR(50) NULL DEFAULT '' COMMENT 'expertID',
    expert_name  VARCHAR(50) NULL DEFAULT '' COMMENT 'expertName',
    `type`       VARCHAR(50) NULL DEFAULT '' COMMENT 'type',
    expert_area  VARCHAR(50) NULL DEFAULT '' COMMENT 'expertArea',
    phone        VARCHAR(50) NULL DEFAULT '' COMMENT 'phone',
    company      VARCHAR(1024) NULL DEFAULT '' COMMENT 'company',
    secret       VARCHAR(50) NULL DEFAULT '' COMMENT 'secret',
    secret_level VARCHAR(50) NULL DEFAULT '' COMMENT 'secretLevel',
    program_i_d  VARCHAR(50) NULL DEFAULT '' COMMENT 'programID',
    start_time   DATETIME    NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'startTime',
    end_time     DATETIME    NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'endTime',
    `comment`    VARCHAR(50) NULL DEFAULT '' COMMENT 'comment',
    score        INT         NULL DEFAULT 0
);
