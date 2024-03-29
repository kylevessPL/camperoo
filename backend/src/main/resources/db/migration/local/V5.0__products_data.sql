--
-- files
--
INSERT INTO files (id, name, content_type, content)
VALUES (1, 'product_1.png', 'image/png', PG_READ_BINARY_FILE('/images/travel_trailer/mallard.png')),
       (2, 'product_2.png', 'image/png', PG_READ_BINARY_FILE('/images/travel_trailer/pioneer.png')),
       (3, 'product_3.png', 'image/png', PG_READ_BINARY_FILE('/images/travel_trailer/sundance.png')),
       (4, 'product_4.png', 'image/png', PG_READ_BINARY_FILE('/images/fifth_wheel/bighorn_traveler.png')),
       (5, 'product_5.png', 'image/png', PG_READ_BINARY_FILE('/images/fifth_wheel/landmark365.png')),
       (6, 'product_6.png', 'image/png', PG_READ_BINARY_FILE('/images/fifth_wheel/milestone.png')),
       (7, 'product_7.png', 'image/png', PG_READ_BINARY_FILE('/images/lightweight_trailer/bambi.png')),
       (8, 'product_8.png', 'image/png', PG_READ_BINARY_FILE('/images/toy_hauler/cyclone.png')),
       (9, 'product_9.png', 'image/png', PG_READ_BINARY_FILE('/images/toy_hauler/gravity.png')),
       (10, 'product_10.png', 'image/png', PG_READ_BINARY_FILE('/images/toy_hauler/road_warrior.png')),
       (11, 'product_11.png', 'image/png', PG_READ_BINARY_FILE('/images/expandable_trailer/solaire.png')),
       (12, 'product_12.png', 'image/png', PG_READ_BINARY_FILE('/images/class_a_motorhome/ace.png')),
       (13, 'product_13.png', 'image/png', PG_READ_BINARY_FILE('/images/class_a_motorhome/axis.png')),
       (14, 'product_14.png', 'image/png', PG_READ_BINARY_FILE('/images/class_a_motorhome/hurricane.png')),
       (15, 'product_15.png', 'image/png', PG_READ_BINARY_FILE('/images/class_b_camper_van/compass_awd.png')),
       (16, 'product_16.png', 'image/png', PG_READ_BINARY_FILE('/images/class_b_camper_van/dazzle.png')),
       (17, 'product_17.png', 'image/png', PG_READ_BINARY_FILE('/images/class_b_camper_van/twist.png')),
       (18, 'product_18.png', 'image/png', PG_READ_BINARY_FILE('/images/class_c_motorhome/four_winds.png')),
       (19, 'product_19.png', 'image/png', PG_READ_BINARY_FILE('/images/class_c_motorhome/omni.png')),
       (20, 'product_20.png', 'image/png', PG_READ_BINARY_FILE('/images/class_c_motorhome/outlaw.png'));


SELECT SETVAL('seq_files_id', 20);

--
-- products
--
INSERT INTO products (id, category_id, price, quantity, image_id)
VALUES (1, 1, 720.00, 11, 1),
       (2, 1, 630.00, 9, 2),
       (3, 1, 707.00, 2, 3),
       (4, 2, 808.00, 4, 4),
       (5, 2, 831.00, 8, 5),
       (6, 2, 864.00, 14, 6),
       (7, 3, 605.00, 17, 7),
       (8, 4, 861.00, 2, 8),
       (9, 4, 756.00, 1, 9),
       (10, 4, 622.00, 3, 10),
       (11, 5, 739.00, 7, 11),
       (12, 6, 753.00, 6, 12),
       (13, 6, 757.00, 4, 13),
       (14, 6, 781.00, 13, 14),
       (15, 7, 777.00, 12, 15),
       (16, 7, 842.00, 9, 16),
       (17, 7, 646.00, 8, 17),
       (18, 8, 886.00, 5, 18),
       (19, 8, 790.00, 14, 19),
       (20, 8, 882.00, 16, 20);
INSERT INTO products (id, category_id, price, transportation, quantity)
VALUES (21, 9, 4.50, TRUE, -1);

SELECT SETVAL('seq_products_id', 21);

--
-- product_names
--
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Mallard', 2, 1),
       (NEXTVAL('seq_product_names_id'), 'Pioneer', 2, 2),
       (NEXTVAL('seq_product_names_id'), 'Sundance', 2, 3),
       (NEXTVAL('seq_product_names_id'), 'Bighorn Traveler', 2, 4),
       (NEXTVAL('seq_product_names_id'), 'Landmark 365', 2, 5),
       (NEXTVAL('seq_product_names_id'), 'Milestone', 2, 6),
       (NEXTVAL('seq_product_names_id'), 'Bambi', 2, 7),
       (NEXTVAL('seq_product_names_id'), 'Cyclone', 2, 8),
       (NEXTVAL('seq_product_names_id'), 'Gravity', 2, 9),
       (NEXTVAL('seq_product_names_id'), 'Road Warrior', 2, 10),
       (NEXTVAL('seq_product_names_id'), 'Solaire', 2, 11),
       (NEXTVAL('seq_product_names_id'), 'A.C.E.', 2, 12),
       (NEXTVAL('seq_product_names_id'), 'Axis', 2, 13),
       (NEXTVAL('seq_product_names_id'), 'Hurricane', 2, 14),
       (NEXTVAL('seq_product_names_id'), 'Compass AWD', 2, 15),
       (NEXTVAL('seq_product_names_id'), 'Dazzle', 2, 16),
       (NEXTVAL('seq_product_names_id'), 'Twist', 2, 17),
       (NEXTVAL('seq_product_names_id'), 'Four Winds', 2, 18),
       (NEXTVAL('seq_product_names_id'), 'Omni', 2, 19),
       (NEXTVAL('seq_product_names_id'), 'Outlaw', 2, 20),
       (NEXTVAL('seq_product_names_id'), 'Transport', 1, 21),
       (NEXTVAL('seq_product_names_id'), 'Transportation', 2, 21);

--
-- product_descriptions
--
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'),
        'Mallard to nowy sposób na spędzanie czasu na świeżym powietrzu. Zbudowany do pracy w ekstremalnym terenie, ale nadający się do holowania lekkimi pojazdami, Pathfinder to zabawna, przemysłowa ultralekka przyczepa podróżna, wyposażona w unikalne funkcje dla współczesnego żądnego przygód podróżnika.',
        1, 1),
       (NEXTVAL('seq_product_descriptions_id'),
        'A new way to enjoy the outdoors. Built to handle extreme terrain but towable with lightweight vehicles, the Pathfinder is a fun, industrial built ultra-lite travel trailer equipped with unique features for today’s adventurous camper.',
        2, 1),
       (NEXTVAL('seq_product_descriptions_id'),
        'Eksploruj nowe terytoria w jednym z wielu modeli przyczep kempingowych oferowanych przez Pioneer RV. Skonstruowany tak, aby był niezawodny dzięki inteligentnemu projektowi, znajdziesz funkcje, których możesz się nie spodziewać, takie jak gazowo-elektryczna lodówka z frontem do tablicy i kominkami w wybranych planach przyczep turystycznych Pioneer. Co więcej, oferuje również poręczny rozkładany stojak do przechowywania, który przechowuje przedmioty takie jak rowery, drewno i lodówki.',
        1, 2),
       (NEXTVAL('seq_product_descriptions_id'),
        'Explore new territory in one of the many travel trailer models that Pioneer RVs have to offer. Constructed to be dependable with a smart design, you’ll find features you may not expect, like a gas/electric refrigerator with chalkboard front and fireplaces in select Pioneer travel trailer floor plans. Or the handy flip-down storage rack, which keeps items like bikes, wood and coolers outdoors.',
        2, 2),
       (NEXTVAL('seq_product_descriptions_id'),
        'Czy jesteś perfekcjonistą z wysokimi wymaganiami, ale skromnym budżetem? Pozwól, że przedstawimy Ci Sundance Ultra-Lite. Opcji jest mnóstwo, począwszy od planów pięter z wyborem zjeżdżalni, bunkrów, dodatkowych półwann i nie tylko. Standardowe funkcje obejmują konfigurację za pomocą przycisku, przygotowanie do opalania i kuchnię na świeżym powietrzu z płytą do grillowania – tego rodzaju opcje, za które można spodziewać się dużo więcej.',
        1, 3),
       (NEXTVAL('seq_product_descriptions_id'),
        'Are you an overachiever with high standards but a modest budget? Let us introduce you to the Sundance Ultra-Lite. The options grow from there, with floor plans with your choice of slides, bunkhouses, bonus half-baths and more. Standard features include push-button setup, solar prep and an outdoor kitchen with a griddle – the kind of options you might expect to pay a lot more for.',
        2, 3),
       (NEXTVAL('seq_product_descriptions_id'),
        'Stworzony dla poszukiwaczy przygód z wielkimi planami, ta gotowa do jazdy bestia jest wytrzymała i niezawodna. Jeśli Twoje marzenie o kamperze obejmuje dłuższe wycieczki po drogach, górzysty teren lub podejście „jedź gdziekolwiek, rób wszystko”, to jest to sprzęt dla Ciebie. Dzięki przestronnemu wnętrzu i wyjątkowym schowkom poczujesz się jak w domu.',
        1, 4),
       (NEXTVAL('seq_product_descriptions_id'),
        'Built for adventurers with big plans, this road-ready beast is tough and dependable. So if your RV dream includes longer road trips, mountainous terrain or a “go anywhere, do anything” attitude, this is your rig. You’ll feel at home with a spacious interior and exceptional storage areas.',
        2, 4),
       (NEXTVAL('seq_product_descriptions_id'),
        'Plany pięter Landmark 365 obejmują garderobę z głębokimi półkami, podwójnymi wieszakami, cedrową podszewką i przygotowaniem do pralki/suszarki, które wyróżniają go. Wiodące w branży osie na oponach serii H z hamulcami 3 3/8”, MORryde LRE 4000, amortyzatory gazowe i pojemność bagażnika sprawiły, że Landmark 365 RV jest pierwszym na rynku luksusowych samochodów kempingowych.',
        1, 5),
       (NEXTVAL('seq_product_descriptions_id'),
        'The Landmark 365''s floor plans feature walk-in closets with deep shelves, double hanging rods, cedar lining and washer/dryer prep make it stand out. Industry leading tires with 3 3/8˝ brakes, MORryde LRE 4000, gas shocks and storage capacity have made the Landmark 365 RV first in the luxury RV market.',
        2, 5),
       (NEXTVAL('seq_product_descriptions_id'),
        'Trafnie nazwany pięciokołowiec Milestone RV zapewnia innowacyjne udogodnienia bez pozostawiania wartości. Milestone może pochwalić się 50-calowym telewizorem rozrywkowym ze zintegrowanym soundbarem i ulepszonym stopniem MORryde z systemem szybkiej regulacji przy każdych drzwiach, co stało się już standardem we wszystkich elementach zewnętrznych Milestone.',
        1, 6),
       (NEXTVAL('seq_product_descriptions_id'),
        'Aptly named, the Milestone fifth wheel RV brand provides innovative amenities without leaving value behind. Features 50-inch entertainment TV with integrated sound bar, and upgraded MORryde solid step with quick adjust system at every door come standard with all of the Milestone exteriors.',
        2, 6),
       (NEXTVAL('seq_product_descriptions_id'),
        'Przez lata Bambi było przezwiskiem dla najmniejszych jednoosiowych przyczep turystycznych. Bambi to model z opcjami maksymalizacji przestrzeni, gotowy do ogromnego ulepszenia dla kempingowiczów. Bambi zajmie się wszystkimi drobiazgami, których potrzebujesz, a więc możesz wyjść i przeżyć wielkie przygody w małej przyczepie.',
        1, 7),
       (NEXTVAL('seq_product_descriptions_id'),
        'For years, Bambi''s been a nickname for smallest single-axle travel trailers. Bambi is a model with space-maximizing options ready to deliver a huge upgrade up for tent campers. Bambi takes care of all the little things you need, so you can get out there and have some big adventures in a small trailer.',
        2, 7),
       (NEXTVAL('seq_product_descriptions_id'),
        'Podbij świat szturmem. Pięciokołowiec Cyclone zapewnia pojemność przyczepy z garażem z dodatkowymi funkcjami. Apartamenty z łóżkiem typu king-size obejmują wysuwaną szafkę roboczą typu „swift space”, a dostęp do piwnicy z trzech stron Store-More zapewnia o 30% więcej miejsca do przechowywania. Zaspokój swoje pragnienie podróży bez ograniczeń.',
        1, 8),
       (NEXTVAL('seq_product_descriptions_id'),
        'Take the world by storm. The Cyclone fifth wheel gives you toy hauler capacity with indulgent features. Our King bedroom suites include a “swift space” pull out dresser workstation and the Store-More three-sided access to the basement provides 30% more storage. Satisfy your wanderlust without sacrificing anything.',
        2, 8),
       (NEXTVAL('seq_product_descriptions_id'),
        'Ten kamper z garażem jest stworzony dla wszystkich weekendowych wojowników. Wyrusz na przygodę ze spokojem, wiedząc, że przyspawane do ramy mocowania bezpiecznie utrzymują twój sprzęt na miejscu. Stwórz własną oazę w dowolnym miejscu na świeżym powietrzu ze standardowymi łóżkami King, systemem dźwiękowym JBL Hi-Fi z subwooferem i portami ładowania bezprzewodowego. Pod koniec dnia odpocznij i zrelaksuj się na patio dzięki zestawowi poręczy tarasowych, które sprawią, że zachód słońca będzie przyjemniejszy niż kiedykolwiek wcześniej.',
        1, 9),
       (NEXTVAL('seq_product_descriptions_id'),
        'Gravity toy hauler is built for all you weekend warriors. Go on an adventure with peace of mind knowing frame welded tie downs are safely holding your toys in place. Create your own oasis anywhere in the great outdoors with standard King beds, a JBL Hi-Fi Sound System with a Subwoofer, and wireless charging ports. At the end of the day, kick back and relax on the ramp door patio with a patio rail kit making the sunset even more enjoyable than ever before.',
        2, 9),
       (NEXTVAL('seq_product_descriptions_id'),
        'Spędzaj miło czas z rodziną i przyjaciółmi w Road Warrior RV. Dzięki łatwemu holowaniu i wszechstronności, Road Warrior wszyscy mogą wziąć udział w przejażdżce: rodzina, przyjaciele oraz Twój sprzęt. Ponadto będziesz cieszyć się najwyższej klasy dodatkami, takimi jak system dźwiękowy JBL Hi-Fi z subwooferem i zabawkowym wozidłem. Drzwi rampy o zerowej grawitacji z zestawem do patio.',
        1, 10),
       (NEXTVAL('seq_product_descriptions_id'),
        'Play hard with family and friends in the Road Warrior RV. With the easy towing of a fifth wheel and the versatility of a Road Warrior toy hauler, everyone and everything can come along for the ride: family, friends and all your toys. Plus you’ll enjoy top-notch touches like a JBL Hi-Fi sound system with subwoofer and a toy hauler Zero gravity ramp door with patio kit.',
        2, 10),
       (NEXTVAL('seq_product_descriptions_id'),
        'SolAire Ultra Lite to ekscytująca linia przyczep kempingowych firmy Palomino, oddział Forest River. SolAire jest doskonały dla par lub małych rodzin, które uwielbiają biwakować lub wyjeżdżać na weekend. Dzięki w pełni przystosowanym do chodzenia wnętrzom z beczkowymi sufitami i oświetleniem LED oraz w pełni spawanej superkonstrukcji, SolAire pozwala ogladać świat ze stylem.',
        1, 11),
       (NEXTVAL('seq_product_descriptions_id'),
        'The SolAire Ultra Lite is an exciting line of travel trailer RVs from Palomino, a division of Forest River. The SolAire is excellent for couples or small families who love to camp or get away for the weekend. Featuring fully walkable interiors with barreled ceilings and LED lighting, and a full-welded super structure, the SolAire lets you see the world in style.',
        2, 11),
       (NEXTVAL('seq_product_descriptions_id'),
        'Dzięki unikalnym i przyjaznym dla rodziny planom pięter dostosowanym do Ciebie, A.C.E ma to wszystko. Konstrukcja tego kampera klasy A stawia rodzinę na pierwszym miejscu. Jeśli nigdy nie myślałeś, że będziesz mieć samochód kempingowy Mudroom, pomyśl jeszcze raz. Teraz możesz mieć wyznaczone miejsce na buty i smycze, dzięki czemu idealnie sprawdzi się na pieszych wycieczkach lub spacerze z psem. Wycieczki drogowe nigdy nie były tak łatwe.',
        1, 12),
       (NEXTVAL('seq_product_descriptions_id'),
        'With unique and family-friendly floor plans made to fit you, A.C.E has it all. This Class A motorhome’s design puts family first. If you never thought you’d have a Motorhome Mudroom, think again. Now you can have a designated spot for boots and leashes, making it ideal after hiking trips or walking your dog. Road trips have never been this easy.',
        2, 12),
       (NEXTVAL('seq_product_descriptions_id'),
        'Ten RV klasy A jest nie tylko łatwy w manewrowaniu, ale także wszechstronny i dostępny w kilku unikalnych układach pięter. Niezależnie od tego, czy chcesz wyjechać na weekend, czy też rozpocząć pełnoetatową wyprawę na wakacje, bez zawahania zwrócisz się do Axis. Przyjmuj ukochanych podczas sezonu łowieckiego i zabawiaj gości na kempingu; będą zachwyceni twoim domem na kółkach.',
        1, 13),
       (NEXTVAL('seq_product_descriptions_id'),
        'Not only is this Class A RV easily maneuverable, it is also versatile and available in several unique floor plans. Whether you want a weekend getaway or to begin full-time RVing, you’ll be happy to call Axis home. Host loved ones during tailgating season and entertain guests at the campground; they’re going to be in awe of your home-on-wheels.',
        2, 13),
       (NEXTVAL('seq_product_descriptions_id'),
        'Unikalne plany pięter Hurricane zostały zaprojektowane z myślą o prawdziwych rodzinach, takich jak Twoja. Każdy model ma główną sypialnię, uniwersalną część dzienną, łazienkę i w pełni wyposażoną kuchnię. Te domowe udogodnienia sprawiają, że Hurricane to najlepszy wybór dla pełnoetatowców i osób często podróżujących.',
        1, 14),
       (NEXTVAL('seq_product_descriptions_id'),
        'Hurricane’s unique floor plans were designed with real families, like yours, in mind. Every model has a master bedroom, versatile living area, bathroom, and fully equipped kitchen. These homelike amenities make Hurricane a top choice for full-timers and frequent travelers.',
        2, 14),
       (NEXTVAL('seq_product_descriptions_id'),
        'Compass AWD oferuje unikalne opcje pięter, co daje możliwość znalezienia planu piętra dopasowanego do Ciebie. Ten wszechstronny samochód kempingowy klasy B+ został zaprojektowany tak, aby był łatwy w użytkowaniu i łatwy w prowadzeniu. Niezależnie od tego, czy będziesz podróżować w weekendy, czy mieszkać w pełnym wymiarze godzin, Compass AWD sprawi, że poczujesz się jak w domu.',
        1, 15),
       (NEXTVAL('seq_product_descriptions_id'),
        'Compass AWD offers unique floor options that make it possible to find the floor plan made to fit you. This versatile Class B+ RV is designed to be easy to live in and easy to drive. Whether you’ll be weekend travelers or living full-time, Compass AWD will make you feel right at home.',
        2, 15),
       (NEXTVAL('seq_product_descriptions_id'),
        'Dazzle jest gotowy na każdą przygodę. Bogate w wygodne udogodnienia, w tym toaletę kasetową, wyjmowany stolik na planie 2JB, wysuwane tylne łóżko typu King na planie 2AB oraz boczne drzwi moskitierowe ObeCo do przedpokoju. Zabawny rozmiar tego kampera pomaga uniknąć stresu, umożliwiając zaparkowanie praktycznie w dowolnym miejscu. Więc zrelaksuj się i podróżuj w dobrym stylu. Spraw, aby jazda była równie przyjemna jak cel podróży.',
        1, 16),
       (NEXTVAL('seq_product_descriptions_id'),
        'The Dazzle is ready for any adventure. Abundant in convenient amenities, including a cassette toilet, removable table in the 2JB floor plan, pullout rear King bed in the 2AB floor plan, and an ObeCo side screen door for the entryway. This camper’s fun size helps avoid stress by letting you park virtually anywhere. So, relax and travel in style. Make the drive as fun as the destination.',
        2, 16),
       (NEXTVAL('seq_product_descriptions_id'),
        'Weź Twist na przejażdżkę i obserwuj, jak rozwija się zabawa. Przyrządzaj posiłki dla przyjaciół i rodziny na dwupalnikowej kuchence gazowej ze szklaną pokrywą lub opowiadaj historie przy stole z cokołami na planie 2AB. Zrób to wszystko, oszczędzając energię, korzystając z kontrolera ładowania słonecznego z panelem słonecznym o mocy 190 W. Bez względu na to, dokąd się wybierasz, Twist z łatwością Cię tam zaprowadzi.',
        1, 17),
       (NEXTVAL('seq_product_descriptions_id'),
        'Take Twist for a spin and watch the fun unfold. Prepare meals for friends and family on the two-burner gas cooktop with a glass cover, or share stories at the dinette table with pedestal legs in the 2AB floor plan. Do it all while saving energy using the solar charge controller with a 190W solar panel. No matter where you go, Twist will get you there with ease.',
        2, 17),
       (NEXTVAL('seq_product_descriptions_id'),
        'Łatwy w prowadzeniu i łatwy do pokochania, będziesz chciał spędzić wszystkie wakacje w swoim Four Winds. Wyposażenie mieszkalne, w zależności od modelu, obejmuje przygotowanie do pralki i suszarki, łóżka piętrowe i mnóstwo miejsc do siedzenia. Wybierz plan piętra i kolory wystroju, które pasują do Ciebie, a znajdziesz się na najlepszej drodze do Czterech Wiatrów, w którym poczujesz się jak w domu.',
        1, 18),
       (NEXTVAL('seq_product_descriptions_id'),
        'Easy to drive and easy to love, you’ll want to spend all of your vacations in your Four Winds. The living amenities, depending on the model, include washer and dryer prep, bunk beds, and a plethora of seating options. Choose the floor plan and decor colors made to fit you and you''ll be on your way to a Four Winds that feels just like home.',
        2, 18),
       (NEXTVAL('seq_product_descriptions_id'),
        'Znajdź swój dom z dala od domu. Niezależnie od tego, czy obozujesz na pełny etat, czy jesteś weekendowym wojownikiem, Omni dopasuje się do Twojego stylu życia. Każdy plan piętra oferuje wystarczająco dużo miejsca dla Twoich bliskich. Super C RV jest wyposażony w panoramiczne okno dachowe z towarzyszącą osłoną energetyczną. Spotkaj się z bliskimi w Dream Dinette i przedyskutuj swoją ulubioną część dnia.',
        1, 19),
       (NEXTVAL('seq_product_descriptions_id'),
        'Find your home away from home. Whether you camp full-time or are a weekend warrior, Omni fits your lifestyle. Every floor plan offers more than enough room for your loved ones. This Super C RV features a panoramic skylight with accompanying power shade. Gather with loved ones at the Dream Dinette and discuss your favorite part of the day.',
        2, 19),
       (NEXTVAL('seq_product_descriptions_id'),
        'Maksymalizujący każdy cal, Outlaw to doskonały dom na kółkach zarówno dla pełnoetatowych, jak i weekendowych poszukiwaczy przygód. Toy hauler ma przestronny garaż; więc śmiało możesz zabrać ze sobą swój sprzęt.',
        1, 20),
       (NEXTVAL('seq_product_descriptions_id'),
        'Maximizing every inch, Outlaw is an excellent home-on-wheels for full-timers and weekend adventurers alike. Each toy hauler is designed with a spacious garage; so go ahead and bring along your outdoor toys.',
        2, 20),
       (NEXTVAL('seq_product_descriptions_id'), 'Transport. Nie może być zamówiony osobno.', 1, 21),
       (NEXTVAL('seq_product_descriptions_id'), 'Transportation. Cannot be ordered separately.', 2, 21);
