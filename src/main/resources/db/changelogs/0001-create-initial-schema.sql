-- Initial schema creation. Why not use Hibernate for this, you ask?
-- One simple reason: you can't specify a truncated index for automatic DDL generation in Hibernate :(

CREATE TABLE url_redirect_data (
   url_id VARCHAR(7) PRIMARY KEY,
   original_url VARCHAR(2000)
);

CREATE INDEX idx_original_url ON url_redirect_data (original_url(200));