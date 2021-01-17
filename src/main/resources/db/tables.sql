DROP TABLE IF EXISTS breach.breach_type;
DROP TABLE IF EXISTS breach.breach_method;
DROP TABLE IF EXISTS breach.region;
DROP TABLE IF EXISTS breach.payment_provider;
DROP TABLE IF EXISTS breach.participant;
DROP TABLE IF EXISTS breach.participant_type;


DROP TABLE IF EXISTS breach.breach;
DROP TABLE IF EXISTS breach.person;
DROP TABLE IF EXISTS breach.bank_card;
DROP TABLE IF EXISTS breach.wallet;
DROP TABLE IF EXISTS breach.website;
DROP TABLE IF EXISTS breach.call_data_record;

DROP TABLE IF EXISTS breach.confiscated;
DROP TABLE IF EXISTS breach.phone;
DROP TABLE IF EXISTS breach.sim_card;

DROP TABLE IF EXISTS breach.config;
CREATE TABLE breach.config
(
    id         text,
    value      json
);
ALTER TABLE breach.config OWNER TO postgres;


-- ******************************* dictionary *******************************

DROP TABLE IF EXISTS breach.breach_type;
CREATE TABLE breach.breach_type
(
    id             int not null constraint breach_type_pk primary key,
    name           text
);
ALTER TABLE breach.breach_type OWNER TO postgres;

INSERT INTO breach.breach_type (id, name) VALUES (1, 'Соцінженерія');
INSERT INTO breach.breach_type (id, name) VALUES (2, 'Колл-центр');
INSERT INTO breach.breach_type (id, name) VALUES (3, 'Телефонне шахрайство');
INSERT INTO breach.breach_type (id, name) VALUES (4, 'Піраміда');

DROP TABLE IF EXISTS breach.breach_method;
CREATE TABLE breach.breach_method
(
    id             int not null constraint breach_method_pk primary key,
    breach_type_id int not null,
    name           text
);
ALTER TABLE breach.breach_method OWNER TO postgres;

INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (1, 1, 'Соцінженерія 1');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (2, 1, 'Соцінженерія 2');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (3, 2, 'Колл-центр 1');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (4, 2, 'Колл-центр 2');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (5, 3, 'Телефонне шахрайство 1');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (6, 3, 'Телефонне шахрайство 2');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (7, 4, 'Піраміда 1');
INSERT INTO breach.breach_method (id, breach_type_id, name) VALUES (8, 4, 'Піраміда 2');


DROP TABLE IF EXISTS breach.region;
CREATE TABLE breach.region
(
    id             text,
    name           text
);
ALTER TABLE breach.region OWNER TO postgres;

INSERT INTO breach.region(id, name)
select level1, name
FROM json_to_recordset((select value from breach.config where id = 'koatuu'))
         x(level1 text, level2 text, level3 text, level4 text, category text, name text)
where level2 = '';


DROP TABLE IF EXISTS breach.payment_provider;
CREATE TABLE breach.payment_provider
(
    id             int,
    name           text,
    legal_name     text,
    website        text,
    image          text,
    local          boolean
);
ALTER TABLE breach.payment_provider OWNER TO postgres;

INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (1, 'IBOX', 'ibox.ua', '', true);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (2, 'EsyPay', 'easypay.ua', '', true);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (3, 'Portmone', 'portmone.com.ua', '', true);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (4, 'iPay', 'ipay.ua', '', true);

INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (100, 'Perfect Money', 'perfectmoney.is ', 'perfectmoney.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (101, 'Payeer', 'payeer.com', 'payeer.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (102, 'PayPal', 'paypal.com', 'paypal.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (103, 'WebMoney', 'webmoney.ru', 'webmoney.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (104, 'QIWI', 'qiwi.com', 'qiwi.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (105, 'AdvCash', 'advcash.com', 'advcash.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (106, 'Yandex', 'money.yandex.ru', 'yandex.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (107, 'Payoneer', 'payoneer.com', 'payoneer.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (108, 'Neteller', 'neteller.com', 'neteller.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (109, 'WUnion', 'westernunion.com', 'western union.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (110, 'PaySera', 'paysera.com', 'paysera.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (111, 'Paxum', 'paxum.com', 'paxum.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (112, 'Alipay', 'alipay.com', 'alipay.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (113, 'Capitalist', 'capitalist.net', 'capitalist.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (114, 'AstroPay', 'astropay.com', 'astropay.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (115, 'MoneyGram', 'moneygram.com', 'moneygram.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (116, 'FasaPay', 'fasapay.com', 'fasapay.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (117, 'Paymer', 'paymer.com', 'paymer.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (118, 'Ria Money Transfers', 'riamoneytransfer.com', 'ria.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (119, 'SolidTrust Pay', 'solidtrustpay.com', 'solidtrustpay.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (120, 'Union Pay', 'unionpayintl.com', 'unionpay.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (121, 'Payza', 'payza.com', 'payza.png', false);
INSERT INTO breach.payment_provider (id, name, website, image, local) VALUES (122, 'LiqPay', 'liqpay.com', 'liqpay.png', false);

DROP TABLE IF EXISTS breach.participant_type;
CREATE TABLE breach.participant_type
(
    id                  int    not null constraint participant_type_pk primary key,
    name                text   not null
);
ALTER TABLE breach.participant_type OWNER TO postgres;

INSERT INTO breach.participant_type VALUES (1, 'Заявник');
INSERT INTO breach.participant_type VALUES (2, 'Розшукувана особа');

-- ******************************* dictionary *******************************





DROP TABLE IF EXISTS breach.breach;
CREATE TABLE breach.breach
(
    id                  uuid not null constraint breach_pk primary key,
    head_id             uuid,
    breach_method_id    int not null,
    sent_id             integer,
    sent_number         text,
    sent_date           timestamp,
    income_number       text,
    income_date         timestamp,
    eo_number           text,
    eo_date             timestamp,
    erdr_number         text,
    erdr_date           timestamp,
    erdr_article        text,
    region_id           text not null,
    story               text,
    oper_info           text
);
ALTER TABLE breach.breach OWNER TO postgres;

DROP TABLE IF EXISTS breach.person;
CREATE TABLE breach.person
(
    id                  uuid not null constraint person_pk primary key,
    head_id             uuid,
    passport            text not null,
    last_name           text not null,
    first_name          text not null,
    middle_name         text,
    birth_date          timestamp,
    region_id           text,
    city                text,
    street              text,
    house               text,
    apartment           text,
    email               text,
    image               bytea,
    modified            timestamp not null default CURRENT_TIMESTAMP
);
ALTER TABLE breach.person OWNER TO postgres;

DROP TABLE IF EXISTS breach.participant;
CREATE TABLE breach.participant
(
    id                  uuid not null constraint participant_pk primary key,
    head_id             uuid not null
        constraint participant_breach_id_fk
            references breach.breach,
    participant_type_id int not null
        constraint participant_type_id_fk
            references breach.participant_type,
    person_id           uuid
        constraint participant_person_id_fk
            references breach.person,
    nsrd                int
);
ALTER TABLE breach.participant OWNER TO postgres;







DROP TABLE IF EXISTS breach.payment_move;
CREATE TABLE breach.payment_move
(
    id                  uuid not null constraint payment_move_pk primary key,
    head_id             uuid not null
);
ALTER TABLE breach.payment_move OWNER TO postgres;

INSERT INTO breach.payment_move(id, head_id)
VALUES ('600ddaf3-7b48-4c78-9c20-0c9517c69270', 'a1c907e5-1a4c-418a-aadc-a94f6a49d4f9');
INSERT INTO breach.payment_move(id, head_id)
VALUES ('7bc421ff-110d-4fb2-9e3e-0ab4a4df0869', 'a1c907e5-1a4c-418a-aadc-a94f6a49d4f9');

DROP TABLE IF EXISTS breach.bank_card_payment;
CREATE TABLE breach.bank_card_payment
(
    id                  uuid not null constraint bank_card_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp,
    bank_card_id        uuid not null
);
ALTER TABLE breach.bank_card_payment OWNER TO postgres;

DROP TABLE IF EXISTS breach.wallet_payment;
CREATE TABLE breach.wallet_payment
(
    id                  uuid not null constraint wallet_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp,
    payment_provider_id int not null,
    wallet_number       text
);
ALTER TABLE breach.wallet_payment OWNER TO postgres;

INSERT INTO breach.wallet_payment(id, head_id, date, payment_provider_id, wallet_number)
VALUES ('e2ee192b-5a85-44a4-89f8-4428641048dd', '600ddaf3-7b48-4c78-9c20-0c9517c69270', now(), 1, '123456');


DROP TABLE IF EXISTS breach.website_payment;
CREATE TABLE breach.website_payment
(
    id                  uuid not null constraint website_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp,
    link                text not null
);
ALTER TABLE breach.website_payment OWNER TO postgres;

DROP TABLE IF EXISTS breach.call_data_record;
CREATE TABLE breach.call_data_record
(
    id                  uuid not null constraint call_data_record_pk primary key,
    head_id             uuid not null,
    date                timestamp,
    type                text,
    imsi                text,
    imei                text,
    originator          text,
    terminator          text,
    cell_address        text,
    cell_latitude       float,
    cell_longitude      float,
    cell_azimuth        float
);
ALTER TABLE breach.call_data_record OWNER TO postgres;









DROP TABLE IF EXISTS breach.confiscated;
CREATE TABLE breach.confiscated
(
    id                  uuid not null constraint confiscated_pk primary key,
    head_id             uuid not null,
    date                timestamp,
    region_id           text,
    city                text,
    street              text,
    house               text,
    apartment           text
);
ALTER TABLE breach.confiscated OWNER TO postgres;

DROP TABLE IF EXISTS breach.phone;
CREATE TABLE breach.phone
(
    id                  uuid not null constraint phone_pk primary key,
    head_id             uuid not null,
    imei1               text,
    imei2               text,
    mac                 text,
    holder_id           uuid
);
ALTER TABLE breach.phone OWNER TO postgres;

DROP TABLE IF EXISTS breach.sim_card;
CREATE TABLE breach.sim_card
(
    id                  uuid not null constraint sim_card_pk primary key,
    head_id             uuid not null,
    phone_number        text,
    iccid               text,
    holder_id           uuid
);
ALTER TABLE breach.sim_card OWNER TO postgres;

DROP TABLE IF EXISTS breach.bank_card;
CREATE TABLE breach.bank_card
(
    id                  uuid not null constraint bank_card_pk primary key,
    head_id             uuid not null,
    card_number         text not null,
    bank_name           text,
    holder_id           uuid
);
ALTER TABLE breach.bank_card OWNER TO postgres;




