package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffGenerator {
    public static Map<String, Map<String, Object>> generateDiff(Map<String, Object> data1, Map<String, Object> data2) {
        var keys = new TreeSet<String>(data1.keySet());
        keys.addAll(data2.keySet());

        var diff = new LinkedHashMap<String, Map<String, Object>>();

        for (var key : keys) {
            var statusMap = new LinkedHashMap<String, Object>();
            if (!data1.containsKey(key)) {
                statusMap.put("status", "added");
                statusMap.put("value", data2.get(key));
            } else if (!data2.containsKey(key)) {
                statusMap.put("status", "deleted");
                statusMap.put("value", data1.get(key));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                statusMap.put("status", "unchanged");
                statusMap.put("value", data1.get(key));
            } else {
                statusMap.put("status", "changed");
                statusMap.put("oldValue", data1.get(key));
                statusMap.put("newValue", data2.get(key));
            }

            diff.put(key, statusMap);
        }

        return diff;
    }
}
