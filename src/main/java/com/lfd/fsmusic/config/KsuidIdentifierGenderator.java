package com.lfd.fsmusic.config;

import java.io.Serializable;

import com.github.ksuid.KsuidGenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class KsuidIdentifierGenderator implements IdentifierGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return KsuidGenerator.generate();
    }
    
}
