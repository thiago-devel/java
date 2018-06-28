package br.com.resseler.directsales.commons.persistence.dao.rowmappers;

import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_COD;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.BCH_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PERSON_CPF;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.PRD_ID;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.REG_DSC;
import static br.com.resseler.directsales.commons.persistence.dao.util.CustomerSalesConstants.REG_ID;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rubyit.resseler.commons.kernel.dto.IdentityDTO;
import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;
import br.com.rubyit.resseler.commons.kernel.enums.Document;
import br.com.rubyit.resseler.core.commons.dto.Regional;
import br.com.rubyit.resseler.core.commons.dto.Salesman;
import br.com.rubyit.resseler.core.commons.dto.SalesmanScore;
import br.com.rubyit.resseler.core.commons.dto.Workshop;

public class SalesmanScoreRM implements RowMapper<SalesmanScore> {

    @Override
    public SalesmanScore mapRow(final ResultSet rs, final int rowNum) throws SQLException {

        final SalesmanScore salesmanScore = new SalesmanScore();

        final ProductDTO product = new ProductDTO();
        product.setID(rs.getLong(PRD_ID));
        product.setDescription(rs.getString(PRD_DSC));
        salesmanScore.setProduct(product);

        final IdentityDTO identityCPF = new IdentityDTO();
        identityCPF.setDocumentType(Document.CPF);
        identityCPF.setDocumentValue(rs.getString(PERSON_CPF));
        
        final Salesman salesman = new Salesman();
        salesman.setID(rs.getLong("SALESMAN_ID"));
        salesman.setFullName(rs.getString("PERSON_NAME"));
        salesman.setIdentity(identityCPF);
        
        salesmanScore.setSalesman(salesman);

        final Workshop workshop = new Workshop();
        workshop.setID(rs.getLong(BCH_ID));
        workshop.setBranchCode(rs.getString(BCH_COD));
        workshop.setDescription(rs.getString(BCH_DSC));
        salesmanScore.setWorkshop(workshop);

        final Regional regional = new Regional();
        regional.setID(rs.getLong(REG_ID));
        regional.setDescription(rs.getString(REG_DSC));
        salesmanScore.setRegional(regional);

        salesmanScore.setActiveCertificatesQty(rs.getLong("SOLD_CERTIFICATES")); 
        salesmanScore.setActiveCertificatesValue(rs.getDouble("PREMIUN_VALUE"));
        salesmanScore.setTotalCommissionValue(rs.getDouble("COMMISSION_VALUE"));

        return salesmanScore;
    }
}
