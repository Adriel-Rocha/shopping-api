package com.adriel.shopping_api.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.adriel.shopping_api.dto.ShopReportDTO;
import com.adriel.shopping_api.model.Shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ShopRepositoryImpl implements ReportRepository {
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Shop> getShopByFilters(Date dateInit, Date dateEnd, BigDecimal valorMinimo) {
        StringBuilder sb = new StringBuilder();
        sb.append("select s ");
        sb.append("from shop s ");
        sb.append("where s.date >= :dateInit ");

        if (dateEnd != null) {
            sb.append("and s.date <= :dateEnd ");
        }

        if (valorMinimo != null) {
            sb.append("and s.total >= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("dateInit", dateInit);

        if (dateEnd != null) {
            query.setParameter("dateEnd", dateEnd);
        }

        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();
    }

    public ShopReportDTO getReportByDate(Date dateInit, Date dateEnd) {

        StringBuilder sb = new StringBuilder();
        
        sb.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :dateInit ");
        sb.append("and sp.date <= :dateEnd ");

        Query query = entityManager.createNativeQuery(sb.toString());
        query.setParameter("dateInit", dateInit);
        query.setParameter("dateEnd", dateEnd);

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((Long) result[0]).intValue());
        shopReportDTO.setTotal((BigDecimal) result[1]);
        shopReportDTO.setMean((BigDecimal) result[2]);

        return shopReportDTO;
        
    }
}
