package br.com.rubyit.resseler.soa.bs.sales;


import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public final class InnerOuterMapper {

    private static InnerOuterMapper instance = null;
    private MapperFacade mapper = null;

    private InnerOuterMapper() {
    }

    public static InnerOuterMapper getInstance() {
    	
        if (instance == null) {
        	synchronized(InnerOuterMapper.class){
        		instance = new InnerOuterMapper();
        	}
        }

        return instance;
    }

    public MapperFacade getMapper() {
        if (mapper == null) {
            final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
            mapper = mapperFactory.getMapperFacade();
        }
        return mapper;
    }

}
