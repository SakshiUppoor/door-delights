BEGIN TRANSACTION;
    DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'types') THEN
            CREATE TYPE types AS ENUM('customer', 'manager', 'delivery_person');
        END IF;
    END$$;
    CREATE TABLE IF NOT EXISTS user_account (
        id SERIAL,
        email VARCHAR(40) NOT NULL,
        hashed_password VARCHAR(255) NOT NULL,
        first_name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255) NOT NULL,
        user_type types NOT NULL,
        PRIMARY KEY (id),
        UNIQUE (email)
    );
    CREATE TABLE IF NOT EXISTS address (
        id SERIAL,
        line1 VARCHAR(255) NOT NULL,
        line2 VARCHAR(255) NOT NULL,
        pincode VARCHAR(10) NOT NULL,
        country VARCHAR(255),
        state VARCHAR(255),
        PRIMARY KEY (id)
    );
    CREATE TABLE IF NOT EXISTS saved_address (
        id SERIAL,
        title VARCHAR(15),
        user_id INT NOT NULL,
        address_id INT NOT NULL,
        FOREIGN KEY (user_id) REFERENCES user_account(id)
        ON DELETE CASCADE,
        FOREIGN KEY (address_id) REFERENCES address(id)
        ON DELETE CASCADE,
        PRIMARY KEY (id)
    );
COMMIT;

