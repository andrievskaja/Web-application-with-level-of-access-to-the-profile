/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.specification;

import com.andrievskaja.business.model.Role;
import com.andrievskaja.business.model.User;
import com.andrievskaja.business.model.User_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Людмила
 */
public class UserSpecifications {
     public static Specification<User> user_role(final Role role) {
        if (role == null) {
            return null;
        }
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(User_.role), role);
            }
        };
    }  
}
