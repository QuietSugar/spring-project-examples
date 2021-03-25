CREATE TABLE if not exists dev_user
(
    id         INTEGER      NOT NULL AUTO_INCREMENT,
    name       VARCHAR(128) NOT NULL,
    age        int          NOT NULL,
    createTime Date,
    updateTime Date,
    PRIMARY KEY (id)
);
