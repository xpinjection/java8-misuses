package org.javaday.java8.misused.stream;

import org.javaday.java8.misused.Annotations.Good;
import org.javaday.java8.misused.Annotations.Ugly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

//Taken from real code
public class ImparativeCodeMix {

    private Map<String, List<String>> productSkusMap = new HashMap<>();

    @Ugly
    class MixWithImparativeCode{

        public List<String> getAllSkusForProducts(List<String> products){
            return products.stream()
                    .map(product -> {
                        List<String> skus = productSkusMap.get(product);
                        if (skus == null) {
                            return new ArrayList<String>();
                        } else {
                            return skus;
                        }
                    })
                    .flatMap(l -> l.stream())
                    .collect(toList());
        }

    }

    @Good
    class StreamOperationsChain {

        public List<String> getAllSkusForProducts(List<String> products){
            return products.stream()
                    .map(productSkusMap::get)
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .collect(toList());
        }
    }

}
