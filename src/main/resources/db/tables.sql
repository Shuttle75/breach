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


DROP TABLE IF EXISTS breach.dictionary;
CREATE TABLE breach.dictionary
(
    id                  int not null,
    link                text,
    name                text
);
ALTER TABLE breach.dictionary OWNER TO postgres;
INSERT INTO breach.dictionary VALUES (1, 'dictionary/breach-method', 'Спосіб вчинення');
INSERT INTO breach.dictionary VALUES (2, 'dictionary/participant-type', 'Тип участі особи');
INSERT INTO breach.dictionary VALUES (3, 'dictionary/prison', 'Тюрми');
INSERT INTO breach.dictionary VALUES (4, 'dictionary/payment-provider', 'Платіжна система');

DROP TABLE IF EXISTS breach.config;
CREATE TABLE breach.config
(
    id         text,
    value      json
);
ALTER TABLE breach.config OWNER TO postgres;


-- ******************************* dictionary *******************************
DROP TABLE IF EXISTS breach.prison;
CREATE TABLE breach.prison
(
    id                  int not null constraint prison_pk primary key,
    name                text,
    code                text,
    cell_latitude       float,
    cell_longitude      float
);
ALTER TABLE breach.prison OWNER TO postgres;
INSERT INTO breach.prison VALUES (1, 'Державна установа «Одеська установа виконання покарань (№ 21)»','8564162',30.727396,46.4439);
INSERT INTO breach.prison VALUES (2, 'Державна установа «Ізмаїльська установа виконання покарань (№ 22)»', '8564179', 28.837906, 45.352653);
INSERT INTO breach.prison VALUES (3, 'Державна установа «Одеська виправна колонія (№ 14)»', '8564127', 30.732013, 46.4479);
INSERT INTO breach.prison VALUES (4, 'Державна установа «Південна виправна колонія (№ 51)»', '8564133', 30.728242, 46.447238);
INSERT INTO breach.prison VALUES (5, 'Державна установа «Миколаївський слідчий ізолятор»', '8564067', 31.981857, 46.981679);
INSERT INTO breach.prison VALUES (6, 'Державна установа «Снігурівська виправна колонія (№ 5)- спеціалізована туберкульозна лікарня»', '8594654', 32.473172, 46.905287);
INSERT INTO breach.prison VALUES (7, 'Державна установа «Ольшанська виправна колонія (№ 53)»', '8564073', 31.763557, 47.16697);
INSERT INTO breach.prison VALUES (8, 'Державна установа «Вознесенська виправна колонія (№ 72)»', '8564050', 31.304509, 47.696496);
INSERT INTO breach.prison VALUES (9, 'Державна установа «Арбузинська виправна колонія (№ 83)»', '8564038', 31.14804, 47.84191);
INSERT INTO breach.prison VALUES (10, 'Державна установа «Казанківська виправна колонія (№ 93)»', '8564044', 32.801172, 47.964053);
INSERT INTO breach.prison VALUES (11, 'Державна установа "Миколаївський пункт тимчасового перебування  іноземців та осіб без громадянства, які незаконно перебувають в Україні, ДМС України"', '40121316', 31.275943, 47.467599);
INSERT INTO breach.prison VALUES (12, 'Державна установа «Херсонський слідчий ізолятор»', '8564707', 32.628015, 46.639397);
INSERT INTO breach.prison VALUES (13, 'Державна установа «Голопристанська виправна колонія (№ 7) – міжобласна спеціалізована туберкульозна лікарня»', '8564663', 32.39962, 46.472828);
INSERT INTO breach.prison VALUES (14, 'Державна установа «Дар’ївська виправна колонія (№ 10)»', '8564676', 32.770178, 46.761895);
INSERT INTO breach.prison VALUES (15, 'Державна установа «Білозерська виправна колонія (№ 105)»', '8564713', 32.770178, 46.761895);
INSERT INTO breach.prison VALUES (16, 'Державна установа «Херсонська виправна колонія (№ 61) - міжобласна спеціалізована туберкульозна лікарня»', '8564682', 32.628601, 46.638672);
INSERT INTO breach.prison VALUES (17, 'Державна установа «Північна виправна колонія (№ 90)»', '8564699', 32.592114, 46.686024);
INSERT INTO breach.prison VALUES (18, 'Керченська виправна колонія (№ 126)', '8563872', 36.436207, 45.344431);
INSERT INTO breach.prison VALUES (19, 'Державна установа «Дніпровська установа виконання покарань (№ 4)»', '14316882', 35.008181, 48.454908);
INSERT INTO breach.prison VALUES (20, 'Державна установа «Криворізька установа виконання покарань (№ 3)»', '14316899', 33.399839, 48.083668);
INSERT INTO breach.prison VALUES (21, 'Державна установа «Солонянська виправна колонія (№ 21)»', '8562921', 34.78399, 48.2217);
INSERT INTO breach.prison VALUES (22, 'Державна установа «Жовтоводська виправна колонія (№ 26)»', '8562950', 33.504612, 48.365374);
INSERT INTO breach.prison VALUES (23, 'Державна установа «Кам’янська виправна колонія (№ 34)»', '8562967', 34.560987, 48.491824);
INSERT INTO breach.prison VALUES (24, 'Державна установа «Широківський виправний центр (№ 75)»', '14316876', 33.48207, 47.715142);
INSERT INTO breach.prison VALUES (25, 'Державна установа «Криворізька виправна колонія (№ 80)»', '8562915', 33.391843, 47.910505);
INSERT INTO breach.prison VALUES (26, 'Державна установа «Дніпровська виправна колонія (№ 89)»', '8562909', 34.939882, 48.46646);
INSERT INTO breach.prison VALUES (27, 'Державна установа «Синельниківська виправна колонія (№ 94)»', '8562989', 35.355938, 48.320497);
INSERT INTO breach.prison VALUES (28, 'Державна установа «П’ятихатська виправна колонія (№ 122)»', '8733073', 33.720457, 48.35694);
INSERT INTO breach.prison VALUES (29, 'Державна установа «Ігренський виправний центр (№ 133)»', '8563027', 35.183514, 48.470457);
INSERT INTO breach.prison VALUES (30, 'Донецька установа виконання покарань (№ 5)', '8563205', 37.801188, 47.986095);
INSERT INTO breach.prison VALUES (31, 'Державна установа «Бахмутська установа виконання покарань (№ 6)»', '8563211', 37.989493, 48.594149);
INSERT INTO breach.prison VALUES (32, 'Державна установа «Маріупольський слідчий ізолятор»', '8563228', 37.519476, 47.184525);
INSERT INTO breach.prison VALUES (33, 'Державна установа «Торецька виправна колонія (№ 2)»', '8563091', 37.859155, 48.392007);
INSERT INTO breach.prison VALUES (34, 'Жданівська виправна колонія (№ 3)', '8563101', 38.310883, 48.112697);
INSERT INTO breach.prison VALUES (35, 'Торезька виправна колонія (№ 28)', '8563122', 38.524203, 48.061502);
INSERT INTO breach.prison VALUES (36, 'Макіївська виправна колонія (№ 32)', '8563139', 37.96669, 48.045956);
INSERT INTO breach.prison VALUES (37, 'Кіровська виправна колонія (№ 33)', '8563145', 37.966162, 48.051211);
INSERT INTO breach.prison VALUES (38, 'Єнакієвська виправна колонія (№ 52)', '8563151', 38.271761, 48.261232);
INSERT INTO breach.prison VALUES (39, 'Мічурінська виправна колонія (№ 57)', '8563240', 37.786898, 48.016522);
INSERT INTO breach.prison VALUES (40, 'Державна установа «Селидівська виправна колонія (№ 82)»', '8563174', 37.397606, 47.99155);
INSERT INTO breach.prison VALUES (41, 'Західна виправна колонія (№ 97)', '8563062', 37.937247, 47.961709);
INSERT INTO breach.prison VALUES (42, 'Державна установа «Приазовська виправна колонія (№ 107)»', '14316907', 37.574224, 47.177306);
INSERT INTO breach.prison VALUES (43, 'Волноваська виправна колонія (№ 120)', '14319082', 37.71954, 47.826916);
INSERT INTO breach.prison VALUES (44, 'Донецька виправна колонія (№ 124)', '8563257', 39.945186, 48.328432);
INSERT INTO breach.prison VALUES (45, 'Державна установа «Маріупольський виправний центр (№ 138)»', '8563197', 37.514787, 47.18973);
INSERT INTO breach.prison VALUES (46, 'Державна установа «Запорізький слідчий ізолятор»', '8563553', 35.176796, 47.819898);
INSERT INTO breach.prison VALUES (47, 'Державна установа «Вільнянська установа виконання покарань (№ 11)»', '14319099', 34.409248, 47.486308);
INSERT INTO breach.prison VALUES (48, 'Державна установа «Дружелюбівський виправний центр (№ 1)»', '8594542', 35.374861, 47.894324);
INSERT INTO breach.prison VALUES (49, 'Державна установа «Веселівський виправний центр (№ 8)»', '14316913', 34.897537, 46.875332);
INSERT INTO breach.prison VALUES (50, 'Державна установа «Вільнянська виправна колонія (№ 20)»', '8563493', 35.426566, 47.937112);
INSERT INTO breach.prison VALUES (51, 'Державна установа «Софіївська виправна колонія (№ 55)»', '8563501', 35.422332, 47.934796);
INSERT INTO breach.prison VALUES (52, 'Державна установа «Бердянська виправна колонія (№ 77)»', '8563518', 36.767119, 46.79044);
INSERT INTO breach.prison VALUES (53, 'Державна установа «Оріхівська виправна колонія (№ 88)»', '8563524', 35.89269, 47.535455);
INSERT INTO breach.prison VALUES (54, 'Державна установа «Біленьківська виправна колонія (№ 99)»', '8563530', 35.038547, 47.62284);
INSERT INTO breach.prison VALUES (55, 'Державна установа «Кам''янська виправна колонія (№ 101)»', '8563560', 35.387338, 47.874583);
INSERT INTO breach.prison VALUES (56, 'Державна установа «Старобільський слідчий ізолятор»', '8562832', 38.900394, 49.270594);
INSERT INTO breach.prison VALUES (57, 'Брянківська виправна колонія (№ 11)', '8562743', 38.665538, 48.505671);
INSERT INTO breach.prison VALUES (58, 'Луганський виправний центр (№ 134)', '33381690', 38.665538, 48.505671);
INSERT INTO breach.prison VALUES (59, 'Алчевська виправна колонія (№ 13)', '8562750', 38.824459, 48.489671);
INSERT INTO breach.prison VALUES (60, 'Перевальська виправна колонія (№ 15)', '8562766', 38.819617, 48.435697);
INSERT INTO breach.prison VALUES (61, 'Комісарівська виправна колонія (№ 22)', '8562789', 38.529974, 48.38752);
INSERT INTO breach.prison VALUES (62, 'Чорнухинська виправна колонія (№ 23)', '8562795', 38.515537, 48.32638);
INSERT INTO breach.prison VALUES (63, 'Петровська виправна колонія (№ 24)', '8562803', 38.875804, 48.297549);
INSERT INTO breach.prison VALUES (64, 'Свердловська виправна колонія (№ 38)', '8562814', 39.56114, 48.076577);
INSERT INTO breach.prison VALUES (65, 'Червонопартизанська виправна колонія (№ 68)', '8562861', 39.795537, 48.077848);
INSERT INTO breach.prison VALUES (66, 'Державна установа «Сумський слідчий ізолятор»', '8565090', 34.75223, 50.895168);
INSERT INTO breach.prison VALUES (67, 'Державна установа «Роменська виправна колонія (№ 56)»', '8565078', 33.593569, 50.678068);
INSERT INTO breach.prison VALUES (68, 'Державна установа «Шосткинська виправна колонія (№ 66)»', '8565084', 33.57245, 51.843979);
INSERT INTO breach.prison VALUES (69, 'Державна установа «Сумська виправна колонія (№ 116)»', '8565115', 34.743305, 50.897194);
INSERT INTO breach.prison VALUES (70, 'Державна установа «Харківська установа виконання покарань (№ 27)»', '8564587', 36.195446, 49.985492);
INSERT INTO breach.prison VALUES (71, 'Державна установа «Покровська виправна колонія (№ 17) – спеціалізована туберкульозна лікарня»', '8564618', 36.71208, 49.531208);
INSERT INTO breach.prison VALUES (72, 'Державна установа «Олексіївська виправна колонія (№ 25)»', '8564541', 36.188622, 50.055899);
INSERT INTO breach.prison VALUES (73, 'Державна установа «Темнівська виправна колонія (№ 100)»', '8733162', 36.363803, 49.799196);
INSERT INTO breach.prison VALUES (74, 'Державна установа «Полтавська установа виконання покарань (№ 23)»', '8564297', 34.541004, 49.589257);
INSERT INTO breach.prison VALUES (75, 'Державна установа «Машівська виправна колонія (№ 9)»', '8564251', 34.72655, 49.382309);
INSERT INTO breach.prison VALUES (76, 'Державна установа «Божковська виправна колонія (№ 16)»', '8564268', 34.755206, 49.671849);
INSERT INTO breach.prison VALUES (77, 'Державна установа «Полтавська виправна колонія (№ 64)»', '8731826', 34.627189, 49.615002);
INSERT INTO breach.prison VALUES (78, 'Державна установа «Кременчуцька виправна колонія (№ 69)»', '8564274', 33.474374, 49.0703);
INSERT INTO breach.prison VALUES (79, 'Державна установа «Житомирська установа виконання покарань (№ 8)»', '8563369', 28.687934, 50.272056);
INSERT INTO breach.prison VALUES (80, 'Державна установа «Житомирська виправна колонія (№ 4)»', '8563323', 28.687934,50.272056);
INSERT INTO breach.prison VALUES (81, 'Державна установа «Бердичівська виправна колонія (№ 70)»', '8563330', 28.615433, 49.893169);
INSERT INTO breach.prison VALUES (82, 'Державна установа «Коростенська виправна колонія (№ 71)»', '8563346', 28.582344, 50.940709);
INSERT INTO breach.prison VALUES (83, 'Державна установа «Райківська виправна колонія (№ 73)»', '8563352', 49.977778, 28.509444);
INSERT INTO breach.prison VALUES (84, 'Державна установа «Бердичівський виправний центр (№ 108)»', '8563398', 28.610658, 49.893707);
INSERT INTO breach.prison VALUES (85, 'Державна установа «Київський слідчий ізолятор»', '8563694', 30.477728, 50.461162);
INSERT INTO breach.prison VALUES (86, 'Державна установа «Білоцерківська виправна колонія (№ 35)»', '8563665', 30.125038, 49.810684);
INSERT INTO breach.prison VALUES (87, 'Державна установа «Бучанська виправна колонія (№ 85)»', '8563671', 30.217907, 50.574831);
INSERT INTO breach.prison VALUES (88, 'Державна установа «Березанська виправна колонія (№ 95)»', '8563688', 31.464706, 50.276343);
INSERT INTO breach.prison VALUES (89, 'Державна установа «Кагарлицька виправна колонія (№ 115)»', '14316936', 30.910751, 49.973771);
INSERT INTO breach.prison VALUES (90, 'Державна установа «Бориспільська виправна колонія (№ 119)»', '8563719', 30.867029, 50.304819);
INSERT INTO breach.prison VALUES (91, 'Державна установа «Черкаський слідчий ізолятор»', '8564883', 32.069644, 49.432678);
INSERT INTO breach.prison VALUES (92, 'Державна установа «Черкаська виправна колонія (№ 62)»', '8564854', 32.031243, 49.384249);
INSERT INTO breach.prison VALUES (93, 'Державна установа «Старобабанівська виправна колонія (№ 92)»', '8564877', 30.358437, 48.847369);
INSERT INTO breach.prison VALUES (94, 'Державна установа «Чернігівський слідчий ізолятор»', '8564966', 31.297796, 51.487194);
INSERT INTO breach.prison VALUES (95, 'Державна установа «Hовгород-Сіверська установа виконання покарань (№ 31)»', '8564972', 33.257276, 52.011185);
INSERT INTO breach.prison VALUES (96, 'Державна установа «Вінницька установа виконання покарань (№ 1)»', '8562602', 28.502886, 49.231955);
INSERT INTO breach.prison VALUES (97, 'Державна установа «Ладижинська виправна колонія (№ 39)»', '8562565', 29.301365, 48.616424);
INSERT INTO breach.prison VALUES (98, 'Державна установа «Піщанська виправна колонія (№ 59)»', '8562571', 28.806393, 48.193185);
INSERT INTO breach.prison VALUES (99, 'Державна установа «Стрижавська виправна колонія (№ 81)»', '8562588', 28.474619, 49.312389);
INSERT INTO breach.prison VALUES (100, 'Державна установа «Вінницька виправна колонія (№ 86)»', '8562594', 28.505665, 49.235226);
INSERT INTO breach.prison VALUES (101, 'Державна установа «Крижопільський виправний центр (№ 113)»', '14316862', 28.685691, 48.372039);
INSERT INTO breach.prison VALUES (102, 'Державна установа «Могилів-Подільська виправна колонія (№ 114)»', '8733082', 27.899418, 48.510316);
INSERT INTO breach.prison VALUES (103, 'Державна установа «Літинська виправна колонія (№ 123)»', '8562625', 28.082315, 49.323098);
INSERT INTO breach.prison VALUES (104, 'Державна установа «Хмельницький слідчий ізолятор»', '8564794', 26.977071, 49.425298);
INSERT INTO breach.prison VALUES (105, 'Державна установа «Ізяславська виправна колонія (№ 31)»', '8594759', 26.819632, 50.129444);
INSERT INTO breach.prison VALUES (106, 'Державна установа «Замкова виправна колонія (№ 58)»', '8564771', 26.817799, 50.128774);
INSERT INTO breach.prison VALUES (107, 'Державна установа «Райківецька виправна колонія (№ 78)»', '8564765', 26.744613, 49.331818);
INSERT INTO breach.prison VALUES (108, 'Державна установа «Шепетівська виправна колонія (№ 98)»', '8564788', 27.115752, 50.239842);
INSERT INTO breach.prison VALUES (109, 'Державна установа «Чернівецька установа виконання покарань (№ 33)»', '8565032', 25.932058, 48.287351);
INSERT INTO breach.prison VALUES (110, 'Державна установа «Сокирянська виправна колонія (№ 67)»', '8565026', 27.412621, 48.443409);
INSERT INTO breach.prison VALUES (111, 'Чернігівський пункт тимчасового перебування іноземців та осіб без громадянства, які незаконно перебувають в Україні ДМС України', '26493974', 30.735577, 51.652413);
INSERT INTO breach.prison VALUES (112, 'Державна установа «Софіївська виправна колонія (№ 45)»', '8562973', 33.808418, 48.233535);
INSERT INTO breach.prison VALUES (113, 'Державна установа «Луцький слідчий ізолятор»', '8562683', 25.325412, 50.747304);
INSERT INTO breach.prison VALUES (114, 'Державна установа «Маневицька виправна колонія (№ 42)»', '8562660', 25.530117, 51.290345);
INSERT INTO breach.prison VALUES (115, 'Державна установа «Цуманська виправна колонія (№ 84)»', '8594507', 25.875469, 50.832784);
INSERT INTO breach.prison VALUES (116, 'Державна установа «Закарпатська установа виконання покарань (№ 9)»', '8563441', 22.291678, 48.625003);
INSERT INTO breach.prison VALUES (117, 'Державна установа «Івано-Франківська установа виконання покарань (№ 12)»', '8563624', 24.709539, 48.915212);
INSERT INTO breach.prison VALUES (118, 'Державна установа «Коломийська виправна колонія (№ 41)»', '8563613', 24.88489, 48.585252);
INSERT INTO breach.prison VALUES (119, 'Державна установа «Галицька виправна колонія (№ 128)»', '8563636', 24.854552, 49.037802);
INSERT INTO breach.prison VALUES (120, 'Державна установа «Львівська установа виконання покарань (№ 19)»', '8563978', 24.0226, 49.84352);
INSERT INTO breach.prison VALUES (121, 'Державна установа «Личаківська виправна колонія (№ 30)»', '8563932', 23.98396, 49.856564);
INSERT INTO breach.prison VALUES (122, 'Державна установа «Дрогобицька виправна колонія (№ 40)»', '8563918', 23.50999, 49.339512);
INSERT INTO breach.prison VALUES (123, 'Державна установа «Сокальська виправна колонія (№ 47)»', '8563949', 24.256758, 50.464984);
INSERT INTO breach.prison VALUES (124, 'Державна установа «Львівська виправна колонія (№ 48)»', '8681175', 24.021725, 49.800722);
INSERT INTO breach.prison VALUES (125, 'Державна установа «Миколаївська виправна колонія (№ 50)»', '14316971', 23.948572, 49.552707);
INSERT INTO breach.prison VALUES (126, 'Державна установа «Рівненський слідчий ізолятор»', '8564400', 26.227748, 50.610097);
INSERT INTO breach.prison VALUES (127, 'Державна установа «Катеринівська виправна колонія (№ 46)»', '8564363', 26.626058, 51.211577);
INSERT INTO breach.prison VALUES (128, 'Державна установа «Полицька виправна колонія (№ 76)»', '8564370', 26.040639, 51.240626);
INSERT INTO breach.prison VALUES (129, 'Державна установа «Городищенська виправна колонія (№ 96)»', '8564386', 26.364925, 50.640667);
INSERT INTO breach.prison VALUES (130, 'Державна установа «Городоцький виправний центр (№ 131)»', '8575616', 26.192583, 50.683051);
INSERT INTO breach.prison VALUES (131, 'Державна установа «Чортківська установа виконання покарань (№ 26)»', '8564481', 25.789363, 49.019294);
INSERT INTO breach.prison VALUES (132, 'Державна установа «Копичинська виправна колонія (№ 112)»', '8564475', 25.897684, 49.116685);
INSERT INTO breach.prison VALUES (133, 'Державна установа «Кропивницька установа виконання покарань (№ 14)»', '8563783', 32.257008, 48.493807);
INSERT INTO breach.prison VALUES (134, 'Державна установа «Кропивницька виправна колонія (№ 6)»', '8563777', 32.25567, 48.49356);
INSERT INTO breach.prison VALUES (135, 'Державна установа «Петрівська виправна колонія (№ 49)»', '8563808', 33.16739, 48.511497);
INSERT INTO breach.prison VALUES (136, 'Державна установа «Олександрівський виправний центр (№ 104)»', '8563799', 32.237275, 48.965452);






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




DROP TABLE IF EXISTS breach.bank_card_payment;
CREATE TABLE breach.bank_card_payment
(
    id                  uuid not null constraint bank_card_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp not null,
    bank_card_id        uuid not null
);
ALTER TABLE breach.bank_card_payment OWNER TO postgres;

DROP TABLE IF EXISTS breach.wallet_payment;
CREATE TABLE breach.wallet_payment
(
    id                  uuid not null constraint wallet_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp not null,
    payment_provider_id int not null,
    wallet_number       text
);
ALTER TABLE breach.wallet_payment OWNER TO postgres;

DROP TABLE IF EXISTS breach.website_payment;
CREATE TABLE breach.website_payment
(
    id                  uuid not null constraint website_payment_pk primary key,
    head_id             uuid not null,
    date                timestamp not null,
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
    head_id             uuid,
    card_number         text not null,
    bank_name           text,
    holder_id           uuid
);
ALTER TABLE breach.bank_card OWNER TO postgres;





