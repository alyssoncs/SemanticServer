CREATE TABLE IF NOT EXISTS holder(
    holder_id BIGSERIAL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS device(
    device_uuid     VARCHAR(40),
    fk_holder_id    BIGINT,
    name            VARCHAR(16) NOT NULL,
    description     VARCHAR(255),

    PRIMARY KEY (device_uuid),
    FOREIGN KEY (fk_holder_id) REFERENCES holder(holder_id)
        ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS thing(
    fk_device_uuid VARCHAR(40),

    PRIMARY KEY (fk_device_uuid),
    FOREIGN KEY (fk_device_uuid) REFERENCES device(device_uuid)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mhub(
    fk_device_uuid VARCHAR(40),

    PRIMARY KEY (fk_device_uuid),
    FOREIGN KEY (fk_device_uuid) REFERENCES device(device_uuid)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS person(
    email           VARCHAR(255),
    fk_holder_id    BIGINT UNIQUE NOT NULL ,
    short_name      VARCHAR(16) NOT NULL,
    full_name       VARCHAR(255),

    PRIMARY KEY (email),
    FOREIGN KEY (fk_holder_id) REFERENCES holder(holder_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS role(
    role_name VARCHAR(255) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS person_role(
    fk_holder_id    BIGINT,
    fk_role_name    VARCHAR(255),

    PRIMARY KEY (fk_holder_id, fk_role_name),
    FOREIGN KEY (fk_holder_id) REFERENCES person(fk_holder_id)
        ON DELETE CASCADE,
    FOREIGN KEY (fk_role_name) REFERENCES role(role_name)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS physical_space(
    fk_holder_id                    BIGINT,
    fk_parent_physical_space_id     BIGINT,
    name                            VARCHAR(15) NOT NULL,
    description                     VARCHAR(255),

    PRIMARY KEY (fk_holder_id),
    FOREIGN KEY (fk_holder_id) REFERENCES holder(holder_id),
    FOREIGN KEY (fk_parent_physical_space_id) REFERENCES physical_space(fk_holder_id)
        ON DELETE CASCADE
);