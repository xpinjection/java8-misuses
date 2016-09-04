package org.javaday.java8.misused.stream;

import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

//Taken from real code
public class NestedForEach {

    @Ugly
    class NestedForEachWithExternalCollection {

        public Set<String> retrievePromoRuleNames(List<BusinessTransaction> transactions) {
            Set<String> ruleNamesWithPromo = new HashSet<>();
            transactions.forEach(trnx -> trnx.getRules().stream()
                    .filter(BusinessRule::isPromotion)
                    .forEach(rule -> ruleNamesWithPromo.add(rule.getRuleName())));
            return ruleNamesWithPromo;
        }
    }

    @Good
    class StreamOperationsChain {

        public Set<String> retrievePromoRuleNames(List<BusinessTransaction> transactions) {
            return transactions.stream()
                    .flatMap(t -> t.getRules().stream())
                    .filter(BusinessRule::isPromotion)
                    .map(BusinessRule::getRuleName)
                    .collect(toSet());
        }
    }

    class BusinessTransaction {
        List<BusinessRule> getRules() {
            return new ArrayList<>();
        }
    }

    class BusinessRule {
        String getRuleName() {
            return "";
        }

        boolean isPromotion() {
            return false;
        }
    }

}
