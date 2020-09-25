package uk.hardwareswap.shortenerapi.repository.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class UrlIdGeneratorStrategy implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        Connection connection = sharedSessionContractImplementor.connection();

        try {
            Statement statement = connection.createStatement();

            ResultSet rs;
            String id;

            do {
                id = generateRandomUrlId();
                rs = statement.executeQuery("select * from url_redirect_data where url_id = '" + id + "'");
            } while (rs.next());



            return id;
        } catch (SQLException ex) {
            log.error("An SQL error occurred while generating a new URL ID: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean supportsJdbcBatchInserts() {
        return false;
    }

    private String generateRandomUrlId() {

        int length = 7;
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
