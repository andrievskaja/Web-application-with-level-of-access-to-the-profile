/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.specification;

import com.andrievskaja.business.model.App;
import com.andrievskaja.business.model.App_;
import com.andrievskaja.business.model.Role;
import static com.andrievskaja.business.model.User_.role;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Людмила
 */
public class AppSpecifications {
        public static Specification<App> app_userId(final Long userId) {
        if (userId == null) {
            return null;
        }
        return new Specification<App>() {
            @Override
            public Predicate toPredicate(Root<App> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(App_.user), userId);
            }
        };
    }  
}
