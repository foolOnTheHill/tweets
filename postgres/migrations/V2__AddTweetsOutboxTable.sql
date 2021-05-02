CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE tweets_outbox (
    event_id UUID DEFAULT uuid_generate_v4(),
    aggregate_id TEXT,
    type TEXT,
    aggregate JSONB,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);