package br.com.resseler.exemple.core.persistence.repository.sales;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.resseler.directsales.sales.repository.RepositorySales;
import br.com.resseler.exemple.core.persistence.repository.sales.config.SalesRepositoryTestConfig;
import br.com.rubyit.resseler.core.commons.dto.InsuranceCertificate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SalesRepositoryTestConfig.class })
public class RepositorySalesTest {

    @Autowired
    private RepositorySales repository;

    @Before
    public void setup() {
        Assert.assertNotNull(repository);
    }

    @Test
    @Ignore
    public void testRetrieveCertificateForSalesman() {

        final List<InsuranceCertificate> certificateList = repository
                .retrieveInsuranceCertificatesBySalesmanIdentification("05956679611");

        Assert.assertNotNull(certificateList);
    }
}
