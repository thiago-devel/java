package br.com.rubyit.resseler.core.utils;

import java.util.HashMap;
import java.util.Map;

import br.com.rubyit.resseler.commons.kernel.dto.ProductDTO;

/**
 * Class ProductPlan
 * Singleton utilitario 
 * que armazena os planos
 * dos produtos
 * @author b11527
 *
 */
public final class ProductPlan {

    private static final Integer ID_PRODUCT_LUIZA_RESIDENCIAL_MAIS_PROTECAO = 11;
    private static final Integer ID_PRODUCT_LUIZA_RESIDENCIAL_MENSAL_PLANO1 = 8;
    private static final Integer ID_PRODUCT_LUIZA_VIDA_MENSAL_PLANO1 = 2;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_CASA_PROTEGIDA = 23;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_PESSOAL = 24;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_RESIDENCIAL = 22;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_PROTECAO_VIDA = 21;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_RESIDENCIAL_PAGTO_MENSAL = 19;
    private static final Integer ID_PRODUCT_SEGURO_LUIZA_VIDA_PAGTO_MENSAL = 20;
    private static final String PLAN_1 = "Plano 1";
    private static final String PLAN_2 = "Plano 2";

    private static Map<Integer, String> mapPlans = new HashMap<>();

    private static ProductPlan uniqueInstance;

    /**
     * Constructor ProductPlan
     */
    private ProductPlan() {
        setMapPlans();
    }

    /**
     * Retorna instancia 
     * do ProductPlan (singleton)
     * @return
     */
    public static synchronized ProductPlan getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProductPlan();
        }

        return uniqueInstance;
    }

    /**
     * Retorna Plano 
     * por produto
     * @param product
     * @return
     */
    public String getPlanByProduct(final ProductDTO product) {
        String plan = "";
        if (mapPlans.get(product.getID()) != null) {
            plan = mapPlans.get(product.getID());
        }
        return plan;
    }

    /**
     * Seta todos os planos
     * em um map
     * Geralmente, produtos tipo residencial
     * possuem plano 1 e os tipos vida
     * pertencem aos plano 2
     */
    private void setMapPlans() {
        mapPlans.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MAIS_PROTECAO, PLAN_1);
        mapPlans.put(ID_PRODUCT_LUIZA_RESIDENCIAL_MENSAL_PLANO1, PLAN_1);
        mapPlans.put(ID_PRODUCT_LUIZA_VIDA_MENSAL_PLANO1, PLAN_2);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_CASA_PROTEGIDA, PLAN_1);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_PESSOAL, PLAN_2);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_RESIDENCIAL, PLAN_1);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_PROTECAO_VIDA, PLAN_2);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_RESIDENCIAL_PAGTO_MENSAL, PLAN_1);
        mapPlans.put(ID_PRODUCT_SEGURO_LUIZA_VIDA_PAGTO_MENSAL, PLAN_2);
    }

}
