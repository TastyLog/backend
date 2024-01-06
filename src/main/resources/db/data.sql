INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '빅페이스 BIGFACE','UCObJpvG3_f0P3EuLJCjzT5g', 'bigface.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '빅페이스 BIGFACE');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '더들리','UCmJEpV4hLzGWLU5rrdOHMhQ', 'dudely.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '더들리');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '섬마을훈태TV','UCkBoDzncl64EZ-Ggh4g5pCw','huntae.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '섬마을훈태TV');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '장사의 신','UCBMBPRnwRgl3aJZDEpTu65Q', 'jangsin.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '장사의 신');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '맛상무','UC1r112Pr9Ngcg2NtcE946HQ', 'matsangmu.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '맛상무');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '정육왕 MeatCreator','UC1oXmhvYHVI2bApphh3IzuQ', 'meat.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '정육왕 MeatCreator');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '먹보스 쭈엽이','UCLwCHoQ9h7DPXvLwx5XwTQg', 'mukboss.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '먹보스 쭈엽이');

INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '김사원세끼','UC-x55HF1-IilhxZOzwJm7JA', 'sawon.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '김사원세끼');


INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '성시경 SUNG SI KYUNG','UCl23-Cci_SMqyGXE1T_LYUg', 'ssk.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '성시경 SUNG SI KYUNG');


INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT 'tzuyang쯔양','UCfpaSruWW3S4dibonKXENjA', 'tzuyang.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = 'tzuyang쯔양');


INSERT INTO YOUTUBER (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '맛있겠다 Yummy','UCQA89gPDjJ-1M1o9bwdGF-g', 'yummy.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM YOUTUBER WHERE youtuber_name  = '맛있겠다 Yummy');


