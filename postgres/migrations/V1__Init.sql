CREATE TABLE "users" (
  "id" BIGSERIAL UNIQUE,
  "username" varchar UNIQUE,
  PRIMARY KEY ("id", "username")
);

CREATE TABLE "follows" (
  "follower_id" bigint,
  "followee_id" bigint,
  PRIMARY KEY ("follower_id", "followee_id")
);

CREATE TABLE "tweets" (
  "id" BIGSERIAL PRIMARY KEY,
  "sender_id" bigint,
  "text" text,
  "timestamp" timestamp
);

ALTER TABLE "follows" ADD FOREIGN KEY ("follower_id") REFERENCES "users" ("id");

ALTER TABLE "follows" ADD FOREIGN KEY ("followee_id") REFERENCES "users" ("id");

ALTER TABLE "tweets" ADD FOREIGN KEY ("sender_id") REFERENCES "users" ("id");
