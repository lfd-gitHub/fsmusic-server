package com.lfd.fsmusic.config;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrefixPhysicalNamingStrategy extends CamelCaseToUnderscoresNamingStrategy {

    public static final String TABLE_NAME_PREFIX = "tb_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String tbName = name.getText().startsWith(TABLE_NAME_PREFIX)
                        ? name.getText() : TABLE_NAME_PREFIX + name.getText();
        Identifier newIdentifier = new Identifier(tbName, name.isQuoted());
        return super.toPhysicalTableName(newIdentifier, context);
    }
}