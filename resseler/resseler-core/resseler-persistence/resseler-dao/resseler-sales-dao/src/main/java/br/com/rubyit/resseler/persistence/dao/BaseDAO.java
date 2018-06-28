package br.com.rubyit.resseler.persistence.dao;

import br.com.rubyit.resseler.persistence.mapper.InnerOuterMapper;
import ma.glasnost.orika.MapperFacade;

public class BaseDAO {

	protected MapperFacade instanceMapperFactoryAndFacade() {
        return InnerOuterMapper.getInstance().getMapper();
    }
}
