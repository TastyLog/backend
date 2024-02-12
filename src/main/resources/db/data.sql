INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '빅페이스 BIGFACE','UCObJpvG3_f0P3EuLJCjzT5g', 'UCObJpvG3_f0P3EuLJCjzT5g.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '빅페이스 BIGFACE');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '더들리','UCmJEpV4hLzGWLU5rrdOHMhQ', 'UCmJEpV4hLzGWLU5rrdOHMhQ.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '더들리');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '섬마을훈태TV','UCkBoDzncl64EZ    -Ggh4g5pCw','UCkBoDzncl64EZ-Ggh4g5pCw.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '섬마을훈태TV');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '장사의 신','UCBMBPRnwRgl3aJZDEpTu65Q', 'UCBMBPRnwRgl3aJZDEpTu65Q.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '장사의 신');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '맛상무','UC1r112Pr9Ngcg2NtcE946HQ', 'UC1r112Pr9Ngcg2NtcE946HQ.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '맛상무');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '정육왕 MeatCreator','UC1oXmhvYHVI2bApphh3IzuQ', 'UC1oXmhvYHVI2bApphh3IzuQ.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '정육왕 MeatCreator');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '먹보스 쭈엽이','UCLwCHoQ9h7DPXvLwx5XwTQg', 'UCLwCHoQ9h7DPXvLwx5XwTQg.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '먹보스 쭈엽이');

INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '김사원세끼','UC-x55HF1-IilhxZOzwJm7JA', 'UC-x55HF1-IilhxZOzwJm7JA.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '김사원세끼');


INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '성시경 SUNG SI KYUNG','UCl23-Cci_SMqyGXE1T_LYUg', 'UCl23-Cci_SMqyGXE1T_LYUg.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '성시경 SUNG SI KYUNG');


INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT 'tzuyang쯔양','UCfpaSruWW3S4dibonKXENjA', 'UCfpaSruWW3S4dibonKXENjA.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = 'tzuyang쯔양');


INSERT INTO youtuber (youtuber_name, channel_id,youtuber_profile,created_at,updated_at)
SELECT '맛있겠다 Yummy','UCQA89gPDjJ-1M1o9bwdGF-g', 'UCQA89gPDjJ-1M1o9bwdGF-g.jpg',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP
    WHERE NOT EXISTS (SELECT 1 FROM youtuber WHERE youtuber_name  = '맛있겠다 Yummy');

-- fulltext search ngram parser setting
ALTER TABLE food ADD FULLTEXT(category,restaurant) WITH PARSER ngram;