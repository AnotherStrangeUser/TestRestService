package name.dak.job.sber.samplsvc.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ConvertController {
    private static final String DEFAULT_VALUE_PROP = "DefaultValue";

    @PostMapping(
            value = {"/", "/{defaults}"},
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<Map<String, Object>> convertDataWithDefaults(@PathVariable(required = false) List<Integer> defaults,
                                                      @RequestBody List<Map<String, Object>> properties) {
        if (defaults != null) {
            int minCount = Math.min(defaults.size(), properties.size());
            for (int i = 0; i < minCount; i++) {
                properties.get(i).put(DEFAULT_VALUE_PROP, defaults.get(i));
            }
            for (int i = minCount; i < properties.size(); i++) {
                properties.get(i).put(DEFAULT_VALUE_PROP, null);
            }
        }
        return  properties;
    }
}
