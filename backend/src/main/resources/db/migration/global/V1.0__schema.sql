--
-- locales
--
CREATE TABLE IF NOT EXISTS locales
(
    id       BIGINT PRIMARY KEY,
    version  BIGINT             NOT NULL DEFAULT 0,
    name     VARCHAR(60) UNIQUE NOT NULL,
    code     VARCHAR(5) UNIQUE  NOT NULL,
    fallback BOOLEAN            NOT NULL DEFAULT FALSE
);

CREATE UNIQUE INDEX ON locales (fallback) WHERE fallback;

--
-- persons
--
CREATE TABLE IF NOT EXISTS persons
(
    id           BIGINT PRIMARY KEY,
    version      BIGINT       NOT NULL DEFAULT 0,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    address_one  VARCHAR(255) NOT NULL,
    address_two  VARCHAR(255),
    zip_code     VARCHAR(6)   NOT NULL CHECK (LENGTH(zip_code) = 6),
    city         VARCHAR(60)  NOT NULL,
    phone_number VARCHAR(9)   NOT NULL CHECK (LENGTH(phone_number) = 9)
);

CREATE SEQUENCE IF NOT EXISTS seq_persons_id;

--
-- users
--
CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT PRIMARY KEY,
    version       BIGINT       NOT NULL DEFAULT 0,
    email         VARCHAR(255) NOT NULL UNIQUE CHECK (LENGTH(email) > 0),
    password_hash VARCHAR(97)  NOT NULL CHECK (LENGTH(password_hash) = 97),
    active        BOOLEAN      NOT NULL DEFAULT FALSE,
    person_id     BIGINT       NOT NULL UNIQUE REFERENCES persons (id)
);

CREATE SEQUENCE IF NOT EXISTS seq_users_id;

--
-- verification_token_types
--
CREATE TABLE IF NOT EXISTS verification_token_types
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    name    VARCHAR(60) UNIQUE NOT NULL
);

--
-- verification_tokens
--
CREATE TABLE IF NOT EXISTS verification_tokens
(
    id              BIGINT PRIMARY KEY,
    version         BIGINT                   NOT NULL DEFAULT 0,
    code            UUID                     NOT NULL DEFAULT uuid_generate_v4(),
    expiration_date TIMESTAMP WITH TIME ZONE NOT NULL,
    type_id         BIGINT                   NOT NULL REFERENCES verification_token_types (id),
    user_id         BIGINT                   NOT NULL REFERENCES users (id)
);

CREATE SEQUENCE IF NOT EXISTS seq_verification_tokens_id;

--
-- roles
--
CREATE TABLE IF NOT EXISTS roles
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    name    VARCHAR(60) UNIQUE NOT NULL
);

--
-- role_descriptions
--
CREATE TABLE IF NOT EXISTS role_descriptions
(
    id          BIGINT PRIMARY KEY,
    version     BIGINT       NOT NULL DEFAULT 0,
    description VARCHAR(255) NOT NULL,
    locale_id   BIGINT       NOT NULL REFERENCES locales (id),
    role_id     BIGINT       NOT NULL REFERENCES roles (id),
    UNIQUE (locale_id, role_id)
);

--
-- users_roles
--
CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id BIGINT NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);

--
-- files
--
CREATE TABLE IF NOT EXISTS files
(
    id           BIGINT PRIMARY KEY,
    version      BIGINT       NOT NULL DEFAULT 0,
    uuid         UUID         NOT NULL DEFAULT uuid_generate_v4(),
    name         VARCHAR(255) NOT NULL,
    content_type VARCHAR(255) NOT NULL,
    content      BYTEA        NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_files_id;

--
-- company_branches
--
CREATE TABLE IF NOT EXISTS company_branches
(
    id           BIGINT PRIMARY KEY,
    version      BIGINT          NOT NULL DEFAULT 0,
    name         VARCHAR(60)     NOT NULL,
    address      VARCHAR(255)    NOT NULL,
    latitude     NUMERIC(17, 15) NOT NULL CHECK (latitude BETWEEN -90 AND 90),
    longitude    NUMERIC(17, 15) NOT NULL CHECK (longitude BETWEEN -180 AND 180),
    email        VARCHAR(255)    NOT NULL CHECK (LENGTH(email) > 0),
    phone_number VARCHAR(9) CHECK (LENGTH(phone_number) = 9)
);

CREATE SEQUENCE IF NOT EXISTS seq_company_branches_id;

--
-- product_categories
--
CREATE TABLE IF NOT EXISTS product_categories
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    code    VARCHAR(60) UNIQUE NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_product_categories_id;

--
-- product_category_names
--
CREATE TABLE IF NOT EXISTS product_category_names
(
    id                  BIGINT PRIMARY KEY,
    version             BIGINT       NOT NULL DEFAULT 0,
    name                VARCHAR(128) NOT NULL,
    locale_id           BIGINT       NOT NULL REFERENCES locales (id),
    product_category_id BIGINT       NOT NULL REFERENCES product_categories (id),
    UNIQUE (locale_id, product_category_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_product_category_names_id;

--
-- product_category_descriptions
--
CREATE TABLE IF NOT EXISTS product_category_descriptions
(
    id                  BIGINT PRIMARY KEY,
    version             BIGINT       NOT NULL DEFAULT 0,
    description         VARCHAR(255) NOT NULL,
    locale_id           BIGINT       NOT NULL REFERENCES locales (id),
    product_category_id BIGINT       NOT NULL REFERENCES product_categories (id),
    UNIQUE (locale_id, product_category_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_product_category_descriptions_id;

--
-- products
--
CREATE TABLE IF NOT EXISTS products
(
    id          BIGINT PRIMARY KEY,
    version     BIGINT               NOT NULL DEFAULT 0,
    category_id BIGINT               NOT NULL REFERENCES product_categories (id),
    price       NUMERIC(12, 2)       NOT NULL CHECK (price >= 0),
    image_id    BIGINT REFERENCES files (id),
    limited     BOOLEAN DEFAULT TRUE NOT NULL,
    quantity    INT     DEFAULT 0    NOT NULL CHECK (quantity >= 0)
);

CREATE SEQUENCE IF NOT EXISTS seq_products_id;

--
-- product_names
--
CREATE TABLE IF NOT EXISTS product_names
(
    id         BIGINT PRIMARY KEY,
    version    BIGINT       NOT NULL DEFAULT 0,
    name       VARCHAR(128) NOT NULL,
    locale_id  BIGINT       NOT NULL REFERENCES locales (id),
    product_id BIGINT       NOT NULL REFERENCES products (id),
    UNIQUE (locale_id, product_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_product_names_id;

--
-- product_descriptions
--
CREATE TABLE IF NOT EXISTS product_descriptions
(
    id          BIGINT PRIMARY KEY,
    version     BIGINT       NOT NULL DEFAULT 0,
    description VARCHAR(255) NOT NULL,
    locale_id   BIGINT       NOT NULL REFERENCES locales (id),
    product_id  BIGINT       NOT NULL REFERENCES products (id),
    UNIQUE (locale_id, product_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_product_descriptions_id;

--
-- discounts
--
CREATE TABLE IF NOT EXISTS discounts
(
    id              BIGINT PRIMARY KEY,
    version         BIGINT             NOT NULL DEFAULT 0,
    code            VARCHAR(60) UNIQUE NOT NULL,
    value           INT                NOT NULL CHECK (value BETWEEN 1 AND 100),
    expiration_date TIMESTAMP WITH TIME ZONE,
    active          BOOLEAN            NOT NULL DEFAULT TRUE
);

CREATE SEQUENCE IF NOT EXISTS seq_discounts_id;

--
-- discount_descriptions
--
CREATE TABLE IF NOT EXISTS discount_descriptions
(
    id          BIGINT PRIMARY KEY,
    version     BIGINT       NOT NULL DEFAULT 0,
    description VARCHAR(255) NOT NULL,
    locale_id   BIGINT       NOT NULL REFERENCES locales (id),
    discount_id BIGINT       NOT NULL REFERENCES discounts (id),
    UNIQUE (locale_id, discount_id)
);

CREATE SEQUENCE IF NOT EXISTS seq_discount_descriptions_id;

--
-- delivery_types
--
CREATE TABLE IF NOT EXISTS delivery_types
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    code    VARCHAR(60) UNIQUE NOT NULL
);

--
-- delivery_type_names
--
CREATE TABLE IF NOT EXISTS delivery_type_names
(
    id               BIGINT PRIMARY KEY,
    version          BIGINT       NOT NULL DEFAULT 0,
    name             VARCHAR(128) NOT NULL,
    locale_id        BIGINT       NOT NULL REFERENCES locales (id),
    delivery_type_id BIGINT       NOT NULL REFERENCES delivery_types (id),
    UNIQUE (locale_id, delivery_type_id)
);

--
-- delivery_type_descriptions
--
CREATE TABLE IF NOT EXISTS delivery_type_descriptions
(
    id               BIGINT PRIMARY KEY,
    version          BIGINT       NOT NULL DEFAULT 0,
    description      VARCHAR(255) NOT NULL,
    locale_id        BIGINT       NOT NULL REFERENCES locales (id),
    delivery_type_id BIGINT       NOT NULL REFERENCES delivery_types (id),
    UNIQUE (locale_id, delivery_type_id)
);

--
-- order_statuses
--
CREATE TABLE IF NOT EXISTS order_statuses
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    code    VARCHAR(60) UNIQUE NOT NULL
);

--
-- order_status_names
--
CREATE TABLE IF NOT EXISTS order_status_names
(
    id              BIGINT PRIMARY KEY,
    version         BIGINT       NOT NULL DEFAULT 0,
    name            VARCHAR(128) NOT NULL,
    locale_id       BIGINT       NOT NULL REFERENCES locales (id),
    order_status_id BIGINT       NOT NULL REFERENCES order_statuses (id),
    UNIQUE (locale_id, order_status_id)
);

--
-- order_status_descriptions
--
CREATE TABLE IF NOT EXISTS order_status_descriptions
(
    id              BIGINT PRIMARY KEY,
    version         BIGINT       NOT NULL DEFAULT 0,
    description     VARCHAR(255) NOT NULL,
    locale_id       BIGINT       NOT NULL REFERENCES locales (id),
    order_status_id BIGINT       NOT NULL REFERENCES order_statuses (id),
    UNIQUE (locale_id, order_status_id)
);

--
-- orders
--
CREATE TABLE IF NOT EXISTS orders
(
    id                 BIGINT PRIMARY KEY,
    version            BIGINT                   NOT NULL DEFAULT 0,
    placement_date     TIMESTAMP WITH TIME ZONE NOT NULL,
    status_change_date TIMESTAMP WITH TIME ZONE,
    subtotal_price     NUMERIC(12, 2)           NOT NULL CHECK (total_price >= 0),
    total_price        NUMERIC(12, 2)           NOT NULL CHECK (total_price >= 0),
    discount_id        BIGINT REFERENCES discounts (id),
    address            VARCHAR(255)             NOT NULL,
    latitude           NUMERIC(17, 15)          NOT NULL CHECK (latitude BETWEEN -90 AND 90),
    longitude          NUMERIC(17, 15)          NOT NULL CHECK (latitude BETWEEN -180 AND 180),
    notes              VARCHAR(255),
    invoice_id         BIGINT UNIQUE REFERENCES files (id),
    company_branch_id  BIGINT                   NOT NULL REFERENCES company_branches (id),
    delivery_type_id   BIGINT                   NOT NULL REFERENCES delivery_types (id),
    status_id          BIGINT                   NOT NULL REFERENCES order_statuses (id),
    user_id            BIGINT                   NOT NULL REFERENCES users (id)
);

CREATE SEQUENCE IF NOT EXISTS seq_orders_id;

--
-- orders_products
--
CREATE TABLE IF NOT EXISTS orders_products
(
    order_id    BIGINT         NOT NULL REFERENCES orders (id),
    product_id  BIGINT         NOT NULL REFERENCES products (id),
    version     BIGINT         NOT NULL DEFAULT 0,
    quantity    INT            NOT NULL CHECK (quantity >= 0),
    total_price NUMERIC(12, 2) NOT NULL CHECK (total_price >= 0),
    PRIMARY KEY (order_id, product_id)
);

--
-- payment_types
--
CREATE TABLE IF NOT EXISTS payment_types
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    code    VARCHAR(60) UNIQUE NOT NULL,
    active  BOOLEAN            NOT NULL DEFAULT TRUE
);

--
-- payment_type_names
--
CREATE TABLE IF NOT EXISTS payment_type_names
(
    id              BIGINT PRIMARY KEY,
    version         BIGINT       NOT NULL DEFAULT 0,
    name            VARCHAR(128) NOT NULL,
    locale_id       BIGINT       NOT NULL REFERENCES locales (id),
    payment_type_id BIGINT       NOT NULL REFERENCES payment_types (id),
    UNIQUE (locale_id, payment_type_id)
);

--
-- payment_statuses
--
CREATE TABLE IF NOT EXISTS payment_statuses
(
    id      BIGINT PRIMARY KEY,
    version BIGINT             NOT NULL DEFAULT 0,
    code    VARCHAR(60) UNIQUE NOT NULL
);

--
-- payment_status_names
--
CREATE TABLE IF NOT EXISTS payment_status_names
(
    id                BIGINT PRIMARY KEY,
    version           BIGINT       NOT NULL DEFAULT 0,
    name              VARCHAR(128) NOT NULL,
    locale_id         BIGINT       NOT NULL REFERENCES locales (id),
    payment_status_id BIGINT       NOT NULL REFERENCES payment_statuses (id),
    UNIQUE (locale_id, payment_status_id)
);

--
-- payment_status_descriptions
--
CREATE TABLE IF NOT EXISTS payment_status_descriptions
(
    id                BIGINT PRIMARY KEY,
    version           BIGINT       NOT NULL DEFAULT 0,
    description       VARCHAR(255) NOT NULL,
    locale_id         BIGINT       NOT NULL REFERENCES locales (id),
    payment_status_id BIGINT       NOT NULL REFERENCES payment_statuses (id),
    UNIQUE (locale_id, payment_status_id)
);

--
-- payments
--
CREATE TABLE IF NOT EXISTS payments
(
    id                 BIGINT PRIMARY KEY,
    version            BIGINT                   NOT NULL DEFAULT 0,
    deadline           TIMESTAMP WITH TIME ZONE NOT NULL,
    status_change_date TIMESTAMP WITH TIME ZONE,
    type_id            BIGINT                   NOT NULL REFERENCES payment_types (id),
    status_id          BIGINT                   NOT NULL REFERENCES payment_statuses (id),
    iban               VARCHAR(34),
    swift_code         VARCHAR(11),
    order_id           BIGINT                   NOT NULL REFERENCES orders (id)
);

CREATE SEQUENCE IF NOT EXISTS seq_payments_id;
