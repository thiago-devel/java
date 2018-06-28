package br.com.rubyit.resseler.soa.bs.sales;

import ma.glasnost.orika.MapperFacade;

public class LoadProductsAndCategoriesParser {

	/*
    private br.com.resseler.wsdirectsales.Sale saleOutter = null;
    private br.com.rubyit.resseler.core.commons.dto.Sale saleInner = null;

    public void setSaleOutter(final br.com.resseler.wsdirectsales.Sale saleOutter) {

        final br.com.resseler.wsdirectsales.Sale source = saleOutter;
        saleInner = instanceMapperFactoryAndFacade().map(source, br.com.rubyit.resseler.core.commons.dto.Sale.class);
    }

    public br.com.rubyit.resseler.core.commons.dto.Sale getSaleInner() {
        return saleInner;
    }

    public void setSaleInner(final br.com.rubyit.resseler.core.commons.dto.Sale saleInner) {

        final br.com.rubyit.resseler.core.commons.dto.Sale source = saleInner;
        saleOutter = instanceMapperFactoryAndFacade().map(source, br.com.resseler.wsdirectsales.Sale.class);
    }

    public br.com.resseler.wsdirectsales.Sale getSaleOutter() {
        return saleOutter;
    }
    */

    private MapperFacade instanceMapperFactoryAndFacade() {
        return InnerOuterMapper.getInstance().getMapper();
    }
}
